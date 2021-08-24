package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

public class DestinyAddActivity extends AppCompatActivity {

    EditText addCity, addCountry, addDescription;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_add);

        addCity = (EditText) findViewById(R.id.add_city);
        addCountry = (EditText) findViewById(R.id.add_country);
        addDescription = (EditText) findViewById(R.id.add_description);
        addButton = (Button) findViewById(R.id.add_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notEmpty(addCity) && notEmpty(addCountry) && notEmpty(addDescription)) {
                    Log.d("Destination", "Add Button Clicked");
                    Destination destination = new Destination(addCity.getText().toString(), addCountry.getText().toString(), addDescription.getText().toString());

                    DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
                    Call<Destination> messageCall = destinationService.addDestination(destination);
                    messageCall.enqueue(new Callback<Destination>() {
                        @Override
                        public void onResponse(Call<Destination> call, Response<Destination> response) {
                            Toast.makeText(getApplicationContext(), "New City Added Successfully", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }

                        @Override
                        public void onFailure(Call<Destination> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private boolean notEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        return false;
    }
}