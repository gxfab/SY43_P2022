package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Income extends ZeroBaseModel implements Parcelable {   
    
    private Frequency frequencyIncome;
    private Long amountIncome;
    private String labelIncome;
    private IncomeCategory incomeCategories;
    private Budget budget;

    public Income() {
    }

    public Income(Frequency frequencyIncome, Long amountIncome, String labelIncome, IncomeCategory incomeCategories) {
        this.frequencyIncome = frequencyIncome;
        this.amountIncome = amountIncome;
        this.labelIncome = labelIncome;
        this.incomeCategories = incomeCategories;
    }

    public Income(Frequency frequencyIncome, Long amountIncome, String labelIncome, IncomeCategory incomeCategories, Budget budget) {
        this.frequencyIncome = frequencyIncome;
        this.amountIncome = amountIncome;
        this.labelIncome = labelIncome;
        this.incomeCategories = incomeCategories;
    }

    public Income(Long id, Frequency frequencyIncome, Long amountIncome, String labelIncome, IncomeCategory incomeCategories, Budget budget) {
        this.id = id;
        this.frequencyIncome = frequencyIncome;
        this.amountIncome = amountIncome;
        this.labelIncome = labelIncome;
        this.incomeCategories = incomeCategories;
    }

    protected Income(Parcel in) {
        if (in.readByte() == 0) {
            amountIncome = null;
        } else {
            amountIncome = in.readLong();
        }
        labelIncome = in.readString();
        incomeCategories = in.readParcelable(IncomeCategory.class.getClassLoader());
        budget = in.readParcelable(Budget.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (amountIncome == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(amountIncome);
        }
        dest.writeString(labelIncome);
        dest.writeParcelable(incomeCategories, flags);
        dest.writeParcelable(budget, flags);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public Frequency getFrequencyIncome() {
        return frequencyIncome;
    }

    public void setFrequencyIncome(Frequency frequencyIncome) {
        this.frequencyIncome = frequencyIncome;
    }

    public Long getAmountIncome() {
        return amountIncome;
    }

    public void setAmountIncome(Long amountIncome) {
        this.amountIncome = amountIncome;
    }

    public String getLabelIncome() {
        return labelIncome;
    }

    public void setLabelIncome(String labelIncome) {
        this.labelIncome = labelIncome;
    }

    public IncomeCategory getIncomeCategories() {
        return incomeCategories;
    }

    public void setIncomeCategories(IncomeCategory incomeCategories) {
        this.incomeCategories = incomeCategories;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Income{" +
                "frequencyIncome=" + frequencyIncome +
                ", amountIncome=" + amountIncome +
                ", labelIncome='" + labelIncome + '\'' +
                ", incomeCategories=" + incomeCategories +
                ", budget=" + budget +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return getFrequencyIncome() == income.getFrequencyIncome() && getAmountIncome().equals(income.getAmountIncome()) && getLabelIncome().equals(income.getLabelIncome()) && getIncomeCategories().equals(income.getIncomeCategories()) && getBudget().equals(income.getBudget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFrequencyIncome(), getAmountIncome(), getLabelIncome(), getIncomeCategories(), getBudget());
    }
}


