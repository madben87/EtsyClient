package com.ben.etsyclient.model.goods;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GoodsList implements Parcelable {

    private int count;
    private ArrayList<Goods> results;

    public GoodsList() {}

    protected GoodsList(Parcel in) {
        count = in.readInt();
        results = in.createTypedArrayList(Goods.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeTypedList(results);
    }
}
