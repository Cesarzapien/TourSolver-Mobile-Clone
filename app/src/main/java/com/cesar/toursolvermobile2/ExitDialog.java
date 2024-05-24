package com.cesar.toursolvermobile2;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ExitDialog extends DialogFragment {

    public interface ExitDialogListener {
        void onExitConfirmed();
    }

    private ExitDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.DialogTheme);
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_exit, null);

        builder.setView(view);

        // Evita que se cierre al tocar fuera del diálogo
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        Button btnNo = view.findViewById(R.id.btn_no);
        Button btnYes = view.findViewById(R.id.btn_yes);

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onExitConfirmed();
                }
                dismiss();
            }
        });

        return dialog;
    }

    public void setExitDialogListener(ExitDialogListener listener) {
        this.listener = listener;
    }
}
