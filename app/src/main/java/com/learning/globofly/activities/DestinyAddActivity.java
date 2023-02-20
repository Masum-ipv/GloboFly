package com.learning.globofly.activities;

import static com.learning.globofly.Utils.Helper.notEmpty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.learning.globofly.Utils.Helper;
import com.learning.globofly.ViewModel.DestinyListViewModel;
import com.learning.globofly.adapters.CountryListAdapter;
import com.learning.globofly.models.Destination;
import com.learning.globofly.Network.ApiServices;
import com.learning.globofly.Network.RetrofitInstance;

import java.util.List;

public class DestinyAddActivity extends AppCompatActivity {

    EditText addCity, addCountry, addDescription;
    Button addButton;
    private DestinyListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_add);

        addCity = findViewById(R.id.add_city);
        addCountry = findViewById(R.id.add_country);
        addDescription = findViewById(R.id.add_description);
        addButton = findViewById(R.id.add_btn);
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notEmpty(addCity) && notEmpty(addCountry) && notEmpty(addDescription)) {
                    Destination destination = new Destination(addCity.getText().toString(), addCountry.getText().toString(), addDescription.getText().toString());
                    viewModel.addDestination(destination);
                    onBackPressed();
                }
            }
        });
    }


}