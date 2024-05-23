package com.cesar.toursolvermobile2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivityAgendaBinding;

public class AgendaActivity extends DrawerBaseActivity {
    ActivityAgendaBinding activityAgendaBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAgendaBinding = activityAgendaBinding.inflate(getLayoutInflater());
        setContentView(activityAgendaBinding.getRoot());
        // Establecer el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Agenda");
        }
    }
}