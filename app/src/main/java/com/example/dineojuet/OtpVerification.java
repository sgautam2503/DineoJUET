package com.example.dineojuet;
//
//import static android.service.controls.ControlsProviderService.TAG;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.google.firebase.FirebaseException;
//import com.google.firebase.FirebaseTooManyRequestsException;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//
//import org.checkerframework.checker.nullness.qual.NonNull;
//
//import java.util.concurrent.TimeUnit;
//
//public class OtpVerification extends AppCompatActivity {
//
//
//    @Override
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_otp_verification);
//        EditText phoneNumber = findViewById(R.id.Mobile);
//        Button button= (Button)findViewById(R.id.SendOTP);
//        EditText OTP = findViewById(R.id.OTP);
//        Button button1= (Button)findViewById(R.id.OTP_verify) ;
//        TextView Resend= (TextView)findViewById(R.id.Resend_OTP);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth mAuth = null;
//                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = null;
//                PhoneAuthOptions options =
//                        PhoneAuthOptions.newBuilder(mAuth)
//                                .setPhoneNumber(phoneNumber)       // Phone number to verify
//                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                                .setActivity(this)                 // Activity (for callback binding)
//                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                                .build();
//                PhoneAuthProvider.verifyPhoneNumber(options);
//            }
//        });
//        PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            @Override
//            public void onVerificationCompleted(PhoneAuthCredential credential) {
//                // This callback will be invoked in two situations:
//                // 1 - Instant verification. In some cases the phone number can be instantly
//                //     verified without needing to send or enter a verification code.
//                // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                //     detect the incoming verification SMS and perform verification without
//                //     user action.
//                Log.d(TAG, "onVerificationCompleted:" + credential);
//
//                signInWithPhoneAuthCredential(credential);
//            }
//
//
//            @Override
//            public void onVerificationFailed(FirebaseException e) {
//                 // This callback is invoked in an invalid request for verification is made,
//                // for instance if the the phone number format is not valid.
//                Log.w(TAG, "onVerificationFailed", e);
//
//                if (e instanceof FirebaseAuthInvalidCredentialsException) {
//                    // Invalid request
//                } else if (e instanceof FirebaseTooManyRequestsException) {
//                    // The SMS quota for the project has been exceeded
//                }
//
//                // Show a message and update the UI
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String verificationId,
//                                   PhoneAuthProvider.@NonNull ForceResendingToken token) {
//                // The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID.
//                Log.d(TAG, "onCodeSent:" + verificationId);
//
//                // Save verification ID and resending token so we can use them later
//                @NonNull String mVerificationId = verificationId;
//                PhoneAuthProvider.@NonNull ForceResendingToken mResendToken = token;
//            }
//        };
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//    }
//}
