package com.example.dineojuet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.dineojuet.Domain.CategoryAdapter;
import com.example.dineojuet.Domain.CategoryDomain;
import com.example.dineojuet.Domain.MenuAdapter;
import com.example.dineojuet.Domain.MenuDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Array;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        recyclerViewCategory();
        recyleViewPopular();
        bottomNavigationbar();
    }

    private void bottomNavigationbar(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, CartActivity.class));
            }
        });
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Veg", "cat_1"));
        category.add(new CategoryDomain("Egg", "cat_2"));
        category.add(new CategoryDomain("Drink", "cat_3"));


        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyleViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<MenuDomain> foodlist=new ArrayList<>();
        foodlist.add(new MenuDomain("Veg Roll","vegroll","Cabbage,Mashed potato, Ketchup etc.",10));
        foodlist.add(new MenuDomain("Egg Roll","eggroll","Egg, Cabbage,Mashed potato, Ketchup etc.",15));
        foodlist.add(new MenuDomain("Samosa","samosa","Crispy refined wheat flour made into triangle cone,Mashed potato",5));
        foodlist.add(new MenuDomain("Paties","paties","Crispy refined wheat flour made into triangle cone,Mashed potato",15));

        adapter2=new MenuAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }


}