package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;

import com.example.practice.adapter.ListProductAdapter;
import com.example.practice.entity.Product;
import com.example.practice.repository.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewProductActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListSong;
    private List<Product> products;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
        initView();
    }

    private void initView() {
        products = new ArrayList<>();
        db = AppDatabase.getAppDatabase(this);
        products = db.productDao().findAll();
        recyclerViewListSong = findViewById(R.id.recycler_view_list_product);
        recyclerViewListSong.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewListSong.setAdapter(new ListProductAdapter(this, products));
        ((RecyclerView.Adapter) recyclerViewListSong.getAdapter()).notifyDataSetChanged();
    }
}