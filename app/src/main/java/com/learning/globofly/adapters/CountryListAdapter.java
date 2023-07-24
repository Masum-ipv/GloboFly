package com.learning.globofly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.globofly.R;
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
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.destiny_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (destinations != null) {
            holder.countryId.setText(destinations.get(position).getCountry());
            holder.cityId.setText(destinations.get(position).getCity());
        } else {
            holder.countryId.setText("No Data");
            holder.cityId.setText("No Data");
        }
    }

    @Override
    public int getItemCount() {
        return destinations == null ? 0 : destinations.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cityId, countryId;

        public CountryViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            cityId = itemView.findViewById(R.id.cityID);
            countryId = itemView.findViewById(R.id.countryID);

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
