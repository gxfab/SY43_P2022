package com.sucelloztm.sucelloz.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Subcategories Entity for the DAO
 */
@Entity(tableName = "sub_categories",
        foreignKeys = {@ForeignKey(entity = Categories.class,
                parentColumns = "category_id",
                childColumns = "categories_id",
                onDelete = ForeignKey.CASCADE)}
)
public class SubCategories {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "categories_id")
    private long categoriesId;

    /**
     * Default constructor
     */
    public SubCategories() {
    }

    /**
     * Custom constructor
     *
     * @param name         name
     * @param categoriesId id of the category
     */
    public SubCategories(String name, long categoriesId) {
        this.name = name;
        this.categoriesId = categoriesId;
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
     * @return category id
     */
    public long getCategoriesId() {
        return categoriesId;
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
     * @param categoriesId id of the category
     */
    public void setCategoriesId(long categoriesId) {
        this.categoriesId = categoriesId;
    }


}
