package com.cesar.toursolvermobile2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.view.LayoutInflater;

public class LoadingAlert {

    private Activity activity;
    private AlertDialog dialog;

    LoadingAlert(Activity myActivity) {
        activity = myActivity;
    }

    void startAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_charging, null));

        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    void closeAlertDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
