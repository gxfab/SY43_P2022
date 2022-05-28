package com.sucelloztm.sucelloz.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.sucelloztm.sucelloz.R;

public class AddCategoryDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.add_category_dialog_fragment,null))
                .setTitle(R.string.title_category)
                .setPositiveButton(R.string.ok_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        //Put data in model
                    }
                })
                .setNegativeButton(R.string.cancel_category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        AddCategoryDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
    public static String TAG= "AddCategoryDialog";
}