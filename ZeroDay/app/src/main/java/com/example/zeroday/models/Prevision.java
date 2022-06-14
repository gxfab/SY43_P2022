package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Prevision extends ZeroBaseModel implements Parcelable {

    private Budget budgetPrevison;
    private String amountPrevision;
    private String datePrevision;
    private String timestampPrevisionStartDate;
    private String timestampPrevisionEndDate;
    private String commentPrevision;

    public Prevision() {
    }

    public Prevision(Budget budgetPrevison, String amountPrevision, String datePrevision, String timestampPrevisionStartDate, String timestampPrevisionEndDate, String commentPrevision) {
        this.budgetPrevison = budgetPrevison;
        this.amountPrevision = amountPrevision;
        this.datePrevision = datePrevision;
        this.timestampPrevisionStartDate = timestampPrevisionStartDate;
        this.timestampPrevisionEndDate = timestampPrevisionEndDate;
    }

    public Prevision(Long id, Budget budgetPrevison, String amountPrevision, String datePrevision, String timestampPrevisionStartDate, String timestampPrevisionEndDate, String commentPrevision) {
        this.id = id;
        this.budgetPrevison = budgetPrevison;
        this.amountPrevision = amountPrevision;
        this.datePrevision = datePrevision;
        this.timestampPrevisionStartDate = timestampPrevisionStartDate;
        this.timestampPrevisionEndDate = timestampPrevisionEndDate;
    }

    protected Prevision(Parcel in) {
        budgetPrevison = in.readParcelable(Budget.class.getClassLoader());
        amountPrevision = in.readString();
        datePrevision = in.readString();
        timestampPrevisionStartDate = in.readString();
        timestampPrevisionEndDate = in.readString();
        commentPrevision = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(budgetPrevison, flags);
        dest.writeString(amountPrevision);
        dest.writeString(datePrevision);
        dest.writeString(timestampPrevisionStartDate);
        dest.writeString(timestampPrevisionEndDate);
        dest.writeString(commentPrevision);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Prevision> CREATOR = new Creator<Prevision>() {
        @Override
        public Prevision createFromParcel(Parcel in) {
            return new Prevision(in);
        }

        @Override
        public Prevision[] newArray(int size) {
            return new Prevision[size];
        }
    };

    public Budget getBudgetPrevison() {
        return budgetPrevison;
    }

    public void setBudgetPrevison(Budget budgetPrevison) {
        this.budgetPrevison = budgetPrevison;
    }

    public String getAmountPrevision() {
        return amountPrevision;
    }

    public void setAmountPrevision(String amountPrevision) {
        this.amountPrevision = amountPrevision;
    }

    public String getDatePrevision() {
        return datePrevision;
    }

    public void setDatePrevision(String datePrevision) {
        this.datePrevision = datePrevision;
    }

    public String getTimestampPrevisionStartDate() {
        return timestampPrevisionStartDate;
    }

    public void setTimestampPrevisionStartDate(String timestampPrevisionStartDate) {
        this.timestampPrevisionStartDate = timestampPrevisionStartDate;
    }

    public String getTimestampPrevisionEndDate() {
        return timestampPrevisionEndDate;
    }

    public void setTimestampPrevisionEndDate(String timestampPrevisionEndDate) {
        this.timestampPrevisionEndDate = timestampPrevisionEndDate;
    }

    public String getCommentPrevision() {
        return commentPrevision;
    }

    public void setCommentPrevision(String commentPrevision) {
        this.commentPrevision = commentPrevision;
    }
}
