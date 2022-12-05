package com.example.dineojuet.Domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dineojuet.Helper.ManagementCart;
import com.example.dineojuet.Interface.ChangeNumberItemListner;
import com.example.dineojuet.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{
    private ArrayList<MenuDomain> menuDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemListner changeNumberItemListner;

    public CartListAdapter(ArrayList<MenuDomain> menuDomains, Context context, ChangeNumberItemListner changeNumberItemListner) {
        this.menuDomains = menuDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemListner = changeNumberItemListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate=LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_viewholder, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
    holder.title.setText(menuDomains.get(position).getTitle());
    holder.feeEachItems.setText(String.valueOf(menuDomains.get(position).getFee()));
    holder.totalEachItem.setText(String.valueOf(Math.round(menuDomains.get(position).getNumberInCart()*menuDomains.get(position).getFee()*100/100)));
    holder.num.setText(String.valueOf(menuDomains.get(position).getNumberInCart()));


    int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(menuDomains.get(position).getPic() ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return menuDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItems;
        ImageView pic;
        TextView totalEachItem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            feeEachItems=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.NumberItemTxt);
        }
    }
}
