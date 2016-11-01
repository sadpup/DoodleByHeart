package com.doodle.byheart.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.base.BaseApplication;
import com.doodle.byheart.base.BaseFragment;
import com.doodle.byheart.util.L;
import com.doodle.byheart.view.GifView;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MainTab011 extends BaseFragment {

	 private GifView gif1;
	 private boolean ismove = true;
	
	public MainTab011() {
		super();
	}
	
	public MainTab011(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.main_tab_011,null);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	protected void initViews() {
		gif1 = (GifView) findViewById(R.id.gif1);
	}

	@Override
	protected void initEvents() {
	     // …Ë÷√‘›Õ£  
        gif1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gif1.setPaused(ismove);
				ismove = !ismove;
			}
		});
	}

	@Override
	protected void init() {
		// …Ë÷√±≥æ∞gifÕº∆¨◊ ‘¥  
        gif1.setMovieResource(R.raw.gif1);
	}
}
