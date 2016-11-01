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
	//TODO 这里需要补充一个dialog
	protected RoundTextView t_centerTextView;//中心标题文字
	protected Button t_rightBtn;//右侧button
	protected CircleImageView t_leftImageView;//左侧图像logo
	protected RelativeLayout t_title_layout;
	
	protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

	/**
	 * 屏幕的宽度、高度、密度
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
		
		//监听app状态的广播
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
	

	/** 初始化视图 **/
	protected abstract void initViews();

	/** 初始化事件 **/
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
	
	/** 通过Class跳转界面 **/
	protected void fStartActivity(Class<?> cls) {
		fStartActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void fStartActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 通过Action跳转界面 **/
	protected void fStartActivity(String action) {
		fStartActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void fStartActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 含有标题和内容的对话框 **/
	protected AlertDialog fShowAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
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

	/** 含有标题、内容、图标、两个按钮的对话框 **/
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

	/** 默认退出 **/
	protected void fDefaultFinish() {
		super.finish();
	}
	

	private BroadcastReceiver baseListen = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			NetWorkUtils mNetWorkUtil = new NetWorkUtils(context);
			String action = intent.getAction();
			if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
//				T.show(context, "网络连接已改变，当前网络状态是："+mNetWorkUtil.getConnectState());
			}
		}
		
	};
	
}
