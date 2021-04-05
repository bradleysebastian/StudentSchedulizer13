package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewCourseActivity extends AppCompatActivity implements View.OnClickListener{

    EditText courseName;
    Button startDatePickBtn;
    TextView startDateETxt;
    Button endDatePickBtn;
    TextView endDateETxt;
    private int intYear;
    private int intMonth;
    private int intDay;
    private int termID;
    EditText status;
    EditText mentorName;
    EditText mentorEmail;
    EditText mentorPhone;
    private Date checkStartDate;
    private Date checkEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_course_activity);

        Intent passingIntent = getIntent();
        termID = passingIntent.getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID,1);

        courseName = findViewById(R.id.new_course_name_input);

        startDatePickBtn = findViewById(R.id.new_course_start_date_btn);
        startDatePickBtn.setOnClickListener(this);
        startDateETxt = findViewById(R.id.new_course_start_date_txt);

        endDatePickBtn = findViewById(R.id.new_course_end_date_btn);
        endDatePickBtn.setOnClickListener(this);
        endDateETxt = findViewById(R.id.new_course_end_date_txt);

        status = findViewById(R.id.new_course_status_txt);
        mentorName = findViewById(R.id.new_course_mentor_name_txt);
        mentorEmail = findViewById(R.id.new_course_mentor_email_txt);
        mentorPhone = findViewById(R.id.new_course_mentor_phone_txt);
        //TODO finish

    }

    @Override
    public void onClick(View v) {
        //Set Current Date
        final Calendar currDayCal = Calendar.getInstance();
        //Load DatePickerDialog starting point using Current Date
        intYear = currDayCal.get(Calendar.YEAR);
        intMonth = currDayCal.get(Calendar.MONTH);
        intDay = currDayCal.get(Calendar.DAY_OF_MONTH);
        //branch for Start Date
        if (v == startDatePickBtn) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override //Set EditTextView w/ Selected Date
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String stringDate;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
//                    startDateETxt.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
//                    startDateETxt.setText(year + "-" + strMonth + "-" + strDay);
                    stringDate = year + "-" + strMonth + "-" + strDay;
                    startDateETxt.setText(stringDate);
                    try {
                        checkStartDate = sdf.parse(stringDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, intYear, intMonth, intDay); //Load DatePickerDialog starting point
            datePickerDialog.show();
        }
        //Branch for End Date
        if(v == endDatePickBtn){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override //Set EditTextView w/ Selected Date
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String stringDate;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
//                    endDateETxt.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
//                    endDateETxt.setText(year + "-" + strMonth + "-" + strDay);
                    stringDate = year + "-" + strMonth + "-" + strDay;
                    endDateETxt.setText(stringDate);
                    try {
                        checkEndDate = sdf.parse(stringDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, intYear, intMonth, intDay); //Load DatePickerDialog starting point
            datePickerDialog.show();
        }
    }

    public void saveNewCourse(View view) {
        if (checkStartDate.after(checkEndDate)) {
//            Toast.makeText(this, "Start Date after End Date",Toast.LENGTH_LONG).show();
            FragmentManager fragMgr = getSupportFragmentManager();
            AlertDateMismatch admDialog = new AlertDateMismatch();
            admDialog.show(fragMgr, "dateMismatch");
        } else {
            CatalogDatabase.getInstance().insertNewCourse(
                    this,
                    termID,
                    courseName.getText().toString(),
                    startDateETxt.getText().toString(),
                    endDateETxt.getText().toString(),
                    status.getText().toString(),
                    mentorName.getText().toString(),
                    mentorEmail.getText().toString(),
                    mentorPhone.getText().toString()
            );
            Intent returnIntent = new Intent(this, AllCoursesActivity.class);
            returnIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(returnIntent);
        }
    }
}