package com.gjn.promptviewlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjn on 2018/6/22.
 */

public class PromatView extends View {
    private static final String TAG = "PromatView";

    public static final int DEFAULT_BG  = 0x80000000;
    public static final int WHITE       = 0xFFFFFFFF;

    private Paint bgPaint;
    private Paint bmPaint;
    private int bgColor = DEFAULT_BG;
    private List<View> views = new ArrayList<>();

    private boolean isTouch = false;

    private shapeDrawHelper drawHelper;

    public PromatView(Context context) {
        super(context);
        //初始化Paint
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        bgPaint.setAntiAlias(true); //抗锯齿
        bmPaint = new Paint();
        bmPaint.setColor(WHITE);
        bmPaint.setAntiAlias(true); //抗锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bgPaint.setColor(bgColor);
        //绘制一个蒙版背景
        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas bmCanvas = new Canvas(bitmap);
        bmCanvas.drawRect(0, 0, bmCanvas.getWidth(), bmCanvas.getHeight(), bgPaint);
        //绘制需要透明的地方
        //设置图形混合方式，这里使用PorterDuff.Mode.XOR模式，与底层重叠部分设为透明
        PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
        bmPaint.setXfermode(mode);
        for (View view : views) {
            if (drawHelper != null) {
                drawHelper.drawShape(bmCanvas, getRect(view), bmPaint);
            } else {
                drawOval(bmCanvas, view, bmPaint);
            }
        }
        //将整个bitmap绘制到主画布
        canvas.drawBitmap(bitmap, 0, 0, bgPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isTouch) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void addRectView(View view) {
        if (!hasView(view) && bgColor != 0) {
            Log.i(TAG, "addTask view = " + view.getClass().getSimpleName());
            views.add(view);
            invalidate();
        }
    }

    public void removeRectView(View view) {
        if (hasView(view)) {
            Log.i(TAG, "remove view = " + view.getClass().getSimpleName());
            views.remove(view);
            invalidate();
        }
    }

    public boolean hasView(View view) {
        if (views.contains(view)) {
            return true;
        }
        return false;
    }

    public void setDrawHelper(shapeDrawHelper drawHelper) {
        this.drawHelper = drawHelper;
        invalidate();
    }

    public void clearDrawHelper() {
        setDrawHelper(null);
    }

    public void clear() {
        views.clear();
        invalidate();
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public void setBgColor(int color) {
        bgColor = color;
        invalidate();
    }

    private void drawOval(Canvas canvas, View view, Paint paint) {
        Rect rect = getRect(view);
        canvas.drawOval(toRectF(rect), paint);
    }

    private Rect getRect(View view) {
        Rect viewRect = new Rect();
        view.getGlobalVisibleRect(viewRect);
        return viewRect;
    }

    private RectF toRectF(Rect rect) {
        RectF rectF = new RectF();
        rectF.set(rect);
        return rectF;
    }

    public interface shapeDrawHelper {
        void drawShape(Canvas canvas, Rect rect, Paint paint);
    }
}
