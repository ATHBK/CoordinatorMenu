package com.athbk.coordinatormenu.model;

/**
 * Created by ATHBK on 01,October,2018
 */
public class BaseMenuProp {

    private String title;
    private int resNormalSize;
    private int resBigSize;

    public BaseMenuProp(String title, int resNormalSize) {
        this.title = title;
        this.resNormalSize = resNormalSize;
    }

    public BaseMenuProp(String title, int resNormalSize, int resBigSize) {
        this.title = title;
        this.resNormalSize = resNormalSize;
        this.resBigSize = resBigSize;
    }

    public String getTitle() {
        return title;
    }

    public int getResNormalSize() {
        return resNormalSize;
    }

    public int getResBigSize() {
        return resBigSize;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResNormalSize(int resNormalSize) {
        this.resNormalSize = resNormalSize;
    }

    public void setResBigSize(int resBigSize) {
        this.resBigSize = resBigSize;
    }
}
