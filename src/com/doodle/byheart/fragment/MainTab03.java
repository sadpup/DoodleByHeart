package com.doodle.byheart.fragment;

import java.util.Calendar;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.base.BaseApplication;
import com.doodle.byheart.base.BaseFragment;
import com.doodle.byheart.util.L;
import com.doodle.byheart.util.NetWorkUtils;
import com.doodle.byheart.util.T;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Address;
import android.location.Location;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainTab03 extends BaseFragment {

	private TextView tv_test;

	public MainTab03() {
		super();
	}
	
	public MainTab03(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.main_tab_03, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected void initViews() {
		tv_test  =  (TextView) findViewById(R.id.tv_test);
	}

	@Override
	protected void initEvents() {
	}

	@Override
	protected void init() {
		Location location = BaseApplication.instance.mLocUtils.location;
		List<Address> addlist = BaseApplication.instance.mLocUtils.regionList;
		Calendar time = BaseApplication.instance.mLocUtils.updateTime;
		boolean is = BaseApplication.instance.mLocUtils.locState;
		if(is){
			tv_test.setText("当前位置："+addlist.get(0).getAddressLine(0)
					+"\n[经："+location.getLatitude()+"纬："+location.getLongitude()
					+"]\n"+time.getTime());
		}else{
			tv_test.setText("获取当前位置失败."+is);
		}
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (hidden) {
            //相当于Fragment的onResume
			init();
        } else {
            //相当于Fragment的onPause
        }
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	};
	
}
