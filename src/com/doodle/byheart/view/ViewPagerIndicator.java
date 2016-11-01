package com.doodle.byheart.view;

import java.util.List;

import com.doodle.byheart.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * http://blog.csdn.net/lmj623565791/article/details/42160391
 * @author zhy
 *
 */
/**
 * @see �Զ����viewpager ʹ�ò���
 * @author ly-lihongliang
 */
public class ViewPagerIndicator extends LinearLayout
{
	/**
	 * ���������εĻ���
	 */
	private Paint mPaint;
	/**
	 * path����һ��������
	 */
	private Path mPath;
	/**
	 * �����εĿ��
	 */
	private int mTriangleWidth;
	/**
	 * �����εĸ߶�
	 */
	private int mTriangleHeight;

	/**
	 * �����εĿ��Ϊ����Tab��1/6
	 */
	private static final float RADIO_TRIANGEL = 1.0f / 6;
	/**
	 * �����ε������
	 */
	private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 3 * RADIO_TRIANGEL);

	/**
	 * ��ʼʱ��������ָʾ����ƫ����
	 */
	private int mInitTranslationX;
	/**
	 * ��ָ����ʱ��ƫ����
	 */
	private float mTranslationX;

	/**
	 * Ĭ�ϵ�Tab����
	 */
	private static final int COUNT_DEFAULT_TAB = 4;
	/**
	 * tab����
	 */
	private int mTabVisibleCount = COUNT_DEFAULT_TAB;

	/**
	 * tab�ϵ�����
	 */
	private List<String> mTabTitles;
	/**
	 * ��֮�󶨵�ViewPager
	 */
	public ViewPager mViewPager;

	/**
	 * ��������ʱ����ɫ
	 */
	private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
	/**
	 * ����ѡ��ʱ����ɫ
	 */
	private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;

	public ViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		// ����Զ������ԣ�tab������
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ViewPagerIndicator);
		mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_item_count,
				COUNT_DEFAULT_TAB);
		if (mTabVisibleCount < 0)
			mTabVisibleCount = COUNT_DEFAULT_TAB;
		a.recycle();

		// ��ʼ������
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.parseColor("#ffffffff"));
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));

	}

	/**
	 * ����ָʾ��
	 */
	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		canvas.save();
		// ����ƽ�Ƶ���ȷ��λ��
		canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
		canvas.drawPath(mPath, mPaint);
		canvas.restore();

		super.dispatchDraw(canvas);
	}

	/**
	 * ��ʼ�������εĿ��
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mTriangleWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);// 1/6 of
																		// width
		mTriangleWidth = Math.min(DIMENSION_TRIANGEL_WIDTH, mTriangleWidth);

		// ��ʼ��������
		initTriangle();

		// ��ʼʱ��ƫ����
		mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mTriangleWidth
				/ 2;
	}

	/**
	 * ���ÿɼ���tab������
	 * 
	 * @param count
	 */
	public void setVisibleTabCount(int count)
	{
		this.mTabVisibleCount = count;
	}

	/**
	 * ����tab�ı������� ��ѡ�������Լ��ڲ����ļ���д��
	 * 
	 * @param datas
	 */
	public void setTabItemTitles(List<String> datas)
	{
		// ��������list��ֵ�����Ƴ������ļ������õ�view
		if (datas != null && datas.size() > 0)
		{
			this.removeAllViews();
			this.mTabTitles = datas;

			for (String title : mTabTitles)
			{
				// ���view
				addView(generateTextView(title));
			}
			// ����item��click�¼�
			setItemClickEvent();
		}

	}

	/**
	 * �����ViewPager�Ļص��ӿ�
	 * 
	 * @author zhy
	 * 
	 */
	public interface PageChangeListener
	{
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels);

		public void onPageSelected(int position);

		public void onPageScrollStateChanged(int state);
	}

	// �����ViewPager�Ļص��ӿ�
	private PageChangeListener onPageChangeListener;

	// �����ViewPager�Ļص��ӿڵ�����
	public void setOnPageChangeListener(PageChangeListener pageChangeListener)
	{
		this.onPageChangeListener = pageChangeListener;
	}

	// ���ù�����ViewPager
	public void setViewPager(ViewPager mViewPager, int pos)
	{
		this.mViewPager = mViewPager;

		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				// ����������ɫ����
				resetTextViewColor();
				highLightTextView(position);

				// �ص�
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageSelected(position);
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				// ����
				scroll(position, positionOffset);

				// �ص�
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrolled(position,
							positionOffset, positionOffsetPixels);
				}

			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
				// �ص�
				if (onPageChangeListener != null)
				{
					onPageChangeListener.onPageScrollStateChanged(state);
				}

			}
		});
		// ���õ�ǰҳ
		mViewPager.setCurrentItem(pos);
		// ����
		highLightTextView(pos);
	}

	/**
	 * �����ı�
	 * 
	 * @param position
	 */
	protected void highLightTextView(int position)
	{
		View view = getChildAt(position);
		if (view instanceof TextView)
		{
			((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
		}

	}

	/**
	 * �����ı���ɫ
	 */
	private void resetTextViewColor()
	{
		for (int i = 0; i < getChildCount(); i++)
		{
			View view = getChildAt(i);
			if (view instanceof TextView)
			{
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	/**
	 * ���õ���¼�
	 */
	public void setItemClickEvent()
	{
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++)
		{
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}

	/**
	 * ���ݱ����������ǵ�TextView
	 * 
	 * @param text
	 * @return
	 */
	private TextView generateTextView(String text)
	{
		TextView tv = new TextView(getContext());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabVisibleCount;
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setText(text);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setLayoutParams(lp);
		return tv;
	}

	/**
	 * ��ʼ��������ָʾ��
	 */
	private void initTriangle()
	{
		mPath = new Path();

		mTriangleHeight = (int) (mTriangleWidth / 2 / Math.sqrt(2));
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
		mPath.close();
	}

	/**
	 * ָʾ��������ָ�������Լ���������
	 * 
	 * @param position
	 * @param offset
	 */
	public void scroll(int position, float offset)
	{
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		// ���ϸı�ƫ������invalidate
		mTranslationX = getWidth() / mTabVisibleCount * (position + offset);

		int tabWidth = getScreenWidth() / mTabVisibleCount;

		// �������������ƶ����������һ����ʱ�򣬿�ʼ����
		if (offset > 0 && position >= (mTabVisibleCount - 2)
				&& getChildCount() > mTabVisibleCount)
		{
			if (mTabVisibleCount != 1)
			{
				this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
						+ (int) (tabWidth * offset), 0);
			} else
			// ΪcountΪ1ʱ �����⴦��
			{
				this.scrollTo(
						position * tabWidth + (int) (tabWidth * offset), 0);
			}
		}

		invalidate();
	}

	/**
	 * ���ò�����view��һЩ��Ҫ���ԣ����������setTabTitles��������view����Ч
	 */
	@Override
	protected void onFinishInflate()
	{
		Log.e("TAG", "onFinishInflate");
		super.onFinishInflate();

		int cCount = getChildCount();

		if (cCount == 0)
			return;

		for (int i = 0; i < cCount; i++)
		{
			View view = getChildAt(i);
			LinearLayout.LayoutParams lp = (LayoutParams) view
					.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabVisibleCount;
			view.setLayoutParams(lp);
		}
		// ���õ���¼�
		setItemClickEvent();

	}

	/**
	 * �����Ļ�Ŀ��
	 * 
	 * @return
	 */
	public int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

}
