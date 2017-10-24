package com.ben.etsyclient.model.goods;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoodsList implements Parcelable, Constants {

    private int count;
    private ArrayList<Goods> results;
    @SerializedName(PARAMS)
    private Params params;
    @SerializedName(PAGINATION)
    private Pagination pagination;

    public GoodsList() {}

    protected GoodsList(Parcel in) {
        count = in.readInt();
        results = in.createTypedArrayList(Goods.CREATOR);
        params = in.readParcelable(Params.class.getClassLoader());
        pagination = in.readParcelable(Pagination.class.getClassLoader());
    }

    public static final Creator<GoodsList> CREATOR = new Creator<GoodsList>() {
        @Override
        public GoodsList createFromParcel(Parcel in) {
            return new GoodsList(in);
        }

        @Override
        public GoodsList[] newArray(int size) {
            return new GoodsList[size];
        }
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Goods> getResults() {
        return results;
    }

    public void setResults(ArrayList<Goods> results) {
        this.results = results;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeTypedList(results);
        dest.writeParcelable(params, 0);
        dest.writeParcelable(pagination, 0);
    }
}
