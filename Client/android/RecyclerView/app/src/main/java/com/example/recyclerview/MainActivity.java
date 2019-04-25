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
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataShop> arrayListRestaurants = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetResturantData("a");
        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="https://api.androidhive.info/json/menu.json";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        Log.d("ketqua",response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("log","bbbbbbb");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);

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

        //arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));

        //GetResturantData("a");
        Log.d("aaaaaaaaaaaaaaaaaaa", String.valueOf(arrayListRestaurants.size()));
        ShopAdapter shopAdapter = new ShopAdapter(arrayListRestaurants,getApplicationContext());
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
       // arrayList.add (new DataShop(R.drawable.aaaa,"dien thoai"));

        ShopAdapter shopAdapter = new ShopAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(shopAdapter);

    }
    public void GetResturantData(String data)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://api.androidhive.info/json/menu.json";
        StringRequest StringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ketqua",response);
                try {
                    Log.d("ket___qua",response);
                    //JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArrayData = new JSONArray(response);
                   // int total = Integer.parseInt(jsonObject.getString("total"));
                   // JSONArray jsonArrayData = jsonObject.getJSONArray("data");
//                    for (int i = 0 ; i< total ; i++)
//                    {
//                        JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
//                        String NameRestaurant = jsonObjectData.getString("name");
//                        String AddressRestaurant = jsonObjectData.getString("address");
//                        String ImgRestaurant = "https://api.androidhive.info/images/food/1.jpg";
//                          String Id= jsonObjectData.getString("_id");
//                        arrayListRestaurants.add(new DataShop(ImgRestaurant,NameRestaurant,AddressRestaurant));
//
//
//                    }



                    for (int i = 0 ; i< 8 ; i++)
                    {
                        JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
                        String NameRestaurant = jsonObjectData.getString("name");
                        Log.d("NameRestaurant",NameRestaurant);
                        String AddressRestaurant = jsonObjectData.getString("description");
                        String ImgRestaurant = jsonObjectData.getString("thumbnail");
                        arrayListRestaurants.add(new DataShop(ImgRestaurant,NameRestaurant,AddressRestaurant,"a"));

                    }
                    initView();
                    Log.d("Test_array", String.valueOf(arrayListRestaurants.size()));

                } catch (JSONException e) {
                    Log.d("loi_ne","bbbbbbbbbbbbbbbbbbbbb");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("loi_ne","ccccccccccccccccccccccccccc");

            }
        });
        Log.d("loi_ne","ddddddddddddd");
        requestQueue.add(StringRequest);

        Log.d("loi_ne","eeeeeeeeeeeeeee");
    }
}
