package com.ben.etsyclient.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.etsyclient.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Category implements Constants, Parcelable {

    @SerializedName(CATEGORY_ID)
    private long categoryId;
    @SerializedName(NAME)
    private String name;
    @SerializedName(META_TITLE)
    private String metaTitle;
    @SerializedName(META_KEYWORDS)
    private String metaKeywords;
    @SerializedName(META_DESCRIPTION)
    private String metaDescription;
    @SerializedName(PAGE_DESCRIPTION)
    private String pageDescription;
    @SerializedName(PAGE_TITLE)
    private String pageTitle;
    @SerializedName(CATEGORY_NAME)
    private String categoryName;
    @SerializedName(SHORT_NAME)
    private String shortName;
    @SerializedName(LONG_NAME)
    private String longName;
    @SerializedName(NUM_CHILDREN)
    private int numChildren;

    public Category() {}

    protected Category(Parcel in) {
        categoryId = in.readLong();
        name = in.readString();
        metaTitle = in.readString();
        metaKeywords = in.readString();
        metaDescription = in.readString();
        pageDescription = in.readString();
        pageTitle = in.readString();
        categoryName = in.readString();
        shortName = in.readString();
        longName = in.readString();
        numChildren = in.readInt();
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(categoryId);
        dest.writeString(name);
        dest.writeString(metaTitle);
        dest.writeString(metaKeywords);
        dest.writeString(metaDescription);
        dest.writeString(pageDescription);
        dest.writeString(pageTitle);
        dest.writeString(categoryName);
        dest.writeString(shortName);
        dest.writeString(longName);
        dest.writeInt(numChildren);
    }
}
