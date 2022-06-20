package com.example.zeroday.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.zeroday.R;
import com.example.zeroday.models.ExpenseCategory;
import com.example.zeroday.models.IncomeCategory;
import com.example.zeroday.seeders.ExpenseCategorySeeder;
import com.example.zeroday.seeders.IncomeCategorySeeder;
import com.example.zeroday.services.ExpenseCategoryService;
import com.example.zeroday.services.IncomeCategoryService;

public class ExpenseActivity extends AppCompatActivity {

    private ExpenseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);


        this.activity = this;

        // On récupère les arguments de l'intent
        Boolean inputType = getIntent().getBooleanExtra("inputType", true);
        Boolean outputType = getIntent().getBooleanExtra("outputType", false);

        // Puis on récupère les vues
        TextView title = findViewById(R.id.expenses_title);
        TextView subTitle = findViewById(R.id.expenses_subtitle);
        Button add = findViewById(R.id.add_category_button);
        Button save = findViewById(R.id.save_button);

        //On agit en conséquence
        //Activité en mode "income" :
        if(inputType == false)
        {
            //On met le titre et le sous-titre correspondants à l'ajout de revenus
            title.setText("Add Incomes");
            subTitle.setText("Add one or more incomes");

            //On charge le bon fragment central
            loadFragment(new GridIncomeFragment());

            //On assigne la création d'une popup d'ajout de catégorie au bouton "Add"
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Création de la popup
                    CategoryPopup categoryPopup = new CategoryPopup(activity);
                    //Affectation de l'action au bouton "cancel" de la popup
                    categoryPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //On détruit la popup
                            categoryPopup.dismiss();

                        }
                    });
                    //Affectation de l'action au bouton "add" de la popup
                    categoryPopup.getAddButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Si le contenu de l'edittext n'est pas vide on ajoute la nouvelle catégorie de revenus
                            if (!(categoryPopup.getEditText().isEmpty()))
                            {
                                new IncomeCategoryService(getApplicationContext()).create(new IncomeCategory("cat-inc-",categoryPopup.getEditText()));
                                categoryPopup.dismiss();
                                //On recharge le fragment pour afficher la catégorie ajoutée
                                loadFragment(new GridIncomeFragment());
                            }
                            else
                            //Sinon on modifie le Hint pour indiquer à l'utilisateur qu'il faut entrer un nom de Catégorie
                            {
                                categoryPopup.getFullEditText().setHint("Enter new category");
                                categoryPopup.getFullEditText().setHintTextColor(Color.parseColor("#88FF0000"));
                            }

                        }
                    });
                    //Affichage de la popup
                    categoryPopup.build();
                }
            });

            //Configuration de l'action du bouton "save"
            //Dans le cas où cette activité sert à configurer le premier budget prévisionnel, on aiguille sa sortie vers l'activité permettant de définir le cycle
            if(outputType==true){
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ExpenseActivity.this,CycleSettingActivity.class);
                        startActivity(intent);
                    }
                });
            }
            //Dans le cas où cette activité sert à ajouter un revenu de manière spontanée (usage courant), on termine l'activité
            else
            {
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }

        }


        //Activité en mode "expense" :
        else
        {
            //On met le titre et le sous-titre correspondants à l'ajout de dépenses
            title.setText("Add Expenses");
            subTitle.setText("Add one or more expenses");

            //On charge le bon fragment central
            loadFragment(new GridExpenseFragment());

            //On assigne la création d'une popup d'ajout de catégorie au bouton "Add"
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Création de la popup
                    CategoryPopup categoryPopup = new CategoryPopup(activity);
                    //Affectation de l'action au bouton "cancel" de la popup
                    categoryPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //On détruit la popup
                            categoryPopup.dismiss();
                        }
                    });

                    //Affectation de l'action au bouton "add" de la popup
                    categoryPopup.getAddButton().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Si le contenu de l'edittext n'est pas vide on ajoute la nouvelle catégorie de revenus
                            if (!(categoryPopup.getEditText().isEmpty()))
                            {
                                new ExpenseCategoryService(getApplicationContext()).create(new ExpenseCategory("cat-exp-",categoryPopup.getEditText()));
                                categoryPopup.dismiss();
                                //On recharge le fragment pour afficher la catégorie ajoutée
                                loadFragment(new GridExpenseFragment());
                            }
                            else
                            //Sinon on modifie le Hint pour indiquer à l'utilisateur qu'il faut entrer un nom de Catégorie
                            {
                                categoryPopup.getFullEditText().setHint("Enter new category");
                                categoryPopup.getFullEditText().setHintTextColor(Color.parseColor("#88FF0000"));
                            }
                        }
                    });
                    categoryPopup.build();
                }
            });

            //Configuration de l'action du bouton "save"
            //Dans le cas où cette activité sert à configurer le premier budget prévisionnel, on aiguille sa sortie vers l'activité principale en vidant totalement la backstack
            if(outputType==true){
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ExpenseActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
            //Dans le cas où cette activité sert à ajouter un revenu de manière spontanée (usage courant), on termine l'activité
            else
            {
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
            }
        }




    }



    //Fonction permettant de charger le fragment passé en parametre dans le conteneur central de l'activité
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.category_grid,fragment);
        transaction.commit();
    }

}
