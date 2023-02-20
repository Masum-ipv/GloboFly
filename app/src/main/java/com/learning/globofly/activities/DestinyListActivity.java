package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learning.globofly.R;
import com.learning.globofly.ViewModel.DestinyListViewModel;
import com.learning.globofly.adapters.CountryListAdapter;
import com.learning.globofly.models.Destination;
import com.learning.globofly.Network.ApiServices;
import com.learning.globofly.Network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

public class DestinyListActivity extends AppCompatActivity {

    RecyclerView destinationRecyclerView;
    FloatingActionButton fab;
    CountryListAdapter countryListAdapter;
    private DestinyListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_list);
        destinationRecyclerView = (RecyclerView) findViewById(R.id.destinationList);
        fab = (FloatingActionButton) findViewById(R.id.addButton);

        loadDestinationList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DestinyListActivity.this, DestinyAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //loadDestinationList(); //TODO: Need to modify
    }

    private void loadDestinationList() {
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);
        viewModel.getDestinationList().observe(this, new Observer<List<Destination>>() {
            @Override
            public void onChanged(List<Destination> destinations) {
                countryListAdapter = new CountryListAdapter(getApplicationContext(), destinations);
                destinationRecyclerView.setAdapter(countryListAdapter);
                destinationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });
    }
}