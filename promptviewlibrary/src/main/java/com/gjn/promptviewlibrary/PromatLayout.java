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

    public PromatLayout(@NonNull Context context) {
        super(context);
        srceenW = context.getResources().getDisplayMetrics().widthPixels;
        srceenH = context.getResources().getDisplayMetrics().heightPixels;

        promatView = new PromatView(context);
        addView(promatView);
    }

    public void addViewLayout(View child, View to){
        this.child = child;
        checkParent(child);
        Rect rect = getRect(to);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = rect.bottom;
        params.leftMargin = rect.left;
        child.setLayoutParams(params);
        addView(child);
    }

    public void removeViewLayout(){
        if (child != null) {
            removeView(child);
        }
    }

    public PromatView getPromatView(){
        return promatView;
    }

    private void checkParent(View child) {
        ViewGroup parent = (ViewGroup) child.getParent();
        if (parent != null) {
            Log.i(TAG, "remove parent!");
            parent.removeView(child);
        }
    }

    private Rect getRect(View view) {
        Rect viewRect = new Rect();
        view.getGlobalVisibleRect(viewRect);
        return viewRect;
    }
}
