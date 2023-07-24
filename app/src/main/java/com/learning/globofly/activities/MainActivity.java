package com.learning.globofly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.learning.globofly.R;
import com.learning.globofly.databinding.ActivityMainBinding;
import com.learning.globofly.utils.Helper;
import com.learning.globofly.viewmodel.DestinyListViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private DestinyListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (Helper.isNetworkAvailable(this)) {
            viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);
            viewModel.getMessage().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    mainBinding.message.setText(s);
                }
            });


            mainBinding.mainGetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DestinyListActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}