package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practice.entity.Product;
import com.example.practice.repository.AppDatabase;

public class MainActivity extends AppCompatActivity {

    EditText productInput, quantityInput;
    Button addBtn, viewBtn;

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
        setContentView(R.layout.activity_main);

        initView();
        
        initListener();
    }

    private void initListener() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = productInput.getText().toString();
                String productQuantityBeforeConvert = quantityInput.getText().toString();

                if (productName.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Need to provide product name", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                if (!isInteger(productQuantityBeforeConvert)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Quantity must be whole number and cannot be null", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                int productQuantity = Integer.parseInt(productQuantityBeforeConvert);

                Product product = new Product();
                product.setName(productName);
                product.setQuantity(productQuantity);
                db.productDao().insertProduct(product);

                Toast toast = Toast.makeText(getApplicationContext(), "Add product successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewProductActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void initView() {
        productInput = (EditText) findViewById(R.id.name_input);
        quantityInput = (EditText) findViewById(R.id.quantity_input);
        addBtn = (Button) findViewById(R.id.add_product);
        viewBtn = (Button) findViewById(R.id.view_product);
        db = AppDatabase.getAppDatabase(this);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}