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
import android.widget.Toast;

public class AllAssessNotesActivity extends AppCompatActivity implements AllAssessNotesFragment.OnAssessNoteSelectedListener{

    private int assessID;
    private int courseID;
    private int termID;

    @Override
    public void onAssessNoteSelected(int assessNoteID) {
        Intent assessNoteDetail = new Intent(this, AssessNoteDetailActivity.class);
        assessNoteDetail.putExtra(AssessNoteDetailActivity.INTEXTRA_ASSESS_NOTE_ID,assessNoteID);
        assessNoteDetail.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
        assessNoteDetail.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        assessNoteDetail.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(assessNoteDetail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_assess_notes_activity);

        assessID = getIntent().getIntExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, 1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        Bundle payload = new Bundle();
        payload.putInt(AssessDetailActivity.INTEXTRA_ASSESS_ID, assessID);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.assess_notes_list_frag_container);

        if(targetFragment == null){
            targetFragment = new AllAssessNotesFragment();
            //Add CourseID for query inside fragment
            targetFragment.setArguments(payload);
            fragChief
                    .beginTransaction()
                    .add(R.id.assess_notes_list_frag_container, targetFragment)
                    .commit();
        }
    }

//    public void returnToAssessments(View view) {
//        //needs courseID to determine Assessments list
//    }

    public void addAssessNote(View view) {
        Intent addAssessNote = new Intent(this,NewAssessNoteActivity.class);
        addAssessNote.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
        addAssessNote.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        addAssessNote.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(addAssessNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_assess_notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.all_assess_menu1:
                returnToAllAssess();
                return true;
            case R.id.all_courses_menu5:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu9:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu8:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnToAllAssess(){
        Intent allAssessInt = new Intent(this, AllAssessActivity.class);
        allAssessInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        allAssessInt.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        startActivity(allAssessInt);
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