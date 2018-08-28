# PromptView
自定义蒙版view

# 依赖
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}


dependencies {
    implementation 'com.github.Gaojianan2016:PromptView:1.0.1'
}
```

# 基本使用
```
package com.gjn.promptview;

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

        utils.addTask("第一步", findViewById(R.id.radioButton))
                .addTask("第二步", findViewById(R.id.button))
                .addTask("第三步", findViewById(R.id.switch1))
                .addTask(imageView, findViewById(R.id.textview_main))
                .addTask("第五步", findViewById(R.id.textView))
                .addTask("第六步", findViewById(R.id.checkBox))
                .addTask("最后一步", findViewById(R.id.textView2));

        findViewById(R.id.textview_main).post(new Runnable() {
            @Override
            public void run() {
                utils.show();
            }
        });

        findViewById(R.id.textview_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.show();
            }
        });
    }
}

```
