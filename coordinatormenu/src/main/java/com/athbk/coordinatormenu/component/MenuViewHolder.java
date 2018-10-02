package com.athbk.coordinatormenu.component;

import android.view.View;

/**
 * Created by ATHBK on 07,September,2018
 */
public abstract class MenuViewHolder<T extends View> {

    private T view;

    public MenuViewHolder(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }
}
