package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateAssessNoteActivity extends AppCompatActivity {

    private EditText assessNotesActual;
    private int assessNoteID;
    private int assessID;
    private int courseID;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_assess_note_activity);

        assessNoteID = getIntent().getIntExtra(AssessNoteDetailActivity.INTEXTRA_ASSESS_NOTE_ID,1);
        assessID = getIntent().getIntExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, 1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);
        assessNotesActual = findViewById(R.id.upd_assess_note_inputtxt);
        assessNotesActual.setText(CatalogDatabase.getInstance().getAssessNote(assessNoteID).getAssessNoteActual());
    }

    public void updAssessNote(View view) {
        CatalogDatabase.getInstance().updateAssessNote(
                this,
                assessNoteID,
                assessNotesActual.getText().toString()
        );
        Intent backToAssessNotes = new Intent(this,AllAssessNotesActivity.class);
        backToAssessNotes.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,assessID);
        backToAssessNotes.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
        backToAssessNotes.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(backToAssessNotes);
    }
}