package com.example.sy43.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.sy43.CategoryActivity;
import com.example.sy43.CategoryDetailsActivity;
import com.example.sy43.MainActivity;
import com.example.sy43.ObjectiveDetailsActivity;
import com.example.sy43.R;
import com.example.sy43.TransactionSummary;
import com.example.sy43.db.entity.Categorydb;
import com.example.sy43.db.entity.SubCategory;
import com.example.sy43.db.entity.Transaction;
import com.example.sy43.viewmodels.CategoryViewModel;
import com.example.sy43.viewmodels.SubCategoryViewModel;
import com.example.sy43.viewmodels.TransactionViewModel;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
public class CategoryAdapter extends ArrayAdapter<Categorydb> {
    private int resourceLayout;
    private Context mContext;
    private CategoryViewModel catViewModel;
    private SubCategoryViewModel subCatViewModel;
    private TransactionViewModel transactionViewModel;
    private LifecycleOwner owner;


    /**
     * Constructeur de l'adapteur de catégorie
     * Cette classe sert à remplir l'objet "ListView" via un layout personnalisé
     *
     * @param context Le context de l'application
     * @param owner Le parent de la lifecycle
     * @param resource La ressource pour pouvoir créer l'adapter
     * @param categories Liste des catégories
     * @param categoryViewModel Viewmodel de la catégorie
     * @param transactionViewModel Viewmodel de la transaction
     * @param subCategoryViewModel Viewmodel de la sous catégorie
     */

    public CategoryAdapter(@NonNull Context context, LifecycleOwner owner, int resource, List<Categorydb> categories, CategoryViewModel categoryViewModel, TransactionViewModel transactionViewModel, SubCategoryViewModel subCategoryViewModel) {
        super(context, resource, categories);
        this.resourceLayout = resource;
        this.mContext = context;
        this.catViewModel = categoryViewModel;
        this.transactionViewModel = transactionViewModel;
        this.subCatViewModel = subCategoryViewModel;
        this.owner = owner;
    }

    /**
     * Méthode appelée pour chaque élement de la liste
     *
     * @param position Position de la catégorie dans la liste
     * @param convertView Vue convertie
     * @param parent Le groupe de la vue parente
     * @return View nouvelle vue
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Categorydb category = getItem(position);

        if (category != null) {
            final float[] value = {0, 0};

            TextView price = v.findViewById(R.id.tvCurrentPrice);
            TextView name = v.findViewById(R.id.tvName);
            ProgressBar progressBar = v.findViewById(R.id.progressBar);
            progressBar.setMax((int) category.getMaxValue());
            progressBar.setProgress((int) category.CurrentValue(), true);
            name.setText(category.getCatName());

            Boolean isObjective = category.getIsObjective();

            if (isObjective) {
                Date objectiveDate = new Date(category.getDate());
                final float[] montant = {0};
/*
                transactionViewModel.getTransactions().observe(owner, new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(List<Transaction> transactions) {
                        for (Transaction trans : transactions) {
                            Date transDate = new Date(trans.getDate());
                            boolean isSameMonth = transDate.getMonth() == objectiveDate.getMonth() && transDate.getYear() == objectiveDate.getYear();
                            if (isSameMonth) {
                                int categoryId = trans.getCategory();
                            }
                        }
                    }
                });
*/
                catViewModel.getCategories().observe(owner, new Observer<List<Categorydb>>() {
                    @Override
                    public void onChanged(List<Categorydb> categorydbs) {
                        for (Categorydb cat : categorydbs) {
                            float current = cat.CurrentValue();
                            float max = cat.getMaxValue();

                            if (current <= max) {
                                float surplus = max - current;
                                montant[0] += surplus;
                            }
                        }
                    }
                });
                catViewModel.getObjectives().observe(owner, new Observer<List<Categorydb>>() {
                    @Override
                    public void onChanged(List<Categorydb> categorydbs) {
                        price.setText("$"+montant[0]/categorydbs.size()+"/$"+category.getMaxValue());
                    }
                });

               // price.setText("$"+montant[0]/numberOfObjectives[0] + "/$"+category.getMaxValue() + " saved");
            } else {
                transactionViewModel.getTransactionsFromCat(category.getCatID()).observe(owner, new Observer<List<Transaction>>() {
                    @Override
                    public void onChanged(List<Transaction> receivedTransactions) {
                        value[0] = 0;
                        for (Transaction trans : receivedTransactions) {
                            Date transDate = new Date(trans.getDate());
                            boolean isSameMonth = transDate.getMonth() == new Date().getMonth() && transDate.getYear() == new Date().getYear();
                            if (isSameMonth) {
                                value[0] += trans.getValue();
                            }
                        }
                        price.setText("$" + value[0] + "/" + value[1]);
                        progressBar.setProgress((int) value[0], true);
                        progressBar.getProgressDrawable().setColorFilter(
                                value[0] < value[1] ? Color.GREEN : Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                        category.setCurrentValue(value[0]);
                        updateCategory(category);
                    }
                });

                subCatViewModel.getSubCategoriesByCatId(category.getCatID()).observe(owner, new Observer<List<SubCategory>>() {
                    @Override
                    public void onChanged(List<SubCategory> subCategories) {
                        value[1] = 0;
                        for (SubCategory subCategory : subCategories) {
                            value[1] += subCategory.getMaxValue();
                        }
                        price.setText("$" + value[0] + "/" + value[1]);
                        progressBar.setMax((int) value[1]);
                        progressBar.getProgressDrawable().setColorFilter(
                                value[0] < value[1] ? Color.GREEN : Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                        category.setMaxValue(value[1]);
                        updateCategory(category);
                    }
                });
            }
            CardView card = v.findViewById(R.id.categoryCard);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class detailsPage = isObjective ? ObjectiveDetailsActivity.class : CategoryDetailsActivity.class;
                    Intent intent = new Intent(v.getContext(), detailsPage);
                    intent.putExtra("categoryId", category.getCatID());
                    v.getContext().startActivity(intent);
                }
            });

            Button del = v.findViewById(R.id.deleteButton);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    catViewModel.delCategories(category.getCatID());
                    CharSequence text = "Deleted";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(MainActivity.getAppContext(), text, duration);
                    toast.show();

                    Intent intent = new Intent(v.getContext(), CategoryActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return v;
    }
    private void updateCategory(Categorydb cat) {
        catViewModel.updateCategory(cat);
    }

}
