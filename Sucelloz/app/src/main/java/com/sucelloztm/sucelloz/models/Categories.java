package com.sucelloztm.sucelloz.models;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import java.lang.String;

/**
 * category entity for the dao
 */
@Entity(tableName = "categories")
public class Categories {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="category_id")
    private long id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="read_only")
    private boolean readOnly;


    /**
     * default constructor
     */
    //CONSTRUCTOR
    public Categories(){}

    /**
     * custom constructor
     * @param name name of the category
     * @param readOnly state of the category
     */
    public Categories(String name,boolean readOnly){
        this.name=name;
        this.readOnly=readOnly;
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
     * @return state
     */
    public boolean getReadOnly(){
        return readOnly;
    }

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
     * @param readOnly state
     */
    public void setReadOnly(boolean readOnly){
        this.readOnly=readOnly;
    }


}
