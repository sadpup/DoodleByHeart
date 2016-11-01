package com.doodle.byheart.base;

import com.doodle.byheart.constant.P;
import com.doodle.byheart.util.L;
import com.doodle.byheart.util.LocUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * @see ȫ��service
 * @author ly-lihongliang
 */
public class BaseService extends Service {

	final Handler handler = new Handler();
	/**
	 * ������ִ�е��߼����ڴ��߳���ִ��
	 */
	Runnable runnable = new Runnable(){ 
	@Override 
	public void run() {
		//������ȫ�ֹ㲥
		Intent i = new Intent(P.GLOBAL_SERVICE);
		sendBroadcast(i);
		//�������λ�û�ȡʧ�ܣ��賢�Դ��»�ȡ
		LocUtils locUtils = BaseApplication.instance.mLocUtils; 
		if(locUtils.locState == false)
			locUtils.execute();
		handler.postDelayed(this, P.GLOBAL_SERVICE_TIME);// 500����ʱʱ�� 
		} 
	}; 

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		L.e("onCreate");
		handler.postDelayed(runnable, 1);
	}

	@Override
	public void onDestroy() {
		L.e("onDestroy");
		handler.removeCallbacks(runnable);// �رն�ʱ������
	}

	@Override
	public void onStart(Intent intent, int startId) {
		L.e("onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return flags;
	}

}
