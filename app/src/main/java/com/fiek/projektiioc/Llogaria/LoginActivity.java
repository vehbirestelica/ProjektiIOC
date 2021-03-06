package com.fiek.projektiioc.Llogaria;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fiek.projektiioc.Kryefaqja.Mainmenu;
import com.fiek.projektiioc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText lEmail,lPassword;
    Button btnLkyqu;
    TextView txtRegjistrohubtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    TextView forgetpassword;
    CheckBox rememberMeCheckBox;

    SharedPreferences mPrefereces;
    SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmail =  findViewById(R.id.txtLEmail);
        lPassword = findViewById(R.id.txtLPassword);
        txtRegjistrohubtn = findViewById(R.id.txtRegjistrohu);
        progressBar =  findViewById(R.id.LprogressBar);
        fAuth = FirebaseAuth.getInstance();
        btnLkyqu = findViewById(R.id.btnLkyqu);
        forgetpassword = findViewById(R.id.forgetpasswordtxt);
        rememberMeCheckBox = findViewById(R.id.checkRemeberMe);

        mPrefereces = getSharedPreferences("com.fiek.projektiioc", Context.MODE_PRIVATE);
        mEditor = mPrefereces.edit();

        fAuth.getInstance().signOut();
        checkSharedPreferences();
        // Nese useri ekziston
//        if(fAuth.getCurrentUser() != null){
//            Intent intent = new Intent(LoginActivity.this,Mainmenu.class);
//            startActivity(intent);
//        }
        btnLkyqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = lEmail.getText().toString().trim();
                String password = lPassword.getText().toString().trim();

                if (email.isEmpty()){
                    lEmail.setError("Emaili duhet të vendoset");
                    return;
                }
                if (password.isEmpty()){
                    lPassword.setError("Passwordi duhet të vendoset");
                    return;
                }
                if (password.length()<6){
                    lPassword.setError("Passwordi duhet të permbaj së paku 6 karaktere");
                    return;
                }
                if(rememberMeCheckBox.isChecked()){
                    mEditor.putString(getString(R.string.checkbox),"True");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.loginemail), email);
                    mEditor.commit();

                    mEditor.putString(getString(R.string.loginpassword), password);
                    mEditor.commit();
                }else{

                    mEditor.putString(getString(R.string.checkbox),"False");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.loginemail), "");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.loginpassword), "");
                    mEditor.commit();
                }

                progressBar.setVisibility(View.VISIBLE);

                //autentikimi
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this,"Jeni kyqur me Sukses",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, Mainmenu.class);
                            startActivity(intent);
                            progressBar.setVisibility(View.GONE);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Gabim në kyqje "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });




        txtRegjistrohubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Ndrysho Passwordin ?");
                passwordResetDialog.setMessage("Shkruaj Email-in tend per ta marre linkun e ndryshimit te passwordit.");

                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Ne rregull", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ///

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Linku për ta ndryshuar passwordin është dërguar në Email-in tënd", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Error!...Linku nuk është derguar."+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("Kthehu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                });
              passwordResetDialog.create().show();
            }
        });




    }

    private void checkSharedPreferences(){
        String checkbox = mPrefereces.getString(getString(R.string.checkbox),"False");
        String email = mPrefereces.getString(getString(R.string.loginemail),"");
        String password = mPrefereces.getString(getString(R.string.loginpassword),"");

        lEmail.setText(email);
        lPassword.setText(password);

        if(checkbox.equals("True")){
            rememberMeCheckBox.setChecked(true);
        }else{
            rememberMeCheckBox.setChecked(false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}