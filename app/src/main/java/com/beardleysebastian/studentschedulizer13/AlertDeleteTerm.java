package com.beardleysebastian.studentschedulizer13;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AlertDeleteTerm extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(getActivity());
        adBuilder.setTitle(R.string.del_term_alert_title);
        adBuilder.setMessage(R.string.del_term_alert_msg);
        adBuilder.setPositiveButton(R.string.del_term_alert_ok,null);
        return adBuilder.create();
    }
}