package com.refugeye;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SwipeView extends LinearLayout {
    private boolean opened = true;
    private ImageView toggle;

    public SwipeView(Context context) {
        super(context);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        toggle = (ImageView) findViewById(R.id.toggle_drawer);
        toggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    public void open() {
        opened = true;
        animate().translationX(dpToPx(0)).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        toggle.animate().rotationBy(180).setDuration(300).start();
    }

    public void close() {
        opened = false;
        animate().translationX(dpToPx(220)).setDuration(300).setInterpolator(new AccelerateDecelerateInterpolator()).start();
        toggle.animate().rotationBy(180).setDuration(300).start();
    }

    public void toggle() {
        if (opened) {
            close();
            return;
        }
        open();
    }


    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
