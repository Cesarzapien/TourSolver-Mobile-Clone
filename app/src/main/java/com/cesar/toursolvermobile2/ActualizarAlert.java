package com.cesar.toursolvermobile2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.view.LayoutInflater;

public class ActualizarAlert {

    private Activity activity;
    private AlertDialog dialog;
    private Handler handler;

    ActualizarAlert(Activity myActivity) {
        activity = myActivity;
        handler = new Handler();
    }

    void startAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_actualizar, null));

        builder.setCancelable(true);

        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        // Programar la ejecución de closeAlertDialog después de 3 segundos
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                closeAlertDialog();
            }
        }, 3000); // 3000 milisegundos = 3 segundos
    }

    void closeAlertDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}