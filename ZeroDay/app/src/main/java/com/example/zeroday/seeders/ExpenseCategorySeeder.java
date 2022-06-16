package com.example.zeroday.seeders;

import android.content.Context;

import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.services.ExpenseCategoryService;

public class ExpenseCategorySeeder extends ZeroBaseSeeder<ExpenseCategoryService> {

    public ExpenseCategorySeeder(Context context) {
        super(context);
        this.service = new ExpenseCategoryService(this.context);
    }

    @Override
    public void seed() {
        this.service.create(new ExpenseCategory("cat-exp-foo","Food"));
        this.service.create(new ExpenseCategory("cat-exp-tran","Transport"));
        this.service.create(new ExpenseCategory("cat-exp-ent","Entertainment"));
        this.service.create(new ExpenseCategory("cat-exp-hea","Health"));
        this.service.create(new ExpenseCategory("cat-exp-hou","House"));
        this.service.create(new ExpenseCategory("cat-exp-com","Communication"));
        this.service.create(new ExpenseCategory("cat-exp-ext","Extras"));
    }
}
