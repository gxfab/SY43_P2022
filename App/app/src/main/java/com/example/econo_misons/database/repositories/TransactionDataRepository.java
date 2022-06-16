package com.example.econo_misons.database.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.charts.TreeMap;
import com.anychart.data.Tree;
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

    public LiveData<TreeMap> getTreemap(List<TreemapEnv> envList){
        TreeMap treeMap = AnyChart.treeMap();
        String catName = "";
        List<DataEntry> treeMapList = new ArrayList<>();
        String root = CurrentData.getBudget().budgetName + ": " + CurrentData.getPrevBudget().yearMonth;
        treeMapList.add(new CustomTreeDataEntry(root,null,root));
        for (int i =0; i<envList.size();i++){
            catName = envList.get(i).categoryName;
            treeMapList.add(new CustomTreeDataEntry(catName,root,catName,(int)envList.get(i).sumEnv));
        }
        treeMap.data(treeMapList, TreeFillingMethod.AS_TABLE);
        treeMap.animation(true, 800);
        treeMap.background().enabled(false);
        treeMap.draw(true);
        treeMap.margin("15");
        MutableLiveData<TreeMap> mutuable = new MutableLiveData<TreeMap>();
        mutuable.setValue(treeMap);
        return mutuable;
    }

}
