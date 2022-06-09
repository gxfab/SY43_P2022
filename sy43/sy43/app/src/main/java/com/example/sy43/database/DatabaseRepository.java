package com.example.sy43.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DatabaseRepository {

    private BillsDao billsDao;
    private CategoryDao categoryDao;
    private ExpensesDao expensesDao;
    private ExtraDebtsDao extraDebtsDao;
    private IncomeDao incomeDao;
    private MonthlyRevenueDao monthlyRevenueDao;
    private ProjectsDao projectsDao;
    private SubCategoryDao subCategoryDao;
    private LiveData<List<Bills>> allBills;
    private LiveData<List<Category>> allcategories;
    private LiveData<List<Expenses>> allexpenses;
    private LiveData<List<Income>> allincomes;
    private LiveData<List<ExtraDebts>> allextradebts;
    private LiveData<List<MonthlyRevenue>> allmonthlyrevenues;
    private LiveData<List<Projects>> allprojects;
    private LiveData<List<SubCategory>> allsubcategories;

    public DatabaseRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        billsDao = appDatabase.billsDao();
        allBills = billsDao.getAll();
        categoryDao = appDatabase.categoryDao();
        allcategories = categoryDao.getAll();
        expensesDao = appDatabase.expensesDao();
        allexpenses = expensesDao.getAll();
        extraDebtsDao = appDatabase.extraDebtDao();
        allextradebts = extraDebtsDao.getAll();
        incomeDao = appDatabase.incomeDao();
        allincomes = incomeDao.getAll();
        monthlyRevenueDao = appDatabase.monthlyRevenueDao();
        allmonthlyrevenues = monthlyRevenueDao.getAll();
        projectsDao = appDatabase.projectDao();
        allprojects = projectsDao.getAll();
        subCategoryDao = appDatabase.subCategoryDao();
        allsubcategories = subCategoryDao.getAll();
    }

    // This creates the abstraction layout between the ViewModel and the SQL database
    public void insert(SubCategory subCategory){
        new InsertSubCategoryAsyncTask(subCategoryDao).execute(subCategory);
    }

    public void update(SubCategory subCategory){
        new UpdateSubCategoryAsyncTask(subCategoryDao).execute(subCategory);
    }

    public void delete(SubCategory subCategory){
        new DeleteSubCategoryAsyncTask(subCategoryDao).execute(subCategory);
    }

    public LiveData<List<SubCategory>> getAllsubcategories(){
        return allsubcategories;
    }

    // Prevent crash

    private static class InsertSubCategoryAsyncTask extends AsyncTask<SubCategory, Void, Void>{
        private SubCategoryDao subCategoryDao;

        private  InsertSubCategoryAsyncTask(SubCategoryDao subCategoryDao){
            this.subCategoryDao = subCategoryDao;
        }
        @Override
        protected Void doInBackground(SubCategory... subCategories) {
            subCategoryDao.insert(subCategories[0]);
            return null;
        }
    }

    private static class UpdateSubCategoryAsyncTask extends AsyncTask<SubCategory, Void, Void>{
        private SubCategoryDao subCategoryDao;

        private  UpdateSubCategoryAsyncTask(SubCategoryDao subCategoryDao){
            this.subCategoryDao = subCategoryDao;
        }
        @Override
        protected Void doInBackground(SubCategory... subCategories) {
            subCategoryDao.update(subCategories[0]);
            return null;
        }
    }

    private static class DeleteSubCategoryAsyncTask extends AsyncTask<SubCategory, Void, Void>{
        private SubCategoryDao subCategoryDao;

        private  DeleteSubCategoryAsyncTask(SubCategoryDao subCategoryDao){
            this.subCategoryDao = subCategoryDao;
        }
        @Override
        protected Void doInBackground(SubCategory... subCategories) {
            subCategoryDao.delete(subCategories[0]);
            return null;
        }
    }

}
