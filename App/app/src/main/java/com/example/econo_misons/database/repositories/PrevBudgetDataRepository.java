package com.example.econo_misons.database.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.econo_misons.database.dao.categoryDAO;
import com.example.econo_misons.database.dao.prevBudgetDAO;
import com.example.econo_misons.database.dao.userDAO;
import com.example.econo_misons.database.models.Category;
import com.example.econo_misons.database.models.Envelope;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.User;

import java.util.List;

public class PrevBudgetDataRepository {

    private final prevBudgetDAO prevDAO;

    public PrevBudgetDataRepository(prevBudgetDAO prevDAO) {this.prevDAO = prevDAO;}

    //PREV BUDGET

    public LiveData<List<PrevisionalBudget>> getAllPrevBudgets() {return this.prevDAO.getAllPrevBudgets();}

    public void addPrevBudget(PrevisionalBudget prevBud) {this.prevDAO.addPrevBudget(prevBud);}

    public void deletePrevBudget(PrevisionalBudget prevBud) {  this.prevDAO.deletePrevBudget(prevBud);}

    public LiveData<List<PrevisionalBudget>> getPrevBudgetsByBudget(int budgetID) {
        return this.prevDAO.getPrevBudgetsByBudget(budgetID);
    }

    public LiveData<List<PrevisionalBudget>> getUserPrevBudgets(int userID) {
        return this.prevDAO.getUserPrevBudgets(userID);
    }

    //ENVELOPES

    public void addEnvelope(Envelope env) {this.prevDAO.addEnvelope(env);}

    public void updateEnvelope(Envelope env) {this.prevDAO.updateEnvelope(env);}

    public void deleteEnvelope(Envelope env) {this.prevDAO.deleteEnvelope(env);}

    public LiveData<List<Envelope>> getALlEnvelopes() {
        return this.prevDAO.getALlEnvelopes();
    }

    public LiveData<List<Envelope>> getPrevBudgetEnvelopes(PrevisionalBudget prevBud) {
        return this.prevDAO.getPrevBudgetEnvelopes(prevBud.budgetID, prevBud.yearMonth);
    }


}