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

public class AllAssessActivity extends AppCompatActivity
        implements AllAssessFragment.OnAssessSelectedListener{

    private static int courseID;
    private static int termID;

    @Override
    public void onAssessSelected(int assessID) {
        Intent assessDetActivity = new Intent(this, AssessDetailActivity.class);
        assessDetActivity.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID, assessID);
        assessDetActivity.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        assessDetActivity.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(assessDetActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_assess_activity);

        Intent passingIntent = getIntent();
        courseID = passingIntent.getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = passingIntent.getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);

        Bundle payload = new Bundle();
        payload.putInt(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.assess_list_frag_container);

        if(targetFragment == null){
            targetFragment = new AllAssessFragment();
            //Add CourseID for query inside fragment
            targetFragment.setArguments(payload);
            fragChief
                    .beginTransaction()
                    .add(R.id.assess_list_frag_container, targetFragment)
                    .commit();
        }
    }

    public void addAssess(View view) {
        Intent addAssessIntent = new Intent(this,NewAssessActivity.class);
        //CourseID extra - new Assessment ties to selected course
        addAssessIntent.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        addAssessIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(addAssessIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_assess_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.all_courses_menu3:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu7:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu6:
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

//    public void returnToCourses(View view) {
//        //Needs termID to 'go back' to relevant course list
//        Intent returnAllCourse = new Intent(this,AllCoursesActivity.class);
//        returnAllCourse.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
//        startActivity(returnAllCourse);
//    }
}