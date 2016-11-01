package com.doodle.byheart.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.doodle.byheart.R;
import com.doodle.byheart.base.BaseActivity;
import com.doodle.byheart.util.ImgUtils;
import com.doodle.byheart.view.MyPaintView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @see »­²¼Ò³Ãæ
 * @author ly-lihongliang
 */
public class PaintActivity extends BaseActivity {
	private Button clearBtn;
	private Button saveBtn;
	private MyPaintView paintView;
	private ImgUtils mImgUtils;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paint);
		initViews();
		initEvents();
	}


	@Override
	protected void onDestroy() { 
		super.onDestroy();
	}
	
	@Override
	protected void initViews() {
		paintView = (MyPaintView) findViewById(R.id.view_paint);
		clearBtn = (Button) findViewById(R.id.btn_clear);
		saveBtn = (Button) findViewById(R.id.btn_save);
		
		mImgUtils = new ImgUtils();
	}

	@Override
	protected void initEvents() {
		clearBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				paintView.clear();
				
			}
		});
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bitmap bmp = mImgUtils.myShot(PaintActivity.this);
				try {
					mImgUtils.saveToSD(bmp, "/sdcard/", "test3.png");
				} catch (IOException e) {
					e.printStackTrace(); 
				}
			}
		});
	}
}