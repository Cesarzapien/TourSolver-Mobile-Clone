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

import com.google.android.material.navigation.NavigationView;

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
        if(itemId == R.id.nav_inicio){
            iniciarNuevaActividad(InicioActivity.class);
        }else if (itemId==R.id.nav_agenda){
            iniciarNuevaActividad(AgendaActivity.class);
        } else if (itemId==R.id.nav_sites) {
            iniciarNuevaActividad(SitesActivity.class);
        } else if (itemId==R.id.nav_leads) {
            iniciarNuevaActividad(LeadsActivity.class);
        } else if (itemId==R.id.nav_settings) {
            iniciarNuevaActividad(SettingsActivity.class);
        } else if (itemId==R.id.nav_logout) {
            // Iniciar Login sin pasar extra
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
    }


    private void iniciarNuevaActividad(Class<?> destinoactividad){
        startActivity(new Intent(this,destinoactividad));
        overridePendingTransition(0,0);
    }



}