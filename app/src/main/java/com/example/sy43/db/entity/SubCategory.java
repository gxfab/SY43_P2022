package om.example.sy43.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Locale.Category;

import android.arch.persistence.roomForeignKey;

@Entity(tableName = "SubCategory",indices ={
@Index(value = {"SubCatID"}, unique = true)},
foreignKeys ={@ForeignKey(entity = Category.class, parentColumns ="CatID", childColumns = "Category")})

public class SubCategory {
    @PrimaryKey(autoGenerate = true)
    private int SubCatID;
    
    @ColumnInfo( name = "SubCatName")
    private String SubCatName;

    @ColumnInfo( name = "MaxValue")
    private float MaxValue;

    @ColumnInfo( name = "CurrentValue")
    private float CurrentValue;

    @ColumnInfo (name = "Category")
    private int Category;

    public int getSubCatID(){
        return SubCatID;
    }

    public String getSubCatName(){
        return SubCatName;
    }

    public float getMaxValue(){
        return MaxValue;
    }

    public float CurrentValue(){
        return CurrentValue;
    }

    public int getCategory(){
        return Category;
    }

    public void setSubCatID(int SubCatID){
        this.SubCatID=SubCatID;
    }

    public void setSubCatName(string SubCatName){
        this.SubCatName = SubCatName;
    }

    public void setMaxValue(float MaxValue){
        this.MaxValue=MaxValue;
    }

    public void setCurrentValue(float CurrentValue){
        this.CurrentValue=CurrentValue;
    }

    public void setCategory(int Category){
        this.Category=Category;
    }
}
