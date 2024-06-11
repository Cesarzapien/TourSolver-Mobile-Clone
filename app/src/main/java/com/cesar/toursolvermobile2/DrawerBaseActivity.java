package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.cesar.toursolvermobile2.model.Geocode;
import com.cesar.toursolvermobile2.model.LastKnownPosition;
import com.cesar.toursolvermobile2.model.OperationalOrderAchievement;
import com.cesar.toursolvermobile2.model.Order;
import com.cesar.toursolvermobile2.model.PlannedOrder;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base,null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // Establecer el título
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Entrada");
        }

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        manejaracciondenavegacion(menuItem.getItemId());
        return false;
    }

    private void manejaracciondenavegacion (int itemId){

        // Obtener los datos actuales del usuario
        Intent currentIntent = getIntent();
        String userName = currentIntent.getStringExtra("user_name");
        String userEmail = currentIntent.getStringExtra("user_email");
        // Obtener los datos de PlannedOrder y Order del intent
        List<OperationalOrderAchievement> achievements = getIntent().getParcelableArrayListExtra("achievements");
        List<LastKnownPosition> lastKnownPositions = getIntent().getParcelableArrayListExtra("positioning");
        List<PlannedOrder> plannedOrders = getIntent().getParcelableArrayListExtra("plannedOrders");
        List<Order> orders = getIntent().getParcelableArrayListExtra("orders");
        List<Geocode> geocodes = getIntent().getParcelableArrayListExtra("geocodes");
        String hour = currentIntent.getStringExtra("hora_exacta");


        Intent intent = null;

        if(itemId == R.id.nav_inicio){
            intent = new Intent(this, InicioActivity.class);
            finish();
            overridePendingTransition(0,0);
        }else if (itemId==R.id.nav_agenda){
            intent = new Intent(this, AgendaActivity.class);
            finish();
            overridePendingTransition(0,0);
        } else if (itemId==R.id.nav_sites) {
            intent = new Intent(this, SitesActivity.class);
            finish();
            overridePendingTransition(0,0);
        } else if (itemId==R.id.nav_leads) {
            intent = new Intent(this, LeadsActivity.class);
            finish();
            overridePendingTransition(0,0);
        } else if (itemId==R.id.nav_settings) {
            intent = new Intent(this, SettingsActivity.class);
            finish();
            overridePendingTransition(0,0);
        } else if (itemId==R.id.nav_logout) {
            // Iniciar Login sin pasar extra
            intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        if (intent != null) {
            intent.putExtra("user_name", userName);
            intent.putExtra("user_email", userEmail);
            intent.putParcelableArrayListExtra("positioning",new ArrayList<>(lastKnownPositions));
            intent.putParcelableArrayListExtra("plannedOrders", new ArrayList<>(plannedOrders));
            intent.putParcelableArrayListExtra("orders", new ArrayList<>(orders));
            intent.putParcelableArrayListExtra("achievements",new ArrayList<>(achievements));
            intent.putParcelableArrayListExtra("geocodes",new ArrayList<>(geocodes));
            intent.putExtra("hora_exacta", hour);
            startActivity(intent);
            finish();
            overridePendingTransition(0,0);
        }

    }


    private void iniciarNuevaActividad(Class<?> destinoactividad){
        startActivity(new Intent(this,destinoactividad));
        finish();
        overridePendingTransition(0,0);
    }



}