package com.doodle.byheart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * @see SurfaceView方式实现LittleFingerView
 * @author ly-lihongliang
 */
public class LittleFingerView extends SurfaceView implements Callback,Runnable{

	private static final String TAG = LittleFingerView.class.getSimpleName();
	public static final int TIME_IN_FRAME = 50;

	Paint mPaint = null;
	Paint mTextPaint = null;
	SurfaceHolder mSurfaceHolder = null;

	boolean mRunning = false;

	Canvas mCanvas = null;

	private Path mPath;

	private float mPosX, mPosY;
	
	public LittleFingerView(Context context) {
		super(context);
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		mCanvas = new Canvas();

		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);

		mPaint.setAntiAlias(true);

		mPaint.setStyle(Paint.Style.STROKE);

		mPaint.setStrokeCap(Paint.Cap.ROUND);

		mPaint.setStrokeWidth(6);

		mPath = new Path();

		mTextPaint = new Paint();

		mTextPaint.setColor(Color.BLACK);

		mTextPaint.setTextSize(15);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.e(TAG, "surfaceChanged");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.e(TAG, "surfaceCreated");
		mRunning = true;
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.e(TAG, "surfaceDestroyed");
		mRunning = false;
	}

	@Override
	public void run() {
		while (mRunning ) {
			long startTime = System.currentTimeMillis();
			synchronized (mSurfaceHolder) {
				mCanvas = mSurfaceHolder.lockCanvas();
				if(mCanvas == null){
					
				}else{
					onDrawView();
					mSurfaceHolder.unlockCanvasAndPost(mCanvas);
				}
			}
			long endTime = System.currentTimeMillis();
			int diffTime = (int) (endTime - startTime);
			Log.e(TAG, "时间"+diffTime);
			while (diffTime <= TIME_IN_FRAME) {
				diffTime = (int) (System.currentTimeMillis() - startTime);
				Thread.yield();
			}
		}		
	}
	
	private void onDrawView() {
			mCanvas.drawColor(Color.WHITE);
			// 绘制曲线
			mCanvas.drawPath(mPath, mPaint);
//			mCanvas.drawText("当前触笔X：" + mPosX, 0, 20, mTextPaint);
//			mCanvas.drawText("当前触笔Y:" + mPosY, 0, 40, mTextPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float x = event.getX();
		float y = event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mPath.moveTo(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			mPath.quadTo(mPosX, mPosY, x, y);
			break;
		case MotionEvent.ACTION_UP:
//			 mPath.reset();
			break;
		}
		// 记录当前触摸点得当前得坐标
		mPosX = x;
		mPosY = y;
		return true;
	}

}
