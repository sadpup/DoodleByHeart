package com.doodle.byheart.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.doodle.byheart.R;
import com.doodle.byheart.adapter.ReMenBaAdapter;
import com.doodle.byheart.base.BaseApplication;
import com.doodle.byheart.base.BaseFragment;
import com.doodle.byheart.view.RoundTextView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class MainTab02 extends BaseFragment {
	
	private ListView lv_remen_ba;
	private List<HashMap<String, Object>> mData;

	public MainTab02() {
		super();
	}
	
	public MainTab02(BaseApplication application, Activity activity,
			Context context)  {
		super(application, activity, context);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.main_tab_02, container, false);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	protected void initViews() {
		lv_remen_ba = (ListView) findViewById(R.id.lv_remen_ba);

	}

	private List<HashMap<String, Object>> setTestData() {
		// 新建一个集合类，用于存放多条数据
	    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	    HashMap<String, Object> map = null;
	    for (int i = 1; i <= 40; i++) {
	      map = new HashMap<String, Object>();
	      map.put("title", "机智如我");
	      map.put("time", "订阅");
	      map.put("info", i+" 订阅|总帖数 "+i);
	      map.put("img", R.drawable.face03);
	      list.add(map);
	    }
		return list;
	}

	@Override
	protected void initEvents() {
		
	}

	@Override
	protected void init() {
		mData = setTestData();
		ReMenBaAdapter adapter = new ReMenBaAdapter(mContext,mData);
		lv_remen_ba.setAdapter(adapter);
	}
	

}
