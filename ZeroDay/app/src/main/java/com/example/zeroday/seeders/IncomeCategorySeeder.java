package com.example.zeroday.seeders;

import android.content.Context;

import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.services.IncomeCategoryService;

public class IncomeCategorySeeder extends ZeroBaseSeeder<IncomeCategoryService>{


    public IncomeCategorySeeder(Context context) {
        super(context);
        this.service = new IncomeCategoryService(this.context);
    }

    @Override
    public void seed() {
        this.service.create(new IncomeCategory("cat-inc-salary","Salary"));
        this.service.create(new IncomeCategory("cat-inc-bonus","Bonus"));
        this.service.create(new IncomeCategory("cat-inc-gift","Gift"));
        this.service.create(new IncomeCategory("cat-inc-loc","Location"));
    }
}
