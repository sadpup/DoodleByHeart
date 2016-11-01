package com.doodle.byheart.util;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;

/**
 * @see ϵͳ��ع���
 * @author ly-lihongliang
 */
public class SysUtil {
	/**
	 * @param context
	 * @return true�����̨��false����ǰ̨
	 * @see �жϳ�����ǰ̨���Ǻ�̨����
	 */
	public static boolean isBackground(Context context) {
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(context.getPackageName())) {
				/*
				 * BACKGROUND=400 EMPTY=500 FOREGROUND=100 GONE=1000
				 * PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */
				L.i(context.getPackageName(), "��appimportace =" + appProcess.importance
						+ ",context.getClass().getName()=" + context.getClass().getName());
				if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					L.i(context.getPackageName(), "���ں�̨" + appProcess.processName);
					return true;
				} else {
					L.i(context.getPackageName(), "����ǰ̨" + appProcess.processName);
					return false;
				}
			}
		}
		return false;
	}
}
