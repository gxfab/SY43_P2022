package com.sucelloztm.sucelloz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sucelloztm.sucelloz.database.SucellozDatabase;
import com.sucelloztm.sucelloz.databinding.ActivityMainBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;


/**
 * Main activity of the application
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    //PREPOLUTE_DATA
    private Categories zeroBudgetCat;
    public String[] zeroBudgetNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SucellozDatabase database = SucellozDatabase.getInstance(this);
        zeroBudgetCat = new Categories("Zero Budget", true);
        zeroBudgetNameList = new String[]{"Incomes", "Bills", "Envelopes",
                "Sinking Funds", "Extra Debt", "Extra Savings"};
        if (database.categoriesDao().getCategoryByName("Zero Budget") == null) {
            database.categoriesDao().insertCategory(zeroBudgetCat);
            long zeroBudgetCatId = database.categoriesDao().getCategoryByName("Zero Budget").getId();
            for (String subCategoryName :
                    zeroBudgetNameList) {
                database.subCategoriesDao().insertSubCategory(new SubCategories(subCategoryName, zeroBudgetCatId));
            }
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_spendings, R.id.navigation_savings, R.id.navigation_zero_budget)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        getSupportActionBar().hide();
    }
}