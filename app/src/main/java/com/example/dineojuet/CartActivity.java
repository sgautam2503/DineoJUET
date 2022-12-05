package com.example.dineojuet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dineojuet.Domain.CartListAdapter;
import com.example.dineojuet.Helper.ManagementCart;
import com.example.dineojuet.Interface.ChangeNumberItemListner;

public class CartActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;

TextView totalFee, emptyTxt;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart=new ManagementCart(this);

        initView();
        initList();
        CalculateCart();

    }




    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView);
        totalFee=findViewById(R.id.totalFee);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView3);
        recyclerViewList=findViewById(R.id.cartView);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListner(){
            @Override
            public void changed() {
                CalculateCart();
            }
        });


        recyclerViewList.setAdapter((adapter));
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);

        }
        else{
            emptyTxt.setVisibility(View.GONE);

        }
    }
        private void CalculateCart() {
            double total = Math.round(managementCart.getTotalFee() * 100 / 100);
            totalFee.setText("Rs." + total);
        }



    }
