package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Infrequent expenses and income entity for the DAO
 */
@Entity(tableName = "infrequent_expenses",
        foreignKeys = {@ForeignKey(entity = SubCategories.class,
                parentColumns = "id",
                childColumns = "sub_categories_id",
                onDelete = ForeignKey.CASCADE)
        })
public class InfrequentExpensesAndIncome {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "amount")
    private int amount;

    @ColumnInfo(name = "sign")
    private String sign;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "sub_categories_id")
    private long subCategoriesId;

    /**
     * Default constructor
     */
    //CONSTRUCTOR
    public InfrequentExpensesAndIncome() {
    }

    /**
     * Custom constructor
     *
     * @param name            name
     * @param amount          amount
     * @param sign            sign, + or -
     * @param date            date
     * @param subCategoriesId id of the associated subcategory
     */
    public InfrequentExpensesAndIncome(String name, int amount, String sign, String date, long subCategoriesId) {
        this.name = name;
        this.amount = amount;
        this.sign = sign;
        this.date = date;
        this.subCategoriesId = subCategoriesId;
    }

    /**
     * Getter
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter
     *
     * @return sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * Getter
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter
     *
     * @return subcategory id
     */
    public long getSubCategoriesId() {
        return subCategoriesId;
    }

    /**
     * Getter
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter
     *
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Getter
     *
     * @param sign sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * Getter
     *
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter
     *
     * @param subCategoriesId subcategory id
     */
    public void setSubCategoriesId(long subCategoriesId) {
        this.subCategoriesId = subCategoriesId;
    }
}
