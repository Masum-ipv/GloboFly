package com.learning.globofly.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Destination implements Parcelable {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("country")
    @Expose
    private String country;

    public Destination() {
    }

    public Destination(String city, String description, String country) {
        this.city = city;
        this.description = description;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //Parcel
    public final static Parcelable.Creator<Destination> CREATOR = new Creator<Destination>() {
        @Override
        public Destination createFromParcel(Parcel parcel) {
            return new Destination(parcel);
        }

        @Override
        public Destination[] newArray(int i) {
            return new Destination[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeValue(city);
        parcel.writeValue(country);
        parcel.writeValue(description);
    }

    public Destination(Parcel parcel) {
        this.city = (String) parcel.readValue(String.class.getClassLoader());
        this.country = (String) parcel.readValue(String.class.getClassLoader());
        this.description = (String) parcel.readValue(String.class.getClassLoader());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination destination = (Destination) o;
        return country.equals(destination.country) &&
                city.equals(destination.city) &&
                description.equals(destination.description);
    }

}
