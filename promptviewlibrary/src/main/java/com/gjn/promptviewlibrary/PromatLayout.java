package com.gjn.promptviewlibrary;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by gjn on 2018/6/22.
 */

public class PromatLayout extends FrameLayout {
    private static final String TAG = "PromatLayout";

    private int srceenW;
    private int srceenH;
    private PromatView promatView;
    private View child;

    private int xPy = 20;
    private int yPy = 20;

    public PromatLayout(@NonNull Context context) {
        super(context);
        srceenW = context.getResources().getDisplayMetrics().widthPixels;
        srceenH = context.getResources().getDisplayMetrics().heightPixels;
        xPy = (int) (context.getResources().getDisplayMetrics().density * 5);
        yPy = (int) (context.getResources().getDisplayMetrics().density * 5);

        promatView = new PromatView(context);
        addView(promatView);
    }

    public void addViewLayout(View child, View... showViews) {
        this.child = child;
        checkParent(child);
        child.setLayoutParams(getChildViewLayoutParams(getRect(showViews[0]), child));
        addView(child);
        for (View showView : showViews) {
            promatView.addRectView(showView);
        }
    }

    public void removeViewLayout() {
        if (child != null) {
            removeView(child);
            promatView.clear();
        }
    }

    public int getxPy() {
        return xPy;
    }

    public void setxPy(int xPy) {
        this.xPy = xPy;
    }

    public int getyPy() {
        return yPy;
    }

    public void setyPy(int yPy) {
        this.yPy = yPy;
    }

    public PromatView getPromatView() {
        return promatView;
    }

    private LayoutParams getChildViewLayoutParams(Rect rect, View child) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        //计算宽高
        child.measure(0, 0);
        int childW = child.getMeasuredWidth();
        int childH = child.getMeasuredHeight();

        if (childW == srceenW) {
            params.leftMargin = 0;
        } else if (childH == srceenH) {
            params.topMargin = 0;
        } else {
            //计算实际要放置的位置
            int left = rect.left + xPy;
            int top = rect.bottom + yPy;
            if (left + childW > srceenW) {
                left = srceenW - childW - xPy;
            }
            if (top + childH > srceenH) {
                top = rect.top - childH - yPy;
            }
            params.leftMargin = left;
            params.topMargin = top;
        }
        return params;
    }

    private void checkParent(View child) {
        ViewGroup parent = (ViewGroup) child.getParent();
        if (parent != null) {
            Log.i(TAG, "remove parent!");
            parent.removeView(child);
            promatView.clear();
        }
    }

    private Rect getRect(View view) {
        Rect viewRect = new Rect();
        view.getGlobalVisibleRect(viewRect);
        return viewRect;
    }
}
