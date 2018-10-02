package com.athbk.coordinatormenu.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.athbk.coordinatormenu.utils.ICallbackScrollListener;
import com.athbk.coordinatormenu.utils.StateScroll;

/**
 * Created by ATHBK on 24,August,2018
 */
public class ContentScrollView extends NestedScrollView {

    private int prevY;
    private int currentScrollY;
    private int currentStateScroll;

    private ICallbackScrollListener callbackScrollListener;

    public ContentScrollView(@NonNull Context context) {
        super(context);
        initLayout();
    }

    public ContentScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public ContentScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        currentScrollY = t;
        if (callbackScrollListener != null){
            callbackScrollListener.onScrollChange(currentScrollY);
        }
        if (currentScrollY > prevY){
            currentStateScroll = StateScroll.DOWN;
        }
        else {
            currentStateScroll = StateScroll.UP;
        }
        prevY = currentScrollY;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN){
            if (callbackScrollListener != null){
                callbackScrollListener.onStartScroll((int) ev.getY());
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (callbackScrollListener != null){
                    callbackScrollListener.onCancelScroll(currentScrollY);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    public void onStopNestedScroll(View target) {
        if (callbackScrollListener != null){
            callbackScrollListener.onCancelScroll(currentScrollY);
        }
        super.onStopNestedScroll(target);
    }

    public int getCurrentScrollY() {
        return currentScrollY;
    }

    public int getCurrentStateScroll() {
        return currentStateScroll;
    }

    public void setCallbackScrollListener(ICallbackScrollListener callbackScrollListener) {
        this.callbackScrollListener = callbackScrollListener;
    }

    public void setPaddingSpacing(int spacing){
        try {
            View child = getChildAt(0);
            if (child != null) {
                if (child instanceof ViewGroup){
                    ((ViewGroup) child).setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
                }
                child.setPadding(0, spacing, 0, 0);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setMarginTop(int marginTop){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, marginTop, 0, 0);
        this.setLayoutParams(lp);
    }

    private void initLayout(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(lp);
        setSmoothScrollingEnabled(true);
    }
}

