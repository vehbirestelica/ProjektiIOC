package com.fiek.projektiioc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Mainmenu extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    TextView Perdoruesi,Roli,Emaili;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    int companiId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        toolbar=findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //headeri
        View header = navigationView.getHeaderView(0);
        Perdoruesi = header.findViewById(R.id.txtHPerdoruesi);
        Roli = header.findViewById(R.id.txtHRoli);
        Emaili = header.findViewById(R.id.txtHEmaili);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Perdoruesi.setText(documentSnapshot.getString("name"));
                Emaili.setText(documentSnapshot.getString("email"));
                companiId = Integer.parseInt(documentSnapshot.getString("companiID"));
                if(companiId>10000 && companiId <20000){
                    Roli.setText("Menaxher");
                }
                else {
                    Roli.setText("Punetore");
                }
            }
        });

        //action bar
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);  //enable hambureger menu
        actionBarDrawerToggle.syncState();

           //loaf default fragment

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerfragment,new MainFragment());
        fragmentTransaction.commit();




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //Close menu when we click on it
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                drawerLayout.closeDrawer(GravityCompat .START);
            }
        }, 200);

       // drawerLayout.closeDrawer(GravityCompat .START);

        //load-i i fragemnteve

        if(menuItem.getItemId() == R.id.m_kryefaqja){

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new MainFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_fakturat){

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new FakturatFragment());
            fragmentTransaction.commit();


        }
        if(menuItem.getItemId() == R.id.m_porosit){

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new BtnPorositeFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_exit){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Mainmenu.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }


        return true;
    }


    //shembull

}

