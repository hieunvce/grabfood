package com.example.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHoler> {
    ArrayList<DataShop> dataShops;
    Context context;

    public ShopAdapter(ArrayList<DataShop> dataShops, Context context) {
        this.dataShops = dataShops;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row,viewGroup,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.txtName.setText(dataShops.get(i).getTen());
        viewHoler.imgHinh.setImageResource(dataShops.get(i).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return dataShops.size();
    }
    public void RemoveItem(int position)
    {
        dataShops.remove(position);
        notifyItemRemoved(position);
    }
    public class  ViewHoler extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgHinh;


        public ViewHoler(@NonNull final View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtHinh);
            imgHinh = (ImageView)itemView.findViewById(R.id.imgHinh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Remove"+ txtName.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
