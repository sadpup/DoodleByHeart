package com.doodle.byheart.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.util.NetWorkUtils;
import com.doodle.byheart.util.T;
import com.doodle.byheart.view.CircleImageView;
import com.doodle.byheart.view.RoundTextView;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @see {tags}
 * @author ly-lihongliang
 */
public abstract class BaseActivity extends FragmentActivity{

	protected BaseApplication mApplication;
	protected NetWorkUtils mNetWorkUtils;
	//TODO ������Ҫ����һ��dialog
	protected RoundTextView t_centerTextView;//���ı�������
	protected Button t_rightBtn;//�Ҳ�button
	protected CircleImageView t_leftImageView;//���ͼ��logo
	protected RelativeLayout t_title_layout;
	
	protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

	/**
	 * ��Ļ�Ŀ�ȡ��߶ȡ��ܶ�
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;//http://blog.csdn.net/java2009cgh/article/details/8182817
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mApplication = (BaseApplication) getApplication();
		mNetWorkUtils = new NetWorkUtils(this);

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
		
		//����app״̬�Ĺ㲥
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		this.registerReceiver(baseListen, filter);
	}
	
	@Override
	protected void onDestroy() {
		clearAsyncTask();
		unregisterReceiver(baseListen);
		super.onDestroy();
	}
	

	/** ��ʼ����ͼ **/
	protected abstract void initViews();

	/** ��ʼ���¼� **/
	protected abstract void initEvents();
	

	protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
		mAsyncTasks.add(asyncTask.execute());
	}

	protected void clearAsyncTask() {
		Iterator<AsyncTask<Void, Void, Boolean>> iterator = mAsyncTasks
				.iterator();
		while (iterator.hasNext()) {
			AsyncTask<Void, Void, Boolean> asyncTask = iterator.next();
			if (asyncTask != null && !asyncTask.isCancelled()) {
				asyncTask.cancel(true);
			}
		}
		mAsyncTasks.clear();
	}
	
	/** ͨ��Class��ת���� **/
	protected void fStartActivity(Class<?> cls) {
		fStartActivity(cls, null);
	}

	/** ����Bundleͨ��Class��ת���� **/
	protected void fStartActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** ͨ��Action��ת���� **/
	protected void fStartActivity(String action) {
		fStartActivity(action, null);
	}

	/** ����Bundleͨ��Action��ת���� **/
	protected void fStartActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** ���б�������ݵĶԻ��� **/
	protected AlertDialog fShowAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** ���б��⡢���ݡ�������ť�ĶԻ��� **/
	protected AlertDialog fShowAlertDialog(String title, String message,
			String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** ���б��⡢���ݡ�ͼ�ꡢ������ť�ĶԻ��� **/
	protected AlertDialog fShowAlertDialog(String title, String message,
			int icon, String positiveText,
			DialogInterface.OnClickListener onPositiveClickListener,
			String negativeText,
			DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** Ĭ���˳� **/
	protected void fDefaultFinish() {
		super.finish();
	}
	

	private BroadcastReceiver baseListen = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			NetWorkUtils mNetWorkUtil = new NetWorkUtils(context);
			String action = intent.getAction();
			if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
//				T.show(context, "���������Ѹı䣬��ǰ����״̬�ǣ�"+mNetWorkUtil.getConnectState());
			}
		}
		
	};
	
}
