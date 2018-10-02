package com.example.athbk.coordinatormenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ATHBK on 02,October,2018
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        Button btnStyle1 = (Button) findViewById(R.id.btnStyle1);
        Button btnStyle2 = (Button) findViewById(R.id.btnStyle2);
        Button btnStyle3 = (Button) findViewById(R.id.btnStyle3);
        btnStyle1.setOnClickListener(this);
        btnStyle2.setOnClickListener(this);
        btnStyle3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStyle1:
                Intent intent1 = new Intent(this, MenuStyle1Activity.class);
                startActivity(intent1);
                break;
            case R.id.btnStyle2:
                Intent intent2 = new Intent(this, MenuStyle2Activity.class);
                startActivity(intent2);
                break;
            case R.id.btnStyle3:
                Intent intent3 = new Intent(this, MenuStyle3Activity.class);
                startActivity(intent3);
                break;
        }
    }
}
