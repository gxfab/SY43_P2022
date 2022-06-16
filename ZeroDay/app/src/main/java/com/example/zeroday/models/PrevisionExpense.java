package com.example.zeroday.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class PrevisionExpense extends ZeroBaseModel implements Parcelable {
    private String labelPrevisionExpense;
    private Double amountPrevisionExpense;
    private String commentPrevisionExpense;
    private ExpenseCategory categoryPrevisionExpense;

    public PrevisionExpense() {
    }

    public PrevisionExpense(String labelPrevisionExpense, Double amountPrevisionExpense, String commentPrevisionExpense, ExpenseCategory categoryPrevisionExpense) {
        this.labelPrevisionExpense = labelPrevisionExpense;
        this.amountPrevisionExpense = amountPrevisionExpense;
        this.commentPrevisionExpense = commentPrevisionExpense;
        this.categoryPrevisionExpense = categoryPrevisionExpense;
    }
    
    public PrevisionExpense(Long id, String labelPrevisionExpense, Double amountPrevisionExpense, String commentPrevisionExpense, ExpenseCategory categoryPrevisionExpense) {
        this.id = id;
        this.labelPrevisionExpense = labelPrevisionExpense;
        this.amountPrevisionExpense = amountPrevisionExpense;
        this.commentPrevisionExpense = commentPrevisionExpense;
        this.categoryPrevisionExpense = categoryPrevisionExpense;
    }

    protected PrevisionExpense(Parcel in) {
        labelPrevisionExpense = in.readString();
        if (in.readByte() == 0) {
            amountPrevisionExpense = null;
        } else {
            amountPrevisionExpense = in.readDouble();
        }
        commentPrevisionExpense = in.readString();
        categoryPrevisionExpense = in.readParcelable(ExpenseCategory.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(labelPrevisionExpense);
        if (amountPrevisionExpense == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(amountPrevisionExpense);
        }
        dest.writeString(commentPrevisionExpense);
        dest.writeParcelable(categoryPrevisionExpense, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PrevisionExpense> CREATOR = new Creator<PrevisionExpense>() {
        @Override
        public PrevisionExpense createFromParcel(Parcel in) {
            return new PrevisionExpense(in);
        }

        @Override
        public PrevisionExpense[] newArray(int size) {
            return new PrevisionExpense[size];
        }
    };

    public String getLabelPrevisionExpense() {
        return labelPrevisionExpense;
    }

    public void setLabelPrevisionExpense(String labelPrevisionExpense) {
        this.labelPrevisionExpense = labelPrevisionExpense;
    }

    public Double getAmountPrevisionExpense() {
        return amountPrevisionExpense;
    }

    public void setAmountPrevisionExpense(Double amountPrevisionExpense) {
        this.amountPrevisionExpense = amountPrevisionExpense;
    }

    public String getCommentPrevisionExpense() {
        return commentPrevisionExpense;
    }

    public void setCommentPrevisionExpense(String commentPrevisionExpense) {
        this.commentPrevisionExpense = commentPrevisionExpense;
    }

    public ExpenseCategory getCategoryPrevisionExpense() {
        return categoryPrevisionExpense;
    }

    public void setCategoryPrevisionExpense(ExpenseCategory categoryPrevisionExpense) {
        this.categoryPrevisionExpense = categoryPrevisionExpense;
    }
}
