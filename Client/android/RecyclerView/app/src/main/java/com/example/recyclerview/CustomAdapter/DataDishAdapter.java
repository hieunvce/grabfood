package com.example.recyclerview.CustomAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.example.recyclerview.DTO.DataDishDTO;
import com.example.recyclerview.R;

import java.util.ArrayList;

public class DataDishAdapter extends RecyclerView.Adapter<DataDishAdapter.ViewHoler> {
    ArrayList<DataDishDTO> dataDishes;
    Context context;
    Dialog myDialog;

    public DataDishAdapter(ArrayList<DataDishDTO> dataDishes, Context context) {
        this.dataDishes = dataDishes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_dish,viewGroup,false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        viewHoler.txtName.setText(dataDishes.get(i).getTen());
        viewHoler.txtGia.setText(dataDishes.get(i).getGia());
        Picasso.get()
                .load(dataDishes.get(i).getHinhAnh())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90,90)
                .into(viewHoler.imgDish);

    }

    @Override
    public int getItemCount() {
        return dataDishes.size();
    }
    public void RemoveItem(int position)
    {
        dataDishes.remove(position);
        notifyItemRemoved(position);
    }
    public class  ViewHoler extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgDish;
        TextView txtGia;
        TextView txtPriceDiaglog;
        TextView txtNameDialog;
        TextView txtDiscriptionDialog;
        TextView  txtNumberDialog;
        ImageButton btnPlusDialog;
        ImageButton  btnMinusDialog;
        Button btnAddToCartDialog;
        public ViewHoler(@NonNull final View itemView) {
            super(itemView);

            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtGia = (TextView)itemView.findViewById(R.id.txtPrice);
            imgDish = (ImageView)itemView.findViewById(R.id.imgDish);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(itemView.getContext(), "select"+ txtName.getText(), Toast.LENGTH_SHORT).show();
                    context = itemView.getContext();
                    myDialog = new Dialog(context);
                    myDialog.setContentView(R.layout.custom_dialog);
                    txtPriceDiaglog = myDialog.findViewById(R.id.txtPriceDialog);
                    txtNameDialog = myDialog.findViewById(R.id.txtNameDialog);
                    txtDiscriptionDialog = myDialog.findViewById(R.id.txtDiscriptionDialog);
                    txtNumberDialog = myDialog.findViewById(R.id.txtNumberDialog);
                    btnPlusDialog = myDialog.findViewById(R.id.btnPlusDialog);
                    btnMinusDialog = myDialog.findViewById(R.id.btnMinusDialog);
                    btnAddToCartDialog=myDialog.findViewById(R.id.btnAddToCartDialog);

                    ShowDialog(getAdapterPosition());

                    myDialog.show();


                }
            });
        }
        public void ShowDialog(final int adapterPosition) {

            txtNameDialog.setText(dataDishes.get(adapterPosition).getTen());
            txtDiscriptionDialog.setText("category");
            txtPriceDiaglog.setText(dataDishes.get(adapterPosition).getGia());
            txtNameDialog.setText(dataDishes.get(adapterPosition).getTen());
            txtNumberDialog.setText(dataDishes.get(adapterPosition).getSoLuong());


            btnPlusDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number=Integer.parseInt(txtNumberDialog.getText().toString()) + 1;
                    txtNumberDialog.setText(String.valueOf(number));
                    dataDishes.get(adapterPosition).setSoLuong(String.valueOf(number));
                }
            });
            btnMinusDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number=Integer.parseInt(txtNumberDialog.getText().toString()) + 1;
                    txtNumberDialog.setText(String.valueOf(number));
                    dataDishes.get(adapterPosition).setSoLuong(String.valueOf(number));
                }
            });

            btnAddToCartDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent("custom-message");
                    //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));

                    intent.putExtra("dataDishesTransferToCart",dataDishes.get(adapterPosition));

                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Log.d("message",(dataDishes.get(adapterPosition).getSoLuong()));
                    myDialog.dismiss();
                }
            });
        }

    }
}
