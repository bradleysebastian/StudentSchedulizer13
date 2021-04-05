package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateAssessActivity extends AppCompatActivity implements View.OnClickListener{

    private int assessID;
    EditText assessName;
    Button dueDatePickBtn;
    TextView dueDateETxt;
    RadioButton objRadBtn;
    RadioButton perfRadBtn;
    private int courseID;
    private int termID;
    private int intYear;
    private int intMonth;
    private int intDay;
    private static String assessDescription = "Objective Assessment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_assess_activity);

        assessID = getIntent().getIntExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID,1);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        assessName = findViewById(R.id.upd_assess_name_input);
        assessName.setText(CatalogDatabase.getInstance().getAssess(assessID).getAssessName(),
                TextView.BufferType.EDITABLE);

        dueDateETxt = findViewById(R.id.upd_assess_due_date_txt);
        dueDateETxt.setText(CatalogDatabase.getInstance().getAssess(assessID).getTestOrDueDate(),
                TextView.BufferType.EDITABLE);

        dueDatePickBtn = findViewById(R.id.upd_assess_due_date_btn);
        dueDatePickBtn.setOnClickListener(this);

        //TODO choose which RadioButton is dotted from assessID.getDescription
        objRadBtn = findViewById(R.id.upd_assess_objective_radio);
        perfRadBtn = findViewById(R.id.upd_assess_performance_radio);
        if(CatalogDatabase.getInstance().getAssess(assessID).getAssessDescription().contentEquals("Performance Assessment")){
            perfRadBtn.setChecked(true);
            assessDescription = "Performance Assessment";
        } else {
            objRadBtn.setChecked(true);
        }

    }

    public void saveUpdateAssess(View view) {
        CatalogDatabase.getInstance().updateAssess(
                this,
                assessID,
                assessName.getText().toString(),
                dueDateETxt.getText().toString(),
                assessDescription);
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