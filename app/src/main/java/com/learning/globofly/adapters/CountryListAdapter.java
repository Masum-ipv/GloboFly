package com.learning.globofly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learning.globofly.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private static ClickListener clickListener;
    Context context;
    List<String> countryNames, cityNames;

    public CountryListAdapter(Context context, List<String> countryNames, List<String> cityNames) {
        this.context = context;
        this.countryNames = countryNames;
        this.cityNames = cityNames;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.country_view, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull CountryListAdapter.CountryViewHolder holder, int position) {
        holder.countryId.setText(countryNames.get(position));
        holder.cityId.setText(cityNames.get(position));
    }

    @Override
    public int getItemCount() {
        return countryNames.size();
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
}
