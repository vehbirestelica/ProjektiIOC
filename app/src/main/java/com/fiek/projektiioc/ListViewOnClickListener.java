package com.fiek.projektiioc;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewOnClickListener extends AppCompatActivity {
    TextView sasia,porosia,derguesi,marresi;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_on_click_listener);

        sasia = (TextView)findViewById(R.id.sasia);
        porosia = (TextView)findViewById(R.id.porosia);
        derguesi = (TextView)findViewById(R.id.derguesi2);
        marresi = (TextView)findViewById(R.id.marresi2);


    }
}