package com.skyward.gif_engine.core;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.widget.ImageView;

/**
 * ����GifDecoder����������������GifͼƬ���Ʋ���ʾ��ImageView�ϡ�
 * 
 * @author skyward
 *
 */
public class GifDrawer {
	private ImageView iv;
	private InputStream is;
	private Movie movie;
	private Bitmap bitmap;
	private Canvas canvas;
	// Android�ֻ������֡������Ҫ�ﵽ60fps�����ÿһ֡�����ʱ�䲻�ܳ���16ms
	private final int frameDuration = 16;
	
	private Handler handler = new Handler();
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			draw();
			handler.postDelayed(runnable, frameDuration);
		}

		private void draw() {
			// ����
			canvas.save();
			movie.draw(canvas, 0, 0);
			canvas.restore();
			movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
			iv.setImageBitmap(bitmap);
		}
	};

	public GifDrawer() {
	}

	public void into(ImageView iv) {
		this.iv = iv;
		if(is == null) {
			return;
		} else if(iv == null) {
			throw new RuntimeException("ImageView can not be null!");
		} else {
			// ��ʼ��ImageView�л���С��Ӱ
			movie = Movie.decodeStream(is);
			if(movie == null) {
				throw new IllegalArgumentException("Illegal gif file!");
			}
			if(movie.width() <= 0 || movie.height() <= 0) {
				return;
			}
			// ��ҪBitmap����ȡCanvas
			bitmap = Bitmap.createBitmap(movie.width(), movie.height(), Bitmap.Config.ARGB_8888);
			canvas = new Canvas(bitmap);
			// ׼����canvas���Ƶ�С��Ӱ��ʾ��ImageView��
			handler.post(runnable);
		}
	}

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

}
