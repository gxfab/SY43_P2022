package com.sucelloztm.sucelloz.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Transaction;
import androidx.room.Query;
import java.util.List;

import java.lang.String;

/**
 * entity for the subcategory of the dao
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

    @ColumnInfo(name="categories_id")
    private long categoriesId;

    /**
     * default constructor
     */
    public SubCategories(){}

    /**
     * custom constructor
     * @param name  name
     * @param categoriesId id of the category
     */
    public SubCategories(String name,long categoriesId){
        this.name=name;
        this.categoriesId=categoriesId;
    }

    /**
     * getter
     * @return id
     */
    public long getId(){
        return id;
    }

    /**
     * getter
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * getter
     * @return category id
     */
    public long getCategoriesId(){ return categoriesId; }


    /**
     * setter
     * @param id id
     */
    public void setId(long id){
        this.id=id;
    }

    /**
     * setter
     * @param name name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * setter
     * @param categoriesId id of the category
     */
    public void setCategoriesId(long categoriesId){ this.categoriesId=categoriesId; }


}
