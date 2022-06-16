package com.example.zeroday.seeders;

import android.content.Context;

import com.example.zeroday.services.ExpenseCategoryService;

public class ExpenseCategorySeeder extends ZeroBaseSeeder<ExpenseCategoryService> {

    public ExpenseCategorySeeder(Context context) {
        super(context);
        this.service = new ExpenseCategoryService(this.context);
    }

    @Override
    public void seed() {
        this.service.create(new ExpenseCategory("cat-exp-food","Food"));
        this.service.create(new ExpenseCategory("cat-exp-tran","Transport"));
        this.service.create(new ExpenseCategory("cat-exp-enter","Entertainment"));
        this.service.create(new ExpenseCategory("cat-exp-hea","Health"));
        this.service.create(new ExpenseCategory("cat-exp-hou","House"));
        this.service.create(new ExpenseCategory("cat-exp-com","Communication"));
    }
}
