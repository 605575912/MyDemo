//package com.lzxmy.demo.upload;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.commons.httpclient.Cookie;
//import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.HttpStatus;
//import org.apache.commons.httpclient.cookie.CookiePolicy;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.methods.multipart.StringPart;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import android.content.Context;
//
///**
// * API客户端接口：用于访问网络数据
// *
// * @author liux (http://my.oschina.net/liux)
// * @version 1.0
// * @created 2012-3-21
// */
//public class ApiClient {
//
//	public static final String UTF_8 = "UTF-8";
//	private final static int TIMEOUT_CONNECTION = 20000;
//	private final static int TIMEOUT_SOCKET = 20000;
//	private final static int RETRY_TIME = 3;
//
//	private static HttpClient getHttpClient() {
//		HttpClient httpClient = new HttpClient();
//		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
//		httpClient.getParams().setCookiePolicy(
//				CookiePolicy.BROWSER_COMPATIBILITY);
//		// 设置 默认的超时重试处理策略
//		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//				new DefaultHttpMethodRetryHandler());
//		// 设置 连接超时时间
//		httpClient.getHttpConnectionManager().getParams()
//				.setConnectionTimeout(TIMEOUT_CONNECTION);
//		// 设置 读数据超时时间
//		httpClient.getHttpConnectionManager().getParams()
//				.setSoTimeout(TIMEOUT_SOCKET);
//		// 设置 字符集
//		httpClient.getParams().setContentCharset(UTF_8);
//		return httpClient;
//	}
//
//	private static PostMethod getHttpPost(String url) {
//		PostMethod httpPost = new PostMethod(url);
//		// 设置 请求超时时间
//		httpPost.getParams().setSoTimeout(TIMEOUT_SOCKET);
//		httpPost.setRequestHeader("Host", "http://192.168.0.1");
//		httpPost.setRequestHeader("Connection", "Keep-Alive");
//		return httpPost;
//	}
//
//	public static String _MakeURL(String p_url, Map<String, Object> params) {
//		StringBuilder url = new StringBuilder(p_url);
//		if (url.indexOf("?") < 0)
//			url.append('?');
//
//		for (String name : params.keySet()) {
//			url.append('&');
//			url.append(name);
//			url.append('=');
//			url.append(String.valueOf(params.get(name)));
//			// 不做URLEncoder处理
//			// url.append(URLEncoder.encode(String.valueOf(params.get(name)),
//			// UTF_8));
//		}
//		return url.toString().replace("?&", "?");
//	}
//
//	// /**
//	// * 网络get请求URL
//	// * @param url
//	// * @throws AppException
//	// */
//	// public static InputStream http_gettest(BMapApiApp appContext, String url)
//	// throws AppException {
//	// InputStream isInputStream = null;
//	// try {
//	// isInputStream = appContext.getAssets().open("getLastEarthquakeXml.xml");
//	// } catch (IOException e) {
//	// // TODO Auto-generated catch block
//	// e.printStackTrace();
//	// }
//	//
//	// return isInputStream;
//	// }
//
//	public interface ProgressListener {
//		public void transferred(long transferedBytes);
//	}
//
//	/**
//	 * 公用post方法
//	 *
//	 * @param url
//	 * @param params
//	 * @param files
//	 * @throws AppException
//	 */
//	public static String post(Context appContext, String url,
//			Map<String, Object> params, Map<String, File> files,
//			ProgressListener listener) {
//		HttpClient httpClient = null;
//		PostMethod httpPost = null;
//
//		// post表单参数处理
//		int length = (params == null ? 0 : params.size())
//				+ (files == null ? 0 : files.size());
//		Part[] parts = new Part[length];
//		int i = 0;
//		if (params != null)
//			for (String name : params.keySet()) {
//				parts[i++] = new StringPart(name, String.valueOf(params
//						.get(name)), UTF_8);
//			}
//		if (files != null)
//			for (String file : files.keySet()) {
//				try {
//					FilePart filePart = new FilePart(file, files.get(file));
//					filePart.setContentType("image/png");
//					parts[i++] = filePart;
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		String responseBody = "";
//		int time = 0;
//		do {
//			try {
//				httpClient = getHttpClient();
//				httpPost = getHttpPost(url);
//				ProgressEntity progressEntity = new ProgressEntity(parts,
//						httpPost.getParams(), listener);
//				httpPost.setRequestEntity(progressEntity);
//				int statusCode = httpClient.executeMethod(httpPost);
//				if (statusCode != HttpStatus.SC_OK) {
//
//				} else if (statusCode == HttpStatus.SC_OK) {
//				}
//				responseBody = httpPost.getResponseBodyAsString().trim();
//				break;
//			} catch (HttpException e) {
//				time++;
//				if (time < RETRY_TIME) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e1) {
//					}
//					continue;
//				}
//				// 发生致命的异常，可能是协议不对或者返回的内容有问题
//				e.printStackTrace();
//
//			} catch (IOException e) {
//				time++;
//				if (time < RETRY_TIME) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e1) {
//					}
//					continue;
//				}
//				// 发生网络异常
//				e.printStackTrace();
//
//			} finally {
//				// 释放连接
//				httpPost.releaseConnection();
//				httpClient = null;
//			}
//		} while (time < RETRY_TIME);
//
//		responseBody = responseBody.replace('', '?');
//		return responseBody;
//	}
//
//	/**
//	 * 公用post方法
//	 *
//	 * @param url
//	 * @param params
//	 * @param files
//	 * @throws AppException
//	 */
//	public static String _post(Context appContext, String url,
//			Map<String, Object> params, Map<String, File> files) {
//		HttpClient httpClient = null;
//		PostMethod httpPost = null;
//
//		// post表单参数处理
//		int length = (params == null ? 0 : params.size())
//				+ (files == null ? 0 : files.size());
//		Part[] parts = new Part[length];
//		int i = 0;
//		if (params != null)
//			for (String name : params.keySet()) {
//				parts[i++] = new StringPart(name, String.valueOf(params
//						.get(name)), UTF_8);
//			}
//		if (files != null)
//			for (String file : files.keySet()) {
//				try {
//					parts[i++] = new FilePart(file, files.get(file));
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
//			}
//		String responseBody = "";
//		int time = 0;
////		do {
////			try {
//				httpClient = getHttpClient();
//				httpPost = getHttpPost(url);
//				httpPost.setRequestEntity(new MultipartRequestEntity(parts,
//						httpPost.getParams()));
////				int statusCode = httpClient.executeMethod(httpPost);
////				if (statusCode != HttpStatus.SC_OK) {
////				} else if (statusCode == HttpStatus.SC_OK) {
////					responseBody = httpPost.getResponseBodyAsString().trim();
////				}
////				break;
////			} catch (HttpException e) {
////				time++;
////				if (time < RETRY_TIME) {
////					try {
////						Thread.sleep(1000);
////					} catch (InterruptedException e1) {
////					}
////					continue;
////				}
////				// 发生致命的异常，可能是协议不对或者返回的内容有问题
////				e.printStackTrace();
////			} catch (IOException e) {
////				time++;
////				if (time < RETRY_TIME) {
////					try {
////						Thread.sleep(1000);
////					} catch (InterruptedException e1) {
////					}
////					continue;
////				}
////				// 发生网络异常
////				e.printStackTrace();
////			} finally {
////				// 释放连接
////				httpPost.releaseConnection();
////				httpClient = null;
////			}
////		} while (time < RETRY_TIME);
//
//		responseBody = responseBody.replace('', '?');
//		return responseBody;
//	}
//
//	/**
//	 * 发宏观
//	 *
//	 * @param Tweet
//	 *            -uid & msg & image
//	 * @return
//	 * @throws AppException
//	 */
//	public static String postFile(Context appContext, File file,
//			Map<String, Object> params, String URL, ProgressListener listener) {
//		Map<String, File> files = new HashMap<String, File>();
//		files.put("file", file);
//		try {
//			return post(appContext, URL, params, files, listener);
//		} catch (Exception e) {
//		}
//		return null;
//	}
//
//}
