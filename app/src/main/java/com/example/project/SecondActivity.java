package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private String TAG = "SecondActivity";
    private ListView listView;
    static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    SimpleAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText searchView = (EditText) findViewById(R.id.search_bar);


        listView = (ListView) findViewById(R.id.listview);


        JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();
        jsonHandlerThread.start();

        try {
            jsonHandlerThread.join();



            adapter = new SimpleAdapter(
                    this,
                    ContactInfo.contactList,
                    R.layout.list_view_layout,
                    new String[]{ContactInfo.NAME, ContactInfo.CATEGORY, ContactInfo.GENDER,
                            ContactInfo.RELIGION, ContactInfo.ADDRESS, ContactInfo.TELEPHONE,
                            ContactInfo.FAX, ContactInfo.WEBSITE},
                    new int[]{R.id.name, R.id.category, R.id.gender, R.id.religion, R.id.address,
                            R.id.telephone, R.id.fax, R.id.website}
            );
            listView.setAdapter(adapter);



        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException: " + e.getMessage());
        }
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        HashMap<String, String> contact = ContactInfo.contactList.get(i);
                        String urlStr = contact.get(ContactInfo.WEBSITEVIEW);
                        Intent intent = new Intent(SecondActivity.this, WebViewActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, urlStr);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(adapter!=null) {
                    SecondActivity.this.adapter.getFilter().filter(charSequence.toString());
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            int Position = ContactInfo.contactList.indexOf(adapter.getItem(i));
                            HashMap<String, String> contact = ContactInfo.contactList.get(Position);
                            String urlStr = contact.get(ContactInfo.WEBSITEVIEW);
                            Intent intent = new Intent(SecondActivity.this, WebViewActivity.class);
                            intent.putExtra(EXTRA_MESSAGE, urlStr);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}


