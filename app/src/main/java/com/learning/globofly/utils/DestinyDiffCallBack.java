package com.learning.globofly.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.learning.globofly.models.Destination;

import java.util.List;

public class DestinyDiffCallBack extends DiffUtil.Callback {
    List<Destination> oldDestinations;
    List<Destination> newDestinations;

    public DestinyDiffCallBack(List<Destination> oldDestinations, List<Destination> newDestinations) {
        this.oldDestinations = oldDestinations;
        this.newDestinations = newDestinations;
    }

    @Override
    public int getOldListSize() {
        return oldDestinations == null ? 0 : oldDestinations.size();
    }

    @Override
    public int getNewListSize() {
        return newDestinations == null ? 0 : newDestinations.size();
    }

    //Unique identifier in the class, here City is unique
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDestinations.get(oldItemPosition).getCity()
                .equals(newDestinations.get(newItemPosition).getCity());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldDestinations.get(oldItemPosition)
                .equals(newDestinations.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
