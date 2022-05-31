package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class ExpenseCategory implements Parcelable {
    private Long idExpenseCategory;
    private String codeExpenseCategory;
    private String labelExpenseCategory;

    public ExpenseCategory(Long idExpenseCategory, String codeExpenseCategory, String labelExpenseCategory) {
        this.idExpenseCategory = idExpenseCategory;
        this.codeExpenseCategory = codeExpenseCategory;
        this.labelExpenseCategory = labelExpenseCategory;
    }

    public ExpenseCategory() {
    }

    protected ExpenseCategory(Parcel in) {
        idExpenseCategory = in.readLong();
        codeExpenseCategory = in.readString();
        labelExpenseCategory = in.readString();
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

    public Long getIdExpenseCategory() {
        return idExpenseCategory;
    }

    public void setIdExpenseCategory(Long idExpenseCategory) {
        this.idExpenseCategory = idExpenseCategory;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseCategory that = (ExpenseCategory) o;
        return Objects.equals(idExpenseCategory, that.idExpenseCategory) &&
                Objects.equals(codeExpenseCategory, that.codeExpenseCategory) &&
                Objects.equals(labelExpenseCategory, that.labelExpenseCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExpenseCategory, codeExpenseCategory, labelExpenseCategory);
    }

    @Override
    public String toString() {
        return "ExpenseCategory{" +
                "idExpenseCategory=" + idExpenseCategory +
                ", codeExpenseCategory='" + codeExpenseCategory + '\'' +
                ", labelExpenseCategory='" + labelExpenseCategory + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if(idExpenseCategory == null){
            parcel.writeByte((byte) 0);
        }else{
            parcel.writeByte((byte) 1);
            parcel.writeLong(idExpenseCategory);
        }
        parcel.writeString(codeExpenseCategory);
        parcel.writeString(labelExpenseCategory);

    }
}
