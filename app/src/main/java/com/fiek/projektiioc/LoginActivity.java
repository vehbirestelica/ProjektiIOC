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

public class LoginActivity extends AppCompatActivity {

    EditText lEmail,lPassword;
    Button btnLkyqu;
    TextView txtRegjistrohubtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

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

                progressBar.setVisibility(View.VISIBLE);

                //autentikimi
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Jeni kyqur me Sukses",Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });




    }
}