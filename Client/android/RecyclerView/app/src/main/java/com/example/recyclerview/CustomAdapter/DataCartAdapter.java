package com.example.recyclerview.CustomAdapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.DTO.DataCartDTO;
import com.squareup.picasso.Picasso;
import com.example.recyclerview.R;

import java.util.ArrayList;

public class DataCartAdapter extends RecyclerView.Adapter<DataCartAdapter.ViewHoler> {
    ArrayList<DataCartDTO> dataCarts;
    Context context;

    public DataCartAdapter(ArrayList<DataCartDTO> dataCarts, Context context) {
        this.dataCarts = dataCarts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_cart,viewGroup,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.txtName.setText(dataCarts.get(i).getTen());
        viewHoler.txtGia.setText(dataCarts.get(i).getGia());
        Picasso.get()
                .load(dataCarts.get(i).getHinhAnh())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90,90)
                .into(viewHoler.imgCart);

    }

    @Override
    public int getItemCount() {
        return dataCarts.size();
    }
    public void RemoveItem(int position)
    {
        dataCarts.remove(position);
        notifyItemRemoved(position);
    }
    public class  ViewHoler extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgCart;
        ImageView imgPlus;
        ImageView imgMinus;
        TextView txtGia;
        TextView txtNumber;


        public ViewHoler(@NonNull final View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.txtNameCart);
            txtGia = (TextView)itemView.findViewById(R.id.txtPriceCart);
            txtNumber = (TextView)itemView.findViewById(R.id.txtNumberCart);

            imgCart = (ImageView)itemView.findViewById(R.id.imgCart);
            imgPlus = (ImageView)itemView.findViewById(R.id.imgPlusCart);
            imgMinus = (ImageView)itemView.findViewById(R.id.imgMinusCart);
            txtNumber.setText("0");
            imgPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number=Integer.parseInt(txtNumber.getText().toString()) + 1;
                    txtNumber.setText(String.valueOf(number));
                }
            });
            imgMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number=Integer.parseInt(txtNumber.getText().toString()) - 1;
                    txtNumber.setText(String.valueOf(number));
                }
            });





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), "select"+ txtName.getText(), Toast.LENGTH_SHORT).show();
                    context = itemView.getContext();





                }
            });

        }





    }
}
