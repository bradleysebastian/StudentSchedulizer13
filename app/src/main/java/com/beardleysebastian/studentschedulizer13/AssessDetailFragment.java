package com.beardleysebastian.studentschedulizer13;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AssessDetailFragment extends Fragment implements View.OnClickListener{

    private Assessment targetAssessment;
    private int assessID;
//    Button delAssessBtn;

    public static AssessDetailFragment newInstance(int assessID){
        AssessDetailFragment adFrag = new AssessDetailFragment();
        Bundle payload = new Bundle();
        payload.putInt("test",assessID);
        adFrag.setArguments(payload);
        return adFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Get the assessID from the intent that started AssessDetailsActivity
        assessID = 1;
        if(getArguments() != null){
            assessID = getArguments().getInt("test");
        }
        targetAssessment = CatalogDatabase.getInstance().getAssess(assessID);
    }

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View AssessDetFragView = inflaterer.inflate(R.layout.assess_detail_fragment,vgContainer,
                false);

        TextView assessNameTxtV = AssessDetFragView.findViewById(R.id.assess_title_det);
        assessNameTxtV.setText(targetAssessment.getAssessDescription() + ": " + targetAssessment.getAssessName());

        TextView assessTargetTxtV = AssessDetFragView.findViewById(R.id.assess_date_det);
        assessTargetTxtV.setText(targetAssessment.getTestOrDueDate());

//        delAssessBtn = AssessDetFragView.findViewById(R.id.del_assess);
//        delAssessBtn.setOnClickListener(this);

        return AssessDetFragView;
    }

    @Override
    public void onClick(View v) {
//        CatalogDatabase.getInstance().deleteAssess(getContext(),assessID);
//        Intent returnIntent = new Intent(getContext(),AllAssessActivity.class);
    }
}