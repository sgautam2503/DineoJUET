package com.example.dineojuet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dineojuet.Domain.MenuDomain;
import com.example.dineojuet.Helper.ManagementCart;

public class ShowDetailsActivity extends AppCompatActivity {
private TextView addToCartBtn;
private TextView titleTxt, feeTxt, descriptionTxt;
private MenuDomain object;
ImageView picFood;
private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        managementCart=new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object= (MenuDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .load(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("Rs."+object.getFee());
        descriptionTxt.setText(object.getDescription());
    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.fee);
        descriptionTxt=findViewById(R.id.descriptionTxt);
    }
}