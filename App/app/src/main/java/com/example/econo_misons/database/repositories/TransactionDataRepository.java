package com.example.econo_misons.database.repositories;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.TreeMap;
import com.anychart.enums.TreeFillingMethod;
import com.example.econo_misons.database.CurrentData;
import com.example.econo_misons.database.CustomTreeDataEntry;
import com.example.econo_misons.database.dao.transactionDAO;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.Transaction;
import com.example.econo_misons.database.models.TreemapEnv;

import java.util.ArrayList;
import java.util.List;

//The functions called by the DBViewModel on a new thread.
//The comments for the functions are in the DBViewModel file if they aren't here
public class TransactionDataRepository {

    private final transactionDAO transdao;

    public TransactionDataRepository(transactionDAO transdao) {this.transdao = transdao;}


    public long addTransaction(Transaction trans) {return this.transdao.addTransaction(trans);}

    public void updateTransaction(Transaction trans) {  this.transdao.updateTransaction(trans);}

    public void deleteTransaction(Transaction trans) {  this.transdao.deleteTransaction(trans);}

    public LiveData<List<Transaction>> getAllTransactions() {return this.transdao.getAllTransactions();}

    public LiveData<List<Transaction>> getBudgetTransactions(int budID) {return this.transdao.getBudgetTransactions(budID);}

    public LiveData<List<Transaction>> getBudgetPrevTransactions(PrevisionalBudget prevBud) {return this.transdao.getBudgetPrevTransactions(prevBud.budgetID, prevBud.yearMonth);}

    public LiveData<List<Transaction>> getUserTransactions(int userID) {return this.transdao.getUserTransactions(userID);}

    public LiveData<List<Envelope>> getCurrentBudgetEnvelope(PrevisionalBudget prevBud) {return this.transdao.getCurrentBudgetEnvelope(prevBud.budgetID, prevBud.yearMonth);}

    public LiveData<List<TreemapEnv>> getTreeMapList(){
        return this.transdao.getTreemapEnv(CurrentData.getPrevBudget().budgetID, CurrentData.getPrevBudget().yearMonth);
    }

    //This function allow us to setup the treeap on a worker thread instead of UI thread
    public LiveData<TreeMap> getTreemap(List<TreemapEnv> envList){

        TreeMap treeMap = AnyChart.treeMap();
        String catName = "";
        List<DataEntry> treeMapList = new ArrayList<>(); //DataEntry list needed for the treemap
        String root = CurrentData.getBudget().budgetName + ": " + CurrentData.getPrevBudget().yearMonth;//Name of the root of the treemap
        treeMapList.add(new CustomTreeDataEntry(root,null,root));

        //Add the Treemap Data in the DataEntry list
        for (int i =0; i<envList.size();i++){
            catName = envList.get(i).categoryName;
            treeMapList.add(new CustomTreeDataEntry(catName,root,catName,(int)envList.get(i).sumEnv));
        }

        //setup the treemap
        treeMap.data(treeMapList, TreeFillingMethod.AS_TABLE);//add the data
        treeMap.animation(true, 800);//Sadly it doesn't seems to work with the treemap chart
        treeMap.background().enabled(false);//Disable background
        treeMap.draw(true);//Enable the treemap
        treeMap.margin("15");//Setup margins

        //return the treemap using LiveData (MutableLiveData is a LiveData that with a setValeu())
        MutableLiveData<TreeMap> mutable = new MutableLiveData<TreeMap>();
        mutable.setValue(treeMap);
        return mutable;
    }

}
