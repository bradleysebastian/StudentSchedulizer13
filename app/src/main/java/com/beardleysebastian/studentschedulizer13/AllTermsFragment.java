package com.beardleysebastian.studentschedulizer13;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllTermsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflaterer, ViewGroup vgContainer,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ATFragView = inflaterer.inflate(R.layout.all_terms_fragment, vgContainer, false);
        //Fire up RecyclerView itself
        RecyclerView ATFragRecyclerView = ATFragView.findViewById(R.id.atfrag_recycler_view);
        ATFragRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Send terms to recycle view
//        TermAdapter termAdapter = new TermAdapter(CatalogDatabase.getInstance().getTerms());
//        Toast.makeText(this, getString(CatalogDatabase.getInstance().getAllTerms2(getActivity()).size()),Toast.LENGTH_LONG).show();
        TermAdapter termAdapter = new TermAdapter(CatalogDatabase.getInstance().getAllTerms2(getActivity()));
        ATFragRecyclerView.setAdapter(termAdapter);

        return ATFragView;
    }
    ////////// Interface for the activity to implement
    public interface OnTermSelectedListener {
        void onTermSelected(int termID);
    }
    ////////// Object reference to fragment's "observer/listener" activity
    private OnTermSelectedListener theListener;
    //////////Use interface object reference to make AllTermsActivity the listener/observer
    @Override
    public void onAttach(Context allTermsContext) {
        super.onAttach(allTermsContext);
        if (allTermsContext instanceof OnTermSelectedListener) {
            theListener = (OnTermSelectedListener) allTermsContext;
        } else {
            throw new RuntimeException(allTermsContext.toString()
                    + " must implement OnTermSelectedListener");
        }
    }
    //////////Remove AllTermsActivity as listener/observer
    @Override
    public void onDetach() {
        super.onDetach();
        theListener = null;
    }

    private class TermHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Use Term's getters to extract info
        private Term singleTerm;
        //Display each Term's Title, Start Date, and End Date
        private TextView termNameTxtV;
//        private TextView termStartTxtV;
//        private TextView termEndTxtV;
        //Each Term's holder in RecyclerView
        public TermHolder(LayoutInflater inflaterer, ViewGroup vgParent) {
            super(inflaterer.inflate(R.layout.all_terms_item, vgParent, false));
            itemView.setOnClickListener(this);
            termNameTxtV = itemView.findViewById(R.id.term_title);
//            termStartTxtV = itemView.findViewById(R.id.term_start);
//            termEndTxtV = itemView.findViewById(R.id.term_end);
        }
        //Bind Title
        public void bindInfo(Term inputTerm) {
            singleTerm = inputTerm;
            termNameTxtV.setText(singleTerm.getTermTitle() + " : " +
            singleTerm.getTermStart() + " - " +
            singleTerm.getTermEnd());
//            termStartTxtV.setText(singleTerm.getTermStart() + " - ");
//            termEndTxtV.setText(singleTerm.getTermEnd());
        }

        //Tap RecyclerView entry: TermID sent to host Activity, which loads entry's Details Activity
        @Override
        public void onClick(View inputView) {
            theListener.onTermSelected(singleTerm.getTermID());
        }

    }

    private class TermAdapter extends RecyclerView.Adapter<TermHolder> {
        private List<Term> allTerms;
        public TermAdapter(List<Term> inputAllTerms) {
            allTerms = inputAllTerms;
        }
        @Override
        public TermHolder onCreateViewHolder(ViewGroup vgParent, int inputViewType) {
            LayoutInflater layoutInflaterer = LayoutInflater.from(getActivity());
            return new TermHolder(layoutInflaterer, vgParent);
        }
        @Override
        public void onBindViewHolder(TermHolder inputHolder, int inputPosition) {
            Term slotTerm = allTerms.get(inputPosition);
            inputHolder.bindInfo(slotTerm);
//            inputHolder.bindStart(slotTerm);
//            inputHolder.bindEnd(slotTerm);
        }
        @Override
        public int getItemCount() {
            return allTerms.size();
        }
    }

}