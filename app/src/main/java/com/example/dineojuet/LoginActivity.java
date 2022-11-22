package com.example.dineojuet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button =(Button)findViewById(R.id.login);
        Button button1=(Button)findViewById(R.id.signingoogle);
        final TextView Signup = (TextView) findViewById(R.id.signupinstead);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText()
                Intent i= new Intent(view.getContext(),MainActivity.class);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i=new Intent(getApplicationContext(),);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i=new Intent(getApplicationContext(),)
            }
        });

    }
}