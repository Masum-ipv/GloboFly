package com.learning.globofly.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.learning.globofly.models.Destination;
import com.learning.globofly.network.ApiServices;
import com.learning.globofly.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationRepository {
    private Application application;
    private List<Destination> destinations;
    private MutableLiveData<List<Destination>> destinationListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageMutableLiveData = new MutableLiveData<>();


    public DestinationRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Destination>> getDestinationList() {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance();
        Call<List<Destination>> call = apiServices.getDestinations();
        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                destinations = response.body();
                destinationListMutableLiveData.setValue(destinations);
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                Log.d("DestinationRepository", "Destination List Loading Fail: " + t.getMessage());
            }
        });

        return destinationListMutableLiveData;
    }

    public MutableLiveData<String> getMessage() {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance();
        Call<String> call = apiServices.getMessage();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                messageMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("DestinationRepository", "Message Received Fail: " + t.getMessage());
            }
        });

        return messageMutableLiveData;
    }

    public void addDestination(Destination destination) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance();
        Call<Destination> call = apiServices.addDestination(destination);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                destinations.add(response.body());
                destinationListMutableLiveData.setValue(destinations);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Adding Fail: " + t.getMessage());
            }
        });
    }

    public void updateDestination(int index, Destination destination) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance();
        Call<Destination> call = apiServices.updateDestination(index, destination);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                destinations.set(index, response.body());
                destinationListMutableLiveData.setValue(destinations);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Update Fail: " + t.getMessage());
            }
        });
    }

    public void deleteDestination(int index) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance();
        Call<Destination> call = apiServices.deleteDestination(index);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                destinations.remove(index);
                destinationListMutableLiveData.setValue(destinations);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Deletion Fail: " + t.getMessage());
            }
        });
    }
}
