package com.example.budgetzeroapp.tool.list.item;

import com.example.budgetzeroapp.R;
import com.example.budgetzeroapp.fragment.DataBaseFragment;
import com.example.budgetzeroapp.fragment.SavingsFragment;
import com.example.budgetzeroapp.fragment.view.ViewExpenseCatFragment;

public class ProgressBarItem extends ListItem{

    private float total, percentage;
    private DataBaseFragment frag;

    public ProgressBarItem(int id, String name, float total,float percentage){
        super(id, name);
        this.percentage = percentage;
        this.total = total;
        if(id == -1) frag = new SavingsFragment();
        else if(id==0){}
        else frag = new ViewExpenseCatFragment(1);
    }

    public int getDrawable(){
        if(name.equals("Health")) return R.drawable.ic_heart;
        else if(name.equals("Shopping")) return R.drawable.ic_shopping;
        else if(name.equals("Leisure")) return R.drawable.ic_leisure;
        else if(name.equals("Vehicle")) return R.drawable.ic_car;
        else if(name.equals("Miscellaneous")) return R.drawable.ic_coffee;
        else return R.drawable.ic_default_thumb;
    }

    public float getTotal(){ return total; }
    public float getPercent(){ return percentage; }
    public DataBaseFragment getFragment(){ return frag; }
}
