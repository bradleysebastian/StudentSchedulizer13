package com.beardleysebastian.studentschedulizer13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AssessDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTEXTRA_ASSESS_ID = "assessID";

    Button delAssessBtn;
    Button updAssessBtn;
    Button assessNotesBtn;
    private int assessID;
    private int courseID;
    private int termID;
    private static int requestCode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assess_detail_activity);
        courseID = getIntent().getIntExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,1);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        delAssessBtn = findViewById(R.id.del_assess2);
        delAssessBtn.setOnClickListener(this);

        updAssessBtn = findViewById(R.id.upd_assess_btn);
        updAssessBtn.setOnClickListener(this);

        assessNotesBtn = findViewById(R.id.assess_notes_btn);
        assessNotesBtn.setOnClickListener(this);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFrag = fragChief.findFragmentById(R.id.assess_det_frag_container);

        if(targetFrag == null){
            //Use assessID from AllAssessFragment to instance AssessDetailFragment
            assessID = getIntent().getIntExtra(INTEXTRA_ASSESS_ID,1);
            targetFrag = AssessDetailFragment.newInstance(assessID);
            fragChief
                    .beginTransaction()
                    .add(R.id.assess_det_frag_container, targetFrag)
                    .commit();
        }
    }

    @Override
    public void onClick(View v) {
        //Delete branch
        if(v == delAssessBtn) {
            CatalogDatabase.getInstance().deleteAssess(this, assessID);
            Intent returnIntent = new Intent(this, AllAssessActivity.class);
            returnIntent.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
            returnIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(returnIntent);
        }
        //Update branch
        if(v == updAssessBtn){
            Intent updAssessIntent = new Intent(this, UpdateAssessActivity.class);
            updAssessIntent.putExtra(INTEXTRA_ASSESS_ID, assessID);
            updAssessIntent.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID,courseID);
            updAssessIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(updAssessIntent);
        }
        //Assessment Notes Branch
        if(v == assessNotesBtn){
            Intent notesIntent = new Intent(this,AllAssessNotesActivity.class);
            notesIntent.putExtra(INTEXTRA_ASSESS_ID, assessID);
            notesIntent.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
            notesIntent.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
            startActivity(notesIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assess_det_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.assess_day_alert:
                assessDayAlert();
                return true;
//            case R.id.assess_due_alert:
//                assessDueAlert();
//                return true;
            case R.id.all_assess_menu3:
                returnAllAssess();
                return true;
            case R.id.all_courses_menu4:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu8:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu7:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void assessDayAlert(){
        Calendar calendar = Calendar.getInstance();
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = CatalogDatabase.getInstance().getAssess(assessID).getTestOrDueDate();
        try {
            date = format.parse(dateString);
            //subtract two days
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -2); //TODO: Test this
            Intent receiverInt = new Intent(this,NotiReceiver.class);
            receiverInt.putExtra(NotiReceiver.NOTI_LOCKER, "Upcoming " +
                    CatalogDatabase.getInstance().getAssess(assessID).getAssessDescription() +
                    ": " + CatalogDatabase.getInstance().getAssess(assessID).getAssessName() + ", " +
                    CatalogDatabase.getInstance().getAssess(assessID).getTestOrDueDate());
            PendingIntent senderInt = PendingIntent.getBroadcast(
                    this,++CourseDetailActivity.requestCode,receiverInt,0);//reqcode must be unique
            AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            long deadLine = calendar.getTimeInMillis();
            alarmMgr.set(AlarmManager.RTC_WAKEUP,deadLine,senderInt);
        } catch (ParseException pe){
            Toast.makeText(this, "bad date", Toast.LENGTH_LONG).show();
        }
    }

    public void assessDueAlert(){
        Calendar calendar = Calendar.getInstance();
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = CatalogDatabase.getInstance().getAssess(assessID).getTestOrDueDate();
        try {
            date = format.parse(dateString);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -2); //TODO: Test this
            Intent receiverInt = new Intent(this,NotiReceiver.class);
            receiverInt.putExtra(NotiReceiver.NOTI_LOCKER, "Project due soon: " +
                    CatalogDatabase.getInstance().getAssess(assessID).getAssessName() + " " +
                    CatalogDatabase.getInstance().getAssess(assessID).getTestOrDueDate());
            PendingIntent senderInt = PendingIntent.getBroadcast(
                    this,++CourseDetailActivity.requestCode,receiverInt,0);//reqcode must be unique
            AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            long deadLine = calendar.getTimeInMillis();
            alarmMgr.set(AlarmManager.RTC_WAKEUP,deadLine,senderInt);
        } catch (ParseException pe){
            Toast.makeText(this, "bad date", Toast.LENGTH_LONG).show();
        }
    }

    public void returnAllAssess(){
        Intent allAssessInt = new Intent(this, AllAssessActivity.class);
        allAssessInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        allAssessInt.putExtra(CourseDetailActivity.INTEXTRA_COURSE_ID, courseID);
//        allAssessInt.putExtra(AssessDetailActivity.INTEXTRA_ASSESS_ID, assessID);
        startActivity(allAssessInt);
    }

    public void returnAllCoursesAct(){
        Intent allCoursesInt = new Intent(this, AllCoursesActivity.class);
        allCoursesInt.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(allCoursesInt);
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