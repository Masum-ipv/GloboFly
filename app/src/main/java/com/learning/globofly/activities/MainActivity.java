package com.learning.globofly.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learning.globofly.R;
import com.learning.globofly.utils.Helper;
import com.learning.globofly.viewmodel.DestinyListViewModel;

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