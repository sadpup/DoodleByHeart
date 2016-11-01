package com.doodle.byheart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.doodle.byheart.activity.PaintActivity;
import com.doodle.byheart.base.BaseActivity;
import com.doodle.byheart.view.CircleImageView;
import com.doodle.byheart.view.RoundTextView;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @see 滑动的主页,注意：布局文件也要修改为对应的
 * @author ly-lihongliang
 */
@SuppressLint("InflateParams")
public class MainActivity2 extends BaseActivity implements OnClickListener{

	private ViewPager mViewPager;// viewpager 控件
	private PagerAdapter mAdapter;// viewpager控件的适配器
	private List<View> mViews;// 用于添加的views集合对象
	private LayoutInflater mInflater;// 将布局文件直接转换为view对象的对象

	private int currentIndex;// 当前下标

	// 底部控件的按钮
	private LinearLayout ll_tab_bottom01;
	private LinearLayout ll_tab_bottom02;
	private LinearLayout ll_tab_bottom03;
	private LinearLayout ll_tab_bottom04;
	private ImageButton btn_tab_bottom01;
	private ImageButton btn_tab_bottom02;
	private ImageButton btn_tab_bottom03;
	private ImageButton btn_tab_bottom04;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		mInflater = LayoutInflater.from(this); // 加载布局管理器
		mViewPager = (ViewPager) findViewById(R.id.vp_main_menu);
		initViews();
		initEvents();
	}

	@Override
	protected void initViews() {
		t_centerTextView = (RoundTextView) findViewById(R.id.t_centerTextView);
		t_title_layout = (RelativeLayout) findViewById(R.id.t_title_layout);
		t_leftImageView = (CircleImageView) findViewById(R.id.t_leftImageView);
		t_rightBtn = (Button) findViewById(R.id.t_rightBtn);
		t_centerTextView.setText(R.string.main_title_name);
		heihei();

		ll_tab_bottom01 = (LinearLayout) findViewById(R.id.ll_tab_bottom01);
		ll_tab_bottom02 = (LinearLayout) findViewById(R.id.ll_tab_bottom02);
		ll_tab_bottom03 = (LinearLayout) findViewById(R.id.ll_tab_bottom03);
		ll_tab_bottom04 = (LinearLayout) findViewById(R.id.ll_tab_bottom04);

		btn_tab_bottom01 = (ImageButton) findViewById(R.id.btn_tab_bottom01);
		btn_tab_bottom02 = (ImageButton) findViewById(R.id.btn_tab_bottom02);
		btn_tab_bottom03 = (ImageButton) findViewById(R.id.btn_tab_bottom03);
		btn_tab_bottom04 = (ImageButton) findViewById(R.id.btn_tab_bottom04);

		mViews = new ArrayList<View>();
		View first = mInflater.inflate(R.layout.main_tab_01, null);
		View second = mInflater.inflate(R.layout.main_tab_02, null);
		View third = mInflater.inflate(R.layout.main_tab_03, null);
		View fourth = mInflater.inflate(R.layout.main_tab_04, null);
		mViews.add(first);
		mViews.add(second);
		mViews.add(third);
		mViews.add(fourth);

		mAdapter = new PagerAdapter() {
			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView(mViews.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return mViews.size();
			}
		};
		btn_tab_bottom01.setImageResource(R.drawable.tab_up01);
	}

	private void heihei() {
		Random r = new Random();
	switch (r.nextInt(9)) {
	case 0:
		t_leftImageView.setImageResource(R.drawable.face01);
		break;
	case 1:
		t_leftImageView.setImageResource(R.drawable.face02);
		break;
	case 2:
		t_leftImageView.setImageResource(R.drawable.face03);
		break;
	case 3:
		t_leftImageView.setImageResource(R.drawable.face03);
		break;
	case 4:
		t_leftImageView.setImageResource(R.drawable.face05);
		break;
	case 5:
		t_leftImageView.setImageResource(R.drawable.face06);
		break;
	case 6:
		t_leftImageView.setImageResource(R.drawable.face07);
		break;
	case 7:
		t_leftImageView.setImageResource(R.drawable.face08);
		break;
	case 8:
		t_leftImageView.setImageResource(R.drawable.face09);
		break;
	}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void initEvents() {
		t_rightBtn.setOnClickListener(this);
		ll_tab_bottom01.setOnClickListener(this);
		ll_tab_bottom02.setOnClickListener(this);
		ll_tab_bottom03.setOnClickListener(this);
		ll_tab_bottom04.setOnClickListener(this);
		
		
		mViewPager.setAdapter(mAdapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				resetTabBtn();
				switch (position) {
				case 0:
					btn_tab_bottom01.setImageResource(R.drawable.tab_up01);
					break;
				case 1:
					btn_tab_bottom02.setImageResource(R.drawable.tab_up02);
					break;
				case 2:
					btn_tab_bottom03.setImageResource(R.drawable.tab_up03);
					break;
				case 3:
					btn_tab_bottom04.setImageResource(R.drawable.tab_up04);
					break;
				}
				currentIndex = position;
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
	}

	protected void resetTabBtn() {
		btn_tab_bottom01.setImageResource(R.drawable.tab_down01);
		btn_tab_bottom02.setImageResource(R.drawable.tab_down02);
		btn_tab_bottom03.setImageResource(R.drawable.tab_down03);
		btn_tab_bottom04.setImageResource(R.drawable.tab_down04);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.t_rightBtn:
			fStartActivity(PaintActivity.class);
			break;
		case R.id.ll_tab_bottom01:
			mViewPager.setCurrentItem(0);
			break;
		case R.id.ll_tab_bottom02:
			mViewPager.setCurrentItem(1);
			break;
		case R.id.ll_tab_bottom03:
			mViewPager.setCurrentItem(2);
			break;
		case R.id.ll_tab_bottom04:
			mViewPager.setCurrentItem(3);
			break;
		}
	}
}
