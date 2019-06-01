package com.example.cefood.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cefood.API.OrderAPI.OrderDataFromAPI;
import com.example.cefood.Fragments.ViewOrderHistory;
import com.example.cefood.R;
import com.example.cefood.DTO.UserHistory;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHistoryCustomAdapter extends ArrayAdapter<OrderDataFromAPI> {

    private Context context;


    public UserHistoryCustomAdapter(Context context, int layoutID, List<OrderDataFromAPI> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    static class ViewHolder {
        TextView tvPrice;
        TextView tvDate;
        LinearLayout llParent;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item_listview_history, null,
                    false);
            holder = new ViewHolder();
            holder.tvPrice = convertView.findViewById(R.id.tv_hisory_totalpayment);
            holder.tvDate = convertView.findViewById(R.id.tv_history_date);
            holder.llParent = convertView.findViewById(R.id.item_employee_ll_parent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        OrderDataFromAPI userHistory = getItem(position);
        holder.tvPrice.setText(userHistory.getTotal().toString());

        String line = userHistory.getCreatedAt();
        String patternDate = "(\\d+)-(\\d+)-(\\d+)";
        String patternTime = "(\\d+):(\\d+):(\\d+)";

        // Create a Pattern object
        Pattern d = Pattern.compile(patternDate);
        Pattern t = Pattern.compile(patternTime);

        // Now create matcher object.
        Matcher m = d.matcher(line);
        Matcher n = t.matcher(line);
        String date = "";
        String time = "";
        if (m.find()) {
            date = m.group(0);
        }

        if (n.find()) {
            time = n.group(0);
        }

        Log.d("Parse datetime", "Parse date " + date + " " + time);

        holder.tvDate.setText(date + " " + time);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDataFromAPI orderDataFromAPI = getItem(position);
                Log.d("OnClick History", orderDataFromAPI.getItems().get(0).getName());
                Intent intent = new Intent(getContext(), ViewOrderHistory.class);
                intent.putExtra("historyItem", (Serializable) orderDataFromAPI);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}
