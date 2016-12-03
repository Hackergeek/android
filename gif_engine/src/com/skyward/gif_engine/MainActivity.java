package com.skyward.gif_engine;

import com.skyward.gif_engine.core.GifDecoder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private String url = "http://img4.imgtn.bdimg.com/it/u=3431965232,1668012162&fm=21&gp=0.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		// ����Զ�̷�������gifͼƬ
		GifDecoder.with(MainActivity.this).load(url).into(iv);
		
		// ����assetsĿ¼�µ�gifͼƬ
//		try {
//			GifDecoder.with(MainActivity.this).load(this.getResources().getAssets().open("a.gif")).into(iv);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
}
