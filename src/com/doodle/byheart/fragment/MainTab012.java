package com.doodle.byheart.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.base.BaseApplication;
import com.doodle.byheart.base.BaseFragment;
import com.doodle.byheart.util.L;
import com.doodle.byheart.view.RoundTextView;
import com.doodle.byheart.view.ViewPagerIndicator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MainTab012 extends BaseFragment {

	public MainTab012() {
		super();
	}
	
	public MainTab012(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.main_tab_012,null);
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
