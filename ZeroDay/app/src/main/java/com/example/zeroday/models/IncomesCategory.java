package com.example.zeroday.models;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Objects;

public class IncomesCategory implements Parcelable {
    private String idCategoryType;
    private String codeCategoryType;
    private String labelCategoryType;

    public IncomesCategory(String idCategoryType, String codeCategoryType, String labelCategoryType) {
        this.idCategoryType = idCategoryType;
        this.codeCategoryType = codeCategoryType;
        this.labelCategoryType = labelCategoryType;
    }

    public IncomesCategory() {
    }

    protected IncomesCategory(Parcel in) {
        idCategoryType = in.readString();
        codeCategoryType = in.readString();
        labelCategoryType = in.readString();
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



    public String getIdCategoryType() {
        return idCategoryType;
    }

    public void setIdCategoryType(String idCategoryType) {
        this.idCategoryType = idCategoryType;
    }

    public String getCodeCategoryType() {
        return codeCategoryType;
    }

    public void setCodeCategoryType(String codeCategoryType) {
        this.codeCategoryType = codeCategoryType;
    }   

    public String getLabelCategoryType() {
        return labelCategoryType;
    }

    public void setLabelCategoryType(String labelCategoryType) {
        this.labelCategoryType = labelCategoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomesCategory that = (IncomesCategory) o;
        return Objects.equals(getIdCategoryType(), that.getIdCategoryType()) && Objects.equals(getCodeCategoryType(), that.getCodeCategoryType()) && Objects.equals(getLabelCategoryType(), that.getLabelCategoryType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCategoryType(), getCodeCategoryType(), getLabelCategoryType());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if(idCategoryType == null){
            parcel.writeByte((byte) 0);
        }else{
            parcel.writeByte((byte) 1);
            parcel.writeString(idCategoryType);
        }
        parcel.writeString(idCategoryType);
        parcel.writeString(codeCategoryType);
        parcel.writeString(labelCategoryType);
    }
}
