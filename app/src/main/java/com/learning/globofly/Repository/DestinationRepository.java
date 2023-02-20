package com.learning.globofly.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.learning.globofly.Network.ApiServices;
import com.learning.globofly.Network.RetrofitInstance;
import com.learning.globofly.models.Destination;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationRepository {
    private static Context mContext;
    private static DestinationRepository destinationRepositoryInstance;
    private List<Destination> mResult;
    private MutableLiveData destinationListMutableLiveData;
    private MutableLiveData messageMutableLiveData;


    public static DestinationRepository getMovieRepositoryInstance(Context context) {
        if (destinationRepositoryInstance == null) {
            mContext = context;
            destinationRepositoryInstance = new DestinationRepository();
        }
        return destinationRepositoryInstance;
    }

    public MutableLiveData<List<Destination>> getDestinationList() {
        if (destinationListMutableLiveData == null) {
            destinationListMutableLiveData = new MutableLiveData();
        }

        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<List<Destination>> call = apiServices.doGetListResources();
        call.enqueue(new Callback<List<Destination>>() {
            @Override
            public void onResponse(Call<List<Destination>> call, Response<List<Destination>> response) {
                mResult = response.body();
                destinationListMutableLiveData.postValue(mResult);
            }

            @Override
            public void onFailure(Call<List<Destination>> call, Throwable t) {
                Log.d("DestinationRepository", "Destination List Loading Fail: " + t.getMessage());
            }
        });

        return destinationListMutableLiveData;
    }

    public MutableLiveData<String> getMessage() {
        if (messageMutableLiveData == null) {
            messageMutableLiveData = new MutableLiveData();
        }

        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<String> call = apiServices.getMessage();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                messageMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("DestinationRepository", "Message Received Fail: " + t.getMessage());
            }
        });

        return messageMutableLiveData;
    }

    public void addDestination(Destination destination) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<Destination> call = apiServices.addDestination(destination);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                mResult.add(destination);
                destinationListMutableLiveData.postValue(mResult);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Adding Fail: " + t.getMessage());
            }
        });
    }

    public void updateDestination(int index, Destination destination) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<Destination> call = apiServices.updateDestination(index, destination);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                mResult.set(index, destination);
                destinationListMutableLiveData.postValue(mResult);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Update Fail: " + t.getMessage());
            }
        });
    }

    public void deleteDestination(int index) {
        ApiServices apiServices = RetrofitInstance.getRetrofitInstance().create(ApiServices.class);
        Call<Destination> call = apiServices.deleteDestination(index);
        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {
                mResult.remove(index);
                destinationListMutableLiveData.postValue(mResult);
            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                Log.d("DestinationRepository", "Destination Deletion Fail: " + t.getMessage());
            }
        });
    }
}
