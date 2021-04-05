package com.beardleysebastian.studentschedulizer13;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDateMismatch extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
        adBuilder.setTitle(R.string.date_mismatch_title);
        adBuilder.setMessage(R.string.date_mismatch_msg);
        adBuilder.setPositiveButton(R.string.date_mismatch_ok,null);
        return adBuilder.create();
    }

}
