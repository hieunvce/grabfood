package com.example.cefood;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.cefood.CustomAdapter.DataCartAdapter;
import com.example.cefood.DTO.Product;
import com.example.cefood.Model.OrderDetail;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ArrayList<OrderDetail> productsInCart = new ArrayList<OrderDetail>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        productsInCart = (ArrayList<OrderDetail>) intent.getSerializableExtra("productsInCart");
        initView();
    }

    public void initView() {

        // RecyclerView Items On Cart
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RView_Cart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DataCartAdapter dataCartAdapter = new DataCartAdapter(productsInCart, getApplicationContext());
        recyclerView.setAdapter(dataCartAdapter);

        // Total money to checkout
        TextView txttotalInCart;
        txttotalInCart = findViewById(R.id.txtTotalInCart);
        Button btnCheckout;
        btnCheckout = findViewById(R.id.btnCheckout);

        int totalInCart = 0;

        // TODO: kkkk
        for (OrderDetail item : productsInCart) {

        }

        // TODO: Cart -> Product -> Restaurant => All Cart Data Is Clear => Solution: SharedPreferences
        // TODO: Khong Update Total trong moi Item khi nhan + , -
        //

    }
}
