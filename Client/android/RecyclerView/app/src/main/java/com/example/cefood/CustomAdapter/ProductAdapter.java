package com.example.cefood.CustomAdapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import com.example.cefood.Model.OrderDetail;
import com.example.cefood.Model.Product;
import com.squareup.picasso.Picasso;
import com.example.cefood.R;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> products;
    Context context;
    Dialog userSelectProductDialog;

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_product, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d("OnBindViewHolder",""+products.get(i).getName() + products.get(i).getPrice());
        // lii
        viewHolder.txtProductName.setText(products.get(i).getName());
        viewHolder.txtProductPrice.setText(products.get(i).getPrice().toString());
        Picasso.get()
                .load(products.get(i).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90, 90)
                .into(viewHolder.imgProductImg);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void RemoveItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Dialog pop up when user select product
        TextView txtProductNameDialog;
        TextView txtProductPriceDialog;
        TextView txtProductCategoryDialog;
        TextView txtQuantity;
        ImageButton btnPlusDialog;
        ImageButton btnMinusDialog;
        Button btnAddToCartDialog;

        //ListView Product
        TextView txtProductName;
        TextView txtProductPrice;
        TextView txtProductCategory;
        ImageView imgProductImg;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
            txtProductPrice = (TextView) itemView.findViewById(R.id.txtProductPrice);
            txtProductCategory = (TextView) itemView.findViewById(R.id.txtProductCategory);
            imgProductImg = (ImageView) itemView.findViewById(R.id.imgProductImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Selected " + txtProductName.getText(), Toast.LENGTH_SHORT).show();

                    context = itemView.getContext();
                    userSelectProductDialog = new Dialog(context);
                    userSelectProductDialog.setContentView(R.layout.user_select_product_dialog);
                    txtProductPriceDialog = userSelectProductDialog.findViewById(R.id.txtProductPriceDialog);
                    txtProductNameDialog = userSelectProductDialog.findViewById(R.id.txtProductNameDialog);
                    txtProductCategoryDialog = userSelectProductDialog.findViewById(R.id.txtProductCategoryDialog);

                    txtQuantity = userSelectProductDialog.findViewById(R.id.txtQuantityDialog);
                    btnPlusDialog = userSelectProductDialog.findViewById(R.id.btnPlusDialog);
                    btnMinusDialog = userSelectProductDialog.findViewById(R.id.btnMinusDialog);
                    btnAddToCartDialog = userSelectProductDialog.findViewById(R.id.btnAddToCartDialog);

                    ShowDialog(getAdapterPosition());

                    userSelectProductDialog.show();
                }
            });
        }

        public void ShowDialog(final int adapterPosition) {
            txtProductNameDialog.setText(products.get(adapterPosition).getName());
            txtProductCategoryDialog.setText(products.get(adapterPosition).getCategory());
            txtProductPriceDialog.setText(products.get(adapterPosition).getPrice().toString());
            //TODO: Change it Quantity
            txtQuantity.setText("1");


            btnPlusDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(txtQuantity.getText().toString()) + 1;
                    int root_price = products.get(adapterPosition).getPrice();
                    int last_price = number * root_price;
                    txtQuantity.setText(String.valueOf(number));
                    txtProductPriceDialog.setText(Integer.toString(last_price));
                }
            });
            btnMinusDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = Integer.parseInt(txtQuantity.getText().toString()) - 1;
                    if (number <= 0)
                        number = 0;
                    int root_price = products.get(adapterPosition).getPrice();
                    int last_price = number * root_price;
                    txtProductPriceDialog.setText(Integer.toString(last_price));
                    txtQuantity.setText(String.valueOf(number));
                }
            });

            btnAddToCartDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderDetail orderedProduct = new OrderDetail();
                    orderedProduct.setProduct(products.get(adapterPosition));
                    orderedProduct.setQuantity(Integer.parseInt(txtQuantity.getText().toString()));
                    Intent intent = new Intent("custom-message");
                    // TODO: Xem lai broadcast va cho nay
                    intent.putExtra("addToCart", (Serializable) orderedProduct);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    Log.d("addToCart", ""+orderedProduct.getProduct().getName() + " "+orderedProduct.getQuantity());
                    userSelectProductDialog.dismiss();
                    //TODO: Tinh nang thanh toan va xem lich su
                }
            });
        }

    }
}
