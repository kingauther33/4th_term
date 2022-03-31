package com.example.android_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    private List<ContactModel> listContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        lvContact = (ListView) findViewById(R.id.lvContact);
        ContactAdapter adapter = new ContactAdapter(listContacts, this);
        lvContact.setAdapter(adapter);

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactModel contactModel = listContacts.get(position);
                Toast.makeText(MainActivity.this, contactModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        ContactModel contact = new ContactModel("Nguyen Van A", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van B", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van C", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van D", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van E", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van F", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van G", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van H", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
        contact = new ContactModel("Nguyen Van J", "0123123213", R.drawable.ic_baseline_supervised_user_circle_24);
        listContacts.add(contact);
    }
}