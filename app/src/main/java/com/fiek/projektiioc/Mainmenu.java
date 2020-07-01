package com.fiek.projektiioc;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class Mainmenu extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    ImageView profileimg;

    TextView Perdoruesi,Roli,Emaili;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    StorageReference storageReference;
    String userId;
    int companyId;

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
        profileimg = header.findViewById(R.id.imgProfili);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();

        //load image into imageview
        StorageReference profileref = storageReference.child("users/"+fAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimg);
            }
        });

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Perdoruesi.setText(documentSnapshot.getString("name"));
                Emaili.setText(documentSnapshot.getString("email"));
                companyId = Integer.parseInt(documentSnapshot.getString("companiID"));
                if(companyId>10000 && companyId <20000){
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
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu.this);
        // Set the message show for the Alert time
        builder.setMessage("A deshironi te largoheni nga IOC?");
        builder.setTitle("Paralajmerim!");
        builder.setCancelable(false);
        builder.setPositiveButton("Po",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        Intent intent = new Intent(Mainmenu.this,LoginActivity.class);
                        startActivity(intent);

                    }
                });

        builder.setNegativeButton("Jo",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        //Close menu when we click on it
//       new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                drawerLayout.closeDrawer(GravityCompat .START);
//            }
//        }, 200);


        drawerLayout.closeDrawer(GravityCompat .START);

        //load-i i fragemnteve

        if(menuItem.getItemId() == R.id.m_kryefaqja){

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new MainFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_fakturat){

//            Intent intentfaktura = new Intent(Mainmenu.this,NewInvoiceActivity.class);
//            startActivity(intentfaktura);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            FakturatFragment fragment = new FakturatFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("Company Id", companyId);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.containerfragment,fragment);
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_porosit){

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new BtnPorositeFragment());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_raporte){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containerfragment,new Raportet());
            fragmentTransaction.commit();

        }
        if(menuItem.getItemId() == R.id.m_llogaria){

            Intent intent = new Intent(Mainmenu.this,LlogariaA.class);
            startActivity(intent);


        }
        if(menuItem.getItemId() == R.id.m_exit){

            AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu.this);
            // Set the message show for the Alert time
            builder.setMessage("A deshironi te largoheni nga IOC?");
            builder.setTitle("Paralajmerim!");
            builder.setCancelable(false);
            builder.setPositiveButton("Po",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which)
                        {
                            Intent intent = new Intent(Mainmenu.this,LoginActivity.class);
                            startActivity(intent);

                        }
                    });

            builder.setNegativeButton("Jo",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which)
                        {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }


        return true;
    }


    //shembull

}

