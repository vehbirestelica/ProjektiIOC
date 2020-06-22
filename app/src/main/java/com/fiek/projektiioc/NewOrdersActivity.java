package com.fiek.projektiioc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NewOrdersActivity extends AppCompatActivity {
    Spinner spinner;
    RadioGroup radioGroup;
    StorageReference storageReference;
    TextView porosia, lokacioni, dataLeshimit, derguesi, marresi;
    Button btnregjistro;
    private static final String TAG = "OrdersActivity";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        storageReference = FirebaseStorage.getInstance().getReference().child("addOrder");
        porosia = (TextView) findViewById(R.id.porosiaID);
        lokacioni = (TextView) findViewById(R.id.lokacioni);
        dataLeshimit = (TextView) findViewById(R.id.txtInvSum);
        marresi = (TextView) findViewById(R.id.marresi);
        derguesi = (TextView) findViewById(R.id.derguesi);
        radioGroup = findViewById(R.id.radioGroup);
        spinner = findViewById(R.id.caktoSasineSpinner);
        btnregjistro = (Button) findViewById(R.id.btnInvSend);

        btnregjistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

            }
        });

        String order = porosia.getText().toString();
        String location = lokacioni.getText().toString();
        String receiver = marresi.getText().toString();
        String sender = derguesi.getText().toString();





    }
}