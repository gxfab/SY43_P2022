package com.example.cookutproject.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = @ForeignKey(entity = Aliment.class,

        parentColumns = "id",

        childColumns = "id_aliment"))
public class Legume extends Aliment{
    @PrimaryKey
    int id;
    String title;
    int id_aliment;
    Legume(int id,int price, String title) {
        super(id,price);
        this.title=title;
    }

    //GETTER
    public String getTitle(){
        return title;
    }
    public int getId_aliment(){
        return id_aliment;
    }

    //SETTER
    public void setTitle(String title){
        this.title=title;
    }

    public void setId_aliment(int id_aliment){
        this.id_aliment=id_aliment;
    }
}
