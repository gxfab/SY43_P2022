package com.sucelloztm.sucelloz.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Transaction;
import androidx.room.Query;
import java.util.List;

import java.lang.String;

@Entity(tableName = "sub_categories",
        foreignKeys = {@ForeignKey(entity = Categories.class,
                parentColumns = "category_id",
                childColumns = "categories_id",
                onDelete = ForeignKey.CASCADE)}
)
public class SubCategories {

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="categories_id")
    private long categoriesId;

    //CONSTRUCTOR
    public SubCategories(){}
    public SubCategories(String name,long categoriesId){
        this.name=name;
        this.categoriesId=categoriesId;
    }
    //GETTER
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public long getCategoriesId(){ return categoriesId; }

    //SETTER
    public void setId(long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCategoriesId(long categoriesId){ this.categoriesId=categoriesId; }


}
