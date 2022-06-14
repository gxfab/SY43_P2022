package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

public class IncomeCategory extends ZeroBaseModel implements Parcelable {
    private String codeIncomeCategory;
    private String labelIncomeCategory;

    public IncomeCategory() {
    }

    public IncomeCategory(String codeIncomeCategory, String labelIncomeCategory) {
        this.codeIncomeCategory = codeIncomeCategory;
        this.labelIncomeCategory = labelIncomeCategory;
    }

    public IncomeCategory(Long id, String codeIncomeCategory, String labelIncomeCategory) {
        this.id = id;
        this.codeIncomeCategory = codeIncomeCategory;
        this.labelIncomeCategory = labelIncomeCategory;
    }

    protected IncomeCategory(Parcel in) {
        codeIncomeCategory = in.readString();
        labelIncomeCategory = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeIncomeCategory);
        dest.writeString(labelIncomeCategory);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IncomeCategory> CREATOR = new Creator<IncomeCategory>() {
        @Override
        public IncomeCategory createFromParcel(Parcel in) {
            return new IncomeCategory(in);
        }

        @Override
        public IncomeCategory[] newArray(int size) {
            return new IncomeCategory[size];
        }
    };

    public String getCodeIncomeCategory() {
        return codeIncomeCategory;
    }

    public void setCodeIncomeCategory(String codeIncomeCategory) {
        this.codeIncomeCategory = codeIncomeCategory;
    }

    public String getLabelIncomeCategory() {
        return labelIncomeCategory;
    }

    public void setLabelIncomeCategory(String labelIncomeCategory) {
        this.labelIncomeCategory = labelIncomeCategory;
    }
}
