package com.example.cefood.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.cefood.API.OrderAPI.OrderAPIInterface;
import com.example.cefood.API.OrderAPI.OrderForm;
import com.example.cefood.API.OrderAPI.OrderFormItem;
import com.example.cefood.AppHelper.WorkWithSharePreferences;
import com.example.cefood.CustomAdapter.DataCartAdapter;
import com.example.cefood.Model.OrderDetail;
import com.example.cefood.R;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartActivity extends AppCompatActivity implements OnItemClick {
    ArrayList<OrderDetail> productsInCart = new ArrayList<OrderDetail>();
    WorkWithSharePreferences workWithSharePreferences = new WorkWithSharePreferences();
    RecyclerView recyclerView;
    int totalPayment = 0;
    TextView txtTotalPayment;
    TextView user_address;
    private static final int REQUEST_CODE = 0x01;
    TextView comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        productsInCart = workWithSharePreferences.getOrderDetailArrayList(sharedPreferences);
        totalPayment = 0;
        for (OrderDetail orderDetail : productsInCart) {
            totalPayment += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
        }
        workWithSharePreferences.saveTotalPayment(totalPayment, sharedPreferences);
        this.txtTotalPayment = findViewById(R.id.txtTotalInCart);

        user_address = findViewById(R.id.tv_user_address);
        user_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, location.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        initView();
    }

    public void initView() {

        // RecyclerView Items On CartActivity
        this.recyclerView = (RecyclerView) findViewById(R.id.RView_Cart);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DataCartAdapter dataCartAdapter = new DataCartAdapter(productsInCart, getApplicationContext(), this);
        recyclerView.setAdapter(dataCartAdapter);
        Button btnCheckout;
        btnCheckout = findViewById(R.id.btnCheckout);
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
//        int totalPayment = workWithSharePreferences.getTotalPayment(sharedPreferences);
        txtTotalPayment.setText(Integer.toString(totalPayment));

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Do something
                for (OrderDetail orderDetail : productsInCart) {
                    Log.d("Checkout", "" + orderDetail.getProduct().getName() + ": " + orderDetail.getQuantity());
                }
                checkout();

                Dialog myDialog;
                myDialog = new Dialog(CartActivity.this);
                myDialog.setContentView(R.layout.dialog_rating);

                SmileRating smileRating = (SmileRating) myDialog.findViewById(R.id.smile_rating);
                comment = (TextView) myDialog.findViewById(R.id.txtComment);
                Button btnrating = (Button)myDialog.findViewById(R.id.btnrating);

                smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                    @Override
                    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                        // reselected is false when user selects different smiley that previously selected one
                        // true when the same smiley is selected.
                        // Except if it first time, then the value will be false.
                        switch (smiley) {
                            case SmileRating.BAD:

                                comment.setText("App cần cải thiện hơn");
                                break;
                            case SmileRating.GOOD:

                                comment.setText("App khá tốt");
                                break;
                            case SmileRating.GREAT:

                                comment.setText("App Rất tuyệt vời");
                                break;
                            case SmileRating.OKAY:

                                comment.setText("App tạm được");
                                break;
                            case SmileRating.TERRIBLE:

                                comment.setText("App khá tệ");
                                break;
                        }
                    }
                });

                myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Intent intent = new Intent(CartActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });

                btnrating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CartActivity.this, MainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });
                myDialog.show();
            }
        });
    }

    @Override
    public void onClick(int index, int sign) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        // sign = 1 => +
        // sign = 0 => -
        if (sign == 1) {
            productsInCart.get(index).setQuantity(productsInCart.get(index).getQuantity() + 1);
            //totalPayment+=productsInCart.get(index).getProduct().getPrice();
        } else if (sign == 0) {
            productsInCart.get(index).setQuantity(productsInCart.get(index).getQuantity() - 1);
            if (productsInCart.get(index).getQuantity() <= 0) {
                productsInCart.remove(index);
            } else {
                //totalPayment-=productsInCart.get(index).getProduct().getPrice();
            }
        }
        totalPayment = 0;
        for (OrderDetail orderDetail : productsInCart) {
            totalPayment += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
        }
        Log.d("CartActivity", "newTotalPayment " + totalPayment);
        txtTotalPayment.setText(Integer.toString(totalPayment));
        workWithSharePreferences.saveTotalPayment(totalPayment, sharedPreferences);
        workWithSharePreferences.saveOrderDetailArrayList(productsInCart, sharedPreferences);
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }

    void checkout() {
        String baseUrl = "https://grabfood-api.herokuapp.com";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OrderAPIInterface apiService = retrofit.create(OrderAPIInterface.class);

        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        String accessToken = sharedPreferences.getString("accessToken", null);
        if (accessToken != null) {
            ArrayList<OrderFormItem> items = new ArrayList<>();
            for (OrderDetail item : productsInCart) {
                OrderFormItem orderFormItem = new OrderFormItem();
                orderFormItem.setImg(item.getProduct().getImg());
                orderFormItem.setName(item.getProduct().getName());
                orderFormItem.setPrice(item.getProduct().getPrice());
                orderFormItem.setQuantity(item.getQuantity());
                items.add(orderFormItem);
            }
            OrderForm orderForm = new OrderForm();
            orderForm.setItems(items);
            orderForm.setTotal(totalPayment);

            Call<ResponseBody> call = apiService.addOrder(accessToken, orderForm);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("Add order", "Add order Response Code: " + response.code());
                    if (response.code() == 201) {
                        Log.d("Add order", "Add order success: " + response.code());
                        // TODO: Remove SharedPreferences
                        sharedPreferences.edit().remove("orderDetailArrayList").commit();

                    } else {
                        Log.d("Add order", "Add order Error.");
                        Toast.makeText(CartActivity.this, "Add order failed!", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Add order", "Add order failed: " + t.getMessage());
                    Toast.makeText(CartActivity.this, "Add order failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                final String result = data.getStringExtra("address");

                if (!result.equals("")) {
                    user_address.setText(result);
                }
            } else {
            }
        }
    }
}
