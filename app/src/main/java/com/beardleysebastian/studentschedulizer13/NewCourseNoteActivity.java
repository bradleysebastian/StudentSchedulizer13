package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewCourseNoteActivity extends AppCompatActivity {

    private EditText courseNotesActual;
    private int courseID;
    private int termID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_course_note_activity);

        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);
        courseNotesActual = findViewById(R.id.new_course_note_inputtxt);
    }

    public void addCourseNote(View view) {
        //CatalogDB: Insert text and courseID into courseNoteTable
        CatalogDatabase.getInstance().insertNewCourseNote(
                this,
                courseID,
                courseNotesActual.getText().toString());
        Intent returnCourse = new Intent(this,CourseDetailActivity.class);
        returnCourse.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        returnCourse.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(returnCourse);
    }
}