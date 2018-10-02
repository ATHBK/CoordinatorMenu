package com.athbk.coordinatormenu.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.athbk.coordinatormenu.R;
import com.athbk.coordinatormenu.model.MenuProp;

/**
 * Created by ATHBK on 13,September,2018
 */
public class MenuLayout extends LinearLayout{

    private MenuProp propMenu;
    private ImageView imageView;
    private TextView textView;

//    private final float RATIO = 0.5f;

    public MenuLayout(Context context) {
        super(context);
    }

    public MenuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public MenuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e("onMeasure", "onMeasure: " + MeasureSpec.getSize(widthMeasureSpec));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.e("onSizeChange", "onSizeChange");
    }

    public void setPropMenu(MenuProp propMenu) {
        this.propMenu = propMenu;
        initView();
    }

    public void initView(){
//        this.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        imageView = new ImageView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(propMenu.getMenuSize(),propMenu.getMenuSize());
        lp.gravity = Gravity.CENTER;
        imageView.setLayoutParams(lp);
//        imageView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        imageView.setImageResource(propMenu.getResNormalSize());
        imageView.setTag(propMenu.getResNormalSize());


        if (propMenu.getPositionTitle() == 0) {
            this.addView(imageView);
            return;
        }

        textView = new TextView(getContext());
        LinearLayout.LayoutParams lp1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.gravity = Gravity.CENTER;
        textView.setLayoutParams(lp1);

        textView.setTextSize(propMenu.getTextSizeMini());
        textView.setTextColor(propMenu.getTextColor());
        Log.e("TAG2", "" + (int) (propMenu.getMaxRatioTextWidth() * propMenu.getMenuSize()));
        textView.setMaxWidth((int) (propMenu.getMaxRatioTextWidth() * propMenu.getMenuSize()));
        textView.setMaxLines(2);
        textView.setGravity(Gravity.CENTER);
//        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        textView.setPadding(0, propMenu.getPaddingTitleTop(), 0, propMenu.getPaddingTitleBottom());
        textView.setText(propMenu.getText());
        if (propMenu.getTextStyle() != 0){
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }

        if (propMenu.getPositionTitle() == 1) {
            this.setOrientation(VERTICAL);
            this.addView(imageView);
            this.addView(textView);
        } else {
            this.setOrientation(VERTICAL);
            this.addView(textView);
            this.addView(imageView);
        }

    }

    public void setScaleImage(float scale){
        if (imageView != null){
            imageView.setPivotX(0);
            imageView.setPivotY(0);
            imageView.setScaleX(1 + scale);
            imageView.setScaleY(1 + scale);

        }

    }

    public void setScaleText(float scale){
        if (textView != null){
            textView.setPivotX(0);
            textView.setPivotY(0);
            textView.setScaleX(0 + scale);
            textView.setScaleY(0 + scale);
        }
    }


    public void setHideText(boolean isHide){
        if (textView == null) return;
        if (isHide){
            textView.setVisibility(INVISIBLE);
        }
        else {
            textView.setVisibility(VISIBLE);
        }
    }

    public void setTextViewAlpha(float alpha){
        if (textView == null) return;
        textView.setAlpha(alpha);
    }

    public void setTextViewSize(float size){
        if (textView == null) return;
        textView.setTextSize(size);
    }


    public void setTextAlpha(float percentScroll){
        if (textView == null) return;
        if (percentScroll > 0.5){
            setHideText(false);
        }
        else {
            setHideText(true);
        }

        float alpha = (percentScroll * 2) - 1;
        if (alpha < 0){
            alpha = 0;
        }

        textView.setAlpha(alpha);
    }

    public void setImageAlpha(float percentScroll){
        if (imageView == null) return;
        if (propMenu == null) return;
        float RATIO = propMenu.getRatioChangeView();
        if (percentScroll > RATIO){
            if ((int)imageView.getTag() == propMenu.getResNormalSize()){
                imageView.setImageResource(propMenu.getResBigSize());
                imageView.setTag(propMenu.getResBigSize());
            }
            imageView.setAlpha((percentScroll * 1/RATIO) - 1);
        }
        else {
            if ((int)imageView.getTag() == propMenu.getResBigSize()){
                imageView.setImageResource(propMenu.getResNormalSize());
                imageView.setTag(propMenu.getResNormalSize());
            }
            imageView.setAlpha(1 - (percentScroll * 1/RATIO));
        }
    }

}
