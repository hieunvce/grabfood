package com.example.cefood.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cefood.Model.Restaurant;
import com.example.cefood.Activity.OrderFood;
import com.example.cefood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> implements Filterable {
    ArrayList<Restaurant> restaurants;
    ArrayList<Restaurant> restaurantsfilter;
    Context context;

    public RestaurantAdapter(ArrayList<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
        this.restaurantsfilter = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_restaurant,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtName.setText(restaurantsfilter.get(i).getName());
        viewHolder.txtAdress.setText(restaurantsfilter.get(i).getAddress());
        Picasso.get()
                .load(restaurantsfilter.get(i).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(90,90)
                .into(viewHolder.imgRestaurant);
    }


    @Override
    public int getItemCount() {
        return restaurantsfilter.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    restaurantsfilter = restaurants;
                    //saverestaurantsfilter = restaurants;
                } else {
                    List<Restaurant> filteredList = new ArrayList<>();
                    for (Restaurant row : restaurants) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    restaurantsfilter = (ArrayList<Restaurant>) filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = restaurantsfilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                restaurantsfilter = (ArrayList<Restaurant>) filterResults.values;
                notifyDataSetChanged();
                //restaurants = saverestaurantsfilter;
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        ImageView imgRestaurant;
        TextView txtAdress;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtAdress = (TextView)itemView.findViewById(R.id.txtAddress);
            imgRestaurant = (ImageView)itemView.findViewById(R.id.imgRestaurant);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(itemView.getContext(), "Selected "+ txtName.getText(), Toast.LENGTH_SHORT).show();
                    TransferDataToOderFood(getAdapterPosition());

                }

            });
        }
    }
    public void TransferDataToOderFood(int adapterPosition) {
        Intent intent = new Intent(context, OrderFood.class);
        intent.putExtra("restaurantId", restaurants.get(adapterPosition).getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}