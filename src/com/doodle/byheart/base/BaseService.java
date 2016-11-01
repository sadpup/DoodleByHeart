package com.doodle.byheart.base;

import com.doodle.byheart.constant.P;
import com.doodle.byheart.util.L;
import com.doodle.byheart.util.LocUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/**
 * @see 全局service
 * @author ly-lihongliang
 */
public class BaseService extends Service {

	final Handler handler = new Handler();
	/**
	 * 周期性执行的逻辑放在此线程中执行
	 */
	Runnable runnable = new Runnable(){ 
	@Override 
	public void run() {
		//发送了全局广播
		Intent i = new Intent(P.GLOBAL_SERVICE);
		sendBroadcast(i);
		//如果地理位置获取失败，需尝试从新获取
		LocUtils locUtils = BaseApplication.instance.mLocUtils; 
		if(locUtils.locState == false)
			locUtils.execute();
		handler.postDelayed(this, P.GLOBAL_SERVICE_TIME);// 500是延时时长 
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
		handler.removeCallbacks(runnable);// 关闭定时器处理
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
