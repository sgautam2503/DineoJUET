package com.example.dineojuet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerification extends AppCompatActivity {
    private  EditText OTP1, OTP2, OTP3, OTP4, OTP5, OTP6;
  private   EditText MobileNo;
  private   Button SendOtp;
  private   Button Verify;
  private   String backendOTP;
  private FirebaseAuth mAuth;
  String phone;
    ProgressBar progressBar;
    ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        mAuth = FirebaseAuth.getInstance();

        MobileNo = findViewById(R.id.Mobile);
        SendOtp = findViewById(R.id.SendOTP);
        OTP1 = findViewById(R.id.OTP1);
        OTP2 = findViewById(R.id.OTP2);
        OTP3 = findViewById(R.id.OTP3);
        OTP4 = findViewById(R.id.OTP4);
        OTP5 = findViewById(R.id.OTP5);
        OTP6 = findViewById(R.id.OTP6);
        Verify = findViewById(R.id.OTP_verify);

         progressBar = findViewById(R.id.progress_sendotp);
         progressBar1 = findViewById(R.id.progress_verify);

        SendOtp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                if (!MobileNo.getText().toString().trim().isEmpty()) {
                    if ((MobileNo.getText().toString().trim()).length() == 10) {
                        phone = "+91" + MobileNo.getText().toString();
                        progressBar.setVisibility(View.VISIBLE);
                        SendOtp.setVisibility(View.INVISIBLE);
                        SendVerificationCode(phone);
//


                    } else {
                        Toast.makeText(OtpVerification.this, "Please Enter Correct No.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(OtpVerification.this, "Please Enter Mobile No.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Verify.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                if (!OTP1.getText().toString().trim().isEmpty() && !OTP2.getText().toString().trim().isEmpty() && !OTP3.getText().toString().trim().isEmpty() && !OTP4.getText().toString().trim().isEmpty() && !OTP5.getText().toString().trim().isEmpty() && !OTP6.getText().toString().trim().isEmpty()) {
                    verifyCode(OTP1.getText().toString() +
                            OTP2.getText().toString() +
                            OTP3.getText().toString() +
                            OTP4.getText().toString() +
                            OTP5.getText().toString() +
                            OTP6.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                    SendOtp.setVisibility(View.INVISIBLE);

                }
                else {
                    Toast.makeText(OtpVerification.this, "Please Enter All No.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



    private void signInWithCredential(PhoneAuthCredential credential) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent i = new Intent(OtpVerification.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(OtpVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }



        PhoneAuthProvider.OnVerificationStateChangedCallbacks
                mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
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

}


        // NoOTPMove()


//    private void NoOTPMove() {
//        OTP1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int Count, int After) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int Before, int Count) {
//                if(!s.tostring().trim().isempty)){
//                    OTP2.requestFocus();
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//   }