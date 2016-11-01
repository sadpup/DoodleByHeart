package com.doodle.byheart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @see 字体美化（标题）
 * @author ly-lihongliang
 */
public class RoundTextView extends TextView{
	
	private TextPaint mPaint;
	private String mText;

	public RoundTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mPaint = new TextPaint(getPaint());
        mPaint.setStyle(TextPaint.Style.STROKE);
        mPaint.setShadowLayer(2.0F, 2.0F, 2.0F, Color.RED);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		

//		Canvas canvas = new Canvas();
//		Paint paint = new Paint();
//		paint.setAntiAlias(true);
//		paint.setDither(true);
//		paint.setColor(mActivity.getResources().getColor(R.color.purple_dark));
//		paint.setStyle(Paint.Style.STROKE);
//		paint.setStrokeJoin(Paint.Join.ROUND);
//		paint.setStrokeCap(Paint.Cap.ROUND);
//		paint.setStrokeWidth(12);
//		if(mText.length()>2){
//			drawText(canvas,mText.substring(0, 1), 0, 0, mPaint,-45); 
//			drawText(canvas,mText.substring(1, 2), 0, 0, mPaint,45); 
//			drawText(canvas,mText.substring(2, mText.length()), 0, 0, mPaint,45); 
//		}else{
//			canvas.drawText(mText, 0, 0, mPaint);
//		}  
		canvas.save();
        canvas.clipRect(0, 0, 73, getBottom());
        canvas.drawText(getText().toString(), 0, getBaseline(), mPaint);
        canvas.restore();
	}
	
	@Override
	public void setText(CharSequence text, BufferType type) {
		super.setText(text, type);
		mText = text.toString();
	}
	
	/**
	 * @param canvas
	 * @param text
	 * @param x
	 * @param y
	 * @param paint
	 * @param angle
	 * @see 画旋转文字方法
	 */
	void drawText(Canvas canvas ,String text , float x ,float y,Paint paint ,float angle){
        if(angle != 0){
            canvas.rotate(angle, x, y); 
        }
        canvas.drawText(text, x, y, paint);
        if(angle != 0){
            canvas.rotate(-angle, x, y); 
        }
    }
}
