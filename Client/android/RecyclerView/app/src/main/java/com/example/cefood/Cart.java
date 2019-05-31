package com.example.cefood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cefood.AppHelper.WorkWithSharePreferences;
import com.example.cefood.CustomAdapter.DataCartAdapter;
import com.example.cefood.Model.OrderDetail;

import java.util.ArrayList;

public class Cart extends AppCompatActivity implements OnItemClick {
    ArrayList<OrderDetail> productsInCart = new ArrayList<OrderDetail>();
    WorkWithSharePreferences workWithSharePreferences = new WorkWithSharePreferences();
    RecyclerView recyclerView;
    int totalPayment = 0;
    TextView txtTotalPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        productsInCart = workWithSharePreferences.getOrderDetailArrayList(sharedPreferences);
        for (OrderDetail orderDetail : productsInCart) {
            totalPayment += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
        }
        workWithSharePreferences.saveTotalPayment(totalPayment, sharedPreferences);
        this.txtTotalPayment = findViewById(R.id.txtTotalInCart);
        initView();
    }

    public void initView() {

        // RecyclerView Items On Cart
        this.recyclerView = (RecyclerView) findViewById(R.id.RView_Cart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DataCartAdapter dataCartAdapter = new DataCartAdapter(productsInCart, getApplicationContext(),this);
        recyclerView.setAdapter(dataCartAdapter);
        Button btnCheckout;
        btnCheckout = findViewById(R.id.btnCheckout);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        int totalPayment = workWithSharePreferences.getTotalPayment(sharedPreferences);
        txtTotalPayment.setText(Integer.toString(totalPayment));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
            }
        });
    }

    @Override
    public void onClick(int index, int sign) {
        // sign = 1 => +
        // sign = 0 => -
        if (sign == 1) {
            productsInCart.get(index).setQuantity(productsInCart.get(index).getQuantity() + 1);
            totalPayment+=productsInCart.get(index).getProduct().getPrice();
        } else if (sign ==0){
            productsInCart.get(index).setQuantity(productsInCart.get(index).getQuantity() - 1);
            if (productsInCart.get(index).getQuantity() <= 0){
                productsInCart.remove(index);
            } else {
                totalPayment+=productsInCart.get(index).getProduct().getPrice();
            }
        }
        Log.d("Cart","newTotalPayment "+totalPayment);
        txtTotalPayment.setText(Integer.toString(totalPayment));
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }
}
