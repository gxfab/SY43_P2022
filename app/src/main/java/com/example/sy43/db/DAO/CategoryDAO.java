/*
import java.utiL.List;
import om.example.sy43.db.entity.Category;

@DAO
public interface CategoryDAO{

    @Query("SELECT * FROM Category WHERE CatID LIKE :CatID")
    Category findByID(int CatID);

    @Query("SELECT MaxValue,CurrentValue FROM Category")
    float findValues();

    @Delete
    void deleteCategory(Category category);
}
*/