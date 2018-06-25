package com.gjn.promptview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gjn.promptviewlibrary.PromatLayout;
import com.gjn.promptviewlibrary.PromatView;

public class MainActivity extends AppCompatActivity {

    private PromatLayout layout;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPromatView();

        textView = new TextView(this);
        textView.setBackgroundColor(Color.GRAY);
        textView.setText("被点击之后出现的");
        textView.setTextSize(20);

        setPromatView();
    }

    private void setPromatView() {
        ViewGroup content = findViewById(android.R.id.content);
        setting(content);
    }

    private void setting(ViewGroup parent) {
        if (hasChild(parent)) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View view = parent.getChildAt(i);
                if (view instanceof ViewGroup) {
                    final ViewGroup child = (ViewGroup) view;
                    setting(child);
                } else {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (layout.getPromatView().hasView(v)) {
                                layout.getPromatView().removeRectView(v);
                                layout.removeViewLayout();
                            } else {
                                layout.getPromatView().addRectView(v);
                                layout.addViewLayout(textView, v);
                            }
                        }
                    });
                }
            }
        }
    }

    private boolean hasChild(ViewGroup group) {
        if (group.getChildCount() > 0) {
            return true;
        }
        return false;
    }

    private void addPromatView() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        layout = new PromatLayout(this);
        viewGroup.addView(layout);
        layout.setEnabled(false);
    }
}
