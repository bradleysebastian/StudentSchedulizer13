package com.beardleysebastian.studentschedulizer13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class NewTermActivity extends AppCompatActivity implements View.OnClickListener{

    EditText termName;
    Button startDatePickBtn;
    TextView startDateETxt;
    Button endDatePickBtn;
    TextView endDateETxt;
    private int intYear;
    private int intMonth;
    private int intDay;
    private Date checkStartDate;
    private Date checkEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_term_activity);

        termName = findViewById(R.id.new_term_name_input);

        startDatePickBtn = findViewById(R.id.new_term_start_date_btn);
        startDatePickBtn.setOnClickListener(this);
        startDateETxt = findViewById(R.id.new_term_start_date_txt);

        endDatePickBtn = findViewById(R.id.new_term_end_date_btn);
        endDatePickBtn.setOnClickListener(this);
        endDateETxt = findViewById(R.id.new_term_end_date_txt);
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

    public void saveNewTerm(View view) {

        if(checkStartDate.after(checkEndDate)){
//            Toast.makeText(this, "Start Date after End Date",Toast.LENGTH_LONG).show();
            FragmentManager fragMgr = getSupportFragmentManager();
            AlertDateMismatch admDialog = new AlertDateMismatch();
            admDialog.show(fragMgr,"dateMismatch");
        } else {
            CatalogDatabase.getInstance().insertNewTerm(this,
                    termName.getText().toString(),
                    startDateETxt.getText().toString(),
                    endDateETxt.getText().toString());
            Intent returnIntent = new Intent(this, AllTermsActivity.class);
            startActivity(returnIntent);
        }
    }
}