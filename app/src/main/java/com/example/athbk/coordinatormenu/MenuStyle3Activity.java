package com.example.athbk.coordinatormenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.athbk.coordinatormenu.CoordinatorMenuLayout;
import com.example.athbk.coordinatormenu.adapter.Menu3Adapter;
import com.example.athbk.coordinatormenu.adapter.MenuAdapter;

/**
 * Created by ATHBK on 02,October,2018
 */
public class MenuStyle3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_3);

        CoordinatorMenuLayout menuLayout = (CoordinatorMenuLayout) findViewById(R.id.menuLayout);

        Menu3Adapter adapter = new Menu3Adapter(this);
        menuLayout.setMenuAdapter(adapter);
    }
}
