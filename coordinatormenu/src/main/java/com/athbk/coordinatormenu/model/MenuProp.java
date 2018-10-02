package com.athbk.coordinatormenu.model;

/**
 * Created by ATHBK on 17,September,2018
 */
public class MenuProp {

    private int menuSize;
    private int menuBigSize;

    private int paddingTitleBottom;
    private int paddingTitleTop;
    private int textSize;
    private int textSizeMini;
    private int textColor;
    private int positionTitle;
    private int textStyle;

    private String text;
    private int resNormalSize;
    private int resBigSize;

    private float ratioChangeView;
    private float maxRatioTextWidth;

    public MenuProp(MenuBuilder builder){
        menuSize = builder.menuSize;
        menuBigSize = builder.menuBigSize;
        paddingTitleBottom = builder.paddingTitleBottom;
        paddingTitleTop = builder.paddingTitleTop;
        textSize = builder.textSize;
        textSizeMini = (textSize * menuSize) / menuBigSize;
        textColor = builder.textColor;
        positionTitle = builder.positionTitle;
        text = builder.text;
        resNormalSize = builder.resNormalSize;
        resBigSize = builder.resBigSize;
        ratioChangeView = builder.ratioChangeView;
        maxRatioTextWidth = builder.maxRatioTextWidth;
        textStyle = builder.textStyle;
    }


    public static class MenuBuilder {
        private int menuSize;
        private int menuBigSize;

        private int paddingTitleBottom;
        private int paddingTitleTop;
        private int textSize;
        private int textColor;
        private int positionTitle;

        private String text;
        private int resNormalSize;
        private int resBigSize;

        private float ratioChangeView;
        private float maxRatioTextWidth;

        private int textStyle;


        public MenuBuilder() {}

        public MenuBuilder setMenuSize(int menuSize) {
            this.menuSize = menuSize;
            return this;
        }

        public MenuBuilder setMenuBigSize(int menuBigSize) {
            this.menuBigSize = menuBigSize;
            return this;
        }

        public MenuBuilder setPaddingTitleBottom(int paddingTitleBottom) {
            this.paddingTitleBottom = paddingTitleBottom;
            return this;
        }

        public MenuBuilder setPaddingTitleTop(int paddingTitleTop) {
            this.paddingTitleTop = paddingTitleTop;
            return this;
        }

        public MenuBuilder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public MenuBuilder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public MenuBuilder setPositionTitle(int positionTitle) {
            this.positionTitle = positionTitle;
            return this;
        }

        public MenuBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public MenuBuilder setResNormalSize(int resNormalSize) {
            this.resNormalSize = resNormalSize;
            return this;
        }

        public MenuBuilder setResBigSize(int resBigSize) {
            this.resBigSize = resBigSize;
            return this;
        }

        public MenuBuilder setRatioChangeView(float ratioChangeView) {
            this.ratioChangeView = ratioChangeView;
            return this;
        }

        public MenuBuilder setMaxRatioTextWidth(float maxRatioTextWidth) {
            this.maxRatioTextWidth = maxRatioTextWidth;
            return this;
        }

        public MenuBuilder setTextStyle(int textStyle) {
            this.textStyle = textStyle;
            return this;
        }

        public MenuProp build(){
            return new MenuProp(this);
        }
    }

    public int getMenuSize() {
        return menuSize;
    }

    public int getMenuBigSize() {
        return menuBigSize;
    }

    public int getPaddingTitleBottom() {
        return paddingTitleBottom;
    }

    public int getPaddingTitleTop() {
        return paddingTitleTop;
    }

    public int getTextSize() {
        return textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getPositionTitle() {
        return positionTitle;
    }

    public String getText() {
        return text;
    }

    public int getResNormalSize() {
        return resNormalSize;
    }

    public int getResBigSize() {
        return resBigSize;
    }

    public int getTextSizeMini() {
        return textSizeMini;
    }

    public float getRatioChangeView() {
        return ratioChangeView;
    }

    public float getMaxRatioTextWidth() {
        return maxRatioTextWidth;
    }

    public int getTextStyle() {
        return textStyle;
    }
}
