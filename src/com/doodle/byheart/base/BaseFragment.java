package com.doodle.byheart.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.doodle.byheart.util.L;
import com.doodle.byheart.util.NetWorkUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	protected BaseApplication mApplication;
	protected Activity mActivity;
	protected Context mContext;
	protected View mView;
	protected NetWorkUtils mNetWorkUtils;

	/**
	 * ��Ļ�Ŀ�ȡ��߶ȡ��ܶ�
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;

	protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();
	
	public BaseFragment() {
		super();
	}

	public BaseFragment(BaseApplication application, Activity activity,
			Context context) {
		mApplication = application;
		mActivity = activity;
		mContext = context;
		mNetWorkUtils = new NetWorkUtils(context);
		/**
		 * ��ȡ��Ļ��ȡ��߶ȡ��ܶ�
		 */
		DisplayMetrics metric = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		initViews();
		initEvents();
		init();
		return mView;
	}
	
	@Override
	public void onDestroy() {
		clearAsyncTask();
		super.onDestroy();
	}

	protected abstract void initViews();

	protected abstract void initEvents();

	protected abstract void init();

	public View findViewById(int id) {
		return mView.findViewById(id);
	}

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
		Intent intent = new Intent();
		intent.setClass(mContext, cls);
		startActivity(intent);
	}
	
	
}
