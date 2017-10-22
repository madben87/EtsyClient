package com.ben.etsyclient.model.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

public class MainImage implements Constants, Parcelable {

    @SerializedName(LISTING_IMAGE_ID)
    private long listingImageId;
    @SerializedName(URL_75X75)
    private String url_75x75;
    @SerializedName(URL_170X135)
    private String url_170x135;
    @SerializedName(URL_570XN)
    private String url_570xN;

    public MainImage() {}

    protected MainImage(Parcel in) {
        listingImageId = in.readLong();
        url_75x75 = in.readString();
        url_170x135 = in.readString();
        url_570xN = in.readString();
    }

    public static final Creator<MainImage> CREATOR = new Creator<MainImage>() {
        @Override
        public MainImage createFromParcel(Parcel in) {
            return new MainImage(in);
        }

        @Override
        public MainImage[] newArray(int size) {
            return new MainImage[size];
        }
    };

    public long getListingImageId() {
        return listingImageId;
    }

    public void setListingImageId(long listingImageId) {
        this.listingImageId = listingImageId;
    }

    public String getUrl_75x75() {
        return url_75x75;
    }

    public void setUrl_75x75(String url_75x75) {
        this.url_75x75 = url_75x75;
    }

    public String getUrl_170x135() {
        return url_170x135;
    }

    public void setUrl_170x135(String url_170x135) {
        this.url_170x135 = url_170x135;
    }

    public String getUrl_570xN() {
        return url_570xN;
    }

    public void setUrl_570xN(String url_570xN) {
        this.url_570xN = url_570xN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listingImageId);
        dest.writeString(url_75x75);
        dest.writeString(url_170x135);
        dest.writeString(url_570xN);
    }
}
