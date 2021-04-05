package com.beardleysebastian.studentschedulizer13;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class AssessNoteDetailFragment extends Fragment {

    private AssessmentNote targetAssessmentNote;
    private int assessNoteID;

    public static AssessNoteDetailFragment newInstance(int assessID){
        AssessNoteDetailFragment andFrag = new AssessNoteDetailFragment();
        Bundle payload = new Bundle();
        payload.putInt("test",assessID);
        andFrag.setArguments(payload);
        return andFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Get the assessID from the intent that started AssessDetailsActivity
        assessNoteID = 1;
        if(getArguments() != null){
            assessNoteID = getArguments().getInt("test");
        }
        targetAssessmentNote = CatalogDatabase.getInstance().getAssessNote(assessNoteID);
    }

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View AssessNoteDetFragView = inflaterer.inflate(R.layout.assess_note_detail_fragment,vgContainer,
                false);

        TextView assessNoteTxtV = AssessNoteDetFragView.findViewById(R.id.assess_note_descr_det);
        assessNoteTxtV.setText(targetAssessmentNote.getAssessNoteActual());

        return AssessNoteDetFragView;
    }

    public String getNoteText(){
        return targetAssessmentNote.getAssessNoteActual();
    }

//    @Override
//    public void onClick(View v) {
////        CatalogDatabase.getInstance().deleteAssess(getContext(),assessID);
////        Intent returnIntent = new Intent(getContext(),AllAssessActivity.class);
//    }
}