package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateCourseNoteActivity extends AppCompatActivity {

    private EditText courseNotesActual;
    private int courseNoteID;
    private int courseID;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_course_note_activity);

        courseNoteID = getIntent().getIntExtra(CourseNoteDetailActivity.INTEXTRA_COURSE_NOTE_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);
        courseNotesActual = findViewById(R.id.upd_course_note_inputtxt);
        courseNotesActual.setText(CatalogDatabase.getInstance().getCourseNote(courseNoteID).getCourseNoteActual());
    }

    public void updCourseNote(View view) {
        CatalogDatabase.getInstance().updateCourseNote(
                this,
                courseNoteID,
                courseNotesActual.getText().toString()
        );
        Intent backToCourseNotes = new Intent(this,AllCourseNotesActivity.class);
        backToCourseNotes.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        backToCourseNotes.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(backToCourseNotes);
    }
}