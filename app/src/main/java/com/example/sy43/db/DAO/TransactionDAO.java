/*
import java.utitl.List;
import om.example.sy43.db.entity.Category;

@DAO
public interface CategoryDAO{

    @Query("SELECT * FROM Transaction WHERE SubCatID LIKE :SubCatID")
    Category findByID(int SubCatID);

    @Query("SELECT Value FROM Transaction")
    float findValues();

    @Query("SELECT * FROM Transaction WHERE Category LIKE :Category")
    Transaction findByCategorie(int category);

    @Query("SELECT * FROM Transaction WHERE SubCategory LIKE :SubCategory")
    Transaction findBySubCategorie(int Subcategory);

    @Delete
    void deleteTransaction(Transaction transaction);
}
 */