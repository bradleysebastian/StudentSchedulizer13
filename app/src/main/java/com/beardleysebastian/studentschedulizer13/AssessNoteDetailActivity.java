package com.beardleysebastian.studentschedulizer13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AssessNoteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTEXTRA_ASSESS_NOTE_ID = "assessNoteID";

    Button delAssessNoteBtn;
    Button updAssessNoteBtn;
    private int assessNoteID;
    private int assessID;
    private int courseID;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assess_note_detail_activity);

        assessNoteID = getIntent().getIntExtra(INTEXTRA_ASSESS_NOTE_ID,1);
        assessID = getIntent().getIntExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, 1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        delAssessNoteBtn = findViewById(R.id.del_assess_note_btn);
        delAssessNoteBtn.setOnClickListener(this);

        updAssessNoteBtn = findViewById(R.id.upd_assess_note_btn);
        updAssessNoteBtn.setOnClickListener(this);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFrag = fragChief.findFragmentById(R.id.assess_note_det_frag_container);

        if(targetFrag == null){
            //Use assessID from AllAssessNoteFragment to instance AssessNoteDetailFragment
            assessNoteID = getIntent().getIntExtra(INTEXTRA_ASSESS_NOTE_ID,1);
            targetFrag = AssessNoteDetailFragment.newInstance(assessNoteID);
            fragChief
                    .beginTransaction()
                    .add(R.id.assess_note_det_frag_container, targetFrag)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        //Delete branch
        if(v == delAssessNoteBtn) {
            CatalogDatabase.getInstance().deleteAssessNote(this,assessNoteID);
            Intent backToAssessNotes = new Intent(this,AllAssessNotesActivity.class);
            backToAssessNotes.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
            backToAssessNotes.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
            backToAssessNotes.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(backToAssessNotes);
        }
        //Update branch
        if(v == updAssessNoteBtn){
            Intent updAssessNote = new Intent(this, UpdateAssessNoteActivity.class);
            updAssessNote.putExtra(INTEXTRA_ASSESS_NOTE_ID, assessNoteID);
            updAssessNote.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
            updAssessNote.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
            updAssessNote.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(updAssessNote);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assess_note_det_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.assess_note_share:
                shareAssessNote();
                return true;
            case R.id.all_assess_menu2:
                returnAllAssessAct();
                return true;
            case R.id.all_courses_menu6:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu10:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu9:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareAssessNote(){
        String noteText = CatalogDatabase.getInstance().getAssessNote(assessNoteID).getAssessNoteActual();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Add Subject");
        shareIntent.putExtra(Intent.EXTRA_TEXT,noteText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    public void returnAllAssessAct(){
        Intent allCoursesInt = new Intent(this, AllAssessActivity.class);
        allCoursesInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        allCoursesInt.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        startActivity(allCoursesInt);
    }

    public void returnAllCoursesAct(){
        Intent allCoursesInt = new Intent(this, AllCoursesActivity.class);
        allCoursesInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(allCoursesInt);
    }

    public void returnToMainMenu(){
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }

    public void returnToAllTerms(){
        Intent allTermsInt = new Intent(this,AllTermsActivity.class);
        startActivity(allTermsInt);
    }
}