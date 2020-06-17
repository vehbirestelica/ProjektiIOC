package com.fiek.projektiioc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class NewInvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invoice);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(NewInvoiceActivity.this,Mainmenu.class);
        startActivity(intent);
    }
}