package com.fiek.projektiioc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegjistrohu;
    TextView txtKyqubtn;
    EditText rName,rEmail,rCompaniID,rPassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = findViewById(R.id.txtemri);
        rEmail = findViewById(R.id.txtemail);
        rCompaniID = findViewById(R.id.txtkompaniaid);
        rPassword = findViewById(R.id.txtpassword);
        txtKyqubtn = findViewById(R.id.txtKyqu);
        btnRegjistrohu =  findViewById(R.id.btnRegjistrohu);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        // Nese useri ekziston
//        if(fAuth.getCurrentUser() != null){
//
//        }

        btnRegjistrohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();

                if (email.isEmpty()){
                    rEmail.setError("Emaili duhet të vendoset");
                    return;
                }
                if (password.isEmpty()){
                    rPassword.setError("Passwordi duhet të vendoset");
                    return;
                }
                if (password.length()<6){
                    rPassword.setError("Passwordi duhet të permbaj së paku 6 karaktere");

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Regjistrimi
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        txtKyqubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}