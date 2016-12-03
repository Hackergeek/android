package com.skyward.gif_engine.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 加载远程服务器、本地、assets目录等地方的Gif图片，以输入流的方式传递给GifDrawer。
 * 
 * @author skyward
 *
 */
public class GifDecoder {
	public static GifDecoder instance = new GifDecoder();
	public static Context context;

	private GifDecoder() {
	}

	public static GifDecoder with(Context c) {
		context = c;
		return instance;
	}

	public GifDrawer load(Uri uri) {
		InputStream is = null;
		try {
			is = context.getContentResolver().openInputStream(uri);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return load(is);
	}

	public GifDrawer load(InputStream is) {
		GifDrawer drawer = new GifDrawer();
		drawer.setIs(is);
		return drawer;
	}

	public GifDrawer load(File f) {
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return load(is);
	}

	public GifDrawer load(String url) {
		// 下载Gif图片
		InputStream is = null;
		// 对Gif文件名进行加密
		String name = MD5Utils.decode(url);
		String path = context.getExternalCacheDir() + File.separator + name;
		File file = new File(path);
		if (file.exists()) {
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			GifDrawer drawer = new GifDrawer();
			LoadGifTask gifTask = new LoadGifTask(drawer);
			gifTask.execute(url);
			return drawer;
		}
		return load(is);
	}

	class LoadGifTask extends AsyncTask<String, Void, String> {
		private String key;
		private GifDrawer gifDrawer;

		public LoadGifTask(GifDrawer gifDrawer) {
			this.gifDrawer = gifDrawer;
		}

		@Override
		protected String doInBackground(String... params) {
			this.key = params[0];
			try {
				InputStream is = HttpLoader.getInputSteamFromUrl(key);
				String name = MD5Utils.decode(key);
				String path = context.getExternalCacheDir() + File.separator + name;
				File file = new File(path);
				FileOutputStream fos = new FileOutputStream(file);
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return key;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				instance.load(key).into(gifDrawer.getIv());
			}
		}
	}

}
