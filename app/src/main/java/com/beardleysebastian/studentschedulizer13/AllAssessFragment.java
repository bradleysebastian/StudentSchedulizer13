package com.beardleysebastian.studentschedulizer13;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AllAssessFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View AAFragView = inflaterer.inflate(R.layout.all_assess_fragment,vgContainer,
                false);
        int courseID = getArguments().getInt(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        //Fire up RecyclerView itself
        RecyclerView AAFragRecyclerView = AAFragView.findViewById(R.id.aafrag_recycler_view);
        AAFragRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Send assessments to RecyclerView
        //Create AssessAdapter from CatalogDB's sample data
        CatalogDatabase.getInstance().loadCourseAssessment(getContext(), courseID);
        AssessAdapter assessAdapter = new AssessAdapter(CatalogDatabase.getInstance()
                .getAllAssessments());
        AAFragRecyclerView.setAdapter(assessAdapter);
        return AAFragView;
    }
    ////////// Interface for the activity to implement
    public interface OnAssessSelectedListener{
        void onAssessSelected(int assessID);
    }
    ////////// Object reference to fragment's "observer/listener" activity
    private OnAssessSelectedListener theListener;
    //////////Use interface object reference to make AllAssessActivity the listener/observer
    @Override
    public void onAttach(Context allAssessContext){
        super.onAttach(allAssessContext);
        if(allAssessContext instanceof OnAssessSelectedListener){
            theListener = (OnAssessSelectedListener) allAssessContext;
        } else {
            throw new RuntimeException(allAssessContext.toString() +
                    "must implement OnAssessSelectedListener");
        }
    }
    //////////Remove AllCoursesActivity as listener/observer
    @Override
    public void onDetach(){
        super.onDetach();
        theListener = null;
    }

    private class AssessHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Use Assessment's getters to extract info
        private Assessment singleAssessment;
        //Display each Assess's Name, Target Take/Due Date
        private TextView assessNameTxtV;
//        private TextView assessTargetDateTxtV;
        //Each Assess's holder in RecyclerView
        public AssessHolder(LayoutInflater inflaterer, ViewGroup vgParent){
            super(inflaterer.inflate(R.layout.all_assess_items,vgParent,false));
            itemView.setOnClickListener(this);
            assessNameTxtV = itemView.findViewById(R.id.assess_title);
//            assessTargetDateTxtV = itemView.findViewById(R.id.assess_date);
        }
        //Bind Info
        public void bindInfo(Assessment inputAssess){
            singleAssessment = inputAssess;
            assessNameTxtV.setText(singleAssessment.getAssessName() + ": " + singleAssessment.getTestOrDueDate());
//            assessTargetDateTxtV.setText(singleAssessment.getTestOrDueDate());
        }
        //Touch RV entry: assessID sent to host Activity, then handed off
        @Override
        public void onClick(View inputView){
            theListener.onAssessSelected(singleAssessment.getAssessID());
        }
    }

    private class AssessAdapter extends RecyclerView.Adapter<AssessHolder>{
        private List<Assessment> allAssessments;
        public AssessAdapter(List<Assessment> inputAllAssess){
            allAssessments = inputAllAssess;
        }
        @Override
        public AssessHolder onCreateViewHolder(ViewGroup vgParent, int inputViewType) {
            LayoutInflater layoutInflaterer = LayoutInflater.from(getActivity());
            return new AssessHolder(layoutInflaterer,vgParent);
        }
        @Override
        public void onBindViewHolder(AssessHolder inputHolder, int inputPosition){
            Assessment slotAssessment = allAssessments.get(inputPosition);
            inputHolder.bindInfo(slotAssessment);
        }
        @Override
        public int getItemCount(){
            return allAssessments.size();
        }
    }
}