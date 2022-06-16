package com.example.zeroday.services;

import android.content.Context;

import com.example.zeroday.models.Prevision;
import com.example.zeroday.repositories.PrevisionRepository;

public class PrevisionService extends ZeroBaseServices<PrevisionRepository, Prevision> {

    public PrevisionService(Context context) {
        super(context);
        this.repository = new PrevisionRepository(this.sqLiteDatabase);
    }
}
