package com.fiek.projektiioc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewOrdersActivity extends AppCompatActivity {
    Spinner spinner;
    EditText porosia, lokacioni, dataLeshimit, derguesi, marresi;
    Button btnregjistro;
    NewOrders newOrders;
    DatabaseReference dbref;
    RadioButton paguar, paPaguar, neProces;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        porosia = (EditText) findViewById(R.id.porosiaID);
        lokacioni = (EditText) findViewById(R.id.lokacioni);
        dataLeshimit = (EditText) findViewById(R.id.txtInvSum);
        marresi = (EditText) findViewById(R.id.marresi);
        derguesi = (EditText) findViewById(R.id.derguesi);
        spinner = findViewById(R.id.caktoSasineSpinner);
        paguar = findViewById(R.id.radio3);
        paPaguar = findViewById(R.id.radio2);
        neProces = findViewById(R.id.radio1);
        btnregjistro = (Button) findViewById(R.id.btnInvSend);
        dbref = FirebaseDatabase.getInstance().getReference().child("addOrder");

        newOrders = new NewOrders();

        btnregjistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                if(paguar.isChecked()){
                    newOrders.setStatusi(paguar.getText().toString());
                } else if (paPaguar.isChecked()){
                    newOrders.setStatusi(String.valueOf(paPaguar));
                } else {
                    newOrders.setStatusi(String.valueOf(neProces));
                }

                newOrders.setPorosia(porosia.getText().toString().trim());
                newOrders.setLokacioni(lokacioni.getText().toString().trim());
                newOrders.setSasia(spinner.getSelectedItem().toString());
                newOrders.setDerguesi(derguesi.getText().toString().trim());
                newOrders.setMarresi(marresi.getText().toString().trim());
                dbref.push().setValue(newOrders);
                Toast.makeText(NewOrdersActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT);
            }
        });
    }
}