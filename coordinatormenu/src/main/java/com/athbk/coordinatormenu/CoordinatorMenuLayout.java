package com.athbk.coordinatormenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.athbk.coordinatormenu.component.ContentScrollView;
import com.athbk.coordinatormenu.component.CoordinatorMenuAdapter;
import com.athbk.coordinatormenu.component.MenuLayout;
import com.athbk.coordinatormenu.model.BaseMenuProp;
import com.athbk.coordinatormenu.model.MenuProp;
import com.athbk.coordinatormenu.utils.ICallbackScrollListener;
import com.athbk.coordinatormenu.utils.OnItemMenuClickListener;
import com.athbk.coordinatormenu.utils.OnScrollListener;
import com.athbk.coordinatormenu.utils.ScrollUtils;
import com.athbk.coordinatormenu.utils.StateScroll;

/**
 * Created by ATHBK on 24,August,2018
 */
public class CoordinatorMenuLayout extends RelativeLayout implements ICallbackScrollListener {

    private int menuSize;
    private int menuBigSize;
    private int menuFullLoadHeight;
    private int menuFullLoadWidth[];

    private int menuPaddingLeft;
    private int menuPaddingRight;
    private int menuBigPaddingTop;
    private int menuBigPaddingBottom;
    private int paddingItemMenu;

    private int heightHeader;
    private int heightSpacingBigMenu;
    private int menuBackgroundResource;
    private int menuBackgroundColor;
    private int menuBackgroundSpacingResource;
    private int menuBackgroundSpacingColor;

    // settings of menu

    private int paddingTitleTop;
    private int paddingTitleBottom;
    private int textPosition;
    private int textColor;
    private int textSize;
    private int textStyle;

    private float ratioChangeView;
    private float ratioMaxWidthTitle;


    private CoordinatorMenuAdapter menuAdapter;
    private View headerView;
    private View contentView;
    private View backgroundView;
    private MenuLayout[] arrayMenuView;
    private View transView;

    private int maxTranslationY;
    private int[] maxTranslationX;

    private float percentScaleLayoutX;
    private float percentScaleLayoutY;

    private float percentAutoPush = 0.1f;

    private AttributeSet attributeSet = null;
    private int defStyleAttr = 0;

    private ContentScrollView scrollView;
    private OnScrollListener onScrollChangeListener;
//    private OnItemMenuClickListener onItemMenuClickListener;

    public CoordinatorMenuLayout(Context context) {
        super(context);
        initAttrs(context, null, 0);
    }

