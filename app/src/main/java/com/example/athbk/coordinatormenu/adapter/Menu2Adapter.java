package com.example.athbk.coordinatormenu.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.athbk.coordinatormenu.component.CoordinatorMenuAdapter;
import com.athbk.coordinatormenu.component.MenuViewHolder;
import com.athbk.coordinatormenu.model.BaseMenuProp;
import com.example.athbk.coordinatormenu.R;

/**
 * Created by ATHBK on 02,October,2018
 */
public class Menu2Adapter  extends CoordinatorMenuAdapter {

    Context context;

    public Menu2Adapter(Context context) {
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header_2, parent, false);
        return new Menu2Adapter.HeaderVH(view);
    }

    @Override
    public MenuViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_rv, parent, false);
        return new Menu2Adapter.ContentVH(view);
    }

    @Override
    public BaseMenuProp onBindMenuView(int position) {
        switch (position){
            case 0:
                return new BaseMenuProp("Menu 1", R.drawable.ic_home_topup, R.drawable.ic_home_recharge_big_white);
            case 1:
                return new BaseMenuProp("Menu 2", R.drawable.ic_home_tranfer, R.drawable.ic_home_transfer_big_white);
            case 2:
                return new BaseMenuProp("Menu 3", R.drawable.ic_home_castout, R.drawable.ic_home_castout_big_white);
            case 3:
                return new BaseMenuProp("Menu 4", R.drawable.ic_home_scan, R.drawable.ic_home_scan_big_white);
        }
        return null;
    }

    @Override
    public int getCountMenu() {
        return 4;
    }


    @Override
    public void onItemMenuClickListener(int position) {
        Log.e("TAG", "Menu : " + position + " click");
    }

    class HeaderVH extends MenuViewHolder {

        public HeaderVH(View view) {
            super(view);
        }
    }

    class ContentVH extends MenuViewHolder {
        RecyclerView recyclerView;

        private RVAdapter adapter;

        public ContentVH(View view) {
            super(view);

            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            adapter = new RVAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
        }
    }

}

