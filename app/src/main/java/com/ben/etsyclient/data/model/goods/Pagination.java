package com.ben.etsyclient.data.model.goods;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Pagination implements Constants, Parcelable {

    @SerializedName(EFFECTIVE_LIMIT)
    private int effectiveLimit;
    @SerializedName(EFFECTIVE_OFFSET)
    private int effectiveOffset;
    @SerializedName(NEXT_OFFSET)
    private int nextOffset;
    @SerializedName(EFFECTIVE_PAGE)
    private int effectivePage;
    @SerializedName(NEXT_PAGE)
    private int nextPage;

    public Pagination() {}

    protected Pagination(Parcel in) {
        effectiveLimit = in.readInt();
        effectiveOffset = in.readInt();
        nextOffset = in.readInt();
        effectivePage = in.readInt();
        nextPage = in.readInt();
    }

    public static final Creator<Pagination> CREATOR = new Creator<Pagination>() {
        @Override
        public Pagination createFromParcel(Parcel in) {
            return new Pagination(in);
        }

        @Override
        public Pagination[] newArray(int size) {
            return new Pagination[size];
        }
    };

    public int getEffectiveLimit() {
        return effectiveLimit;
    }

    public void setEffectiveLimit(int effectiveLimit) {
        this.effectiveLimit = effectiveLimit;
    }

    public int getEffectiveOffset() {
        return effectiveOffset;
    }

    public void setEffectiveOffset(int effectiveOffset) {
        this.effectiveOffset = effectiveOffset;
    }

    public int getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(int nextOffset) {
        this.nextOffset = nextOffset;
    }

    public int getEffectivePage() {
        return effectivePage;
    }

    public void setEffectivePage(int effectivePage) {
        this.effectivePage = effectivePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(effectiveLimit);
        dest.writeInt(effectiveOffset);
        dest.writeInt(nextOffset);
        dest.writeInt(effectivePage);
        dest.writeInt(nextPage);
    }
}
