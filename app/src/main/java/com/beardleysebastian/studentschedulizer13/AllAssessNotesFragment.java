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

public class AllAssessNotesFragment extends Fragment {

    private int assessID;//Change back to assessID

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState){
        View AANFragView = inflaterer.inflate(R.layout.all_assess_notes_fragment,vgContainer,false);
        assessID = getArguments().getInt(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);//Change back to assessID
        //Fire up RecyclerView itself
        RecyclerView AANFragRecyclerView = AANFragView.findViewById(R.id.aanfrag_recycler_view);
        AANFragRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //CatalogDatabase loadAssessNotes method needed...
        CatalogDatabase.getInstance().loadAssessNotes(getContext(),assessID);//Change back to assessID
        AllAssessNotesFragment.AssessNoteAdapter assessNoteAdapter = new AllAssessNotesFragment.AssessNoteAdapter(CatalogDatabase.getInstance()
                .getAllAssessmentNotes());
        AANFragRecyclerView.setAdapter(assessNoteAdapter);
        return AANFragView;
    }

    ////////// Interface for the activity to implement
    public interface OnAssessNoteSelectedListener{
        void onAssessNoteSelected(int assessNoteID);
    }
    ////////// Object reference to fragment's "observer/listener" activity
    private AllAssessNotesFragment.OnAssessNoteSelectedListener theListener;
    //////////Use interface object reference to make AllAssessNoteActivity the listener/observer
    @Override
    public void onAttach(Context allAssessNotesContext){
        super.onAttach(allAssessNotesContext);
        if(allAssessNotesContext instanceof AllAssessNotesFragment.OnAssessNoteSelectedListener){
            theListener = (AllAssessNotesFragment.OnAssessNoteSelectedListener) allAssessNotesContext;
        } else {
            throw new RuntimeException(allAssessNotesContext.toString() +
                    "must implement OnAssessNoteSelectedListener");
        }
    }
    //////////Remove AllCoursesActivity as listener/observer
    @Override
    public void onDetach(){
        super.onDetach();
        theListener = null;
    }
    private class AssessNoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Use Assessment's getters to extract info
        private AssessmentNote singleAssessmentNote;
        //Display each Assess Note
        private TextView assessNoteActualTxtV;
        //Each Assess's holder in RecyclerView
        public AssessNoteHolder(LayoutInflater inflaterer, ViewGroup vgParent){
            super(inflaterer.inflate(R.layout.all_assess_notes_item,vgParent,false));
            itemView.setOnClickListener(this);
            assessNoteActualTxtV = itemView.findViewById(R.id.assess_notes_actual);
        }
        //Bind Info
        public void bindInfo(AssessmentNote inputAssessNote){
            singleAssessmentNote = inputAssessNote;
            assessNoteActualTxtV.setText(singleAssessmentNote.getAssessNoteActual());
        }
        //Touch RV entry: assessNoteID sent to host Activity, then handed off
        @Override
        public void onClick(View inputView){
            theListener.onAssessNoteSelected(singleAssessmentNote.getAssessNoteID());
        }
    }
    private class AssessNoteAdapter extends RecyclerView.Adapter<AllAssessNotesFragment.AssessNoteHolder>{
        private List<AssessmentNote> allAssessmentNotes;
        public AssessNoteAdapter(List<AssessmentNote> inputAllAssessNotes){
            allAssessmentNotes = inputAllAssessNotes;
        }
        @Override
        public AllAssessNotesFragment.AssessNoteHolder onCreateViewHolder(ViewGroup vgParent, int inputViewType) {
            LayoutInflater layoutInflaterer = LayoutInflater.from(getActivity());
            return new AllAssessNotesFragment.AssessNoteHolder(layoutInflaterer,vgParent);
        }
        @Override
        public void onBindViewHolder(AllAssessNotesFragment.AssessNoteHolder inputHolder, int inputPosition){
            AssessmentNote slotAssessmentNote = allAssessmentNotes.get(inputPosition);
            inputHolder.bindInfo(slotAssessmentNote);
        }
        @Override
        public int getItemCount(){
            return allAssessmentNotes.size();
        }
    }

}