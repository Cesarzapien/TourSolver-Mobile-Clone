package com.cesar.toursolvermobile2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Geocode implements Parcelable {

    @SerializedName("addressComplement")
    private String addressComplement;

    @SerializedName("address")
    private String address;

    @SerializedName("postcode")
    private String postcode;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("region")
    private String region;

    @SerializedName("score")
    private double score;

    @SerializedName("geocodeType")
    private int geocodeType;

    @SerializedName("geocodeCity")
    private String geocodeCity;

    @SerializedName("geocodePostalCode")
    private String geocodePostalCode;

    @SerializedName("geocodeAddressLine")
    private String geocodeAddressLine;

    protected Geocode(Parcel in) {
        addressComplement = in.readString();
        address = in.readString();
        postcode = in.readString();
        city = in.readString();
        country = in.readString();
        region = in.readString();
        score = in.readDouble();
        geocodeType = in.readInt();
        geocodeCity = in.readString();
        geocodePostalCode = in.readString();
        geocodeAddressLine = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(addressComplement);
        dest.writeString(address);
        dest.writeString(postcode);
        dest.writeString(city);
        dest.writeString(country);
        dest.writeString(region);
        dest.writeDouble(score);
        dest.writeInt(geocodeType);
        dest.writeString(geocodeCity);
        dest.writeString(geocodePostalCode);
        dest.writeString(geocodeAddressLine);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Geocode> CREATOR = new Creator<Geocode>() {
        @Override
        public Geocode createFromParcel(Parcel in) {
            return new Geocode(in);
        }

        @Override
        public Geocode[] newArray(int size) {
            return new Geocode[size];
        }
    };

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getGeocodeType() {
        return geocodeType;
    }

    public void setGeocodeType(int geocodeType) {
        this.geocodeType = geocodeType;
    }

    public String getGeocodeCity() {
        return geocodeCity;
    }

    public void setGeocodeCity(String geocodeCity) {
        this.geocodeCity = geocodeCity;
    }

    public String getGeocodePostalCode() {
        return geocodePostalCode;
    }

    public void setGeocodePostalCode(String geocodePostalCode) {
        this.geocodePostalCode = geocodePostalCode;
    }

    public String getGeocodeAddressLine() {
        return geocodeAddressLine;
    }

    public void setGeocodeAddressLine(String geocodeAddressLine) {
        this.geocodeAddressLine = geocodeAddressLine;
    }

    @Override
    public String toString() {
        return "Geocode{" +
                "addressComplement='" + addressComplement + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", score=" + score +
                ", geocodeType=" + geocodeType +
                ", geocodeCity='" + geocodeCity + '\'' +
                ", geocodePostalCode='" + geocodePostalCode + '\'' +
                ", geocodeAddressLine='" + geocodeAddressLine + '\'' +
                '}';
    }
}
