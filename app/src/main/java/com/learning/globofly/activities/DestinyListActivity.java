package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
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
import com.learning.globofly.adapters.CountryListAdapter;
import com.learning.globofly.models.Destination;
import com.learning.globofly.services.DestinationService;
import com.learning.globofly.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;

public class DestinyListActivity extends AppCompatActivity {

    RecyclerView destinationRecyclerView;
    FloatingActionButton fab;
    CountryListAdapter countryListAdapter;

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
        loadDestinationList();
    }

    private void loadDestinationList() {
        List<String> countryNames = new ArrayList<>();
        List<String> cityNames = new ArrayList<>();
        DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
        Call<List<Destination>> messageCall = destinationService.doGetListResources();
        messageCall.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                if (response.isSuccessful()) {
                    for (Destination destination : response.body()) {
                        Log.d("Destination", "onResponse destination.getCountry() : " + destination.getCountry());
                        Log.d("Destination", "onResponse destination.getCity() : " + destination.getCity());
                        countryNames.add(destination.getCountry());
                        cityNames.add(destination.getCity());
                    }
                    countryListAdapter = new CountryListAdapter(getApplicationContext(), countryNames, cityNames);
                    destinationRecyclerView.setAdapter(countryListAdapter);
                    destinationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    countryListAdapter.setOnItemClickerListener(new CountryListAdapter.ClickListener() {
                        @Override
                        public void onItemClickListener(View view, int position) {
                            Intent intent = new Intent(DestinyListActivity.this, DestinyDetailActivity.class);
                            intent.putExtra("index", position);
                            intent.putExtra("city", response.body().get(position).getCity());
                            intent.putExtra("country", response.body().get(position).getCountry());
                            intent.putExtra("description", response.body().get(position).getDescription());
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(DestinyListActivity.this, "Failed to retrieve items", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                Toast.makeText(DestinyListActivity.this, "Error Occured to Get Destination List" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}