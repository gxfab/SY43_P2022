package om.example.sy43.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Category", indices = 
{@Index(value = {"CatName"},unique=true)})

public class Category {
    @PrimaryKey(autoGenerate = true)
    private int CatID;
    
    @ColumnInfo( name = "CatName")
    private String CatName;

    @ColumnInfo( name = "MaxValue")
    private float MaxValue;

    @ColumnInfo( name = "CurrentValue")
    private float CurrentValue;

    public int getCatID(){
        return CatID;
    }

    public String getCatName(){
        return CatName;
    }

    public float getMaxValue(){
        return MaxValue;
    }

    public float CurrentValue(){
        return CurrentValue;
    }

    public void setCatID(int CatID){
        this.CatID=CatID;
    }

    public void setCatName(string CatName){
        this.CatName = CatName;
    }

    public void setMaxValue(float MaxValue){
        this.MaxValue=MaxValue;
    }

    public void setCurrentValue(float CurrentValue){
        this.CurrentValue=CurrentValue;
    }
}
