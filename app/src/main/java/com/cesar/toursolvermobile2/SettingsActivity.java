package com.cesar.toursolvermobile2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivitySettingsBinding;

public class SettingsActivity extends DrawerBaseActivity {

    ActivitySettingsBinding activitySettingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = activitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
        // Establecer el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Parámetros");
        }
    }
}