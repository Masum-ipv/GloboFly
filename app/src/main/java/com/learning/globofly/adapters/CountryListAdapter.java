package com.learning.globofly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.globofly.R;
import com.learning.globofly.databinding.DestinyItemBinding;
import com.learning.globofly.models.Destination;
import com.learning.globofly.utils.DestinyDiffCallBack;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private static ClickListener clickListener;
    Context context;
    private List<Destination> destinations;

    public CountryListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DestinyItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.destiny_item,
                parent,
                false
        );
        return new CountryViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Destination destination = destinations.get(position);
        holder.itemBinding.setDestination(destination);
    }

    @Override
    public int getItemCount() {
        return destinations == null ? 0 : destinations.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private DestinyItemBinding itemBinding;

        public CountryViewHolder(DestinyItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClickListener(view, getBindingAdapterPosition());
        }
    }

    public interface ClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickerListener(ClickListener clickerListener) {
        CountryListAdapter.clickListener = clickerListener;
    }

    public void setDestinations(List<Destination> newDestinations) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(
                new DestinyDiffCallBack(destinations, newDestinations), false);
        destinations = newDestinations;
        result.dispatchUpdatesTo(CountryListAdapter.this);
    }
}
