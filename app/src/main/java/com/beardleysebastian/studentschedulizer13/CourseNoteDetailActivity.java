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

public class CourseNoteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTEXTRA_COURSE_NOTE_ID = "courseNoteID";

    Button delCourseNoteBtn;
    Button updCourseNoteBtn;
    private int courseNoteID;
    private int courseID;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_note_detail_activity);

        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);
        courseNoteID = getIntent().getIntExtra(INTEXTRA_COURSE_NOTE_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);

        delCourseNoteBtn = findViewById(R.id.del_course_note_btn);
        delCourseNoteBtn.setOnClickListener(this);

        updCourseNoteBtn = findViewById(R.id.upd_course_note_btn);
        updCourseNoteBtn.setOnClickListener(this);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFrag = fragChief.findFragmentById(R.id.course_note_det_frag_container);

        if(targetFrag == null){
            //Use assessID from AllAssessNoteFragment to instance AssessNoteDetailFragment
            courseNoteID = getIntent().getIntExtra(INTEXTRA_COURSE_NOTE_ID,1);
            targetFrag = CourseNoteDetailFragment.newInstance(courseNoteID);
            fragChief
                    .beginTransaction()
                    .add(R.id.course_note_det_frag_container, targetFrag)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        //Delete branch
        if(v == delCourseNoteBtn) {
            CatalogDatabase.getInstance().deleteCourseNote(this,courseNoteID);
            Intent backToCourseNotes = new Intent(this,AllCourseNotesActivity.class);
            backToCourseNotes.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
            backToCourseNotes.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(backToCourseNotes);
        }
        //Update branch
        if(v == updCourseNoteBtn){
            Intent updAssessNote = new Intent(this, UpdateCourseNoteActivity.class);
            updAssessNote.putExtra(INTEXTRA_COURSE_NOTE_ID, courseNoteID);
            updAssessNote.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
            updAssessNote.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(updAssessNote);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_note_det_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.course_note_share:
                shareCourseNote();
                return true;
            case R.id.all_course_notes_menu:
                returnToAllCourseNotes();
                return true;
            case R.id.all_courses_menu3:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu5:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu4:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareCourseNote(){
        String noteText = CatalogDatabase.getInstance().getCourseNote(courseNoteID).getCourseNoteActual();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Add Subject");
        shareIntent.putExtra(Intent.EXTRA_TEXT,noteText);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

//    public void returnSelectedCourse(){
//        Intent selectedCourseInt = new Intent(this,CourseDetailActivity.class);
//        selectedCourseInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
//        selectedCourseInt.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
//        startActivity(selectedCourseInt);
//    }

    public void returnToAllCourseNotes(){
        Intent allCourseNotesInt = new Intent(this,AllCourseNotesActivity.class);
        allCourseNotesInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        allCourseNotesInt.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        startActivity(allCourseNotesInt);
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