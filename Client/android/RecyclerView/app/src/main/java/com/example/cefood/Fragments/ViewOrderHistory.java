package com.example.cefood.Fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.cefood.API.OrderAPI.OrderDataFromAPI;
import com.example.cefood.API.OrderAPI.OrderItemFromAPI;
import com.example.cefood.CustomAdapter.DataCartAdapter;
import com.example.cefood.CustomAdapter.HistoryDetailAdapter;
import com.example.cefood.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewOrderHistory extends AppCompatActivity {

    OrderDataFromAPI historyItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_history);
        historyItem = (OrderDataFromAPI) getIntent().getSerializableExtra("historyItem");
        bindView();
    }

    private void bindView() {
        RecyclerView rvDetailItem = findViewById(R.id.his_detail_item);
        TextView tvTotal = findViewById(R.id.tvToTal);
        ArrayList<OrderItemFromAPI> orderItemFromAPI = new ArrayList<OrderItemFromAPI>(historyItem.getItems());
        rvDetailItem.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDetailItem.setLayoutManager(layoutManager);
        HistoryDetailAdapter historyDetailAdapter = new HistoryDetailAdapter(orderItemFromAPI, getApplicationContext());
        rvDetailItem.setAdapter(historyDetailAdapter);

        tvTotal.setText(historyItem.getTotal().toString());
    }
}
