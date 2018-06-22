package com.gjn.promptviewlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjn on 2018/6/22.
 */

public class PromatView extends View {
    private Paint paint;
    private List<View> views = new ArrayList<>();

    private boolean isTouch = false;

    public PromatView(Context context) {
        this(context, null);
    }

    public PromatView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PromatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.RED);
        //抗锯齿
        paint.setAntiAlias(true);
        //处理抖动
        paint.setDither(true);
        paint.setTextSize(24);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (View view : views) {
            Rect rect = new Rect();
            getRect(view, rect);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isTouch) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void addRectView(View view){
        if (!hasView(view)) {
            views.add(view);
            invalidate();
        }
    }

    public void removeRectView(View view){
        if (hasView(view)) {
            views.remove(view);
            invalidate();
        }
    }

    public boolean hasView(View view){
        if (views.contains(view)) {
            return true;
        }
        return false;
    }

    public void clear(){
        views.clear();
        invalidate();
    }

    public boolean isTouch() {
        return isTouch;
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    private void getRect(View view, Rect rect) {
        view.getGlobalVisibleRect(rect);
    }
}
