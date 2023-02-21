package com.learning.globofly.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learning.globofly.R;
import com.learning.globofly.models.Destination;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private static ClickListener clickListener;
    Context context;
    private List<Destination> mList;

    public CountryListAdapter(Context context) {
        this.context = context;
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
        if (mList != null) {
            holder.countryId.setText(mList.get(position).getCountry());
            holder.cityId.setText(mList.get(position).getCity());
        } else {
            holder.countryId.setText("No Data");
            holder.cityId.setText("No Data");
        }
    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        else return 0;
    }

    public void setDestinyList(List<Destination> destinations) {
        this.mList = destinations;
        notifyDataSetChanged();
    }

    public Destination getDestinationAtPosition(int position) {
        return mList.get(position);
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
