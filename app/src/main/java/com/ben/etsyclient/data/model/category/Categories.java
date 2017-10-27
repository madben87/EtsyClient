package com.ben.etsyclient.data.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Categories implements Parcelable {

    private int count;
    private ArrayList<Category> results;

    protected Categories(Parcel in) {
        count = in.readInt();
        results = new ArrayList<>();
        in.readList(results, getClass().getClassLoader());
    }

    public Categories() {}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Category> getResults() {
        return results;
    }

    public void setResults(ArrayList<Category> results) {
        this.results = results;
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeList(results);
    }
}
