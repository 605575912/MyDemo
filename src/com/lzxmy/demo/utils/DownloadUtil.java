package com.lzxmy.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Environment;

public class DownloadUtil {
	static final String GET = "GET";
	static final String POST = "POST";
	public static String IMAGE_PATH = "/ivgLBS/image";
	public static String IMAGE_HEAD = "/ivgLBS/head";
	public static String CONFIG_PATH = "/ivgLBS/config";
	public static String ZIP_PATH = "/ivgLBS/filezip";
	public static String VOICE_PATH = "/ivgLBS/voice";
	public static String sdcard = "";

	static {
		// 判断是否挂载了SD卡
		String storageState = Environment.getExternalStorageState();
		if (storageState.equals(Environment.MEDIA_MOUNTED)) {
			sdcard = Environment.getExternalStorageDirectory()
					.getAbsolutePath();// 存放照片的文件夹
		}
	}

	public static String setPath(String url) {
		if (url == null) {
			return "";
		}
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				| url.equals("")) {
			return "";
		}
		// http://192.168.1.222:8081/var/upload/user/1/avatar/1_b.jpg?t=1411018828
		//http://192.168.1.222:8081/var/upload/images/20141011/20141011153204_466_400x400.jpg
		String id = "";
		int T = url.lastIndexOf("t=");
		if (T >= 0) {
			id = url.substring(T + 2, url.length());
		}
		int start = url.lastIndexOf("/");
		int end = url.lastIndexOf(".");
		if (start > -1 && end > -1) {
			id = id + url.substring(start + 1, end);
		} else {
			int start2 = url.lastIndexOf("/");
			int end2 = url.lastIndexOf(".");
			if (end2 > 0) {
				id = id + url.substring(start2 + 1, end2);
			}

		}
		File saveDir = new File(sdcard + IMAGE_PATH);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// File ApkFile = new File(IMAGE_PATH + "/" + id + ".jpg");
		// if (ApkFile.exists()) {
		return sdcard + IMAGE_PATH + "/" + id + ".jpg";
		// }
		// return "";
	}

	/**
	 * 下载图片
	 */
	public static String downimage(String url) {
		String path = null;
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return null;
		}
		path = setPath(url);
		File ApkFile = new File(path);
		if (ApkFile.exists()) {
			return path;
		}
		boolean interceptFlag = false;
		URL myFileUrl = null;

		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			InputStream is = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(path);
			byte buf[] = new byte[1024];
			do {
				int numread = is.read(buf);
				if (numread <= 0) {
					interceptFlag = true;
					break;
				}
				fos.write(buf, 0, numread);
			} while (!interceptFlag);// 点击取消就停止下载.
			interceptFlag = false;
			fos.close();
			is.close();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 下载zip
	 */
	public static String downzip(String url) {
		String path = null;
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return null;
		}
		path = setzipPath(url);

		File ApkFile = new File(path);
		if (ApkFile.exists()) {
			ApkFile.delete();
		}
		boolean interceptFlag = false;
		URL myFileUrl = null;

		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			InputStream is = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(path);
			byte buf[] = new byte[1024];
			do {
				int numread = is.read(buf);
				if (numread <= 0) {
					interceptFlag = true;
					break;
				}
				fos.write(buf, 0, numread);
			} while (!interceptFlag);// 点击取消就停止下载.
			interceptFlag = false;
			fos.close();
			is.close();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 下载语音
	 */
	public static String downVoice(String url) {
		String path = null;
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return null;
		}
		path = setSpxPath(url);

		File ApkFile = new File(path);
		if (ApkFile.exists()) {
			return path;
		}
		boolean interceptFlag = false;
		URL myFileUrl = null;

		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			InputStream is = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(path);
			byte buf[] = new byte[1024];
			do {
				int numread = is.read(buf);
				if (numread <= 0) {
					interceptFlag = true;
					break;
				}
				fos.write(buf, 0, numread);
			} while (!interceptFlag);// 点击取消就停止下载.
			interceptFlag = false;
			fos.close();
			is.close();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String setzipPath(String url) {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				| url.equals("")) {
			return "";
		}
		String id = "ti.zip";
		int start = url.lastIndexOf("/");
		int end = url.length();
		id = url.substring(start + 1, end);
		File saveDir = new File(DownloadUtil.sdcard + ZIP_PATH);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// File ApkFile = new File(IMAGE_PATH + "/" + id + ".jpg");
		// if (ApkFile.exists()) {
		return DownloadUtil.sdcard + ZIP_PATH + "/" + id;
		// }
		// return "";
	}

	public static String setSpxPath(String url) {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				| url.equals("")) {
			return "";
		}
		String id = "ti.spx";
		int start = url.lastIndexOf("/");
		int end = url.length();
		id = url.substring(start + 1, end);
		File saveDir = new File(DownloadUtil.sdcard + VOICE_PATH);
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		// File ApkFile = new File(IMAGE_PATH + "/" + id + ".jpg");
		// if (ApkFile.exists()) {
		return DownloadUtil.sdcard + VOICE_PATH + "/" + id;
		// }
		// return "";
	}

}
