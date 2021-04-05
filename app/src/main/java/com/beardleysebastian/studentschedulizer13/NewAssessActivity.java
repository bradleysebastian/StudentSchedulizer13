package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class NewAssessActivity extends AppCompatActivity implements View.OnClickListener{

    EditText assessName;
    Button dueDatePickBtn;
    TextView dueDateETxt;
    private int courseID;
    private int termID;
    private int intYear;
    private int intMonth;
    private int intDay;
    private String assessDescription = "Objective Assessment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_assess_activity);

        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        assessName = findViewById(R.id.new_assess_name_input);

        dueDatePickBtn = findViewById(R.id.new_assess_due_date_btn);
        dueDatePickBtn.setOnClickListener(this);
        dueDateETxt = findViewById(R.id.new_assess_due_date_txt);

    }

    public void saveNewAssess(View view) {
        CatalogDatabase.getInstance().insertNewAssess(
                this,
                courseID,
                assessName.getText().toString(),
                //TODO - assessDescription, (make CatalogDB change first)
                dueDateETxt.getText().toString(),
                assessDescription); //added 11-22
        Intent backToAllAssess = new Intent(this,AllAssessActivity.class);
        backToAllAssess.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
        backToAllAssess.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(backToAllAssess);
    }

    @Override
    public void onClick(View v) {
        //Set Current Date
        final Calendar currDayCal = Calendar.getInstance();
        //Load DatePickerDialog starting point using Current Date
        intYear = currDayCal.get(Calendar.YEAR);
        intMonth = currDayCal.get(Calendar.MONTH);
        intDay = currDayCal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override //Set EditTextView w/ Selected Date
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strMonth;
                if(month < 9){
                    strMonth = "0" + (month + 1);
                } else {
                    strMonth = "" + (month + 1);
                }

                String strDay;
                if(dayOfMonth < 10){
                    strDay = "0" + dayOfMonth;
                } else {
                    strDay = "" + dayOfMonth;
                }
                dueDateETxt.setText(year + "-" + strMonth + "-" + strDay);
            }
        }, intYear, intMonth, intDay); //Load DatePickerDialog starting point
        datePickerDialog.show();
    }

    public void setObjective(View view) {
        assessDescription = "Objective Assessment";
    }

    public void setPerformance(View view) {
        assessDescription = "Performance Assessment";
    }
}