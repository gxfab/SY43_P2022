package com.example.econo_misons.database;

import com.example.econo_misons.database.models.Budget;
import com.example.econo_misons.database.models.PrevisionalBudget;
import com.example.econo_misons.database.models.User;

//Public class Used to store and share the Current Data between activities
public class CurrentData {
    private static User user;
    private static Budget budget;
    private static PrevisionalBudget prevBudget;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentData.user = user;
    }

    public static Budget getBudget() {
        return budget;
    }

    public static void setBudget(Budget budget) {
        CurrentData.budget = budget;
    }

    public static PrevisionalBudget getPrevBudget() {
        return prevBudget;
    }

    public static void setPrevBudget(PrevisionalBudget prevBudget) {
        CurrentData.prevBudget = prevBudget;
    }

    public static void init(){
        if ((CurrentData.user == null) && (CurrentData.budget == null) && (CurrentData.prevBudget == null)){
            CurrentData.user = new User("Suiram");
            CurrentData.user.id = 1;
            CurrentData.budget = new Budget("Budget perso");
            CurrentData.budget.id = 1;
            CurrentData.prevBudget = new PrevisionalBudget(1,"2022-06");
        }
    }
}
