package com.gjn.promptview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.gjn.promptviewlibrary.PromatView;

public class MainActivity extends AppCompatActivity {

    private PromatView promatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPromatView();

        promatView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                promatView.clear();
                return true;
            }
        });

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
                    Log.e("-s-", "child=====>" + view.getClass().getName() + " setClick");
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (promatView.hasView(v)) {
                                promatView.removeRectView(v);
                            } else {
                                promatView.addRectView(v);
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
        promatView = new PromatView(this);
        promatView.setBackgroundColor(Color.argb(50, 0, 0, 0));
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.addView(promatView);
    }
}
