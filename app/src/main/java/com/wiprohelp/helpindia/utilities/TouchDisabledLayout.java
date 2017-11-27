package com.wiprohelp.helpindia.utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by AB335009 on 1/13/2016.
 */
public class TouchDisabledLayout extends RelativeLayout {

    public TouchDisabledLayout(Context context) {
        super(context);
    }

    public TouchDisabledLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchDisabledLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
