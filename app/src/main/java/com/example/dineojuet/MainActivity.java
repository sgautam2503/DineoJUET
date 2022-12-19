package com.example.dineojuet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dineojuet.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        progressDialog=new ProgressDialog(this);

        binding.signup.setOnClickListener(view -> {
            String firstname=binding.firstname.getText().toString().trim();
            String lastname=binding.lastname.getText().toString().trim();
            String email=binding.email.getText().toString().trim();
            String enrol=binding.enrol.getText().toString().trim();
            String password=binding.password.getText().toString();

            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener(authResult -> {
                        SendVerificationEmail();
                        if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
                            startActivity(new Intent(MainActivity.this, OtpVerification.class));
                            Toast.makeText(MainActivity.this, "Your Account has been Created.", Toast.LENGTH_SHORT).show();

                            firebaseFirestore.collection("User")
                                    .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                    .set(new UserModel(firstname, lastname, email, enrol, password));
                            Toast.makeText(MainActivity.this, "Your Account has been Created.", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(e -> {
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    });
        }

        );

        binding.signininstead.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

    }

    private void SendVerificationEmail() {
        Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                .sendEmailVerification()
                .addOnSuccessListener(unused -> {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Verification link Sent to Your Email id",Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                   Toast.makeText(MainActivity.this,"Verification link not sent",Toast.LENGTH_SHORT).show();
                });
    }


}