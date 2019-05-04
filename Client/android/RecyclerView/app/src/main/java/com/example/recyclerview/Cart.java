package com.example.recyclerview;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recyclerview.CustomAdapter.DataCartAdapter;
import com.example.recyclerview.CustomAdapter.DataDishAdapter;
import com.example.recyclerview.DTO.DataDishDTO;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    ArrayList<DataDishDTO> dataDishes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent=getIntent();

        dataDishes= (ArrayList<DataDishDTO>) intent.getSerializableExtra("ArraydeDataDishes");
        initView();
    }

    public  void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RView_Cart);
        recyclerView.setHasFixedSize(true);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, StaggeredGridLayoutManager.VERTICAL);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        // recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        //arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));

        //GetResturantData("a");


        DataCartAdapter dataCartAdapter = new DataCartAdapter(dataDishes,getApplicationContext());
        recyclerView.setAdapter(dataCartAdapter);

    }
}
