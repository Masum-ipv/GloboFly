package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learning.globofly.R;
import com.learning.globofly.models.Destination;
import com.learning.globofly.services.DestinationService;
import com.learning.globofly.services.ServiceBuilder;

public class DestinyDetailActivity extends AppCompatActivity {

    EditText updateCity, updateCountry, updateDescription;
    Button updateButton, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_detail);

        updateCity = (EditText) findViewById(R.id.et_city);
        updateCountry = (EditText) findViewById(R.id.et_country);
        updateDescription = (EditText) findViewById(R.id.et_description);
        updateButton = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        String city = intent.getStringExtra("city");
        String country = intent.getStringExtra("country");
        String description = intent.getStringExtra("description");

        updateCity.setText(city);
        updateCountry.setText(country);
        updateDescription.setText(description);
        Destination destination = new Destination(city, country, description);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editCity = updateCity.getText().toString();
                String editCountry = updateCountry.getText().toString();
                String editDescription = updateDescription.getText().toString();
                Destination destination = new Destination(editCity, editCountry, editDescription);

                DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
                Call<Destination> messageCall = destinationService.updateDestination(index, destination);
                messageCall.enqueue(new Callback<Destination>() {
                    @Override
                    public void onResponse(Call<Destination> call, Response<Destination> response) {
                        Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Call<Destination> call, Throwable t) {

                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
                Call<Destination> messageCall = destinationService.deleteDestination(index);
                messageCall.enqueue(new Callback<Destination>() {
                    @Override
                    public void onResponse(Call<Destination> call, Response<Destination> response) {
                        Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Call<Destination> call, Throwable t) {

                    }
                });
            }
        });
    }
}