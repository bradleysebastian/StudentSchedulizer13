package com.beardleysebastian.studentschedulizer13;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseNoteDetailFragment extends Fragment {

    private CourseNote targetCourseNote;
    private int courseNoteID;

    public static CourseNoteDetailFragment newInstance(int courseID){
        CourseNoteDetailFragment cndFrag = new CourseNoteDetailFragment();
        Bundle payload = new Bundle();
        payload.putInt("test",courseID);
        cndFrag.setArguments(payload);
        return cndFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Get the assessID from the intent that started AssessDetailsActivity
        courseNoteID = 1;
        if(getArguments() != null){
            courseNoteID = getArguments().getInt("test");
        }
        targetCourseNote = CatalogDatabase.getInstance().getCourseNote(courseNoteID);
    }

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View courseNoteDetFragView = inflaterer.inflate(R.layout.course_note_detail_fragment,vgContainer,
                false);

        TextView courseNoteTxtV = courseNoteDetFragView.findViewById(R.id.course_note_descr_det);
        courseNoteTxtV.setText(targetCourseNote.getCourseNoteActual());

        return courseNoteDetFragView;
    }

    public String getNoteText(){
        return targetCourseNote.getCourseNoteActual();
    }
}