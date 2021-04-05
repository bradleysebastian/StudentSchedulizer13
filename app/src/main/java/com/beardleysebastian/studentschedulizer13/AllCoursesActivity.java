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

public class AllCoursesActivity extends AppCompatActivity implements AllCoursesFragment.OnCourseSelectedListener {

    private static int termID;

    @Override
    public void onCourseSelected(int courseID) {
        // Send the courseID of the clicked RecyclerView entry from this Activity's Fragment to CourseDetailsActivity (to pass to its fragment)
        Intent intent = new Intent(this, CourseDetailActivity.class);
        intent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        intent.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_courses_activity);
        //Get termID as IntExtra
        Intent passingIntent = getIntent();
        termID = passingIntent.getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);
        //Bundle termID for Fragment
        Bundle payload = new Bundle();
        payload.putInt(TermDetailActivity.INTEXTRA_TERM_ID,termID);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.course_list_frag_container);

        if (targetFragment == null) {
            targetFragment = new AllCoursesFragment();
            //termID Bundle added to FragArgs
            targetFragment.setArguments(payload);
            fragChief
                    .beginTransaction()
                    .add(R.id.course_list_frag_container, targetFragment)
                    .commit();
        }
    }

    public void addNewCourse(View view) {
        Intent addCourseIntent = new Intent(this, NewCourseActivity.class);
        //TermID to ensure new Assessment ties to selected Course
        addCourseIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        startActivity(addCourseIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_courses_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.main_menu_menu6:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu5:
                backToTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void backToTerms(View view) {
    public void backToTerms() {
        Intent toTerms = new Intent(this,AllTermsActivity.class);
        startActivity(toTerms);
    }

    public void returnToMainMenu(){
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }
}