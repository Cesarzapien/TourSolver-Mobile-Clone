package com.cesar.toursolvermobile2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivityLeadsBinding;

public class LeadsActivity extends DrawerBaseActivity {

    ActivityLeadsBinding activityLeadsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeadsBinding = activityLeadsBinding.inflate(getLayoutInflater());
        setContentView(activityLeadsBinding.getRoot());
        // Establecer el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Clientes potenciales");
        }
    }
}