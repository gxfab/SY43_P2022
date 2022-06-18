package com.example.sy43.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Table contenant toutes les dépenses faites par l'utilisateur depuis le début, ainsi que la date de ces dépenses, leur montant et leurs catégories
 */

@Entity(tableName = "transaction",
        indices = {@Index(value = {"TransactionID"})},
        foreignKeys = {@ForeignKey(entity = Categorydb.class, parentColumns = "CatID", childColumns = "Category", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = SubCategory.class, parentColumns = "SubCatID", childColumns = "SubCategory", onDelete = ForeignKey.CASCADE)})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int TransactionID;

    @ColumnInfo(name = "Value")
    private float Value;

    @ColumnInfo(name = "Category")
    private int Category;

    @ColumnInfo(name = "SubCategory")
    private int SubCategory;

    @ColumnInfo(name = "Date")
    private long date;

    public int getTransactionID() {
        return TransactionID;
    }

    public float getValue() {
        return Value;
    }

    public int getCategory() {
        return Category;
    }

    public int getSubCategory() {
        return SubCategory;
    }

    public long getDate() {
        return date;
    }

    public void setTransactionID(int TransactionID) {
        this.TransactionID = TransactionID;
    }

    public void setValue(float Value) {
        this.Value = Value;
    }

    public void setCategory(int Category) {
        this.Category = Category;
    }

    public void setSubCategory(int SubCategory) {
        this.SubCategory = SubCategory;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
