package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewAssessNoteActivity extends AppCompatActivity {

    private EditText assessNotesActual;
    private int courseID;
    private int termID;
    private int assessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assess_note_activity);

        assessID = getIntent().getIntExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, 1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);
        assessNotesActual = findViewById(R.id.new_assess_note_inputtxt);

    }

    public void addAssessNote(View view) {
        //CatalogDB: Insert text and assessID into assessNoteTable
        CatalogDatabase.getInstance().insertNewAssessNote(
                this,
                assessID,
                assessNotesActual.getText().toString());
        Intent returnAssess = new Intent(this,AssessDetailActivity.class);
        returnAssess.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
        returnAssess.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        returnAssess.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(returnAssess);
    }
}