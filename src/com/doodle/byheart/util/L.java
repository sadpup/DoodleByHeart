package com.doodle.byheart.util;

import android.util.Log;

/**
 * @see ��־������
 * @author ly-lihongliang
 */
public class L {

	private L() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	private static boolean isDebug = true;// �Ƿ���Ҫ��ӡbug��������application��onCreate���������ʼ��
	public final static String TAG = "[ͿѻС����]";

	// �����ĸ���Ĭ��tag�ĺ���
	public static void i(String msg) {
		if (isDebug)
			Log.i(TAG, msg);
	}

	public static void d(String msg) {
		if (isDebug)
			Log.d(TAG, msg);
	}

	public static void e(String msg) {
		if (isDebug)
			Log.e(TAG, msg);
	}

	public static void v(String msg) {
		if (isDebug)
			Log.v(TAG, msg);
	}

	// �����Ǵ����Զ���tag�ĺ���
	public static void i(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			Log.i(tag, msg);
	}
}
