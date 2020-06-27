package com.fiek.projektiioc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class LlogariaA extends AppCompatActivity {
    TextView llPerdoruesi,llRoli,llEmaili;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ImageView profileimage;
    Button changeprofileimg;
    String userId;
    StorageReference storageReference;
    int companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llogaria);

        llPerdoruesi = (TextView) findViewById(R.id.txtperdoruesill);
        llRoli = (TextView)findViewById(R.id.txtrolill);
        llEmaili=(TextView)findViewById(R.id.txtemailll);

        profileimage= (ImageView) findViewById(R.id.profileimg);
        changeprofileimg= (Button) findViewById(R.id.btnndryshoimg);

        storageReference = FirebaseStorage.getInstance().getReference();

        //load image into imageview
        StorageReference profileref = storageReference.child("users/"+fAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        profileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimage);
            }
        });

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                llPerdoruesi.setText(documentSnapshot.getString("name"));
                llEmaili.setText(documentSnapshot.getString("email"));
                companyId = Integer.parseInt(documentSnapshot.getString("companiID"));
                if(companyId>10000 && companyId <20000){
                    llRoli.setText("Menaxher");
                }
                else {
                    llRoli.setText("Punetore");
                }
            }
        });

        changeprofileimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN GALLERY

                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent , 1000 );
                //request code per gallery eshte 1000 , duhet te percaktohet i njejt me ate ne onactivityresults
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK);
            Uri imageUri = data.getData();
            //profileimage.setImageURI(imageUri);
            uploadimagetoFirebase(imageUri);
        }

    }

    private void uploadimagetoFirebase(Uri imageUri) {

        //upload profile image to firebase
        final StorageReference fileRef = storageReference.child("users/"+fAuth.getInstance().getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(LlogariaA.this, "Profili eshte perditesuar :) ", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileimage);
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LlogariaA.this, "Profili nuk eshte perditesuar !!! ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}