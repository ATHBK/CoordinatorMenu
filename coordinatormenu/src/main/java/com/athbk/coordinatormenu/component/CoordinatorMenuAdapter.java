package com.athbk.coordinatormenu.component;

import android.view.View;
import android.view.ViewGroup;

import com.athbk.coordinatormenu.model.BaseMenuProp;

/**
 * Created by ATHBK on 07,September,2018
 */
public abstract class CoordinatorMenuAdapter {

    public abstract MenuViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    public abstract MenuViewHolder onCreateContentViewHolder(ViewGroup parent);

    public abstract BaseMenuProp onBindMenuView(int position);

    public abstract void onItemMenuClickListener(int position);

    public abstract int getCountMenu();

    public View getHeaderView(ViewGroup parent){
        if (onCreateHeaderViewHolder(parent) == null) return null;
        return onCreateHeaderViewHolder(parent).getView();
    }

    public View getContentView(ViewGroup parent){
        if (onCreateContentViewHolder(parent) == null) return null;
        return onCreateContentViewHolder(parent).getView();
    }
}
