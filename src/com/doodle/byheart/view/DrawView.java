package com.doodle.byheart.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("NewApi")
public class DrawView extends View {

	private Context mContext;
	private Activity mActivity;

	public DrawView(Context context) {
		super(context);
		init(context);
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
//		这是一种资源调用的方法    http://www.cnblogs.com/purediy/p/3799534.html
//		TypedArray a = context.getTheme().obtainStyledAttributes(
//				attrs,
//				R.styleable.PieChart,
//				0, 0);
//		try {
//			mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
//			mTextPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
//		} finally {
//			a.recycle();
//		}
	}

	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public DrawView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	private void init(Context context){
		this.mContext = context;
//		this.mActivity = context
	}

}
