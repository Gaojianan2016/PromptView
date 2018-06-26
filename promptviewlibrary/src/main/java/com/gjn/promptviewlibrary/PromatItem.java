package com.gjn.promptviewlibrary;

import android.view.View;

/**
 * Created by gjn on 2018/6/26.
 */

public class PromatItem {
    //提示字符串
    private String tipMsg;
    //提示view
    private View tipView;
    //标示view
    private View view;

    public String getTipMsg() {
        return tipMsg;
    }

    public void setTipMsg(String tipMsg) {
        this.tipMsg = tipMsg;
    }

    public View getTipView() {
        return tipView;
    }

    public void setTipView(View tipView) {
        this.tipView = tipView;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
