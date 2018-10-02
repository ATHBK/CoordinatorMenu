package com.example.athbk.coordinatormenu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.athbk.coordinatormenu.component.CoordinatorMenuAdapter;
import com.athbk.coordinatormenu.component.MenuViewHolder;
import com.athbk.coordinatormenu.model.BaseMenuProp;
import com.example.athbk.coordinatormenu.MenuStyle2Activity;
import com.example.athbk.coordinatormenu.R;

/**
 * Created by ATHBK on 07,September,2018
 */
public class Menu3Adapter extends CoordinatorMenuAdapter {

    Context context;

    public Menu3Adapter(Context context) {
        this.context = context;
    }

    @Override
    public MenuViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header_3, parent, false);
        return new HeaderVH(view);
    }

    @Override
    public MenuViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_content_rv, parent, false);
        return new ContentVH(view);
    }

    @Override
    public BaseMenuProp onBindMenuView(int position) {
        switch (position){
            case 0:
                return new BaseMenuProp("Menu 1", R.drawable.ic_home_topup, R.drawable.ic_home_topup_big);
            case 1:
                return new BaseMenuProp("Menu 2", R.drawable.ic_home_tranfer, R.drawable.ic_home_menu_tranfer_big);
            case 2:
                return new BaseMenuProp("Menu 3", R.drawable.ic_home_castout, R.drawable.ic_home_castout_big);
            case 3:
                return new BaseMenuProp("Menu 4", R.drawable.ic_home_scan, R.drawable.ic_home_scan_big);
        }
        return null;
    }

    @Override
    public int getCountMenu() {
        return 4;
    }


    @Override
    public void onItemMenuClickListener(int position) {
        Intent intent = new Intent(context, MenuStyle2Activity.class);
        context.startActivity(intent);
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
