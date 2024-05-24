package com.cesar.toursolvermobile2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.cesar.toursolvermobile2.databinding.ActivityInicioBinding;

public class InicioActivity extends DrawerBaseActivity {

    ActivityInicioBinding activityInicioBinding;
    private CountDownTimer mCountDownTimer;
    private static final long COUNTDOWN_TIME = 180000; // 3 minutos
    private boolean isPaused = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInicioBinding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(activityInicioBinding.getRoot());

        activityInicioBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
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