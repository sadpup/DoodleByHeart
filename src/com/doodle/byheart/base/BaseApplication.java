package com.doodle.byheart.base;

import com.doodle.byheart.util.LocUtils;
import com.doodle.byheart.util.NetWorkUtils.NetWorkState;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * @see {tags}
 * @author ly-lihongliang
 */
public class BaseApplication extends Application{

	public static BaseApplication instance = null;
	
	private Intent startService ;
	
	/**
	 * 位置信息获取工具类
	 */
	public LocUtils mLocUtils;
	
	@Override
	public void onCreate() {
		super.onCreate();
		initAPP();
	}

	private void initAPP() {
		//获取全局的application对象
		instance = this;
        //获取位置信息，在baseService中对其处理
        mLocUtils = new LocUtils(this);
		//开启全局服务
        startService = new Intent(this, BaseService.class);
        startService(startService);
        
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.e("BaseApplication", "onLowMemory");
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.e("BaseApplication", "onTerminate");
	}

	
	/**
	 * APP是否处于后台
	 */
	public boolean isAppInBackground;
	/**
	 * 网络状态
	 */
	public NetWorkState netWorkState;
}
