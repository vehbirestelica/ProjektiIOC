package com.fiek.projektiioc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewOrdersActivity extends AppCompatActivity {
    Spinner spinner, spinnerDerguesi;
    EditText porosia, lokacioni, dataLeshimit, derguesi, marresi;
    Button btnregjistro;
    NewOrders newOrders;
    DatabaseReference dbref;
    RadioButton paguar, paPaguar, neProces;
    TextView userID;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        userID = (TextView) findViewById(R.id.userID);
        porosia = (EditText) findViewById(R.id.porosiaID);
        lokacioni = (EditText) findViewById(R.id.lokacioni);
        dataLeshimit = (EditText) findViewById(R.id.txtInvSum);
        marresi = (EditText) findViewById(R.id.marresi);
        spinner = findViewById(R.id.caktoSasineSpinner);
        spinnerDerguesi = findViewById(R.id.derguesi);
        paguar = findViewById(R.id.radio3);
        paPaguar = findViewById(R.id.radio2);
        neProces = findViewById(R.id.radio1);
        btnregjistro = (Button) findViewById(R.id.btnInvSend);
        dbref = FirebaseDatabase.getInstance().getReference().child("addOrder");

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        newOrders = new NewOrders();

        btnregjistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                if(paguar.isChecked()){
                    newOrders.setStatusi(paguar.getText().toString());
                } else if (paPaguar.isChecked()){
                    newOrders.setStatusi(paPaguar.getText().toString());
                } else {
                    newOrders.setStatusi(neProces.getText().toString());
                }

//                String dataLeshimit = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                newOrders.setPorosia(porosia.getText().toString().trim());
                newOrders.setLokacioni(lokacioni.getText().toString().trim());
                newOrders.setSasia(spinner.getSelectedItem().toString());
                newOrders.setDerguesi(spinnerDerguesi.getSelectedItem().toString().trim());
                newOrders.setMarresi(marresi.getText().toString().trim());
                newOrders.setUserID(currentUser.getUid());
                newOrders.setDataLeshimit(dataLeshimit.getText().toString());
                dbref.push().setValue(newOrders);
                Toast.makeText(NewOrdersActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}