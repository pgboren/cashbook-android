package com.soleap.cashbook.common.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.soleap.cashbook.R;

public class DialogUtils {

    public static void askForDeleteDialog(Context context, String msg_message, String msg_yes, String msg_no, OnDialogButtonClick onYes, OnDialogButtonClick onNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg_message)
                .setCancelable(false)
                .setPositiveButton(msg_yes , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (onYes != null) {
                            onYes.onClick();
                        }
                    }
                })
                .setNegativeButton(msg_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        if (onNo != null) {
                            onNo.onClick();
                        }
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle("AlertDialogExample");
        alert.show();
    }

    public interface OnDialogButtonClick {
        void onClick();
    }
}
