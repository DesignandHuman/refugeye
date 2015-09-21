package com.refugeye;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragingView extends ViewGroup {

    private ViewDragHelper dragHelper;

    public DragingView(Context context) {
        super(context);
    }

    public DragingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return DragingView.this.getMeasuredHeight() - child.getMeasuredHeight();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (yvel > 0) {
                    dragHelper.settleCapturedViewAt(releasedChild.getLeft(), DragingView.this.getMeasuredHeight() - releasedChild.getMeasuredHeight());
                } else {
                    dragHelper.settleCapturedViewAt(releasedChild.getLeft(), 0);
                }
                invalidate();
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
