package com.iexin.team.netty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 长连接
 * 
 * @author lzx
 * 
 */
public class PushSocket {
	Socket socket;
	Writer writer;
	BufferedReader reader;
	int port = 9892;
	String dress = "";
	MessageListener messageListener;
	StatusListener statusListener;
	long id = 0; // 连接ID
	private BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10,
			true);
	int treadsize = 2;
	int timeout = 0;
	private ExecutorService listenerExecutor = null;

	public PushSocket(String dress, int port) {
		this.port = port;
		this.dress = dress;
		listenerExecutor = Executors.newFixedThreadPool(treadsize,
				new ThreadFactory() {

					@Override
					public Thread newThread(Runnable runnable) {
						Thread thread = new Thread(runnable, "Netty");
						thread.setDaemon(true);
						return thread;
					}
				});
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public void setStatusListener(StatusListener statusListener) {
		this.statusListener = statusListener;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * 连接服务器
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connect() {
		try {
			socket = new Socket(dress, port);
			// socket.setKeepAlive(true);
			socket.setSoTimeout(timeout);
			this.id = System.currentTimeMillis();
			if (socket != null & socket.isConnected()) {
				writer = new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream(), "UTF-8"));
				// 写完以后进行读操作
				reader = new BufferedReader(new InputStreamReader(
						socket.getInputStream(), "UTF-8"));
				if (statusListener != null) {
					statusListener.channelConnected(this);
				}
				if (listenerExecutor == null) {
					listenerExecutor = Executors.newFixedThreadPool(treadsize,
							new ThreadFactory() {

								@Override
								public Thread newThread(Runnable runnable) {
									Thread thread = new Thread(runnable,
											"Netty");
									thread.setDaemon(true);
									return thread;
								}
							});
				}
				listenerExecutor.submit(new Thread() {
					@Override
					public void run() {
						while (isConnect()) {
							String packet = nextPacket();
							if (packet != null) {
								write(packet);
							}
						}
					};
				});
				listenerExecutor.submit(new Thread() {
					@Override
					public void run() {
						read();
					};
				});
			} else {
				close();
				if (statusListener != null) {
					statusListener.channelClosed(this);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			close();
			if (statusListener != null) {
				statusListener.channelClosed(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			close();
			if (statusListener != null) {
				statusListener.channelClosed(this);
			}
		}

	}

	public void close() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket = null;
			writer = null;
			reader = null;
			synchronized (queue) {
				queue.notifyAll();
			}
			queue.clear();
		}
	}

	/**
	 * 读取消息队列
	 * 
	 * @return packet
	 */
	private String nextPacket() {
		String packet = null;
		while (isConnect() && (packet = queue.poll()) == null) {
			try {
				synchronized (queue) {
					queue.wait();
				}
			} catch (InterruptedException ie) {
				// Do nothing
				close();
				if (statusListener != null) {
					statusListener.channelClosed(this);
				}
			}
		}
		return packet;
	}

	/**
	 * 发送消息队列
	 * 
	 * @param packet
	 */
	public void push(String message) {
		queue.offer(message);
		synchronized (queue) {
			queue.notifyAll();
			if (queue.size() > 8) {
				close();
			}
		}
	}

	private void read() {
		try {
			if (reader != null) {
				char[] chars = new char[1024];
				int len;
				while ((len = reader.read(chars)) != -1) {
//					String message = new String(chars, 0, len);
					if (messageListener != null) {
						messageListener.messageReceived(new String(chars, 0, len));
					}
				}
				// String temp = null;
				// while (true) {
				// temp = reader.readLine();
				// // stringBuffer.append(temp);
				// if (messageListener != null) {
				// messageListener.messageReceived(temp);
				// }
				// }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			close();
			if (statusListener != null) {
				statusListener.channelClosed(this);
			}
		}
	}

	private void write(String message) {
		try {
			if (writer != null) {
				writer.write(message);
				writer.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			close();
			if (statusListener != null) {
				statusListener.channelClosed(this);
			}
		}
	}

	/**
	 * 判断是否连接成功
	 * 
	 * @return
	 */
	public boolean isConnect() {
		if (socket == null || !socket.isConnected()) {
			return false;
		}
		return true;
	}

	public long getId() {
		return id;
	}
}
