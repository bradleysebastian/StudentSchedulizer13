package com.beardleysebastian.studentschedulizer13;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class TermDetailFragment extends Fragment {

    private Term targetTerm;
    int termID;
    //TODO find best point to fire off ASYNC query for Term's Courses ArrayList
    //TODO
    //TODO Shows Courses Button
    //TODO Change Details Button
    //TODO Add Courses Button???
    //TODO Remove Courses Button???
    //TODO Delete Term Button

    public static TermDetailFragment newInstance(int termID) {
        TermDetailFragment tdFragment = new TermDetailFragment();
        Bundle payload = new Bundle();
        payload.putInt("test", termID);
        tdFragment.setArguments(payload);
        return tdFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the termID from the intent that started TermDetailsActivity
//        int termID = 1;
        if (getArguments() != null) {
            termID = getArguments().getInt("test");
        }
        targetTerm = CatalogDatabase.getInstance().getTerm(termID);
    }

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View TermDetFragView = inflaterer.inflate(R.layout.term_detail_fragment, vgContainer, false);

        TextView termNameTxtV = TermDetFragView.findViewById(R.id.term_title_det);
        termNameTxtV.setText(targetTerm.getTermTitle());

        TextView termDescsTxtV = TermDetFragView.findViewById(R.id.termDescription);
        termDescsTxtV.setText(targetTerm.getTermDescription());

        TextView termStartEnd = TermDetFragView.findViewById(R.id.start_end);
        termStartEnd.setText(targetTerm.getTermStart() + " - " + targetTerm.getTermEnd());

        return TermDetFragView;
    }


}