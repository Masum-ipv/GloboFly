package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learning.globofly.R;
import com.learning.globofly.Utils.Helper;
import com.learning.globofly.Network.ApiServices;
import com.learning.globofly.Network.RetrofitInstance;
import com.learning.globofly.ViewModel.DestinyListViewModel;
import com.learning.globofly.adapters.CountryListAdapter;
import com.learning.globofly.models.Destination;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView message;
    Button start;
    private DestinyListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        start = (Button) findViewById(R.id.main_get_started);

        if (Helper.isNetworkAvailable(this)) {
            viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);
            viewModel.getMessage().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    message.setText(s);
                }
            });


//            ApiServices apiServices = RetrofitInstance.createService(ApiServices.class);
//            Call<String> messageCall = apiServices.getMessage();
//            messageCall.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    if (response.isSuccessful()) {
//                        message.setText(response.body());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                }
//            });

            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DestinyListActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}