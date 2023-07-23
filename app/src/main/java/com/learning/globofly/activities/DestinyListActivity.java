package com.learning.globofly.activities;

import static com.learning.globofly.activities.DestinyAddActivity.CITY;
import static com.learning.globofly.activities.DestinyAddActivity.COUNTRY;
import static com.learning.globofly.activities.DestinyAddActivity.DESCRIPTION;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learning.globofly.R;
import com.learning.globofly.adapters.CountryListAdapter;
import com.learning.globofly.adapters.CountryListAdapter.ClickListener;
import com.learning.globofly.models.Destination;
import com.learning.globofly.viewmodel.DestinyListViewModel;

import java.util.List;

public class DestinyListActivity extends AppCompatActivity implements ClickListener {

    RecyclerView destinationRecyclerView;
    private List<Destination> destinations;
    FloatingActionButton fab;
    CountryListAdapter countryListAdapter;
    private DestinyListViewModel viewModel;
    int swipeDirections = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    public static final String REQUEST_CODE = "request_code";
    public static final String INDEX = "index";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_WORD_ACTIVITY_REQUEST_CODE = 2;
    ActivityResultLauncher<Intent> intentResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny_list);

        fab = findViewById(R.id.addButton);
        destinationRecyclerView = findViewById(R.id.destinationList);
        loadDestinations();

        intentResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Destination destination = new Destination();
                            int index = result.getData().getIntExtra(INDEX, -1);
                            destination.setCity(result.getData().getStringExtra(CITY));
                            destination.setCountry(result.getData().getStringExtra(COUNTRY));
                            destination.setDescription(result.getData().getStringExtra(DESCRIPTION));

                            int request_code = result.getData().getIntExtra(REQUEST_CODE, -1);
                            if (request_code == NEW_WORD_ACTIVITY_REQUEST_CODE) {
                                viewModel.addDestination(destination);
                            } else if (request_code == UPDATE_WORD_ACTIVITY_REQUEST_CODE) {
                                viewModel.updateDestination(index, destination);
                            }
                        }
                    }
                });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DestinyListActivity.this, DestinyAddActivity.class);
                intentResultLauncher.launch(intent);
            }
        });

        //Swipe Left/Right for delete
        swipeCard(swipeDirections);
    }

    private void loadDestinations() {
        viewModel = new ViewModelProvider(this).get(DestinyListViewModel.class);
        viewModel.getDestinationList().observe(this, new Observer<List<Destination>>() {
            @Override
            public void onChanged(List<Destination> destinationList) {
                destinations = destinationList;
                loadAdapter();
            }
        });
    }

    private void loadAdapter() {
        countryListAdapter = new CountryListAdapter(this, destinations);
        destinationRecyclerView.setAdapter(countryListAdapter);
        destinationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryListAdapter.setOnItemClickerListener(this);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Destination destination = destinations.get(position);
        Intent intent = new Intent(DestinyListActivity.this, DestinyAddActivity.class);
        intent.putExtra(INDEX, position);
        intent.putExtra(CITY, destination.getCity());
        intent.putExtra(COUNTRY, destination.getCountry());
        intent.putExtra(DESCRIPTION, destination.getDescription());
        intentResultLauncher.launch(intent);
    }

    private void swipeCard(int swipeDirections) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirections) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();
                Destination destination = destinations.get(position);
                Toast.makeText(getApplicationContext(), destination.getCity() + " has been deleted.", Toast.LENGTH_SHORT).show();
                // Delete the word
                viewModel.deleteDestination(position);
                countryListAdapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(destinationRecyclerView);
    }

}