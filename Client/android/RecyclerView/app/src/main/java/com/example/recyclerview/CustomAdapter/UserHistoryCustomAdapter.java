package com.example.recyclerview.CustomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerview.R;
import com.example.recyclerview.DTO.UserHistory;
import com.example.recyclerview.Server.UserNetworkProvider;

import java.util.List;

public class UserHistoryCustomAdapter extends ArrayAdapter<UserHistory> {
    private Context context;
    public UserHistoryCustomAdapter(Context context, int layoutID, List<UserHistory> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }


    static class ViewHolder{
        TextView tvFullName;
        TextView tvFood;
        TextView tvPrice;
        TextView tvDate;
        LinearLayout llParent;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item_listview_history, null,
                    false);
            holder = new ViewHolder();
            holder.tvFullName =  convertView.findViewById(R.id.tv_his_user);
            holder.tvFood =  convertView.findViewById(R.id.tv_his_food);
            holder.tvPrice =  convertView.findViewById(R.id.tv_his_price);
            holder.tvDate =  convertView.findViewById(R.id.tv_his_date);
            holder.llParent =  convertView.findViewById(R.id.item_employee_ll_parent);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        UserHistory userHistory = getItem(position);
        holder.tvFullName.setText(userHistory.getFullName());
        holder.tvFood.setText(userHistory.getFood());
        holder.tvPrice.setText(userHistory.getPrice());
        holder.tvDate.setText(userHistory.getDate());
        return convertView;
    }

}
