package com.learning.globofly.activities;

import static com.learning.globofly.Utils.Helper.notEmpty;
import static com.learning.globofly.activities.DestinyListActivity.INDEX;
import static com.learning.globofly.activities.DestinyListActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;
import static com.learning.globofly.activities.DestinyListActivity.REQUEST_CODE;
import static com.learning.globofly.activities.DestinyListActivity.UPDATE_WORD_ACTIVITY_REQUEST_CODE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learning.globofly.R;
import com.learning.globofly.ViewModel.DestinyListViewModel;

public class DestinyAddActivity extends AppCompatActivity {

    EditText city, country, description;
    Button saveButton;
    private DestinyListViewModel viewModel;
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String DESCRIPTION = "description";
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_add_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        setTitle("Create New Destination");

        city = findViewById(R.id.add_city);
        country = findViewById(R.id.add_country);
        description = findViewById(R.id.add_description);
        saveButton = findViewById(R.id.add_btn);
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) { //During data update
            index = bundle.getInt(INDEX);
            city.setText(bundle.getString(CITY));
            country.setText(bundle.getString(COUNTRY));
            description.setText(bundle.getString(DESCRIPTION));
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (notEmpty(city) && notEmpty(country) && notEmpty(description)) {
                    intent.putExtra(CITY, city.getText().toString());
                    intent.putExtra(COUNTRY, country.getText().toString());
                    intent.putExtra(DESCRIPTION, description.getText().toString());

                    if (bundle != null) { //Update
                        intent.putExtra(INDEX, index);
                        intent.putExtra(REQUEST_CODE, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
                        Toast.makeText(DestinyAddActivity.this, city.getText().toString() + " has been updated.", Toast.LENGTH_SHORT).show();
                    } else { //Insert
                        intent.putExtra(REQUEST_CODE, NEW_WORD_ACTIVITY_REQUEST_CODE);
                        Toast.makeText(DestinyAddActivity.this, "New destiny " + city.getText().toString() + " has been added.", Toast.LENGTH_SHORT).show();
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(DestinyAddActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}