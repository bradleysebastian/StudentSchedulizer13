package com.beardleysebastian.studentschedulizer13;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AllTermsActivity extends AppCompatActivity implements AllTermsFragment.OnTermSelectedListener {

    @Override
    public void onTermSelected(int termID) {
        // Send the termID of the clicked RecyclerView entry from this Activity's Fragment to TermDetailsActivity (to pass to its fragment)
        Intent intent = new Intent(this, TermDetailActivity.class);
        intent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_terms_activity);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.list_fragment_container);

        if (targetFragment == null) {
            targetFragment = new AllTermsFragment();
            fragChief
                    .beginTransaction()
                    .add(R.id.list_fragment_container, targetFragment)
                    .commit();
        }
    }

    public void addNewTerm(View view) {
        Intent addTermIntent = new Intent(this, NewTermActivity.class);
        //Don't think this needs an extra...
        startActivity(addTermIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_terms_act_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.main_menu_menu1:
                returnToMainMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void returnToMainMenu(){
        Intent mainIntent = new Intent(this,MainActivity.class);
        startActivity(mainIntent);
    }
}