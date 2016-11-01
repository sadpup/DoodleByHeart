package com.doodle.byheart.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.Display;
import android.view.View;

public class ImgUtils {

	private Activity activity;
	private Context context;

	public ImgUtils() {

	}

	public ImgUtils(Activity activity, Context context) {
		this.activity = activity;
		this.context = context;
	}

	public Bitmap myShot(Activity activity) {
		// 获取windows中最顶层的view
		View view = activity.getWindow().getDecorView();
		view.buildDrawingCache();

		// 获取状态栏高度
		Rect rect = new Rect();
		view.getWindowVisibleDisplayFrame(rect);
		int statusBarHeights = rect.top;
		Display display = activity.getWindowManager().getDefaultDisplay();

		// 获取屏幕宽和高
		int widths = display.getWidth();
		int heights = display.getHeight();

		// 允许当前窗口保存缓存信息
		view.setDrawingCacheEnabled(true);

		// 去掉状态栏
		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeights, widths,
				heights - statusBarHeights);

		// 销毁缓存信息
		view.destroyDrawingCache();

		return bmp;
	}

	public void saveToSD(Bitmap bmp, String dirName, String fileName) throws IOException {
		// 判断sd卡是否存在
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dir = new File(dirName);
			// 判断文件夹是否存在，不存在则创建
			if (!dir.exists()) {
				dir.mkdir();
			}

			File file = new File(dirName + fileName);
			// 判断文件是否存在，不存在则创建
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				if (fos != null) {
					// 第一参数是图片格式，第二个是图片质量，第三个是输出流
					bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
					// 用完关闭
					fos.flush();
					fos.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public void blur(Bitmap bkg, View view,Activity activity) {
		long startMs = System.currentTimeMillis();

		float radius = 20;

		Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()), (int) (view.getMeasuredHeight()),
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(overlay);

		canvas.translate(-view.getLeft(), -view.getTop());
		canvas.drawBitmap(bkg, 0, 0, null);

		RenderScript rs = RenderScript.create(activity);//getAcctivity();

		Allocation overlayAlloc = Allocation.createFromBitmap(rs, overlay);

		ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, overlayAlloc.getElement());

		blur.setInput(overlayAlloc);

		blur.setRadius(radius);

		blur.forEach(overlayAlloc);

		overlayAlloc.copyTo(overlay);

		view.setBackground(new BitmapDrawable(activity.getResources(), overlay));//getResources()

		rs.destroy();
	}
}
