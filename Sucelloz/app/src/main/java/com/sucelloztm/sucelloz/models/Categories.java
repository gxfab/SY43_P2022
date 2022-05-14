package com.sucelloztm.sucelloz.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import java.lang.String;

@Entity(tableName = "categories")
public class Categories {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="category_id")
    private long id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="read_only")
    private boolean readOnly;


    //CONSTRUCTOR
    public Categories(){}
    public Categories(String name,boolean readOnly){
        this.name=name;
        this.readOnly=readOnly;
    }
    //GETTER
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public boolean getReadOnly(){
        return readOnly;
    }
    //SETTER
    public void setId(long id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setReadOnly(boolean readOnly){
        this.readOnly=readOnly;
    }


}
