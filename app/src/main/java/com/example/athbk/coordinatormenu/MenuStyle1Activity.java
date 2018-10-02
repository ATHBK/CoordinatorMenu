package com.example.athbk.coordinatormenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.athbk.coordinatormenu.CoordinatorMenuLayout;
import com.example.athbk.coordinatormenu.adapter.MenuAdapter;

public class MenuStyle1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoordinatorMenuLayout menuLayout = (CoordinatorMenuLayout) findViewById(R.id.menuLayout);

        MenuAdapter adapter = new MenuAdapter(this);
        menuLayout.setMenuAdapter(adapter);

    }
}
