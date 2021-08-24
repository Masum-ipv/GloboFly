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
import android.widget.TextView;

import com.learning.globofly.R;
import com.learning.globofly.models.Destination;
import com.learning.globofly.services.DestinationService;
import com.learning.globofly.services.ServiceBuilder;

public class MainActivity extends AppCompatActivity {

    TextView message;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        start = (Button) findViewById(R.id.main_get_started);

        DestinationService destinationService = ServiceBuilder.createService(DestinationService.class);
        Call<String> messageCall = destinationService.getMessage();
        messageCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    message.setText(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DestinyListActivity.class);
                startActivity(intent);
            }
        });
    }
}