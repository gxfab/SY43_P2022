package com.sucelloztm.sucelloz.ui.subcategories;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;
import com.sucelloztm.sucelloz.databinding.SubCategoriesFragmentBinding;
import com.sucelloztm.sucelloz.models.Categories;
import com.sucelloztm.sucelloz.models.SubCategories;
import com.sucelloztm.sucelloz.ui.dialogs.AddCategoryDialogFragment;
import com.sucelloztm.sucelloz.ui.dialogs.AddSubCategoryDialogFragment;
import com.sucelloztm.sucelloz.ui.miscellaneous.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment for the subcategories
 */
public class SubCategoriesFragment extends Fragment {

    private SubCategoriesFragmentBinding binding;
    private SubCategoriesViewModel subCategoriesViewModel;
    private List<SubCategories> currentSubCategoriesList;
    private RecyclerView recyclerView;
    private  int itemIndex;

    /**
     * on create method
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * on create view method
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saved instance state
     * @return view
     */
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = SubCategoriesFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        subCategoriesViewModel = new ViewModelProvider(this).get(SubCategoriesViewModel.class);
        currentSubCategoriesList = new ArrayList<>();
        SubCategoriesAdapter adapter = new SubCategoriesAdapter(currentSubCategoriesList);

        final Observer<List<SubCategories>> subCategoriesDataSet = new Observer<List<SubCategories>>() {
            @Override
            public void onChanged(List<SubCategories> subCategories) {
                currentSubCategoriesList.clear();
                currentSubCategoriesList.addAll(subCategories);
                adapter.notifyDataSetChanged();
            }
        };
        subCategoriesViewModel.getSubCategories().observe(getViewLifecycleOwner(),subCategoriesDataSet);
        recyclerView = binding.innerRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                itemIndex=position;
                return false;
            }
        });

        return root;
    }

    /**
     * on view created method
     * @param view view
     * @param savedInstanceState saved instance state
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.returnCategoriesButtonSubcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SubCategoriesFragment.this).navigate(R.id.action_navigation_sub_categories_to_navigation_categories);
            }
        });
        binding.addSubcategoryButtonSubcategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddSubCategoryDialogFragment().show(getChildFragmentManager(),AddCategoryDialogFragment.TAG);
            }
        });
    }


    /**
     * on destroy view method
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * on create context menu method
     * @param menu menu
     * @param v view
     * @param menuInfo menu info
     */
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    /**
     * on context item selected method
     * @param item menu item
     * @return boolean to know if the method executed properly
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.modify_menu_item:
                dialogForModifySubCategory(getActivity(),currentSubCategoriesList.get(itemIndex).getId(),currentSubCategoriesList.get(itemIndex).getCategoriesId()).show();
                return true;
            case R.id.delete_menu_item:
                subCategoriesViewModel.deleteSubCategory(currentSubCategoriesList.get(itemIndex));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * updates the dialog to modify the subcategory fragment
     * @param activity activity
     * @param idOfSubCategory id of the subcategory
     * @param idOfCategory id of the category
     * @return dialog
     */
    public Dialog dialogForModifySubCategory(Activity activity, long idOfSubCategory,long idOfCategory){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final EditText nameEditText = new EditText(activity);

        builder.setTitle("Change name of subcategory").setMessage("New name").setView(nameEditText);
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final String nameOfSubCategory = nameEditText.getText().toString();
                final SubCategories subCategoryToModify = new SubCategories(nameOfSubCategory,idOfCategory);
                subCategoryToModify.setId(idOfSubCategory);
                subCategoriesViewModel.updateSubCategory(subCategoryToModify);
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



