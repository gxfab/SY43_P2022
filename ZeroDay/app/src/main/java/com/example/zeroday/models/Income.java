package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Income implements Parcelable {
    private Long idIncome;
    private Frequence frequenceIncome;
    private Long amountIncome;
    private String labelIncome;
    private IncomesCategory incomeCategories;

    public Income() {
    }

    public Income(Long idIncome, Frequence frequenceIncome, Long amountIncome, String labelIncome, IncomesCategory incomeCategories) {
        this.idIncome = idIncome;
        this.frequenceIncome = frequenceIncome;
        this.amountIncome = amountIncome;
        this.labelIncome = labelIncome;
        this.incomeCategories = incomeCategories;
    }

    protected Income(Parcel in) {
        if (in.readByte() == 0) {
            idIncome = null;
        } else {
            idIncome = in.readLong();
        }
        if (in.readByte() == 0) {
            amountIncome = null;
        } else {
            amountIncome = in.readLong();
        }
        labelIncome = in.readString();
        incomeCategories = in.readParcelable(IncomesCategory.class.getClassLoader());
    }

    public static final Creator<Income> CREATOR = new Creator<Income>() {
        @Override
        public Income createFromParcel(Parcel in) {
            return new Income(in);
        }

        @Override
        public Income[] newArray(int size) {
            return new Income[size];
        }
    };

    public Long getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(Long idIncome) {
        this.idIncome = idIncome;
    }

    public Frequence getFrequenceIncome() {
        return frequenceIncome;
    }

    public Long getAmmountIncome() {
        return amountIncome;
    }

    public String getLabelIncome() {
        return labelIncome;
    }

    public IncomesCategory getIncomeCategories() {
        return incomeCategories;
    }

    public void setFrequenceIncome(Frequence frequenceIncome) {
        this.frequenceIncome = frequenceIncome;
    }

    public void setAmmountIncome(Long amountIncome) {
        this.amountIncome = amountIncome;
    }

    public void setLabelIncome(String labelIncome) {
        this.labelIncome = labelIncome;
    }

    public void setIncomeCategories(IncomesCategory incomeCategories) {
        this.incomeCategories = incomeCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idIncome == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(idIncome);
        }
        if (amountIncome == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(amountIncome);
        }
        parcel.writeString(labelIncome);
        parcel.writeParcelable(incomeCategories, i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Income)) return false;
        Income income = (Income) o;
        return getIdIncome().equals(income.getIdIncome()) && getFrequenceIncome() == income.getFrequenceIncome() && amountIncome.equals(income.amountIncome) && getLabelIncome().equals(income.getLabelIncome()) && getIncomeCategories().equals(income.getIncomeCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdIncome(), getFrequenceIncome(), amountIncome, getLabelIncome(), getIncomeCategories());
    }

    @Override
    public String toString() {
        return "Income{" +
                "idIncome=" + idIncome +
                ", frequenceIncome=" + frequenceIncome +
                ", amountIncome=" + amountIncome +
                ", labelIncome='" + labelIncome + '\'' +
                ", incomeCategories=" + incomeCategories +
                '}';
    }
}


