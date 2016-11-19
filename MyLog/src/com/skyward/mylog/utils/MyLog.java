package com.skyward.mylog.utils;

import android.util.Log;

public class MyLog {
	public static final boolean isDebug = false;
	
	public static int i(String tag, String msg) {
		if (isDebug) {
			return Log.i(tag, msg);
		} else {
			return 0;
		}
	}

	public static int d(String tag, String msg) {
		if (isDebug) {
			return Log.d(tag, msg);
		} else {
			return 0;
		}
	}

	public static int e(String tag, String msg) {
		if (isDebug) {
			return Log.e(tag, msg);
		} else {
			return 0;
		}
	}

	public static int w(String tag, String msg) {
		if (isDebug) {
			return Log.w(tag, msg);
		} else {
			return 0;
		}
	}

	public static int v(String tag, String msg) {
		if (isDebug) {
			return Log.v(tag, msg);
		} else {
			return 0;
		}
	}
}
