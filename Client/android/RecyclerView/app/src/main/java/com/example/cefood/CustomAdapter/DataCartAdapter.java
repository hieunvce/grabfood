package com.example.cefood.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.cefood.Model.OrderDetail;
import com.example.cefood.Model.Product;
import com.example.cefood.OnItemClick;
import com.squareup.picasso.Picasso;
import com.example.cefood.R;

import java.util.ArrayList;

public class DataCartAdapter extends RecyclerView.Adapter<DataCartAdapter.ViewHolder> {
    ArrayList<OrderDetail> productsInCart;
    Context context;
    private OnItemClick mCallback;

    public DataCartAdapter(ArrayList<OrderDetail> productsInCart, Context context,OnItemClick listener) {
        this.productsInCart = productsInCart;
        this.context = context;
        this.mCallback = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_cart, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return productsInCart.size();
    }

    public void RemoveItem(int position) {
        productsInCart.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProductInCartImg;
        TextView txtInCartProductName;
        TextView txtInCartProductPrice;
        ImageView imgPlus;
        ImageView imgMinus;
        TextView txtInCartProductTotal;
        TextView txtInCartQuantity;

        public ViewHolder(@NonNull final View itemView) {

            super(itemView);

            imgProductInCartImg = (ImageView) itemView.findViewById(R.id.imgInCartProductImg);
            txtInCartProductName = (TextView) itemView.findViewById(R.id.txtInCartProductName);
            txtInCartProductPrice = (TextView) itemView.findViewById(R.id.txtInCartProductPrice);
            txtInCartProductTotal = (TextView) itemView.findViewById(R.id.txtInCartProductTotal);

            imgPlus = (ImageView) itemView.findViewById(R.id.imgPlusCart);
            imgMinus = (ImageView) itemView.findViewById(R.id.imgMinusCart);
            txtInCartQuantity = (TextView) itemView.findViewById(R.id.txtInCartQuantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context = itemView.getContext();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Product orderedProduct = productsInCart.get(i).getProduct();
        int orderedProductQuantity = productsInCart.get(i).getQuantity();

        viewHolder.txtInCartProductName.setText(orderedProduct.getName());
        viewHolder.txtInCartProductPrice.setText(orderedProduct.getPrice().toString());
        viewHolder.txtInCartQuantity.setText(String.valueOf(productsInCart.get(i).getQuantity()));
        int inCartProductTotal = orderedProduct.getPrice()*orderedProductQuantity;
        viewHolder.txtInCartProductTotal.setText(Integer.toString(inCartProductTotal));

        Picasso.get()
                .load(orderedProduct.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90, 90)
                .into(viewHolder.imgProductInCartImg);

        viewHolder.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClick(i,1);
            }
        });

        viewHolder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClick(i,0);
            }
        });
    }
}
