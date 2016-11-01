package com.doodle.byheart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.doodle.byheart.activity.PaintActivity;
import com.doodle.byheart.activity.PersonalCenter;
import com.doodle.byheart.base.BaseActivity;
import com.doodle.byheart.fragment.MainTab01;
import com.doodle.byheart.fragment.MainTab02;
import com.doodle.byheart.fragment.MainTab03;
import com.doodle.byheart.fragment.MainTab04;
import com.doodle.byheart.view.CircleImageView;
import com.doodle.byheart.view.RoundTextView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @see ��ȫ��ʹ��fragmentǶ�׵���ҳ
 * @author ly-lihongliang
 */
@SuppressLint("InflateParams")
public class MainActivity extends BaseActivity implements OnClickListener {

	private Fragment mTab01;
	private Fragment mTab02;
	private Fragment mTab03;
	private Fragment mTab04;

	// �ײ��ؼ��İ�ť
	private LinearLayout ll_tab_bottom01;
	private LinearLayout ll_tab_bottom02;
	private LinearLayout ll_tab_bottom03;
	private LinearLayout ll_tab_bottom04;
	private ImageButton btn_tab_bottom01;
	private ImageButton btn_tab_bottom02;
	private ImageButton btn_tab_bottom03;
	private ImageButton btn_tab_bottom04;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		fragmentManager = getSupportFragmentManager();
		setTabSelection(1);
	}

	private void setTabSelection(int index) {
		resetTabBtn();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (index) {
		case 1:
			// ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ
			t_centerTextView.setText(R.string.main_title_01);
			btn_tab_bottom01.setImageResource(R.drawable.tab_up01);
			if (mTab01 == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				mTab01 = new MainTab01(mApplication,this,this);
				transaction.add(R.id.id_content, mTab01);
			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(mTab01);
			}
			break;
		case 2:
			// ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ
			t_centerTextView.setText(R.string.main_title_02);
			btn_tab_bottom02.setImageResource(R.drawable.tab_up02);
			if (mTab02 == null) {
				// ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������
				mTab02 = new MainTab02(mApplication,this,this);
				transaction.add(R.id.id_content, mTab02);
			} else {
				// ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(mTab02);
			}
			break;
		case 3:
			// ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ
			btn_tab_bottom03.setImageResource(R.drawable.tab_up03);
			if (mTab03 == null) {
				// ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������
				mTab03 = new MainTab03(mApplication,this,this);
				transaction.add(R.id.id_content, mTab03);
			} else {
				// ���NewsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(mTab03);
			}
			break;
		case 4:
			// �����������tabʱ���ı�ؼ���ͼƬ��������ɫ
			btn_tab_bottom04.setImageResource(R.drawable.tab_up04);
			if (mTab04 == null) {
				// ���SettingFragmentΪ�գ��򴴽�һ������ӵ�������
				mTab04 = new MainTab04(mApplication,this,this);
				transaction.add(R.id.id_content, mTab04);
			} else {
				// ���SettingFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(mTab04);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mTab01 != null) {
			transaction.hide(mTab01);
		}
		if (mTab02 != null) {
			transaction.hide(mTab02);
		}
		if (mTab03 != null) {
			transaction.hide(mTab03);
		}
		if (mTab04 != null) {
			transaction.hide(mTab04);
		}
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

		// btn_tab_bottom01.setImageResource(R.drawable.tab_up01);
		
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
		t_leftImageView.setOnClickListener(this);
		ll_tab_bottom01.setOnClickListener(this);
		ll_tab_bottom02.setOnClickListener(this);
		ll_tab_bottom03.setOnClickListener(this);
		ll_tab_bottom04.setOnClickListener(this);

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
		case R.id.t_leftImageView:
			fStartActivity(PersonalCenter.class);
			break;
		case R.id.ll_tab_bottom01:
			setTabSelection(1);
			break;
		case R.id.ll_tab_bottom02:
			setTabSelection(2);
			break;
		case R.id.ll_tab_bottom03:
			setTabSelection(3);
			break;
		case R.id.ll_tab_bottom04:
			setTabSelection(4);
			break;
		}
	}
}
