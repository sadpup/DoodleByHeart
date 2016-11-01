package com.doodle.byheart.fragment;


import com.doodle.byheart.R;
import com.doodle.byheart.base.BaseApplication;
import com.doodle.byheart.base.BaseFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MainTab04 extends BaseFragment
{

	public MainTab04() {
		super();
	}
	
	public MainTab04(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		mView = inflater.inflate(R.layout.main_tab_04, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	protected void initViews() {
		
	}

	@Override
	protected void initEvents() {
		
	}

	@Override
	protected void init() {
		
	}

}
