package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Budget implements Parcelable {
    private Long idBudget;
    private String codeBudget;
    private String startDateBuget;
    private String endDateBuget;
    private Frequence frequence;

    public Budget(Long idBudget, String codeBudget, String startDateBuget, String endDateBuget, Frequence frequence) {
        this.idBudget = idBudget;
        this.codeBudget = codeBudget;
        this.startDateBuget = startDateBuget;
        this.endDateBuget = endDateBuget;
        this.frequence = frequence;
    }

    public Budget() {
    }

    protected Budget(Parcel in) {
        if (in.readByte() == 0) {
            idBudget = null;
        } else {
            idBudget = in.readLong();
        }
        codeBudget = in.readString();
        startDateBuget = in.readString();
        endDateBuget = in.readString();
    }

    public static final Creator<Budget> CREATOR = new Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel in) {
            return new Budget(in);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };

    public Long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Long idBudget) {
        this.idBudget = idBudget;
    }

    public String getCodeBudget() {
        return codeBudget;
    }

    public void setCodeBudget(String codeBudget) {
        this.codeBudget = codeBudget;
    }

    public String getStartDateBuget() {
        return startDateBuget;
    }

    public void setStartDateBuget(String startDateBuget) {
        this.startDateBuget = startDateBuget;
    }

    public String getEndDateBuget() {
        return endDateBuget;
    }

    public void setEndDateBuget(String endDateBuget) {
        this.endDateBuget = endDateBuget;
    }

    public Frequence getFrequence() {
        return frequence;
    }

    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Budget)) return false;
        Budget budget = (Budget) o;
        return getIdBudget().equals(budget.getIdBudget()) && getCodeBudget().equals(budget.getCodeBudget()) && getStartDateBuget().equals(budget.getStartDateBuget()) && Objects.equals(getEndDateBuget(), budget.getEndDateBuget()) && getFrequence() == budget.getFrequence();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdBudget(), getCodeBudget(), getStartDateBuget(), getEndDateBuget(), getFrequence());
    }

    @Override
    public String toString() {
        return "Budget{" +
                "idBudget=" + idBudget +
                ", codeBudget='" + codeBudget + '\'' +
                ", startDateBuget='" + startDateBuget + '\'' +
                ", endDateBuget='" + endDateBuget + '\'' +
                ", frequence=" + frequence +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idBudget == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(idBudget);
        }
        parcel.writeString(codeBudget);
        parcel.writeString(startDateBuget);
        parcel.writeString(endDateBuget);
    }
}
