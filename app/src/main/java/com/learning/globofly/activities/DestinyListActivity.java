package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learning.globofly.R;
import com.learning.globofly.models.Destination;
import com.learning.globofly.services.DestinationService;
import com.learning.globofly.services.ServiceBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DestinyListActivity extends AppCompatActivity {

    ListView destinationList;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_list);
        destinationList = (ListView) findViewById(R.id.destinationList);
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
        DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
        Call<List<Destination>> messageCall = destinationService.doGetListResources();
        messageCall.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                if (response.isSuccessful()) {
                    for (Destination destination : response.body()) {
                        Log.d("Destination", "onResponse destination.getCountry() : " + destination.getCountry());
                        countryNames.add(destination.getCountry());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(DestinyListActivity.this, R.layout.country_view, R.id.countryID, countryNames);
                    destinationList.setAdapter(adapter);
                } else {
                    Toast.makeText(DestinyListActivity.this, "Faild to retrive items", Toast.LENGTH_SHORT).show();
                }

                destinationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DestinyListActivity.this, DestinyDetailActivity.class);
                        intent.putExtra("index", i);
                        intent.putExtra("city", response.body().get(i).getCity());
                        intent.putExtra("country", response.body().get(i).getCountry());
                        intent.putExtra("description", response.body().get(i).getDescription());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                Toast.makeText(DestinyListActivity.this, "Error Occured to Get Destination List" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}