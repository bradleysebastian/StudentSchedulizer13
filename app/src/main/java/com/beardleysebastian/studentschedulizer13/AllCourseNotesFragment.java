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

import java.util.List;

public class AllCourseNotesFragment extends Fragment {

    private int courseID;

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        View ACNFragView = inflaterer.inflate(R.layout.all_course_notes_fragment,vgContainer,false);
        courseID = getArguments().getInt(CourseDetailActivity.INTEXTRA_COURSE_ID,1);//Change back to assessID
        //Fire up RecyclerView itself
        RecyclerView ACNFragRecyclerView = ACNFragView.findViewById(R.id.acnfrag_recycler_view);
        ACNFragRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //CatalogDatabase loadCoursesNotes method needed...
        CatalogDatabase.getInstance().loadCourseNotes(getContext(),courseID);//Change back to assessID
        AllCourseNotesFragment.CourseNoteAdapter courseNoteAdapter = new AllCourseNotesFragment.CourseNoteAdapter(CatalogDatabase.getInstance()
                .getAllCourseNotes());
        ACNFragRecyclerView.setAdapter(courseNoteAdapter);
        return ACNFragView;
    }

    ////////// Interface for the activity to implement
    public interface OnCourseNoteSelectedListener{
        void onCourseNoteSelected(int courseNoteID);
    }
    ////////// Object reference to fragment's "observer/listener" activity
    private AllCourseNotesFragment.OnCourseNoteSelectedListener theListener;

    //////////Use interface object reference to make AllAssessNoteActivity the listener/observer
    @Override
    public void onAttach(Context allCourseNotesContext){
        super.onAttach(allCourseNotesContext);
        if(allCourseNotesContext instanceof AllCourseNotesFragment.OnCourseNoteSelectedListener){
            theListener = (AllCourseNotesFragment.OnCourseNoteSelectedListener) allCourseNotesContext;
        } else {
            throw new RuntimeException(allCourseNotesContext.toString() +
                    "must implement OnCourseNoteSelectedListener");
        }
    }
    //////////Remove AllCoursesActivity as listener/observer
    @Override
    public void onDetach(){
        super.onDetach();
        theListener = null;
    }

    private class CourseNoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Use Assessment's getters to extract info
        private CourseNote singleCourseNote;
        //Display each Assess Note
        private TextView courseNoteActualTxtV;
        //Each Assess's holder in RecyclerView
        public CourseNoteHolder(LayoutInflater inflaterer, ViewGroup vgParent){
            super(inflaterer.inflate(R.layout.all_course_notes_item,vgParent,false));
            itemView.setOnClickListener(this);
            courseNoteActualTxtV = itemView.findViewById(R.id.course_notes_actual);
        }
        //Bind Info
        public void bindInfo(CourseNote inputAssessNote){
            singleCourseNote = inputAssessNote;
            courseNoteActualTxtV.setText(singleCourseNote.getCourseNoteActual());
        }
        //Touch RV entry: courseNoteID sent to host Activity, then handed off
        @Override
        public void onClick(View inputView){
            theListener.onCourseNoteSelected(singleCourseNote.getCourseNoteID());
        }
    }

    private class CourseNoteAdapter extends RecyclerView.Adapter<AllCourseNotesFragment.CourseNoteHolder>{
        private List<CourseNote> allCourseNotes;
        public CourseNoteAdapter(List<CourseNote> inputAllCourseNotes){
            allCourseNotes = inputAllCourseNotes;
        }
        @Override
        public AllCourseNotesFragment.CourseNoteHolder onCreateViewHolder(ViewGroup vgParent, int inputViewType) {
            LayoutInflater layoutInflaterer = LayoutInflater.from(getActivity());
            return new AllCourseNotesFragment.CourseNoteHolder(layoutInflaterer,vgParent);
        }
        @Override
        public void onBindViewHolder(AllCourseNotesFragment.CourseNoteHolder inputHolder, int inputPosition){
            CourseNote slotCourseNote = allCourseNotes.get(inputPosition);
            inputHolder.bindInfo(slotCourseNote);
        }
        @Override
        public int getItemCount(){
            return allCourseNotes.size();
        }
    }
}