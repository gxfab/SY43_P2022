package com.sucelloztm.sucelloz.repositories;

import com.sucelloztm.sucelloz.database.DAO.CategoriesDao;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.MainRepository;

import java.util.ArrayList;

public class CategoriesRepository {
    private SucellozDatabase database;
    private CategoriesDao categoriesDao;

    public CategoriesRepository(SucellozDatabase database){
        this.database=database;

        this.categoriesDao=database.categoriesDao();
    }

    public ArrayList<Categories> getCategories(){
        return (ArrayList<Categories>) categoriesDao.getCategories();
    }
}
