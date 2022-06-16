package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class ExpenseCategory extends ZeroBaseModel implements Parcelable {

    private String codeExpenseCategory;
    private String labelExpenseCategory;

    public ExpenseCategory() {
    }

    public ExpenseCategory(String codeExpenseCategory, String labelExpenseCategory) {
        this.codeExpenseCategory = codeExpenseCategory;
        this.labelExpenseCategory = labelExpenseCategory;
    }

    public ExpenseCategory(Long id, String codeExpenseCategory, String labelExpenseCategory) {
        this.id = id;
        this.codeExpenseCategory = codeExpenseCategory;
        this.labelExpenseCategory = labelExpenseCategory;
    }

    protected ExpenseCategory(Parcel in) {
        codeExpenseCategory = in.readString();
        labelExpenseCategory = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codeExpenseCategory);
        dest.writeString(labelExpenseCategory);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExpenseCategory> CREATOR = new Creator<ExpenseCategory>() {
        @Override
        public ExpenseCategory createFromParcel(Parcel in) {
            return new ExpenseCategory(in);
        }

        @Override
        public ExpenseCategory[] newArray(int size) {
            return new ExpenseCategory[size];
        }
    };

    public String getCodeExpenseCategory() {
        return codeExpenseCategory;
    }

    public void setCodeExpenseCategory(String codeExpenseCategory) {
        this.codeExpenseCategory = codeExpenseCategory;
    }

    public String getLabelExpenseCategory() {
        return labelExpenseCategory;
    }

    public void setLabelExpenseCategory(String labelExpenseCategory) {
        this.labelExpenseCategory = labelExpenseCategory;
    }
}
