package com.example.zeroday.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class IncomesCategory implements Parcelable {
    private String idIncomesCategory;
    private String codeIncomesCategory;
    private String labelIncomesCategory;

    public IncomesCategory(String idIncomesCategory, String codeIncomesCategory, String labelIncomesCategory) {
        this.idIncomesCategory = idIncomesCategory;
        this.codeIncomesCategory = codeIncomesCategory;
        this.labelIncomesCategory = labelIncomesCategory;
    }

    public IncomesCategory() {
    }

    protected IncomesCategory(Parcel in) {
        idIncomesCategory = in.readString();
        codeIncomesCategory = in.readString();
        labelIncomesCategory = in.readString();
    }

    public static final Creator<IncomesCategory> CREATOR = new Creator<IncomesCategory>() {
        @Override
        public IncomesCategory createFromParcel(Parcel in) {
            return new IncomesCategory(in);
        }

        @Override
        public IncomesCategory[] newArray(int size) {
            return new IncomesCategory[size];
        }
    };



    public String getIdIncomesCategory() {
        return idIncomesCategory;
    }

    public void setIdIncomesCategory(String idIncomesCategory) {
        this.idIncomesCategory = idIncomesCategory;
    }

    public String getCodeIncomesCategory() {
        return codeIncomesCategory;
    }

    public void setCodeIncomesCategory(String codeIncomesCategory) {
        this.codeIncomesCategory = codeIncomesCategory;
    }   

    public String getLabelIncomesCategory() {
        return labelIncomesCategory;
    }

    public void setLabelIncomesCategory(String labelIncomesCategory) {
        this.labelIncomesCategory = labelIncomesCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomesCategory that = (IncomesCategory) o;
        return Objects.equals(getIdIncomesCategory(), that.getIdIncomesCategory()) && Objects.equals(getCodeIncomesCategory(), that.getCodeIncomesCategory()) && Objects.equals(getLabelIncomesCategory(), that.getLabelIncomesCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdIncomesCategory(), getCodeIncomesCategory(), getLabelIncomesCategory());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if(idIncomesCategory == null){
            parcel.writeByte((byte) 0);
        }else{
            parcel.writeByte((byte) 1);
            parcel.writeString(idIncomesCategory);
        }
        parcel.writeString(idIncomesCategory);
        parcel.writeString(codeIncomesCategory);
        parcel.writeString(labelIncomesCategory);
    }

    @Override
    public String toString() {
        return "IncomesCategory{" +
                "idIncomesCategory='" + idIncomesCategory + '\'' +
                ", codeIncomesCategory='" + codeIncomesCategory + '\'' +
                ", labelIncomesCategory='" + labelIncomesCategory + '\'' +
                '}';
    }
}
