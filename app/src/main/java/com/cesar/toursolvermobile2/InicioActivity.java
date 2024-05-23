package com.cesar.toursolvermobile2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;

public class InicioActivity extends DrawerBaseActivity {

    ActivityInicioBinding activityInicioBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = activityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());
    }
}