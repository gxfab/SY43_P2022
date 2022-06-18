package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.common.util.concurrent.ListenableFuture;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.repositories.CategoriesRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddCategoryDialogViewModel extends AndroidViewModel {
    private CategoriesRepository categoriesRepository;
    private ExecutorService executor;

    public AddCategoryDialogViewModel(@NonNull Application application) {
        super(application);
        this.categoriesRepository= new CategoriesRepository(application);
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(Categories category){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoriesRepository.insert(category);
            }
        });
    }
}
