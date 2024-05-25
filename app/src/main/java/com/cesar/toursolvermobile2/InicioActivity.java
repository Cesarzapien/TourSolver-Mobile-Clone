package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;
import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends DrawerBaseActivity {

    ActualizarAlert actualizarAlert = new ActualizarAlert(InicioActivity.this);
    TextView userNamee,userEmaill,profileName;
    ActivityInicioBinding activityInicioBinding;
    private CountDownTimer mCountDownTimer;
    private static final long COUNTDOWN_TIME = 180000; // 3 minutos
    private boolean isPaused = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user_name");
        String userEmail = intent.getStringExtra("user_email");

        // Obtener la referencia del NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Obtener la referencia del HeaderView del NavigationView
        View headerView = navigationView.getHeaderView(0);

        // Obtener los TextView del HeaderView
        profileName = findViewById(R.id.profilename);
        userNamee = headerView.findViewById(R.id.username); // Asegúrate de tener este TextView en tu layout
        userEmaill = headerView.findViewById(R.id.useremail); // Asegúrate de tener este TextView en tu layout

        // Sobrescribir los strings
        if (userName != null) {
            userNamee.setText(userName);
            profileName.setText(userName);
        }

        if (userEmail != null) {
            userEmaill.setText(userEmail);
        }

        activityInicioBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_settings) {
            actualizarAlert.startAlertDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;
        startTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPaused = false;
        stopTimer();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.setExitDialogListener(new ExitDialog.ExitDialogListener() {
            @Override
            public void onExitConfirmed() {
                finishAffinity();
            }
        });
        exitDialog.show(fragmentManager, "exit_dialog");
    }

    private void startTimer() {
        if (isPaused && mCountDownTimer == null) {
            mCountDownTimer = new CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_TIME) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // No necesitas hacer nada aquí para este caso
                }

                @Override
                public void onFinish() {
                    redirectToLoginActivity();
                }
            }.start();
        }
    }

    private void stopTimer() {
        if (!isPaused && mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    private void resetTimer() {
        stopTimer();
        startTimer();
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }


}