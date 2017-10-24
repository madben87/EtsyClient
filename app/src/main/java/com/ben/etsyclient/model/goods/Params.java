package com.ben.etsyclient.model.goods;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Params implements Constants, Parcelable {

    @SerializedName(KEYWORD)
    private String keywords;
    @SerializedName(CATEGORY)
    private String category;

    public Params() {}

    protected Params(Parcel in) {
        keywords = in.readString();
        category = in.readString();
    }

    public static final Creator<Params> CREATOR = new Creator<Params>() {
        @Override
        public Params createFromParcel(Parcel in) {
            return new Params(in);
        }

        @Override
        public Params[] newArray(int size) {
            return new Params[size];
        }
    };

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(keywords);
        dest.writeString(category);
    }

    //https://openapi.etsy.com/v2/listings/active?api_key=kg64vu4dsbfzm5o6fgl27vf8&category=paper_goods&keywords=terminator&includes=MainImage&limit=25&offset=0
    //&limit=25&offset=0
}
