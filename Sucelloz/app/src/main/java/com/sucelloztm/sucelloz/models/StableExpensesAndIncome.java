package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Stable expenses and income entity for the DAO
 */
@Entity(tableName = "stable_expenses",
        foreignKeys = {@ForeignKey(entity = SubCategories.class,
                parentColumns = "id",
                childColumns = "sub_categories_id",
                onDelete = ForeignKey.CASCADE)
        }
)
public class StableExpensesAndIncome {
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

    @ColumnInfo(name = "frequency")
    private int frequency;

    @ColumnInfo(name = "sub_categories_id")
    private long subCategoriesId;


    /**
     * Default constructor
     */
    public StableExpensesAndIncome() {
    }

    /**
     * Custom constructor
     *
     * @param name            name
     * @param amount          amount
     * @param sign            sign
     * @param date            date
     * @param frequency       frequency
     * @param subCategoriesId id of the subcategory
     */
    public StableExpensesAndIncome(String name, int amount, String sign, String date, int frequency, long subCategoriesId) {
        this.name = name;
        this.amount = amount;
        this.sign = sign;
        this.date = date;
        this.frequency = frequency;
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
     * @return frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Getter
     *
     * @return id of the subcategory
     */
    public long getSubCategoriesId() {
        return subCategoriesId;
    }


    /**
     * Setter
     *
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter
     *
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Setter
     *
     * @param sign sign
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * Setter
     *
     * @param date date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Setter
     *
     * @param frequency frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * Setter
     *
     * @param subCategoriesId id of the subcategory
     */
    public void setSubCategoriesId(long subCategoriesId) {
        this.subCategoriesId = subCategoriesId;
    }
}
