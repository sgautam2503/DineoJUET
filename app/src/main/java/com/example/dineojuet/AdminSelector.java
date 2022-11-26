package com.example.dineojuet;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class AdminSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_selector);
        Window window=this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button button =findViewById(R.id.Student);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

    }
    public void openLoginActivity(){
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
}