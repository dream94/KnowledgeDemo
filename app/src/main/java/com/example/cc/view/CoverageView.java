package com.example.cc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.cc.knowldegedemo.R;

/**
 * Created by cc on 2019/1/17.
 * 蒙层引导View
 */

public class CoverageView extends View {
    private boolean isCircleTransparent;
    private View transparentView;

    public CoverageView(Context context) {
        super(context);
    }

    public CoverageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCircleTransparent(boolean circleTransparent) {
        isCircleTransparent = circleTransparent;
        invalidate();
    }

    public void setTransparentView(View view) {
        transparentView = view;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(bitmap);
        // 背景画笔
        Paint bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.shadow));

        // 绘制屏幕背景
        temp.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

        if (isCircleTransparent) {
            Paint mCirclePaint = new Paint();
            mCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mCirclePaint.setAntiAlias(true);
            int x = getWidth() / 2;
            int y = getHeight() / 2;
            int radius = Math.min(getWidth(), getHeight()) / 2;
            temp.drawCircle(x, y, radius, mCirclePaint);
        }
        if (transparentView != null) {
            Paint mCirclePaint = new Paint();
            mCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mCirclePaint.setAntiAlias(true);

            int[] location = new int[2];
            transparentView.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
            float x = transparentView.getWidth() / 2 + location[0];
            float y = transparentView.getHeight() / 2 + location[1];
            int radius = Math.max(transparentView.getWidth(), transparentView.getHeight()) / 2 + 30;
            Log.e("dream", "x:" + x + ", y:" + y);
            temp.drawCircle(x, y, radius, mCirclePaint);
        }

        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 0, 0, paint);
        bitmap.recycle();
    }
}
