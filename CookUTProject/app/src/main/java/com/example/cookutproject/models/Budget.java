package com.example.cookutproject.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Budget {
    private float previsionnel;
    private float depense;
    private float recette;
    private List<Operation> operations = new ArrayList<Operation>() {
    };

    Budget(float previsionnel, float depense, float recette, ArrayList<Operation> operation){
        this.previsionnel = previsionnel;
        this.depense = depense;
        this.recette = recette;
        this.operations = operation;
    }
    public float getPrevisionnel() {
        return previsionnel;
    }

    public float getDepense() {
        return depense;
    }

    public float getRecette() {
        return recette;
    }

    public List<Operation> getAllOperation() {
        return operations;
    }

    public void setPrevisionnel(float previsionnel) {
        this.previsionnel = previsionnel;
    }

    public void setDepense(float depense) {
        this.depense += depense;
    }

    public void setRecette(float recette) {
        this.recette += recette;
    }

    public void setOperation(Operation Operation) {
        this.operations.add(Operation);
    }

    /**
     * Retourne une Operation suivant le numéro de Operation demandé
     * @param id l'identifiant de la Operation
     * @return une Operation ou null si la Operation n'existe pas
     */

    public Operation getOperationById (int id){
        Operation OperationToReturn;
        int i=0;
        Operation enumarationOperation=operations.get(i);
        while (enumarationOperation.getId()!= id){
            enumarationOperation = operations.get(i);
        }
        if(enumarationOperation.getId()==id){
            return enumarationOperation;
        }else
            return null;

    }

    public void sumDepenses (){
        float totalDepense = 0;
        int i=0;
        while (operations.get(i)!=null && operations.get(i).getFacture().getAmount()<0){
            totalDepense += operations.get(i).getFacture().getAmount();
        }
        this.depense = totalDepense;
    }

    public void sumRecettes(){
        float totalRecette = 0;
        int i=0;
        while (operations.get(i)!=null && operations.get(i).getFacture().getAmount()>0){
            totalRecette += operations.get(i).getFacture().getAmount();
        }
        this.depense = totalRecette;
    }
}
