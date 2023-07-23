package com.learning.globofly.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.learning.globofly.models.Destination;
import com.learning.globofly.repository.DestinationRepository;

import java.util.List;

public class DestinyListViewModel extends AndroidViewModel {

    private DestinationRepository destinationRepository;

    public DestinyListViewModel(@NonNull Application application) {
        super(application);
        destinationRepository = new DestinationRepository(application);
    }

    public MutableLiveData<List<Destination>> getDestinationList() {
        return destinationRepository.getDestinationList();
    }

    public MutableLiveData<String> getMessage() {
        return destinationRepository.getMessage();
    }

    public void addDestination(Destination destination) {
        destinationRepository.addDestination(destination);
    }

    public void updateDestination(int index, Destination destination) {
        destinationRepository.updateDestination(index, destination);
    }

    public void deleteDestination(int index) {
        destinationRepository.deleteDestination(index);
    }
}
