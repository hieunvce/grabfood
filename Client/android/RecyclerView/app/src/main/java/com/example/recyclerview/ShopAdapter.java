package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
        View itemView = layoutInflater.inflate(R.layout.item_restaurant,viewGroup,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.txtName.setText(dataShops.get(i).getTen());
        viewHoler.txtAdress.setText(dataShops.get(i).getTen());
        Picasso.get()
                .load(dataShops.get(i).getHinhAnh())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90,90)
                .into(viewHoler.imgRestaurant);

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
        ImageView imgRestaurant;
        TextView txtAdress;

        public ViewHoler(@NonNull final View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtAdress = (TextView)itemView.findViewById(R.id.txtAddress);
            imgRestaurant = (ImageView)itemView.findViewById(R.id.imgRestaurant);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Remove"+ txtName.getText(), Toast.LENGTH_SHORT).show();
                    TransferDataToOderFood(getAdapterPosition());
                }
            });
        }
    }

    public void TransferDataToOderFood(int adapterPosition) {
        Intent intent = new Intent(context,OrderFood.class);
        intent.putExtra("id",dataShops.get(adapterPosition).getId());
    }
}
