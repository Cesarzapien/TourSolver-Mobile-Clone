package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cesar.toursolvermobile2.databinding.ActivityLeadsBinding;
import com.google.android.material.navigation.NavigationView;

public class LeadsActivity extends DrawerBaseActivity {
    TextView userNamee, userEmaill;

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

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");

        // Obtener la referencia del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Obtener la referencia del HeaderView del NavigationView
        View headerView = navigationView.getHeaderView(0);

        // Obtener los TextView del HeaderView
        userNamee = headerView.findViewById(R.id.username); // Asegúrate de tener este TextView en tu layout
        userEmaill = headerView.findViewById(R.id.useremail); // Asegúrate de tener este TextView en tu layout

        // Sobrescribir los strings
        if (userName != null) {
            userNamee.setText(userName);
        }

        if (userEmail != null) {
            userEmaill.setText(userEmail);
        }

    }
}