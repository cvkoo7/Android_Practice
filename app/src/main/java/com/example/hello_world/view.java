package com.example.hello_world;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class view extends AppCompatActivity {
    ListView lv_customerList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        lv_customerList = findViewById(R.id.listView);
        dataBaseHelper = new DataBaseHelper(view.this);
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(view.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(customerArrayAdapter);
        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickedCustomer);
                customerArrayAdapter = new ArrayAdapter<CustomerModel>(view.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
                lv_customerList.setAdapter(customerArrayAdapter);
                Toast.makeText(view.this,"Deleted " + clickedCustomer.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}