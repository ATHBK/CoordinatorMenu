package com.athbk.coordinatormenu.utils;

/**
 * Created by ATHBK on 24,August,2018
 */
public interface ICallbackScrollListener {

    void onScrollChange(int scrollY);

    void onStartScroll(int scrollY);

    void onCancelScroll(int scrollY);
}
