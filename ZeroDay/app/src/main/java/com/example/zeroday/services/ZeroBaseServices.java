package com.example.zeroday.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.zeroday.dao.DbHelper;
import com.example.zeroday.models.ZeroBaseModel;
import com.example.zeroday.repositories.ZeroBaseRepository;

import java.util.List;

public abstract class ZeroBaseServices<T extends ZeroBaseRepository, M extends ZeroBaseModel> {

    protected T repository;
    protected SQLiteDatabase sqLiteDatabase;
    protected Context context;

    public ZeroBaseServices(Context context) {
        this.context = context;
        this.sqLiteDatabase = new DbHelper(this.context).getWritableDatabase();
    }

    public T getRepository() {
        return repository;
    }

    public void setRepository(T repository) {
        this.repository = repository;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<M> getAll() {
        return repository.getAll();
    }

    public M findOneById(Long id) {
        return (M) repository.findOneById(id);
    }

    public M findOneByCode(String code) {
        return (M) repository.findOneByCode(code);
    }

    public void create(M zeroBaseModelobject) {
        Long id = this.repository.insert(zeroBaseModelobject);
        zeroBaseModelobject.setId(id);
    }

    public void update(M zeroBaseModelobject) {
        Long id = this.repository.update(zeroBaseModelobject);
        zeroBaseModelobject.setId(id);
    }

    public void delete(M zeroBaseModelobject) {
        repository.delete(zeroBaseModelobject);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    
}
