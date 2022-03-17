package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView itemRV;
    private ItemAdapter adapter;
    private ArrayList<ModalClass> itemModalArrayList,reqModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String reqPrice = getIntent().getStringExtra("message");
        //int req = Integer.parseInt(reqPrice);
        int req = 30;

        itemRV = findViewById(R.id.listofitems);
        buildRecyclerView(req);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        ArrayList<ModalClass> filteredlist = new ArrayList<>();

        for (ModalClass item : itemModalArrayList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView(int req) {

        itemModalArrayList = new ArrayList<>();
        reqModalArrayList = new ArrayList<>();

        itemModalArrayList.add(new ModalClass("Apple", "Red","10"));
        itemModalArrayList.add(new ModalClass("Mango", "Yellow","20"));
        itemModalArrayList.add(new ModalClass("Banana", "Yellow","30"));
        itemModalArrayList.add(new ModalClass("Orange", "orange","50"));
        itemModalArrayList.add(new ModalClass("Guava", "Green","40"));

       int n = itemModalArrayList.size();

        for(int i= 0;i<n;i++){
            ModalClass modalClass = itemModalArrayList.get(i);
            if(Integer.parseInt(modalClass.getPrice()) >= req){
                reqModalArrayList.add(modalClass);
            }
        }

       adapter = new ItemAdapter(reqModalArrayList,this);

        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        itemRV.setHasFixedSize(true);
        itemRV.setLayoutManager(manager);
        itemRV.setAdapter(adapter);
    }
}


