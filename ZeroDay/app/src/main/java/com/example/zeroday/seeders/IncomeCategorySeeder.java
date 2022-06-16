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
    protected void seed() {
        this.service.create(new IncomeCategory("cat-inc-sal","Salary"));
        this.service.create(new IncomeCategory("cat-inc-bon","Bonus"));
        this.service.create(new IncomeCategory("cat-inc-gif","Gift"));
        this.service.create(new IncomeCategory("cat-inc-loc","Location"));
    }
}
