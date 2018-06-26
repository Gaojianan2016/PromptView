package com.gjn.promptviewlibrary;

import android.app.Activity;
import android.graphics.Color;
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

    private int maker;
    private List<PromatItem> lists = new ArrayList<>();

    private PromatLayout promatLayout;
    private ViewGroup decorView;
    private TextView tipTextView;

    public PromatUtils(Activity activity) {
        tipTextView = new TextView(activity);
        tipTextView.setTextColor(Color.WHITE);

        decorView = (ViewGroup) activity.getWindow().getDecorView();

        promatLayout = new PromatLayout(activity);
    }

    public PromatUtils addTask(PromatItem item) {
        lists.add(item);
        return this;
    }

    public PromatUtils addTask(String tip, View view) {
        PromatItem item = new PromatItem();
        item.setTipMsg(tip);
        item.setView(view);
        return addTask(item);
    }

    public PromatUtils addTask(View tipView, View view) {
        PromatItem item = new PromatItem();
        item.setTipView(tipView);
        item.setView(view);
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
            promatLayout.addViewLayout(item.getTipView(), item.getView());
        } else {
            String tip = item.getTipMsg();
            tipTextView.setText(tip);
            promatLayout.addViewLayout(tipTextView, item.getView());
        }
    }

}
