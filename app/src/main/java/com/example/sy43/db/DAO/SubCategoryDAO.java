/*
import java.util.List;
import om.example.sy43.db.entity.SubCategory;

@DAO
public interface CategoryDAO{

    @Query("SELECT * FROM SubCategory WHERE SubCatID LIKE :SubCatID")
    Category findByID(int SubCatID);

    @Query("SELECT MaxValue,CurrentValue FROM SubCategory")
    float findValues();

    @Query("SELECT * FROM SubCategory WHERE Category LIKE :Category")
    SubCategory findByCategorie(int category);

    @Delete
    void deleteSubCategory(SubCategory Subcategory);
}
*/