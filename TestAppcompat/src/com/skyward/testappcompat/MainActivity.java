package com.skyward.testappcompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar mAppcompatProgressBar;
	private ProgressBar mSystemProgressBar;
	private SwipeRefreshLayout srl;

	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		srl = (SwipeRefreshLayout) findViewById(R.id.srl);
		// 设置刷新进度条布局的大小
		srl.setSize(SwipeRefreshLayout.LARGE);
		// 设置刷新进入条的切换颜色集合
		srl.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
		// 设置进度条的背景颜色
		srl.setProgressBackgroundColorSchemeColor(Color.YELLOW);
		// 设置下拉多少距离开始刷新
		srl.setDistanceToTriggerSync(300);
		srl.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				Toast.makeText(MainActivity.this, "数据加载完毕，停止刷新", Toast.LENGTH_SHORT).show();
				// 停止刷新
				// srl.setRefreshing(false);
			}
		});

		mAppcompatProgressBar = (ProgressBar) findViewById(R.id.appcompatProgressBar);
		mAppcompatProgressBar.setMax(100);
		mAppcompatProgressBar.setProgress(50);

		mSystemProgressBar = (ProgressBar) findViewById(R.id.systemProgressBar);
		mSystemProgressBar.setMax(100);
		mSystemProgressBar.setProgress(50);

		String[] items = { "条目0", "条目1", "条目2", "条目3", "条目4", "条目5", "条目6", };
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	}

	public void showProgressBar(View v) {

	}

	public void showSystemDialog(View v) {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setTitle("系统对话框");
		builder.setMessage("给我一个女朋友");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void showAppcompatDialog(View v) {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("Appcompat对话框");
		builder.setMessage("给我一个女朋友");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void showPopupMenu(View v) {
		PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
		popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				Toast.makeText(MainActivity.this, arg0.getTitle(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		popupMenu.show();
	}

	public void showListPopupWindow(View v) {
		ListPopupWindow listPopupWindow = new ListPopupWindow(MainActivity.this);
		listPopupWindow.setAdapter(adapter);
		// 设置锚点，弹出的位置是相对于v的位置
		listPopupWindow.setAnchorView(v);
		listPopupWindow.setWidth(200);
		listPopupWindow.setHeight(500);
		listPopupWindow.show();
	}

}
