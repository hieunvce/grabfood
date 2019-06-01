package com.example.cefood.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cefood.API.OrderAPI.OrderItemFromAPI;
import com.example.cefood.Activity.OnItemClick;
import com.example.cefood.Model.OrderDetail;
import com.example.cefood.Model.Product;
import com.example.cefood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryDetailAdapter extends RecyclerView.Adapter<HistoryDetailAdapter.ViewHolder> {
    ArrayList<OrderItemFromAPI> orderItemFromAPIS;
    Context context;

    public HistoryDetailAdapter(ArrayList<OrderItemFromAPI> orderItemFromAPIS, Context applicationContext) {
        this.orderItemFromAPIS = orderItemFromAPIS;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_history_order_detail, viewGroup, false);
        return new HistoryDetailAdapter.ViewHolder(itemView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgImg;
        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductTotal;
        TextView tvQuantity;

        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            imgImg = (ImageView) itemView.findViewById(R.id.img_his_detail_img);
            tvProductName = (TextView) itemView.findViewById(R.id.txt_his_detail_ProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.txt_his_detail_ProductPrice);
            tvProductTotal = (TextView) itemView.findViewById(R.id.txt_his_detail_ProductTotal);
            tvQuantity = (TextView) itemView.findViewById(R.id.txt_his_detail_Quantity);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDetailAdapter.ViewHolder viewHolder, final int i) {

        OrderItemFromAPI item = orderItemFromAPIS.get(i);

        viewHolder.tvProductName.setText(item.getName());
        viewHolder.tvProductPrice.setText(item.getPrice().toString());
        int total = item.getPrice()*item.getQuantity();
        viewHolder.tvProductTotal.setText(Integer.toString(total));
        viewHolder.tvQuantity.setText(item.getQuantity().toString());

        Picasso.get()
                .load(item.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90, 90)
                .into(viewHolder.imgImg);
    }

    @Override
    public int getItemCount() {
        if (orderItemFromAPIS.size()>=0){
            return orderItemFromAPIS.size();
        } else {
            return 0;
        }
    }
}
