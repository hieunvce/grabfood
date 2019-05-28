package com.example.recyclerview.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.recyclerview.DTO.DataShopDTO;
import com.example.recyclerview.MainActivity;
import com.example.recyclerview.R;
import com.example.recyclerview.CustomAdapter.ShopAdapter;
import com.example.recyclerview.location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {
    ArrayList<DataShopDTO> arrayListRestaurants = new ArrayList<>();
    RecyclerView RecyclerViewHorizontal;
    RecyclerView RecyclerViewVertical ;
    TextView user_address;
    private static final int REQUEST_CODE= 0x01;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.home_page_fragment,parent,false);
        RecyclerViewHorizontal = view.findViewById(R.id.RView_RestaurantNearYou);
        user_address = view.findViewById(R.id.tv_user_address);
        //initRecylerViewHorizontal();
        //initRecylerViewVertical();
        GetResturantData("a");

        user_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), location.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra requestCode có trùng với REQUEST_CODE vừa dùng
        if(requestCode == REQUEST_CODE) {

            // resultCode được set bởi DetailActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if(resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                final String result = data.getStringExtra("address");

                // Sử dụng kết quả result bằng cách hiện Toast
                if (!result.equals(""))
                {
                    user_address.setText(result);
                }
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }





    public  void initRecylerViewHorizontal(){
        RecyclerViewHorizontal.setHasFixedSize(true);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, StaggeredGridLayoutManager.VERTICAL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerViewHorizontal.setLayoutManager(layoutManager);
        // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        // recyclerView.addItemDecoration(dividerItemDecoration);

        ShopAdapter shopAdapter = new ShopAdapter(arrayListRestaurants,getActivity().getApplicationContext());
        RecyclerViewHorizontal.setAdapter(shopAdapter);
    }


    public void GetResturantData(String data)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
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
                                arrayListRestaurants.add(new DataShopDTO(ImgRestaurant,NameRestaurant,AddressRestaurant,"123456789"));

                            }

                            Log.d("Test_array", String.valueOf(arrayListRestaurants.size()));
                            initRecylerViewHorizontal();
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
