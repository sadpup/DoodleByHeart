package com.doodle.byheart;

import com.doodle.byheart.base.BaseActivity;
import com.doodle.byheart.constant.P;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public class GuideActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		boolean kk = true;//代表是否有广告图片
		if(kk){
			findViewById(R.id.img_guide).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
	    			fStartActivity(MainActivity.class);
	    			finish();
				}
			});
			//TODO 加载网络请求
			/**
			 * 1，首先发送 自己版本号设备号给后台，（根据后台返回是否有最新广告来判断是否加载广告页）
			 * 2,根据后台返回的广告页面地址加载网络图片
			 * 
			 * */
			putAsyncTask(adTask);
		}else{
			new Handler().postDelayed(new Runnable() {  
	            @Override  
	            public void run() {  
	    			fStartActivity(MainActivity.class);
	    			finish();
	            }  
	  
	        }, P.SPLASH_DISPLAY_LENGHT);  
		}
		
	}
	
	private AsyncTask adTask = new AsyncTask<Void, Void, Boolean>(){

		@Override
		protected Boolean doInBackground(Void... params) {
			
			return null;
		}
		
	};		

	@Override
	protected void initViews() {
	}

	@Override
	protected void initEvents() {
	}

}
