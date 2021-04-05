package com.beardleysebastian.studentschedulizer13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Simple activity load (all terms) - no extra needed
    public void showAllTerms(View view) {
        Intent allTermsIntent = new Intent(this, AllTermsActivity.class);
        startActivity(allTermsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId() ){
            case R.id.insert_sample_data:
                insertSampleData();
                return true;
            case R.id.remove_sample_data:
                removeAllData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void removeAllData() {
        Toast.makeText(this, "Sample Data Removed - Database Completely Emptied", Toast.LENGTH_LONG).show();
        CatalogDatabase.getInstance().clearData(this);
    }

    private void insertSampleData() {
        CatalogDatabase.getInstance().insertNewTerm(this, "A",
                "2020-11-01", "2020-11-30");
        CatalogDatabase.getInstance().insertNewTerm(this, "B",
                "2020-12-01", "2020-12-31");
        CatalogDatabase.getInstance().insertNewTerm(this, "C",
                "2021-01-01", "2021-01-30");
        CatalogDatabase.getInstance().insertNewCourse(this, 1, "A1",
                "2020-11-01", "2020-11-10","Incomplete",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 1, "A2",
                "2020-11-11", "2020-11-20","Completed",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 1, "A3",
                "2020-11-21", "2020-11-30","In Progress",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 2, "B1",
                "2020-12-01", "2020-12-10","Planned",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 2, "B2",
                "2020-12-11", "2020-12-20","Planned - Audit",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 2, "B3",
                "2020-12-21", "2020-12-30","Planned",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 3, "C1",
                "2021-01-01", "2021-01-10","Not In Catalog Yet",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 3, "C2",
                "2021-01-11", "2021-01-20","Planned",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewCourse(this, 3, "C3",
                "2021-01-21", "2021-01-31","Not In Catalog Yet",
                "Mr. Smith", "smith@smith.com", "123456789");
        CatalogDatabase.getInstance().insertNewAssess(this,1,
                "A1GOLD","2020-11-05","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,1,
                "A1SILVER","2020-11-07","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,1,
                "A1BRONZE","2020-11-10","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,2,
                "A2GOLD","2020-11-15","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,2,
                "A2SILVER","2020-11-17","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,2,
                "A2BRONZE","2020-11-20","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,3,
                "A3GOLD","2020-11-25","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,3,
                "A3SILVER","2020-11-27","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,3,
                "A3BRONZE","2020-11-30","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,4,
                "B1GOLD","2020-12-05","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,4,
                "B1SILVER","2020-12-07","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,4,
                "B1BRONZE","2020-12-10","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,5,
                "B2GOLD","2020-12-15","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,5,
                "B2SILVER","2020-12-17","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,5,
                "B2BRONZE","2020-12-20","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,6,
                "B3GOLD","2020-12-25","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,6,
                "B3SILVER","2020-12-27","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,6,
                "B3BRONZE","2020-12-30","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,7,
                "C1GOLD","2021-01-05","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,7,
                "C1SILVER","2021-01-07","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,7,
                "C1BRONZE","2021-01-10","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,8,
                "C2GOLD","2021-01-15","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,8,
                "C2SILVER","2021-01-17","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,8,
                "C2BRONZE","2021-01-20","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,9,
                "C3GOLD","2021-01-25","Objective Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,9,
                "C3SILVER","2021-01-27","Performance Assessment");
        CatalogDatabase.getInstance().insertNewAssess(this,9,
                "C3BRONZE","2021-01-30","Objective Assessment");
        CatalogDatabase.getInstance().insertNewCourseNote(this,1,
                "Course A1: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,1,
                "Course A1: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,2,
                "Course A2: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,2,
                "Course A2: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,3,
                "Course A3: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,3,
                "Course A3: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,4,
                "Course B1: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,4,
                "Course B1: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,5,
                "Course B2: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,5,
                "Course B2: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,6,
                "Course B3: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,6,
                "Course B3: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,7,
                "Course C1: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,7,
                "Course C1: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,8,
                "Course C2: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,8,
                "Course C2: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,9,
                "Course C3: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewCourseNote(this,9,
                "Course C3: More Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah Blah " +
                        "Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 1,
                "Assessment A1GOLD: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 2,
                "Assessment A1SILVER: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 3,
                "Assessment A1BRONZE: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 4,
                "Assessment A2GOLD: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 5,
                "Assessment A2SILVER: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 6,
                "Assessment A2SILVER: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        CatalogDatabase.getInstance().insertNewAssessNote(this, 7,
                "Assessment A3GOLD: Blah Blah Blah Blah Blah Blah Blah BlahBlah Blah Blah " +
                        "Blah Blah Blah Blah Blah");
        Toast.makeText(this, "Sample Data Inserted - Database Populated", Toast.LENGTH_LONG).show();
    }


//    Add'l feature still being considered
//    public void showMonthlyCourseLoad(View view) {
//        Intent monLoadAct = new Intent(this, MonthlyWorkload.class);
//        startActivity(monLoadAct);
//    }
}