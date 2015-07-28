package com.gauss.recorder;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import com.gauss.speex.encode.SpeexEncoder;

public class SpeexRecorder implements Runnable {

	// private Logger log = LoggerFactory.getLogger(SpeexRecorder.class);
	private volatile boolean isRecording;
	private final Object mutex = new Object();
	private int sampleRate = 44100;
	// private static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
	int packagesize = 160;
	private String fileName = null;
	Context context;
//	VoiceValue handler;
	boolean issave = true;
//	int chatType = LBSAction.groupchat;// 0 组聊天 1 点对点

	public SpeexRecorder(String fileName, Context context,
			int chatType) {
		super();
		this.context = context;
//		this.handler = handler;
//		this.chatType = chatType;
		this.fileName = fileName;
		isRecording = true;

	}

	public AudioRecord findAudioRecord() {
		int[] mSampleRates = new int[] { 8000, 11025, 22050, 44100 };
		for (int sampleRate : mSampleRates) {
			for (short audioFormat : new short[] { AudioFormat.ENCODING_PCM_16BIT }) {
				for (short channelConfig : new short[] { AudioFormat.CHANNEL_IN_MONO }) {
					try {
						int bufferSize = AudioRecord.getMinBufferSize(
								sampleRate, channelConfig, audioFormat);

						if (bufferSize != AudioRecord.ERROR_BAD_VALUE) {
							// check if we can instantiate and have a success
							AudioRecord recorder = new AudioRecord(
									MediaRecorder.AudioSource.MIC, sampleRate,
									channelConfig, audioFormat, bufferSize);

							if (recorder.getState() == AudioRecord.STATE_INITIALIZED) {
								this.sampleRate = sampleRate;
								return recorder;
							}

						}
					} catch (Exception e) {
					}
				}
			}
		}
		return null;
	}

	@Override
	public void run() {
		AudioRecord recordInstance = findAudioRecord();
		if (recordInstance == null) {
//			ShowProgressBar.showTitleDialog(context, "无法记录音频!是否开启权限");
			return;
		}
		android.os.Process
				.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
		SpeexEncoder encoder = new SpeexEncoder(this.fileName, sampleRate);
		// Thread encodeThread = new Thread(encoder);
		encoder.setRecording(true);
		// encodeThread.start();
//		StartLBS.getInstance().submit(encoder);

		synchronized (mutex) {
			while (!this.isRecording) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					throw new IllegalStateException("Wait() interrupted!", e);
				}
			}
		}
		short[] tempBuffer = new short[packagesize];
		recordInstance.startRecording();
		long time = System.currentTimeMillis();
		int bufferRead = 0;
		long timelong = 0;
		do {

			// log.debug("start to recording.........");
			bufferRead = recordInstance.read(tempBuffer, 0, packagesize);
			// bufferRead = recordInstance.read(tempBuffer, 0, 320);
			if (bufferRead == AudioRecord.ERROR_INVALID_OPERATION) {
				throw new IllegalStateException(
						"read() returned AudioRecord.ERROR_INVALID_OPERATION");
			} else if (bufferRead == AudioRecord.ERROR_BAD_VALUE) {
				throw new IllegalStateException(
						"read() returned AudioRecord.ERROR_BAD_VALUE");
			} else if (bufferRead == AudioRecord.ERROR_INVALID_OPERATION) {
				throw new IllegalStateException(
						"read() returned AudioRecord.ERROR_INVALID_OPERATION");
			}

			// for (int i = 0; i < tempBuffer.length; i++) {
			// tempBuffer[i] = (byte) (tempBuffer[i] * 5);
			// }
			// log.debug("put data into encoder collector....");
			encoder.putData(tempBuffer, bufferRead);
			int v = 0;
			// 将 buffer 内容取出，进行平方和运算
			for (int i = 0; i < tempBuffer.length; i++) {
				// 这里没有做运算的优化，为了更加清晰的展示代码
				v += tempBuffer[i] * tempBuffer[i];
			}
			timelong = System.currentTimeMillis() - time;
			if (timelong < 30000) {
				int value = Math.abs((v / bufferRead) / 10000) >> 1;
//				handler.VoiceValueChanged(value);
			} else {
				break;
			}
			// Log.d("TAG", String.valueOf(v / (float) bufferRead));
		} while (isRecording);
//		handler.Recorded();
		try {
			recordInstance.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
		recordInstance.release();
		// tell encoder to stop.
		encoder.setRecording(false);
		if (issave & timelong > 1000) {
//			VoicePacket packet = new VoicePacket();
//			packet.setVoicepath(fileName);
//			packet.setChatType(chatType);
//			packet.setContent(String.valueOf((int) Math.ceil(timelong / 1000)));
//			packet.setPacketID(System.currentTimeMillis() + "");
//			packet.setReceivetime(System.currentTimeMillis());
//			packet.setShowtime(System.currentTimeMillis());
//			packet.setStatus(LBSAction.FILE_UPING);
//			packet.setMsg_type(LBSAction.CHAT_MSG_VOICE);
//			packet.setHeader_image(StartLBS.mySelf.getAvatarBig());
//			Intent intent = new Intent("xampp.android.intent.voice");
//			intent.putExtra("chat", packet);
//			context.sendBroadcast(intent);
		}
		bufferRead = 0;
		tempBuffer = null;
	}

	void setRecording(boolean isRecording) {
		synchronized (mutex) {
			this.isRecording = isRecording;
			if (this.isRecording) {
				mutex.notify();
			}
		}
	}

	public void stop(boolean issave) {
		this.issave = issave;
		setRecording(false);
	}

	public boolean isRecording() {
		synchronized (mutex) {
			return isRecording;
		}
	}
}
