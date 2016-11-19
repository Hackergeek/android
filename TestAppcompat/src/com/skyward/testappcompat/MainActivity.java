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
		// ����ˢ�½��������ֵĴ�С
		srl.setSize(SwipeRefreshLayout.LARGE);
		// ����ˢ�½��������л���ɫ����
		srl.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
		// ���ý������ı�����ɫ
		srl.setProgressBackgroundColorSchemeColor(Color.YELLOW);
		// �����������پ��뿪ʼˢ��
		srl.setDistanceToTriggerSync(300);
		srl.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				Toast.makeText(MainActivity.this, "���ݼ�����ϣ�ֹͣˢ��", Toast.LENGTH_SHORT).show();
				// ֹͣˢ��
				// srl.setRefreshing(false);
			}
		});

		mAppcompatProgressBar = (ProgressBar) findViewById(R.id.appcompatProgressBar);
		mAppcompatProgressBar.setMax(100);
		mAppcompatProgressBar.setProgress(50);

		mSystemProgressBar = (ProgressBar) findViewById(R.id.systemProgressBar);
		mSystemProgressBar.setMax(100);
		mSystemProgressBar.setProgress(50);

		String[] items = { "��Ŀ0", "��Ŀ1", "��Ŀ2", "��Ŀ3", "��Ŀ4", "��Ŀ5", "��Ŀ6", };
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
	}

	public void showProgressBar(View v) {

	}

	public void showSystemDialog(View v) {
		AlertDialog.Builder builder = new Builder(MainActivity.this);
		builder.setTitle("ϵͳ�Ի���");
		builder.setMessage("����һ��Ů����");
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.show();
	}

	public void showAppcompatDialog(View v) {
		android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("Appcompat�Ի���");
		builder.setMessage("����һ��Ů����");
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

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
		// ����ê�㣬������λ���������v��λ��
		listPopupWindow.setAnchorView(v);
		listPopupWindow.setWidth(200);
		listPopupWindow.setHeight(500);
		listPopupWindow.show();
	}

}
