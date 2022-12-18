package com.example.dineojuet;

import static org.checkerframework.checker.units.qual.s.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.checkerframework.checker.units.qual.s;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {
    private  EditText OTP1, OTP2, OTP3, OTP4, OTP5, OTP6;
    private   EditText MobileNo;
    private   Button SendOtp;
    private   String backendOTP;
    private FirebaseAuth mAuth;
    private TextView resend;
    private Button verify;
    String phone;
    ProgressBar progressBar;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        mAuth = FirebaseAuth.getInstance();

        MobileNo = findViewById(R.id.Mobile);
        SendOtp = findViewById(R.id.SendOTP);
        OTP1 = findViewById(R.id.OTP1);
        OTP2 = findViewById(R.id.OTP2);
        OTP3 = findViewById(R.id.OTP3);
        OTP4 = findViewById(R.id.OTP4);
        OTP5 = findViewById(R.id.OTP5);
        OTP6 = findViewById(R.id.OTP6);
        verify = findViewById(R.id.OTP_verify);

        progressBar = findViewById(R.id.progress_sendotp);
        progressBar1 = findViewById(R.id.progress_verify);

        SendOtp.setOnClickListener(view -> {
            if (!MobileNo.getText().toString().trim().isEmpty()) {
                if ((MobileNo.getText().toString().trim()).length() == 10) {
                    phone = "+91" + MobileNo.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    SendOtp.setVisibility(View.INVISIBLE);
                    SendVerificationCode(phone);
                }
                else {
                    Toast.makeText(OtpVerification.this, "Please Enter Your Correct Mobile No.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(OtpVerification.this, "Please Enter Mobile No.", Toast.LENGTH_SHORT).show();
            }

        });

        verify.setOnClickListener(view -> {
            if (!OTP1.getText().toString().trim().isEmpty() && !OTP2.getText().toString().trim().isEmpty() && !OTP3.getText().toString().trim().isEmpty() && !OTP4.getText().toString().trim().isEmpty() && !OTP5.getText().toString().trim().isEmpty() && !OTP6.getText().toString().trim().isEmpty()) {
                verifyCode(OTP1.getText().toString() +
                        OTP2.getText().toString() +
                        OTP3.getText().toString() +
                        OTP4.getText().toString() +
                        OTP5.getText().toString() +
                        OTP6.getText().toString());
                progressBar1.setVisibility(View.VISIBLE);
                verify.setVisibility(View.INVISIBLE);

            }
            else {
                Toast.makeText(OtpVerification.this, "Please Enter All No.", Toast.LENGTH_SHORT).show();
            }

        });
        NoOTPMove();



	// Resend Textview Code 


        resend = findViewById(R.id.Resend_OTP);
        resend.setOnClickListener(view -> {
            SendVerificationCode(phone);

        });
    }






    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Intent i = new Intent(OtpVerification.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(OtpVerification.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }



    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            progressBar.setVisibility(View.GONE);
            SendOtp.setVisibility(View.VISIBLE);
            backendOTP = s;
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            progressBar.setVisibility(View.GONE);
            SendOtp.setVisibility(View.VISIBLE);

            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OtpVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            SendOtp.setVisibility(View.VISIBLE);
        }
    };


    public void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(backendOTP, code);
        signInWithCredential(credential);
    }

    private void SendVerificationCode(String phone) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void NoOTPMove() {
        OTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
                if(!toString().trim().isEmpty()){
                    OTP2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
                if(!toString().trim().isEmpty()){
                    OTP3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
                if(!toString().trim().isEmpty()){
                    OTP4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
                if(!toString().trim().isEmpty()){
                    OTP5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        OTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
                if(!toString().trim().isEmpty()){
                    OTP6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
