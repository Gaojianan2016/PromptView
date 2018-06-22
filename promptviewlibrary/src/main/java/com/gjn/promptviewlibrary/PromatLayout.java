package com.gjn.promptviewlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by gjn on 2018/6/22.
 */

public class PromatLayout extends FrameLayout {
    public PromatLayout(@NonNull Context context) {
        this(context, null);
    }

    public PromatLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public PromatLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
