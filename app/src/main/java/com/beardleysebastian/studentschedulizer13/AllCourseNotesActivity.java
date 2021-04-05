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

public class AllCourseNotesActivity extends AppCompatActivity implements AllCourseNotesFragment.OnCourseNoteSelectedListener{

    private int termID;
    private int courseID;

    @Override
    public void onCourseNoteSelected(int courseNoteID) {
        Intent courseNoteDetail = new Intent(this, CourseNoteDetailActivity.class);
        courseNoteDetail.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        courseNoteDetail.putExtra(CourseNoteDetailActivity.INTEXTRA_COURSE_NOTE_ID,courseNoteID);
        courseNoteDetail.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        startActivity(courseNoteDetail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_course_notes_activity);

        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);

        Bundle payload = new Bundle();
        payload.putInt(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.course_notes_list_frag_container);

        if(targetFragment == null){
            targetFragment = new AllCourseNotesFragment();
            //Add CourseID for query inside fragment
            targetFragment.setArguments(payload);
            fragChief
                    .beginTransaction()
                    .add(R.id.course_notes_list_frag_container, targetFragment)
                    .commit();
        }
    }

    public void returnToCourses(View view) {
        Intent backToCourse = new Intent(this,CourseDetailActivity.class);
        backToCourse.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        backToCourse.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        startActivity(backToCourse);
    }

    public void newCourseNote(View view) {
        Intent newCourseNote = new Intent(this,NewCourseNoteActivity.class);
        newCourseNote.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        newCourseNote.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(newCourseNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_course_notes_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.all_courses_menu2:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu4:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu3:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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