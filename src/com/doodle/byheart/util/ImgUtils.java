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
		// ��ȡwindows������view
		View view = activity.getWindow().getDecorView();
		view.buildDrawingCache();

		// ��ȡ״̬���߶�
		Rect rect = new Rect();
		view.getWindowVisibleDisplayFrame(rect);
		int statusBarHeights = rect.top;
		Display display = activity.getWindowManager().getDefaultDisplay();

		// ��ȡ��Ļ��͸�
		int widths = display.getWidth();
		int heights = display.getHeight();

		// ����ǰ���ڱ��滺����Ϣ
		view.setDrawingCacheEnabled(true);

		// ȥ��״̬��
		Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeights, widths,
				heights - statusBarHeights);

		// ���ٻ�����Ϣ
		view.destroyDrawingCache();

		return bmp;
	}

	public void saveToSD(Bitmap bmp, String dirName, String fileName) throws IOException {
		// �ж�sd���Ƿ����
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File dir = new File(dirName);
			// �ж��ļ����Ƿ���ڣ��������򴴽�
			if (!dir.exists()) {
				dir.mkdir();
			}

			File file = new File(dirName + fileName);
			// �ж��ļ��Ƿ���ڣ��������򴴽�
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				if (fos != null) {
					// ��һ������ͼƬ��ʽ���ڶ�����ͼƬ�������������������
					bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
					// ����ر�
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
