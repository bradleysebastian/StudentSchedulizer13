package com.beardleysebastian.studentschedulizer13;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AllCoursesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ACFragView = inflaterer.inflate(R.layout.all_courses_fragment, vgContainer, false);
        int termID = getArguments().getInt(TermDetailActivity.INTEXTRA_TERM_ID);
        //Fire up RecyclerView itself
        RecyclerView ACFragRecyclerView = ACFragView.findViewById(R.id.acfrag_recycler_view);
        ACFragRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Send courses to recycle view
        CatalogDatabase.getInstance().loadTermCourses(getContext(),termID);
        CourseAdapter courseAdapter = new CourseAdapter(CatalogDatabase.getInstance().getAllCourses());
        ACFragRecyclerView.setAdapter(courseAdapter);

        return ACFragView;
    }
    ////////// Interface for the activity to implement
    public interface OnCourseSelectedListener {
        void onCourseSelected(int courseID);
    }
    ////////// Object reference to fragment's "observer/listener" activity
    private OnCourseSelectedListener theListener;
    //////////Use interface object reference to make AllCoursesActivity the listener/observer
    @Override
    public void onAttach(Context allCoursesContext) {
        super.onAttach(allCoursesContext);
        if (allCoursesContext instanceof OnCourseSelectedListener) {
            theListener = (OnCourseSelectedListener) allCoursesContext;
        } else {
            throw new RuntimeException(allCoursesContext.toString()
                    + " must implement OnCourseSelectedListener");
        }
    }
    //////////Remove AllCoursesActivity as listener/observer
    @Override
    public void onDetach() {
        super.onDetach();
        theListener = null;
    }

    private class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Use Course's getters to extract info
        private Course singleCourse;
        //Display each Course's Title, Target Start/End Dates
        private TextView courseNameTxtV;
//        private TextView courseStartEndTextV;
        //Each Course's holder in RecycleView
        public CourseHolder(LayoutInflater inflaterer, ViewGroup vgParent){
            super(inflaterer.inflate(R.layout.all_courses_item, vgParent, false));
            itemView.setOnClickListener(this);
            courseNameTxtV = itemView.findViewById(R.id.course_title);
//            courseStartEndTextV = itemView.findViewById(R.id.course_start);
        }
        //Bind Title, Start & End
        public void bindInfo(Course inputCourse){
            singleCourse = inputCourse;
            courseNameTxtV.setText(singleCourse.getCourseName() + ": " + singleCourse.getTargetStart() + " - " + singleCourse.getTargetEnd());
//            courseStartEndTextV.setText(singleCourse.getTargetStart() + " - " + singleCourse.getTargetEnd());
        }
        //Touch RecyclerView entry: CourseID sent to host AllCoursesAct, whichs hands to CourseDetAct
        @Override
        public void onClick(View v) {
            theListener.onCourseSelected(singleCourse.getCourseID());
        }
    }

    private class CourseAdapter extends RecyclerView.Adapter<CourseHolder>{
        private List<Course> allCourses;
        public CourseAdapter(List<Course> inputAllCourses){
            allCourses = inputAllCourses;
        }
        @Override
        public CourseHolder onCreateViewHolder(ViewGroup vgParent, int inputViewType){
            LayoutInflater layoutInflaterer = LayoutInflater.from(getActivity());
            return new CourseHolder(layoutInflaterer, vgParent);
        }
        @Override
        public void onBindViewHolder(CourseHolder inputHolder, int inputPosition){
            Course slotCourse = allCourses.get(inputPosition);
            inputHolder.bindInfo(slotCourse);
        }
        @Override
        public int getItemCount(){
            return allCourses.size();
        }
    }

}