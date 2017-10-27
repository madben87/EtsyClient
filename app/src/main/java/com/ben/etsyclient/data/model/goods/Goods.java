package com.ben.etsyclient.data.model.goods;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Goods implements Constants, Parcelable {

    @SerializedName(LISTING_ID)
    private long listingId;
    @SerializedName(TITLE)
    private String title;
    @SerializedName(DESCRIPTION)
    private String description;
    @SerializedName(PRICE)
    private double price;
    @SerializedName(CURRENCY_CODE)
    private String currencyCode;
    @SerializedName(MAIN_IMAGE)
    private MainImage mainImage;

    public Goods() {}

    protected Goods(Parcel in) {
        listingId = in.readLong();
        title = in.readString();
        description = in.readString();
        price = in.readDouble();
        currencyCode = in.readString();
        mainImage = in.readParcelable(MainImage.class.getClassLoader());
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel in) {
            return new Goods(in);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public MainImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(MainImage mainImage) {
        this.mainImage = mainImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listingId);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeString(currencyCode);
        dest.writeParcelable(mainImage, 0);
    }
}
