package com.beardleysebastian.studentschedulizer13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class CourseDetailActivity extends AppCompatActivity {

    public static final String INTEXTRA_COURSE_ID = "courseID";
    public static int courseID;
    private static int termID;
    public static int requestCode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail_activity);
        termID = getIntent().getIntExtra(TermDetailActivity.INTEXTRA_TERM_ID, 1);

        FragmentManager fragChief = getSupportFragmentManager();
        Fragment targetFragment = fragChief.findFragmentById(R.id.course_det_frag_container);

        if(targetFragment == null){
            //Use courseID from AllCoursesFragment to instance CourseDetailFragment
            courseID = getIntent().getIntExtra(INTEXTRA_COURSE_ID,1);
            targetFragment = CourseDetailFragment.newInstance(courseID);
            fragChief
                    .beginTransaction()
                    .add(R.id.course_det_frag_container, targetFragment)
                    .commit();
        }
//        courseStartAlertChannel(); //create channel immediately
//        courseStartNotification(courseID); //pops noti onscreen activate
    }

    public void showCourseAssess(View view) {
        Intent allAssessActivity = new Intent(this, AllAssessActivity.class);
        allAssessActivity.putExtra(INTEXTRA_COURSE_ID, courseID);
        allAssessActivity.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(allAssessActivity);
    }

    public void deleteCourse(View view) {
        CatalogDatabase.getInstance().deleteCourse(this, courseID);
        //Needs termID to load Intent for return to AllCoursesActivity, I think
        Intent returnAllCourses = new Intent(this, AllCoursesActivity.class);
        returnAllCourses.putExtra(TermDetailActivity.INTEXTRA_TERM_ID, termID);
        startActivity(returnAllCourses);
    }

    public void updateCourse(View view) {
        Intent updateCourseActivity = new Intent(this,UpdateCourseActivity.class);
        updateCourseActivity.putExtra(INTEXTRA_COURSE_ID, courseID);
        updateCourseActivity.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        startActivity(updateCourseActivity);
    }
    //REMOVE - Create new CourseNotes from AllCourseNotesActivity
//    public void addCourseNoteBtn(View view) {
//        Intent newCourseNote = new Intent(this,NewCourseNoteActivity.class);
//        newCourseNote.putExtra(INTEXTRA_COURSE_ID,courseID);
//        startActivity(newCourseNote);
//    }

    public void viewCourseNotes(View view) {
        Intent viewCourseNotes = new Intent(this,AllCourseNotesActivity.class);
        viewCourseNotes.putExtra(INTEXTRA_COURSE_ID,courseID);
        viewCourseNotes.putExtra(TermDetailActivity.INTEXTRA_TERM_ID,termID);
        startActivity(viewCourseNotes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.course_det_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.course_start_alert:
                courseStartAlert();
                return true;
            case R.id.course_end_alert:
                courseEndAlert();
                return true;
            case R.id.all_courses_menu1:
                returnAllCoursesAct();
                return true;
            case R.id.main_menu_menu3:
                returnToMainMenu();
                return true;
            case R.id.all_terms_menu2:
                returnToAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void courseStartAlert(){
        Calendar calendar = Calendar.getInstance();
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = CatalogDatabase.getInstance().getCourse(courseID).getTargetStart();
        try {
            date = format.parse(dateString);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -2); //TODO: Test this
            Intent receiverInt = new Intent(this,NotiReceiver.class);
            receiverInt.putExtra(NotiReceiver.NOTI_LOCKER, "Course begins soon: " +
                    CatalogDatabase.getInstance().getCourse(courseID).getCourseName() + " " +
                    CatalogDatabase.getInstance().getCourse(courseID).getTargetStart());
            PendingIntent senderInt = PendingIntent.getBroadcast(
                    this,++requestCode,receiverInt,0);//reqcode must be unique
            AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            long deadLine = calendar.getTimeInMillis();
            alarmMgr.set(AlarmManager.RTC_WAKEUP,deadLine,senderInt);
        } catch (ParseException pe){
            Toast.makeText(this, "bad date", Toast.LENGTH_LONG).show();
        }
    }

    public void courseEndAlert(){
        Calendar calendar = Calendar.getInstance();
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = CatalogDatabase.getInstance().getCourse(courseID).getTargetEnd();
        try {
            date = format.parse(dateString);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -2); //TODO: Test this
            Intent receiverInt = new Intent(this,NotiReceiver.class);
            receiverInt.putExtra(NotiReceiver.NOTI_LOCKER, "Course ends soon: " +
                    CatalogDatabase.getInstance().getCourse(courseID).getCourseName() + " " +
                    CatalogDatabase.getInstance().getCourse(courseID).getTargetEnd());
            PendingIntent senderInt = PendingIntent.getBroadcast(
                    this,++requestCode,receiverInt,0);//reqcode must be unique
            AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            long deadLine = calendar.getTimeInMillis();
            alarmMgr.set(AlarmManager.RTC_WAKEUP,deadLine,senderInt);
        } catch (ParseException pe){
            Toast.makeText(this, "bad date", Toast.LENGTH_LONG).show();
        }
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