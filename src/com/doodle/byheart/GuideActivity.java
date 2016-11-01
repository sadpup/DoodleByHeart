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
		boolean kk = true;//�����Ƿ��й��ͼƬ
		if(kk){
			findViewById(R.id.img_guide).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
	    			fStartActivity(MainActivity.class);
	    			finish();
				}
			});
			//TODO ������������
			/**
			 * 1�����ȷ��� �Լ��汾���豸�Ÿ���̨�������ݺ�̨�����Ƿ������¹�����ж��Ƿ���ع��ҳ��
			 * 2,���ݺ�̨���صĹ��ҳ���ַ��������ͼƬ
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
