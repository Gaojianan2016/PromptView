package com.gjn.promptviewlibrary;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjn on 2018/6/26.
 */

public class PromatUtils {
    private static final String TAG = "PromatUtils";

    public static final int OVAL_TYPE = 0;
    public static final int RECT_TYPE = 1;
    public static final int CIRCLE_TYPE = 2;

    private int maker;
    private List<PromatItem> lists = new ArrayList<>();

    private PromatLayout promatLayout;
    private ViewGroup decorView;
    private TextView tipTextView;
    private Activity activity;

    public PromatUtils(Activity activity) {
        this.activity = activity;
        tipTextView = new TextView(activity);
        tipTextView.setTextColor(Color.WHITE);

        decorView = (ViewGroup) activity.getWindow().getDecorView();

        promatLayout = new PromatLayout(activity);
    }

    public PromatUtils addTask(PromatItem item) {
        lists.add(item);
        return this;
    }

    public PromatUtils addTask(Object tip, View... views) {
        PromatItem item = new PromatItem();
        if (tip instanceof String) {
            item.setTipMsg((String) tip);
        }else if(tip instanceof View){
            item.setTipView((View) tip);
        }
        item.setViews(views);
        return addTask(item);
    }

    public PromatUtils addTask(Object tip, int... viewIds) {
        PromatItem item = new PromatItem();
        if (tip instanceof String) {
            item.setTipMsg((String) tip);
        }else if(tip instanceof View){
            item.setTipView((View) tip);
        }
        View[] views = new View[viewIds.length];
        for (int i = 0; i < viewIds.length; i++) {
            views[i] = activity.findViewById(viewIds[i]);
        }
        item.setViews(views);
        return addTask(item);
    }

    public void clearTask() {
        lists.clear();
        if (decorView != null) {
            if (promatLayout != null) {
                promatLayout.removeViewLayout();
            }
            decorView.removeView(promatLayout);
        }
    }

    public void show() {
        if (lists.size() == 0) {
            Log.e(TAG, "no task");
            return;
        }
        maker = 0;
        //添加引导布局
        decorView.addView(promatLayout);
        //显示提示
        showTipView();
        promatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maker++;
                if (maker < lists.size()) {
                    showTipView();
                } else {
                    decorView.removeView(promatLayout);
                }
            }
        });
    }

    public void setPromatDrawHelper(PromatView.shapeDrawHelper drawHelper){
        promatLayout.getPromatView().setDrawHelper(drawHelper);
    }

    public void setXYpyByDp(int xPy, int yPy){
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        promatLayout.setxPy((int) (xPy * displayMetrics.density));
        promatLayout.setyPy((int) (yPy * displayMetrics.density));
    }

    public void setType(int type){
        if (type == RECT_TYPE) {
            promatLayout.getPromatView().setDrawHelper(new PromatView.shapeDrawHelper() {
                @Override
                public void drawShape(Canvas canvas, Rect rect, Paint paint) {
                    canvas.drawRect(rect, paint);
                }
            });
        }else if (type == CIRCLE_TYPE) {
            promatLayout.getPromatView().setDrawHelper(new PromatView.shapeDrawHelper() {
                @Override
                public void drawShape(Canvas canvas, Rect rect, Paint paint) {
                    int r = Math.min(rect.width(), rect.height());
                    canvas.drawCircle(rect.centerX(), rect.centerY(), r, paint);
                }
            });
        }else {
            promatLayout.getPromatView().setDrawHelper(null);
        }
    }

    public PromatLayout getPromatLayout() {
        return promatLayout;
    }

    public TextView getTipTextView() {
        return tipTextView;
    }

    private void showTipView() {
        promatLayout.removeViewLayout();
        PromatItem item = lists.get(maker);
        if (item.getTipView() != null) {
            promatLayout.addViewLayout(item.getTipView(), item.getViews());
        } else {
            String tip = item.getTipMsg();
            tipTextView.setText(tip);
            promatLayout.addViewLayout(tipTextView, item.getViews());
        }
    }

}
