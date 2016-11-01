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
	 * λ����Ϣ��ȡ������
	 */
	public LocUtils mLocUtils;
	
	@Override
	public void onCreate() {
		super.onCreate();
		initAPP();
	}

	private void initAPP() {
		//��ȡȫ�ֵ�application����
		instance = this;
        //��ȡλ����Ϣ����baseService�ж��䴦��
        mLocUtils = new LocUtils(this);
		//����ȫ�ַ���
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
	 * APP�Ƿ��ں�̨
	 */
	public boolean isAppInBackground;
	/**
	 * ����״̬
	 */
	public NetWorkState netWorkState;
}
