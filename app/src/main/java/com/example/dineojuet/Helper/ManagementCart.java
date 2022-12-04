package com.example.dineojuet.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.dineojuet.Domain.MenuDomain;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }
    public void insertFood(MenuDomain item){
        ArrayList<MenuDomain> listFood=getListCart();
        boolean existAlready=false;
        int n=0;
        for (int i=0;i< listFood.size();i++){
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }
        else{
            listFood.add(item);
        }
        tinyDB.putListObject("Cardlist",listFood);
        Toast.makeText(context,"Added To Cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<MenuDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
}
