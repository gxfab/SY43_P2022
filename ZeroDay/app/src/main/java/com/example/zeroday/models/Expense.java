package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense extends ZeroBaseModel implements Parcelable {

    private String labelExpense;
    private Double amountExpense;
    private String dateExpense;
    private Frequency frequencyExpense;
    private ExpenseCategory categoryExpense;
    private String commentExpense;

    public Expense() {
    }

    public Expense(String labelExpense, Double amountExpense, String dateExpense, Frequency frequencyExpense, ExpenseCategory categoryExpense, String commentExpense) {
        this.labelExpense = labelExpense;
        this.amountExpense = amountExpense;
        this.dateExpense = dateExpense;
        this.frequencyExpense = frequencyExpense;
        this.categoryExpense = categoryExpense;
        this.commentExpense = commentExpense;
    }

    public Expense(Long id, String labelExpense, Double amountExpense, String dateExpense, Frequency frequencyExpense, ExpenseCategory categoryExpense, String commentExpense) {
        this.id = id;
        this.labelExpense = labelExpense;
        this.amountExpense = amountExpense;
        this.dateExpense = dateExpense;
        this.frequencyExpense = frequencyExpense;
        this.categoryExpense = categoryExpense;
        this.commentExpense = commentExpense;
    }

    protected Expense(Parcel in) {
        labelExpense = in.readString();
        if (in.readByte() == 0) {
            amountExpense = null;
        } else {
            amountExpense = in.readDouble();
        }
        dateExpense = in.readString();
        categoryExpense = in.readParcelable(ExpenseCategory.class.getClassLoader());
        commentExpense = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(labelExpense);
        if (amountExpense == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(amountExpense);
        }
        dest.writeString(dateExpense);
        dest.writeParcelable(categoryExpense, flags);
        dest.writeString(commentExpense);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public String getLabelExpense() {
        return labelExpense;
    }

    public void setLabelExpense(String labelExpense) {
        this.labelExpense = labelExpense;
    }

    public Double getAmountExpense() {
        return amountExpense;
    }

    public void setAmountExpense(Double amountExpense) {
        this.amountExpense = amountExpense;
    }

    public String getDateExpense() {
        return dateExpense;
    }

    public void setDateExpense(String dateExpense) {
        this.dateExpense = dateExpense;
    }

    public Frequency getFrequenceExpense() {
        return frequencyExpense;
    }

    public void setFrequenceExpense(Frequency frequencyExpense) {
        this.frequencyExpense = frequencyExpense;
    }

    public ExpenseCategory getCategoryExpense() {
        return categoryExpense;
    }

    public void setCategoryExpense(ExpenseCategory categoryExpense) {
        this.categoryExpense = categoryExpense;
    }

    public String getCommentExpense() {
        return commentExpense;
    }

    public void setCommentExpense(String commentExpense) {
        this.commentExpense = commentExpense;
    }
}
