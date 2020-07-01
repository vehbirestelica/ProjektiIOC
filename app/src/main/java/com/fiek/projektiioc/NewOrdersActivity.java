package com.fiek.projektiioc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class NewOrdersActivity extends AppCompatActivity {
    Spinner spinner, spinnerDerguesi;
    EditText porosia, lokacioni, dataLeshimit, derguesi, marresi;
    Button btnregjistro;
    NewOrders newOrders;
    DatabaseReference dbref;
    RadioButton paguar, paPaguar, neProces;
    TextView userID;
    Button BtnGoogleMapPick;
    RelativeLayout newOrderLayout;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
       userID = findViewById(R.id.userID);
        porosia = (EditText) findViewById(R.id.porosiaID);
        lokacioni = (EditText) findViewById(R.id.lokacioni);
        dataLeshimit = (EditText) findViewById(R.id.txtInvSum);
        marresi = (EditText) findViewById(R.id.marresi);
        spinner = findViewById(R.id.caktoSasineSpinner);
        spinnerDerguesi = findViewById(R.id.derguesi);
        paguar = findViewById(R.id.radio3);
        paPaguar = findViewById(R.id.radio2);
        neProces = findViewById(R.id.radio1);
        BtnGoogleMapPick = findViewById(R.id.Btngooglemap);
        btnregjistro = (Button) findViewById(R.id.btnInvSend);
        dbref = FirebaseDatabase.getInstance().getReference().child("addOrder");


        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        newOrders = new NewOrders();


////////////////////////////////loaction picker////////////////////////////////
        Places.initialize(getApplicationContext(),"AIzaSyBucPJkgWbAepULcsDFNjqSRCoXd3UVeQ4");
        BtnGoogleMapPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS);


                //gjenero intentin
                Intent intent  = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY , fieldList).build(NewOrdersActivity.this);
                startActivityForResult(intent,100);
            }
        });
//////////////////////////////////////////////////////////////////////////////////

        newOrderLayout = findViewById(R.id.newOrderLayout);

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
                showSnackbar();
//                Toast.makeText(NewOrdersActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showSnackbar(){
        Snackbar.make(newOrderLayout,"Porosia e Re u shtua.",Snackbar.LENGTH_INDEFINITE)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        onStart();
                    Snackbar snackbar = Snackbar.make(newOrderLayout,"Porosia u anulua!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorBlack))
                .setDuration(5000)
                .show();
    }
    @Override
    protected void onStart () {
        super.onStart();
        Query query = FirebaseDatabase.getInstance().getReference("addOrder").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error) {

            }
        });
    }


    ////////////////////////////////location picker on activity results////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode ==RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            lokacioni.setText(String.format(place.getName()));

        }
        else if(resultCode == AutocompleteActivity.RESULT_ERROR){
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
}