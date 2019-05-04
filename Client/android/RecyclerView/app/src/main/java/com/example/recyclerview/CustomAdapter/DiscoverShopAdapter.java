package com.example.recyclerview.CustomAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.DTO.DataDiscoverShopDTO;
import com.example.recyclerview.R;

import java.util.ArrayList;

public class DiscoverShopAdapter extends RecyclerView.Adapter<DiscoverShopAdapter.ViewHoler> {
    ArrayList<DataDiscoverShopDTO> dataDiscoverShops;
    Context context;

    public DiscoverShopAdapter(ArrayList<DataDiscoverShopDTO> dataDiscoverShops, Context context) {
        this.dataDiscoverShops = dataDiscoverShops;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_discover,viewGroup,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.txtName.setText(dataDiscoverShops.get(i).getTen());
        viewHoler.imgHinh.setImageResource(dataDiscoverShops.get(i).getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return dataDiscoverShops.size();
    }
    public void RemoveItem(int position)
    {
        dataDiscoverShops.remove(position);
        notifyItemRemoved(position);
    }
    public class  ViewHoler extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgHinh;


        public ViewHoler(@NonNull final View itemView) {
            super(itemView);
            imgHinh = (ImageView)itemView.findViewById(R.id.imgdiscover);
            txtName = (TextView) itemView.findViewById(R.id.txtdiscover);
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
