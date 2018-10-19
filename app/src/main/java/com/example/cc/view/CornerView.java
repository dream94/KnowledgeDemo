package com.example.cc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by cc on 2018/10/15.
 * 利用图层覆盖实现边角圆角
 */

public class CornerView extends View {
    public CornerView(Context context) {
        super(context);
    }

    public CornerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CornerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(50, 50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(LAYER_TYPE_HARDWARE, null);
        Paint paint = new Paint();
        Bitmap fgBitmap = getFgBitmap();
        canvas.drawBitmap(fgBitmap, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        Bitmap bgBitmap = getBgBitmap();
        canvas.drawBitmap(bgBitmap, 0, 0, paint);

        paint.setXfermode(null);
        fgBitmap.recycle();
        bgBitmap.recycle();
    }


    private int size = 50;

    private Bitmap getFgBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, size, size, paint);
        return bitmap;
    }

    private Bitmap getBgBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(0, size, size, paint);    //左上角的圆
        return bitmap;
    }
}
