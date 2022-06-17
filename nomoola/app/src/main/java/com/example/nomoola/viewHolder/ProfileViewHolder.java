package com.example.nomoola.viewHolder;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nomoola.R;
import com.example.nomoola.activity.MainActivity;
import com.example.nomoola.database.dao.ProfileDAO;
import com.example.nomoola.database.entity.Profile;
import com.example.nomoola.fragment.ProfileFragment;
import com.example.nomoola.viewModel.CategoryViewModel;
import com.example.nomoola.viewModel.ProfileViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.content.SharedPreferences;

import org.w3c.dom.Text;

import java.util.Locale;

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private TextView userName;
    private TextInputLayout editUserNameLayout;
    private TextInputEditText editUserName;
    private TextView language_english;
    private TextView language_francais;
    private TextView currency_euro;
    private TextView currency_dollar;
    private Button button;

    private ProfileViewModel profileViewModel;
    private FragmentManager fragmentManager;
    private Profile profile;
    private View view;

    public ProfileViewHolder(View view, FragmentManager fragmentManager, ProfileViewModel profileViewModel){
        super(view);
        this.view = view;
        this.fragmentManager = fragmentManager;
        this.profileViewModel = profileViewModel;

        this.userName = view.findViewById(R.id.profile_name);
        this.editUserNameLayout = view.findViewById(R.id.name_text_input);
        this.editUserName = view.findViewById(R.id.name_editext);
        this.language_english = view.findViewById(R.id.textview_english);
        this.language_francais = view.findViewById(R.id.textview_francais);
        this.currency_euro = view.findViewById(R.id.textview_euro);
        this.currency_dollar = view.findViewById(R.id.textview_dollar);
        this.button = view.findViewById(R.id.button);


        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editUserName.getText().toString();
                profileViewModel.setUsername(1, value);
            }
        });

        this.language_francais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.setLanguage(1, Profile.userLanguage.FRENCH);
            }
        });
        this.language_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileViewModel.setLanguage(1, Profile.userLanguage.ENGLISH);
            }
        });

        this.currency_euro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileViewModel.setCurrency(1, Profile.userCurrency.EURO);
            }
        });

        this.currency_dollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileViewModel.setCurrency(1, Profile.userCurrency.USDOLLAR);
            }
        });
    }
    public void bind(Profile profile){
        this.profile = profile;
        this.userName.setText(this.profile.getM_USERNAME());
        this.editUserName.setText(this.profile.getM_USERNAME());

        if (this.profile.getM_LANGUAGE() == Profile.userLanguage.ENGLISH) {
            this.language_english.setBackgroundResource(R.drawable.rounded_corners_green);
            this.language_francais.setBackgroundResource(R.drawable.rounded_corners_white);

        }else{
            this.language_english.setBackgroundResource(R.drawable.rounded_corners_white);
            this.language_francais.setBackgroundResource(R.drawable.rounded_corners_green);
        }

        if (this.profile.getM_CURRENCY() == Profile.userCurrency.EURO) {
            this.currency_euro.setBackgroundResource(R.drawable.rounded_corners_green);
            this.currency_dollar.setBackgroundResource(R.drawable.rounded_corners_white);
        }else{
            this.currency_euro.setBackgroundResource(R.drawable.rounded_corners_white);
            this.currency_dollar.setBackgroundResource(R.drawable.rounded_corners_green);
        }

    }

    public static ProfileViewHolder create(ViewGroup parent, FragmentManager fragmentManager, ProfileViewModel profileViewModel){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_profile, parent, false);
        return new ProfileViewHolder(view, fragmentManager, profileViewModel);
    }
}
