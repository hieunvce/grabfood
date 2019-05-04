package com.example.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerview.CustomAdapter.DataDishAdapter;
import com.example.recyclerview.DTO.DataDishDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class OrderFood extends AppCompatActivity {
    ArrayList<DataDishDTO> arrayListDish = new ArrayList<>();
    ArrayList<DataDishDTO> dataDishesTransferToCart = new ArrayList<>();
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        Intent intent = getIntent();
        String data= intent.getStringExtra("id");

        Log.d("dulieu",data);
        //initView();
        GetResturantData("a");
        Button GoToCart = (Button)findViewById(R.id.btnGoToCart);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("custom-message"));

        GoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderFood.this, Cart.class);
                intent.putExtra("ArraydeDataDishes",dataDishesTransferToCart);


                startActivity(intent);

            }
        });
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent



            dataDishesTransferToCart.add((DataDishDTO) intent.getSerializableExtra("dataDishesTransferToCart"));
            Log.d("OrderFood",(dataDishesTransferToCart.get(0).getSoLuong()));

        }
    };
    public  void initView(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.RView_OrderFood);
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

        DataDishAdapter dataDishAdapter = new DataDishAdapter(arrayListDish,getApplicationContext());

        recyclerView.setAdapter(dataDishAdapter);

    }

    public void GetResturantData(String data)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(OrderFood.this);
        String url = "https://api.androidhive.info/json/menu.json";
        StringRequest StringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Orderfood",response);
                        try {
                            Log.d("Orderfood",response);
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
                                Log.d("Orderfood",NameRestaurant);

                                String ImgRestaurant = jsonObjectData.getString("thumbnail");
                                String Price = jsonObjectData.getString("price");
                                arrayListDish.add(new DataDishDTO(ImgRestaurant,NameRestaurant,Price,"0"));

                            }

                            Log.d("Orderfood", String.valueOf(arrayListDish.size()));
                            initView();
                        } catch (JSONException e) {
                            Log.d("Orderfood","bbbbbbbbbbbbbbbbbbbbb");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Orderfood","ccccccccccccccccccccccccccc");

            }
        });
        Log.d("Orderfood","ddddddddddddd");
        requestQueue.add(StringRequest);

        Log.d("Orderfood","eeeeeeeeeeeeeee");
    }


}
