package com.example.sy43.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.repositories.SubCategoryRepository;

import java.util.List;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCatRepo;

    public void init() {
        subCatRepo = SubCategoryRepository.getInstance();
    }

    public MutableLiveData<List<SubCategory>> getSubCategoriesByCatId(int catId){
        return subCatRepo.getSubCategoriesByCatId(catId);
    }

}