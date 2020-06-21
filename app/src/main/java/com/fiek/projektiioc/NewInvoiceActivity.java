package com.fiek.projektiioc;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewInvoiceActivity extends AppCompatActivity {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    ImageView selectedImage;
    TextView title,sum,comment;
    Spinner type;
    Button cameraBtn, sendBtn;
    String currentPhotoPath,storagephotoUri,strType,strTitle,strSum,strComment;
    File f;
    Uri contentUri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_invoice);

        selectedImage = findViewById(R.id.invimgview);
        cameraBtn = findViewById(R.id.btnInvTakePic);
        sendBtn = findViewById(R.id.btnInvSend);

        title = findViewById(R.id.txtInvTitle);
        sum = findViewById(R.id.txtInvSum);
        comment = findViewById(R.id.txtInvComment);

        type = findViewById(R.id.spInvReason);

        storageReference = FirebaseStorage.getInstance().getReference();

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f == null){
                    Toast.makeText(NewInvoiceActivity.this,"Take a invoice photo",Toast.LENGTH_SHORT).show();
                }
                else {
                uploadImageToFirebase(f.getName(), contentUri);
                    //Toast.makeText(NewInvoiceActivity.this,"Invoice sent",Toast.LENGTH_SHORT).show();

                    onBackPressed();
                }
            }
        });

    }

    private void createNewInvoice(String strTitle, String strType, String strSum, String strComment, String storagephotoUri) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference newInvoiceRef = db.collection("Invoices").document();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Invoice invoice = new Invoice();
        invoice.setInvTitle(strTitle);
        invoice.setInvType(strType);
        invoice.setInvSum(strSum);
        invoice.setInvComment(strComment);
        invoice.setInvId(newInvoiceRef.getId());
        invoice.setInvPhotoUri(storagephotoUri);
        invoice.setUserId(userId);

        newInvoiceRef.set(invoice).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(NewInvoiceActivity.this,"Invoice sent",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(NewInvoiceActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_PERM_CODE);
        }
        else
        {
            dispatchTakePictureIntent();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }
            else {
                Toast.makeText(this,"Camera Permission is Required to Use camera",Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                f = new File(currentPhotoPath);
                selectedImage.setImageURI(Uri.fromFile(f));
                Log.d("Uritag","Absolute Uri of image is " + Uri.fromFile(f));
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

                //uploadImageToFirebase(f.getName(), contentUri);


            }
        }
    }

    private void uploadImageToFirebase(String name, Uri contentUri) {

        final StorageReference image = storageReference.child("invoicies/" + name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("UploadedUriTag","onSuccess: Uploaded Image Uri is " + uri.toString());
                        storagephotoUri = uri.toString();
                        strType = type.getSelectedItem().toString();
                        strSum = sum.getText().toString();
                        strComment = comment.getText().toString();
                        strTitle = title.getText().toString();

                        createNewInvoice(strTitle,strType,strSum,strComment,storagephotoUri);
                    }
                });
               // Toast.makeText(NewInvoiceActivity.this,"Image Uplpaded.", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewInvoiceActivity.this,"Upload Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(NewInvoiceActivity.this,Mainmenu.class);
        startActivity(intent);
    }
}