package fr.sy43.studzero.sqlite.model;

public class CategoryType {
    private int idCategoryType;
    private String nameCategory;

    public CategoryType(int idCategoryType, String nameCategory) {
        this.idCategoryType = idCategoryType;
        this.nameCategory = nameCategory;
    }

    public CategoryType(String nameCategory) {
        this.idCategoryType = Integer.parseInt(null);
        this.nameCategory = nameCategory;
    }

    public int getIdCategoryType() {
        return idCategoryType;
    }

    public void setIdCategoryType(int idCategoryType) {
        this.idCategoryType = idCategoryType;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
