package com.sucelloztm.sucelloz.ui.categories;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.CategoriesFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.ui.dialogs.AddCategoryDialogFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoriesFragment extends Fragment implements LifecycleOwner {

    private CategoriesFragmentBinding binding;
    private CategoriesViewModel categoriesViewModel;
    private List<Categories> currentCategoriesList;
    private RecyclerView recyclerView;
    private Categories currentCategory;
    String currentCategoryName;
    private int itemIndex;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CategoriesFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        currentCategoriesList = new ArrayList<>();
        CategoriesAdapter adapter=new CategoriesAdapter(currentCategoriesList);

        final Observer<List<Categories>> categoriesDataSet= categoriesList -> {
            currentCategoriesList.clear();
            currentCategoriesList.addAll(categoriesList);
            adapter.notifyDataSetChanged();
        };
        categoriesViewModel.getAllCategories().observe(getViewLifecycleOwner(),categoriesDataSet);
        recyclerView = binding.outerRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);



        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                TextView currentCategoryTextView=(TextView) v.findViewById(R.id.text_view_categories);
                currentCategoryName = currentCategoryTextView.getText().toString();
                final Observer<Categories> currentCategoryObserver= category ->{
                    currentCategory=category;
                    categoriesViewModel.setCurrentCategory(currentCategory);
                };
                categoriesViewModel.getCategoryByName(currentCategoryName).observe(getViewLifecycleOwner(),currentCategoryObserver);
                NavHostFragment.findNavController(CategoriesFragment.this).navigate(R.id.action_navigation_categories_to_navigation_sub_categories);
                //Log.d("CategoriesFragment",currentCategoryName);
            }
        });
        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                itemIndex = position;
                return false;
            }
        });



        return root;
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.returnHomeButtonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CategoriesFragment.this).navigate(R.id.action_navigation_categories_to_navigation_home);
            }
        });
        binding.addCategoryButtonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCategoryDialogFragment().show(getChildFragmentManager(),AddCategoryDialogFragment.TAG);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.modify_menu_item:
                dialogForModifyCategory(getActivity(),currentCategoriesList.get(itemIndex).getId()).show();
                return true;
            case R.id.delete_menu_item:
                categoriesViewModel.deleteCategory(currentCategoriesList.get(itemIndex));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public Dialog dialogForModifyCategory(Activity activity, long idOfCategory){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final EditText nameEditText = new EditText(activity);

        builder.setTitle("Change name of category").setMessage("New name").setView(nameEditText);
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 final String nameOfCategory = nameEditText.getText().toString();
                 final Categories categoryToModify = new Categories(nameOfCategory,false);
                 categoryToModify.setId(idOfCategory);
                 categoriesViewModel.updateCategory(categoryToModify);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }


}