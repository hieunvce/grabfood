package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class location extends AppCompatActivity {

    private location.AddressListResultReceiver addressResultReceiver;

    private EditText addressNameTv;
    private ListView addressListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);



        addressNameTv = findViewById(R.id.addresses_t);
        addressListView = findViewById(R.id.addresses_lst);

        addressResultReceiver = new AddressListResultReceiver(new Handler());

        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent data = new Intent();
                data.putExtra("address", selectedItem);

                // Đặt resultCode là Activity.RESULT_OK to
                // thể hiện đã thành công và có chứa kết quả trả về
                setResult(Activity.RESULT_OK, data);

                // gọi hàm finish() để đóng Activity hiện tại và trở về MainActivity.
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        // đặt resultCode là Activity.RESULT_CANCELED thể hiện
        // đã thất bại khi người dùng click vào nút Back.
        // Khi này sẽ không trả về data.
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    public void getAddressesByName(View view){
        getAddresses(addressNameTv.getText().toString());
    }

    private void getAddresses(String addName) {
        if (!Geocoder.isPresent()) {
            Toast.makeText(location.this,
                    "Can't find address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, AddressesByNameIntentService.class);
        intent.putExtra("address_receiver", addressResultReceiver);
        intent.putExtra("address_name", addName);
        startService(intent);
    }

    private class AddressListResultReceiver extends ResultReceiver {
        AddressListResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0) {
                Toast.makeText(location.this,
                        "Enter address name, " ,
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (resultCode == 1) {
                Toast.makeText(location.this,
                        "Address not found, " ,
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String[] addressList = resultData.getStringArray("addressList");
            showResults(addressList);
        }
    }

    private void showResults(String[] addressList){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, addressList);
        addressListView.setAdapter(arrayAdapter);
    }
}