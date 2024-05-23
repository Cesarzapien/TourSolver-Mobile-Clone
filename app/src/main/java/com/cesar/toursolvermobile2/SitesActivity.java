package com.cesar.toursolvermobile2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivitySitesBinding;

public class SitesActivity extends DrawerBaseActivity {
    ActivitySitesBinding activitySitesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySitesBinding = activitySitesBinding.inflate(getLayoutInflater());
        setContentView(activitySitesBinding.getRoot());
        // Establecer el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sitios");
        }
    }
}