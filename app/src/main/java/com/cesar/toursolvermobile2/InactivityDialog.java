package com.cesar.toursolvermobile2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class InactivityDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.DialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_inactivity, null);

        builder.setView(view);

        // Evita que se cierre al tocar fuera del diálogo
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        Button btnOK = view.findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // Cierra el diálogo
            }
        });

        return dialog;
    }

}

