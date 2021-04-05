package com.beardleysebastian.studentschedulizer13;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TermDetailActivity extends AppCompatActivity {

    public static final String INTEXTRA_TERM_ID = "termID";
    public static int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_detail_activity);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.details_fragment_container);

        if(savedInstanceState != null){
            termID = savedInstanceState.getInt(INTEXTRA_TERM_ID,termID);
        }

        if (targetFragment == null) {
            // Use termID from AllTermsFragment to instantiate TermDetailFragment\
            termID = getIntent().getIntExtra(INTEXTRA_TERM_ID, 1);
            targetFragment = TermDetailFragment.newInstance(termID);
            fragChief
                    .beginTransaction()
                    .add(R.id.details_fragment_container, targetFragment)
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INTEXTRA_TERM_ID, termID);
//        Toast.makeText(this,"savedInstance loaded", Toast.LENGTH_LONG).show();
    }

    public void showTermsCourses(View view) {
        Intent showCoursesInt = new Intent(this,AllCoursesActivity.class);
        showCoursesInt.putExtra(INTEXTRA_TERM_ID,termID);
        startActivity(showCoursesInt);
    }

    public void updateTermInfo(View view) {
        Intent updateTerm = new Intent(this,UpdateTermActivity.class);
        updateTerm.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        startActivity(updateTerm);
    }

    public void deleteTerm(View view) {
        //Check for Courses in term
        if(CatalogDatabase.getInstance().loadTermCourses(this,termID)) {
            //Y - Alert Dialog
            FragmentManager fragMgr = getSupportFragmentManager();
            AlertDeleteTerm adtDialog = new AlertDeleteTerm();
            adtDialog.show(fragMgr,"delTermAlert");
        } else {
            //N - Delete Term
            CatalogDatabase.getInstance().deleteTerm(this,termID);
            Intent returnToTerms = new Intent(this, AllTermsActivity.class);
            startActivity(returnToTerms);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.term_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.main_menu_menu2:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu1:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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