    public CoordinatorMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attributeSet = attrs;
        initAttrs(context, attrs, 0);
    }

    public CoordinatorMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attributeSet = attrs;
        this.defStyleAttr = defStyleAttr;
        initAttrs(context, attrs, defStyleAttr);
    }


    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorMenuLayout, defStyleAttr, 0);
        menuSize = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_size, 30);
        menuBigSize = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_big_size, 50);
        menuPaddingLeft = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_padding_left, 0);
        menuPaddingRight = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_padding_right, 0);
        menuBigPaddingTop = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_big_padding_top, 0);
        menuBigPaddingBottom = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_big_padding_bottom, 0);
        paddingItemMenu = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_padding_item, 0);

        paddingTitleTop = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_title_padding_top, 0);
        paddingTitleBottom = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_title_padding_bottom, 0);
        textPosition = ta.getInt(R.styleable.CoordinatorMenuLayout_menu_title_text_position, 1);
        textColor = ta.getColor(R.styleable.CoordinatorMenuLayout_menu_title_text_color, getResources().getColor(android.R.color.black));
        textSize = (int) ta.getDimension(R.styleable.CoordinatorMenuLayout_menu_title_text_size, 14);
        textStyle = ta.getInt(R.styleable.CoordinatorMenuLayout_menu_title_text_style, 0);

        ratioChangeView = ta.getFloat(R.styleable.CoordinatorMenuLayout_menu_ratio_change_view, 0.5f);
        ratioMaxWidthTitle = ta.getFloat(R.styleable.CoordinatorMenuLayout_menu_ratio_max_width_title, 1.6f);

        menuBackgroundResource = ta.getResourceId(R.styleable.CoordinatorMenuLayout_menu_background_resource, -1);
        menuBackgroundColor = ta.getColor(R.styleable.CoordinatorMenuLayout_menu_background_color, -1);
        menuBackgroundSpacingResource = ta.getResourceId(R.styleable.CoordinatorMenuLayout_menu_background_spacing_resource, R.drawable.bg);
        menuBackgroundSpacingColor = ta.getColor(R.styleable.CoordinatorMenuLayout_menu_background_spacing_color, -1);
        ta.recycle();

    }

    private void initView(){
        try {
            this.contentView = menuAdapter.getContentView(this);

            this.scrollView = new ContentScrollView(getContext());
            this.scrollView.addView(contentView);
            this.scrollView.setCallbackScrollListener(this);

            this.headerView = menuAdapter.getHeaderView(this);

            transView = new View(getContext());

            if (menuBackgroundResource != -1 || menuBackgroundColor != -1) {
                backgroundView = new View(getContext());
                this.addView(backgroundView);
            }

            this.addView(scrollView);
            this.addView(transView);
            this.addView(headerView);

            int count = menuAdapter.getCountMenu();
            this.arrayMenuView = new MenuLayout[count];
            for (int i=0; i< count; i++){
                final int position = i;
                BaseMenuProp baseMenuProp = menuAdapter.onBindMenuView(i);

                arrayMenuView[i] = new MenuLayout(getContext(), attributeSet, defStyleAttr);
                arrayMenuView[i].setPropMenu(new MenuProp.MenuBuilder()
                        .setMenuSize(menuSize)
                        .setMenuBigSize(menuBigSize)
                        .setPaddingTitleBottom(paddingTitleBottom)
                        .setPaddingTitleTop(paddingTitleTop)
                        .setPositionTitle(textPosition)
                        .setTextColor(textColor)
                        .setTextSize(textSize)
                        .setText(baseMenuProp.getTitle())
                        .setResNormalSize(baseMenuProp.getResNormalSize())
                        .setResBigSize(baseMenuProp.getResBigSize())
                        .setRatioChangeView(ratioChangeView)
                        .setMaxRatioTextWidth(ratioMaxWidthTitle)
                        .setTextStyle(textStyle)
                        .build());
                this.addView(arrayMenuView[i]);

                arrayMenuView[i].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (menuAdapter != null){
                            menuAdapter.onItemMenuClickListener(position);
                        }
                    }
                });
            }

            ScrollUtils.addOnGlobalLayoutListener(arrayMenuView[0], new Runnable() {
                @Override
                public void run() {
                    menuFullLoadWidth = new int[arrayMenuView.length];

                    for (int i=0; i< arrayMenuView.length; i++) {
                        menuFullLoadWidth[i] = arrayMenuView[i].getWidth() * menuBigSize / menuSize;
                        int maxHeight = arrayMenuView[i].getHeight() * menuBigSize / menuSize;
                        if (maxHeight > menuFullLoadHeight){
                            menuFullLoadHeight = maxHeight;
                        }
                    }
//                    Log.e("TAG", "" + heightText + "/" + arrayMenuView[0].getHeight() + "/" + arrayMenuView[0].getMeasuredHeight());
                    menuFullLoadHeight = menuFullLoadHeight + paddingTitleTop + paddingTitleBottom;

//                    Log.e("TAG", "" + menuFullLoadWidth + "/" + menuFullLoadHeight + "/" + arrayMenuView[0].getHeightText());

                    ScrollUtils.addOnGlobalLayoutListener(headerView, new Runnable() {
                        @Override
                        public void run() {
                            initMenuView();
                            initContentView();
                            updateScrollView(0);
                        }
                    });
                }
            });
