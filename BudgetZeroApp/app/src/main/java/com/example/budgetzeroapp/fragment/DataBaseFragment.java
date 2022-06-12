package com.example.budgetzeroapp.fragment;

import android.content.Context;
import android.view.View;

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.budgetzeroapp.tool.DBHelper;
import com.example.budgetzeroapp.tool.item.ListItem;
import com.example.budgetzeroapp.MainActivity;
import com.example.budgetzeroapp.tool.adapter.SimpleListAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class DataBaseFragment extends Fragment {
    protected DBHelper database;
    protected MainActivity activity;
    protected Context context;
    protected int id;

    public DataBaseFragment() {
        activity = (MainActivity) getActivity();
        if (isAdded()) {
            context = activity.getApplicationContext();
            database = new DBHelper(context);
        }
        id = 0;
    }

    public DataBaseFragment(int id) {
        activity = (MainActivity) getActivity();
        if (isAdded()) {
            context = activity.getApplicationContext();
            database = new DBHelper(context);
        }
        this.id = id;
    }


    public void message(String message) {
        if (isAdded())
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void redirect(Fragment f) {activity.replaceFragment(f); }


    //ListView list = <ViewExpenseFragment>
    public <F extends DataBaseFragment> ListView clickableListNameID(
            View view, int listViewID, int layout, List<ListItem> items, Class<F> cls) {

        SimpleListAdapter arrayAdapter=new SimpleListAdapter(context,layout, items);
        ListView obj;
        obj = (ListView) view.findViewById(listViewID);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener((parent, v, position, id) -> {
            ListItem item = (ListItem)obj.getItemAtPosition(position);
            int idItem = item.getId();
            try{
                F frag = cls.getDeclaredConstructor(Integer.class).newInstance(idItem);
                redirect(frag);
            }catch (java.lang.InstantiationException instantiationException){ }
            catch(NoSuchMethodException noSME) { }
            catch (IllegalAccessException illAE){ }
            catch (InvocationTargetException invTE){ }
        });
        return obj;
    }

    public <F extends DataBaseFragment> Spinner clickableSpinnerNameID(
            View view, int spinnerID, int layout, List<ListItem> items, Class<F> cls) {

        SimpleListAdapter arrayAdapter=new SimpleListAdapter(context,layout, items);
        Spinner obj;
        obj = (Spinner) view.findViewById(spinnerID);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener((parent, v, position, id) -> {
            ListItem item = (ListItem)obj.getItemAtPosition(position);
            try{
                F frag = cls.getDeclaredConstructor().newInstance();
                frag.id = item.getId();
                redirect(frag);
            }catch (java.lang.InstantiationException instantiationException){ }
            catch(NoSuchMethodException noSME) { }
            catch (IllegalAccessException illAE){ }
            catch (InvocationTargetException invTE){ }
        });
        return obj;
    }

}
