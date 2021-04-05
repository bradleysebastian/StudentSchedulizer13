package com.beardleysebastian.studentschedulizer13;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CourseDetailFragment extends Fragment {

    private Course targetCourse;

    public static CourseDetailFragment newInstance(int courseID){
        CourseDetailFragment cdFragment = new CourseDetailFragment();
        Bundle payload = new Bundle();
        payload.putInt("test",courseID);
        cdFragment.setArguments(payload);
        return cdFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the courseID from the intent that started CourseDetailsActivity
        int courseID = 1;
        if(getArguments() != null){
            courseID = getArguments().getInt("test");
        }
        targetCourse = CatalogDatabase.getInstance().getCourse(courseID);
    }

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View CourseDetFragView = inflaterer.inflate(R.layout.course_detail_fragment,vgContainer,false);

        TextView courseNameTxtV = CourseDetFragView.findViewById(R.id.course_title_det);
        courseNameTxtV.setText(targetCourse.getCourseName() + " - " + targetCourse.getCourseStatus());

        TextView courseDescTxtV = CourseDetFragView.findViewById(R.id.course_mentor_det);
        courseDescTxtV.setText("Mentor: " + targetCourse.getCourseMentorName() + " " + targetCourse.getCourseMentorEmail() + " " + targetCourse.getCourseMentorPhone());

        TextView courseStartEndTxtV = CourseDetFragView.findViewById(R.id.course_start_end_det);
        courseStartEndTxtV.setText(targetCourse.getTargetStart() + " - " + targetCourse.getTargetEnd());

        return CourseDetFragView;
    }
}