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
public class MainTab01 extends BaseFragment {

	private List<Fragment> mTabContents = new ArrayList<Fragment>();

	private FragmentPagerAdapter mAdapter;
	private ViewPager mViewPager;
//	private List<String> mDatas = Arrays.asList("狂野", "泡撸字", "老学校", "滚刷",
//			"抽象", "砖块", "孩子气", "风景", "暴力", "现实主义", "布告栏", "便签", "卡通");
	private List<String> mDatas = Arrays.asList("推荐", "经典", "收藏");

	private ViewPagerIndicator mIndicator;
	
	public MainTab01() {
		super();
	}
	
	public MainTab01(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.main_tab_01,null);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	protected void initViews() {
		mViewPager = (ViewPager) findViewById(R.id.id_vp);
		mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
	}

	@Override
	protected void initEvents() {
		
	}

	@Override
	protected void init() {
		MainTab011 fragment011 = new MainTab011(mApplication,mActivity,mContext); 
		mTabContents.add(fragment011);
		MainTab012 fragment012 = new MainTab012(mApplication,mActivity,mContext); 
		mTabContents.add(fragment012);
		MainTab013 fragment013 = new MainTab013(mApplication,mActivity,mContext); 
		mTabContents.add(fragment013);
//		for (Fragment mDatas: mFragments){
//		VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
//		mTabContents.add(fragment);
//		}

		mAdapter = new FragmentPagerAdapter(getFragmentManager()) {
			@Override
			public int getCount() {
				return mTabContents.size();
			}

			@Override
			public Fragment getItem(int position) {
				return mTabContents.get(position);
			}
		};
		//设置Tab上的标题
		mIndicator.setTabItemTitles(mDatas);
		mViewPager.setAdapter(mAdapter);
		//设置关联的ViewPager
		mIndicator.setViewPager(mViewPager,0);
	}
}
