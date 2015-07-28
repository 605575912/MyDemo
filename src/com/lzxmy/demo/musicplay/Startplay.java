package com.lzxmy.demo.musicplay;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Environment;

class Startplay implements Runnable {
	Context context;
	private MediaPlayer mediaPlayer;

	public Startplay(Context context) {
		this.context = context;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			play(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��������
	 * 
	 * @throws IOException
	 */
	protected void play(Context context) throws IOException {
		String path = "";
		// 判断是否挂载了SD卡
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = context.getExternalCacheDir();
			if (file != null) {
				path = file.getPath();
			} else {
				path = Environment.getExternalStorageDirectory()
						.getAbsolutePath();// 存放照片的文件夹
				path = path + "/APP/image";
			}
		} else {
			path = context.getCacheDir().getPath();
		}
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
	
			AssetManager manager = context.getAssets();
			String[] listfile = manager.list("mp3");
			String fileString = "";
			for (int i = 0; i < listfile.length; i++) {
				fileString = listfile[i];
				break;
			}
			path = path + "/" + fileString;
			File file = new File(path);
			if (!file.exists()) {
				InputStream inputStream = manager.open("mp3/"+fileString);
				FileOutputStream outStream = new FileOutputStream(path);
				byte buffer[] = new byte[4 * 1024];
				while ((inputStream.read(buffer)) != -1) {
					outStream.write(buffer);
				}
				outStream.flush();
				outStream.close();
				inputStream.close();
			}
		}
		File file = new File(path);
		if (file.exists() && file.length() > 0) {
			try {
				mediaPlayer = new MediaPlayer();
				// ����ָ������ý���ַ
				mediaPlayer.setDataSource(path);
				// ������Ƶ��������
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

				// ͨ���첽�ķ�ʽװ��ý����Դ
				mediaPlayer.prepareAsync();
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						// װ����� ��ʼ������ý��
						mediaPlayer.start();
						// �����ظ����ţ��Ѳ��Ű�ť����Ϊ������
						// btn_play.setEnabled(false);
					}
				});
				// ����ѭ������
				mediaPlayer.setLooping(true);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// �ڲ�����ϱ��ص�
						// btn_play.setEnabled(true);
					}
				});

				mediaPlayer.setOnErrorListener(new OnErrorListener() {

					@Override
					public boolean onError(MediaPlayer mp, int what, int extra) {
						// �����������²���
						// replay();
						return false;
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Toast.makeText(context, "�ļ�������", 0).show();
		}

	}
}
