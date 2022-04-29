package om.example.sy43.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.roomForeignKey;

@Entity(tableName = "transaction", indices = {@Index(value = {"ID_Transaction", "Value", "Name", "Category", "SubCategory"}
    unique = true)})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int ID_Transaction;

    @ColumnInfo(name = "Value")
    private float Value;

    @ColumnInfo(name = "Name")
    private String Name;

    @ColumnInfo(name = "Category")
    private int Category;

    @ColumnInfo(name = "SubCategory")
    private int SubCategory;
}
