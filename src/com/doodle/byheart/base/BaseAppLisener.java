package com.doodle.byheart.base;

import com.doodle.byheart.constant.P;
import com.doodle.byheart.util.L;
import com.doodle.byheart.util.NetWorkUtils;
import com.doodle.byheart.util.T;
import com.doodle.byheart.util.SysUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * @see app����״̬����(���簡��ǰ��̨���а�����������ȴ���)
 * @author ly-lihongliang
 */
public class BaseAppLisener extends BroadcastReceiver {

	private NetWorkUtils mNetWorkUtil;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		mNetWorkUtil = new NetWorkUtils(context);
		String action = intent.getAction();
		if(action.equals(P.GLOBAL_SERVICE)){
			if (SysUtil.isBackground(context)) {
				BaseApplication.instance.isAppInBackground = true;
			} else {
				BaseApplication.instance.isAppInBackground = false;
			}
		}else if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
			L.e( "����״̬�Ѿ��ı�");
			mNetWorkUtil = new NetWorkUtils(context);
			BaseApplication.instance.netWorkState = mNetWorkUtil.getConnectState();
		}
	}

}
