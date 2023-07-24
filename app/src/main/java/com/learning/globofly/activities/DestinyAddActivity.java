package com.learning.globofly.activities;

import static com.learning.globofly.activities.DestinyListActivity.INDEX;
import static com.learning.globofly.activities.DestinyListActivity.NEW_WORD_ACTIVITY_REQUEST_CODE;
import static com.learning.globofly.activities.DestinyListActivity.REQUEST_CODE;
import static com.learning.globofly.activities.DestinyListActivity.UPDATE_WORD_ACTIVITY_REQUEST_CODE;
import static com.learning.globofly.utils.Helper.notEmpty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.learning.globofly.R;
import com.learning.globofly.databinding.ActivityDestinyAddEditBinding;
import com.learning.globofly.models.Destination;
import com.learning.globofly.viewmodel.DestinyListViewModel;

public class DestinyAddActivity extends AppCompatActivity {

    private ActivityDestinyAddEditBinding itemBinding;
    private DestinyListViewModel viewModel;
    private Bundle bundle;
    public static final String DESTINATION = "destination";
    private int index = 0;
    private AddActivityClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemBinding = DataBindingUtil.setContentView(this, R.layout.activity_destiny_add_edit);

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

        //TODO: fix it
        setTitle("Create New Destination");
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);

        bundle = getIntent().getExtras();
        if (bundle != null) { //During data update
            Destination destination = bundle.getParcelable(DESTINATION);
            index = bundle.getInt(INDEX);
            itemBinding.setDestination(destination);
        }

        handlers = new AddActivityClickHandlers(this);
        itemBinding.setClickHandler(handlers);
    }

    public class AddActivityClickHandlers {
        Context context;

        public AddActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onSaveBtnClick(View view) {
            Intent intent = new Intent();
            if (notEmpty(itemBinding.addCity) && notEmpty(itemBinding.addCountry) && notEmpty(itemBinding.addDescription)) {
                Destination destination = new Destination(
                        itemBinding.addCity.getText().toString(),
                        itemBinding.addDescription.getText().toString(),
                        itemBinding.addCountry.getText().toString());
                intent.putExtra(DESTINATION, destination);

                if (bundle != null) { //Update
                    intent.putExtra(INDEX, index);
                    intent.putExtra(REQUEST_CODE, UPDATE_WORD_ACTIVITY_REQUEST_CODE);
                    Toast.makeText(DestinyAddActivity.this, itemBinding.addCity.getText().toString() + " has been updated.", Toast.LENGTH_SHORT).show();
                } else { //Insert
                    intent.putExtra(REQUEST_CODE, NEW_WORD_ACTIVITY_REQUEST_CODE);
                    Toast.makeText(DestinyAddActivity.this, "New destiny " + itemBinding.addCity.getText().toString() + " has been added.", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(DestinyAddActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

}