package fr.sy43.studzero.sqlite.model;

/**
 * This class represents a category type
 */
public class CategoryType {
    /**
     * Id of the category type
     */
    private int idCategoryType;
    /**
     * Name of the cateory type
     */
    private String nameCategory;

    /**
     * Constructor of the class
     * @param idCategoryType
     * @param nameCategory
     */
    public CategoryType(int idCategoryType, String nameCategory) {
        this.idCategoryType = idCategoryType;
        this.nameCategory = nameCategory;
    }

    /**
     * Constructor of the class
     * @param nameCategory
     */
    public CategoryType(String nameCategory) {
        this.idCategoryType = 0;
        this.nameCategory = nameCategory;
    }

    /**
     * @return id of the category type
     */
    public int getIdCategoryType() {
        return idCategoryType;
    }

    /**
     * Set the id of the category type
     * @param idCategoryType
     */
    public void setIdCategoryType(int idCategoryType) {
        this.idCategoryType = idCategoryType;
    }

    /**
     * @return the name of the category type
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * Set the name of the category type
     * @param nameCategory
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
