package com.gjn.promptview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gjn.promptviewlibrary.PromatUtils;

public class MainActivity extends AppCompatActivity {

    private PromatUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utils = new PromatUtils(this);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.ic_launcher_round);

        utils.addTask("第一步", R.id.radioButton, R.id.button)
                .addTask("第二步", R.id.button, R.id.switch1, R.id.textview_main)
                .addTask("第三步", R.id.switch1)
                .addTask(imageView, R.id.textview_main)
                .addTask("第五步", R.id.textView, R.id.button, R.id.textView2, R.id.checkBox)
                .addTask("第六步", R.id.checkBox)
                .addTask(imageView, R.id.textView2);

        utils.setType(1);
        utils.getTipTextView().setTextColor(Color.RED);

        findViewById(R.id.textview_main).post(new Runnable() {
            @Override
            public void run() {
                utils.show();
            }
        });

        findViewById(R.id.textview_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.setType(2);
                utils.setXYpyByDp(25, 40);
                utils.show();
            }
        });
    }
}