//
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d("TAG", "onMeasure");

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        Log.d("TAG", "onLayout");
    }

    public void setMenuAdapter(CoordinatorMenuAdapter menuAdapter) {
        this.menuAdapter = menuAdapter;
        initView();
    }

    private void initMenuView(){
        if (arrayMenuView == null || arrayMenuView.length == 0) return;

        int headerHeight = this.headerView.getHeight();
        this.heightHeader = headerHeight;
        int widthHeight = this.headerView.getWidth();

        int marginTop = (headerHeight - menuSize)/2;
        int widthSpacingMenu = widthHeight - menuPaddingLeft - menuPaddingRight;
        int widthApart = widthSpacingMenu / arrayMenuView.length;

        int widthApartBigSize = widthHeight / arrayMenuView.length;

        maxTranslationY = menuBigPaddingTop + menuSize + marginTop;

        maxTranslationX = new int[arrayMenuView.length];

        percentScaleLayoutX = ((float)menuBigSize/ menuSize) - 1;
        percentScaleLayoutY =((float)menuBigSize/ menuSize) - 1;

        for (int i=0; i< arrayMenuView.length; i++){
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int marginLeft = menuPaddingLeft + ((widthApart / 2 - menuSize / 2) + widthApart * i);
            if (paddingItemMenu > 0) {
                marginLeft = menuPaddingLeft + paddingItemMenu * i + menuSize * i - (arrayMenuView[i].getWidth() - menuSize) / 2;
                Log.e("TAG-1", "" + (arrayMenuView[i].getWidth() - menuSize));
            }

            lp.setMargins(marginLeft, marginTop, 0, 0);
            arrayMenuView[i].setLayoutParams(lp);
//            Log.e("TAG-cal", "widthApartBigSize: " + widthApartBigSize);
            int marginLeftBigSize = ((widthApartBigSize - menuFullLoadWidth[i]) / 2) +  widthApartBigSize * i;
//            Log.e("TAG-marginLeftBigSize", "" + marginLeftBigSize);
            maxTranslationX[i] = marginLeft - marginLeftBigSize;
        }
    }

    private void initContentView(){
        this.heightSpacingBigMenu = menuFullLoadHeight + menuBigPaddingTop + menuBigPaddingBottom;
        int paddingContent = heightSpacingBigMenu + heightHeader;

//        scrollView.setPaddingSpacing(paddingContent);
        scrollView.setMarginTop(heightHeader);
        scrollView.setPaddingSpacing(heightSpacingBigMenu);


        initSpacingView(paddingContent);
    }

    private void initSpacingView(int heightSpacing){
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightSpacing);

        if (backgroundView != null) {
            backgroundView.setLayoutParams(lp);
            if (menuBackgroundResource != -1) {
                backgroundView.setBackgroundResource(menuBackgroundResource);
            }
            if (menuBackgroundColor != -1){
                backgroundView.setBackgroundResource(menuBackgroundColor);
            }
        }

        transView.setLayoutParams(lp);
        transView.setBackgroundResource(menuBackgroundSpacingResource);
        if (menuBackgroundSpacingColor != -1){
            transView.setBackgroundColor(menuBackgroundSpacingColor);
        }
    }

    @Override
    public void onScrollChange(int scrollY) {
//        Log.e("TAG", "VAO: " + scrollY);
        updateScrollView(scrollY);
    }

    @Override
    public void onStartScroll(int scrollY) {

    }

    @Override
    public void onCancelScroll(int scrollY) {
        finishMove(scrollY);
    }

    private void finishMove(final int scrollY){
        try {
            int minScrollAuto = (int) (heightSpacingBigMenu * percentAutoPush);
//            Log.e("TAG", "" + minScrollAuto + "/" + scrollY + "/" + heightSpacingBigMenu);

            if (scrollY < heightSpacingBigMenu && scrollY >= minScrollAuto){
                if (scrollView.getCurrentStateScroll() == StateScroll.DOWN) {
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
//                            Log.e("TAG", "Tu dong scroll len");
                            scrollView.fling(heightSpacingBigMenu);
                            scrollView.smoothScrollTo(0, heightSpacingBigMenu);
                        }
                    });
                }
                else {
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
//                            Log.e("TAG", "Tu dong scroll xuong");
                            scrollView.fling(0);
                            scrollView.smoothScrollTo(0, 0);
                        }
                    });
                }
            }
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void updateScrollView(int scrollY){
        try {
            transView.setTranslationY(-scrollY);

            int adjustedScrollY = (int) ScrollUtils.getFloat(scrollY, 0, heightSpacingBigMenu);
            float percentScroll = (float) (heightSpacingBigMenu - adjustedScrollY) / heightSpacingBigMenu;

            int translationY = (int) (maxTranslationY * percentScroll);

            for (int i=0; i< arrayMenuView.length; i++){
                int translationX = (int) (maxTranslationX[i] * percentScroll);

                float scaleLayoutX = percentScaleLayoutX * percentScroll;
                float scaleLayoutY = percentScaleLayoutY * percentScroll;

                arrayMenuView[i].setTranslationY(translationY);
                arrayMenuView[i].setTranslationX(-translationX);
                arrayMenuView[i].setPivotX(0);
                arrayMenuView[i].setPivotY(0);
                arrayMenuView[i].setScaleX(1 + scaleLayoutX);
                arrayMenuView[i].setScaleY(1 + scaleLayoutY);
                arrayMenuView[i].setImageAlpha(percentScroll);
                arrayMenuView[i].setTextAlpha(percentScroll);
            }

            if (onScrollChangeListener != null){
                onScrollChangeListener.onScroll(percentScroll);
            }

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setOnScrollChangeListener(OnScrollListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

//    public void setOnItemMenuClickListener(OnItemMenuClickListener onItemMenuClickListener) {
//        this.onItemMenuClickListener = onItemMenuClickListener;
//    }
}
