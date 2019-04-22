package com.example.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initView1();
    }
    public  void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RView_RestaurantNearYou);
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
        ArrayList<DataShop> arrayList = new ArrayList<>();
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.day,"giay"));
        arrayList.add (new DataShop(R.drawable.day,"giay"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        ShopAdapter shopAdapter = new ShopAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(shopAdapter);











    }

    public  void initView1(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RView_Discover);
        recyclerView.setHasFixedSize(true);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, StaggeredGridLayoutManager.VERTICAL);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        // recyclerView.addItemDecoration(dividerItemDecoration);
        ArrayList<DataShop> arrayList = new ArrayList<>();
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.day,"giay"));
        arrayList.add (new DataShop(R.drawable.day,"giay"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.b,"tron"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));
        ShopAdapter shopAdapter = new ShopAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(shopAdapter);

    }
}
