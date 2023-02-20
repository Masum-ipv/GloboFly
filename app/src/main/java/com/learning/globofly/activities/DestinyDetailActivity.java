package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learning.globofly.R;
import com.learning.globofly.Utils.Helper;
import com.learning.globofly.ViewModel.DestinyListViewModel;
import com.learning.globofly.models.Destination;
import com.learning.globofly.Network.ApiServices;
import com.learning.globofly.Network.RetrofitInstance;

public class DestinyDetailActivity extends AppCompatActivity {

    private EditText updateCity, updateCountry, updateDescription;
    private Button updateButton, delete;
    private DestinyListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_detail);

        updateCity = findViewById(R.id.et_city);
        updateCountry = findViewById(R.id.et_country);
        updateDescription = findViewById(R.id.et_description);
        updateButton = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        String city = intent.getStringExtra("city");
        String country = intent.getStringExtra("country");
        String description = intent.getStringExtra("description");

        updateCity.setText(city);
        updateCountry.setText(country);
        updateDescription.setText(description);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Helper.notEmpty(updateCity) && Helper.notEmpty(updateCountry) && Helper.notEmpty(updateDescription)) {
                    Destination destination = new Destination(updateCity.getText().toString(), updateCountry.getText().toString(), updateDescription.getText().toString());
                    viewModel.updateDestination(index, destination);
                    onBackPressed();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteDestination(index);
                onBackPressed();
            }
        });
    }
}