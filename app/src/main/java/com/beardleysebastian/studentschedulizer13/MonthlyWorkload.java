package com.beardleysebastian.studentschedulizer13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MonthlyWorkload extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private List<Course> courseLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_workload_activity);

//        yearSpinner = (Spinner) findViewById(R.id.year_spinner);
//        ArrayAdapter<CharSequence> yrSpinAdapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
//        yrSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        yearSpinner.setAdapter(yrSpinAdapter);
//        yearSpinner.setOnItemSelectedListener(this);
//
//        monthSpinner = (Spinner) findViewById(R.id.month_spinner);
//        ArrayAdapter<CharSequence> monSpinAdapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
//        monSpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        monthSpinner.setAdapter(monSpinAdapter);
//        monthSpinner.setOnItemSelectedListener(this);

        RecyclerView mlRecyclerView = findViewById(R.id.month_data_RV);
        mlRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        CatalogDatabase.getInstance().loadTermCourses(this,1);
        courseLoad = CatalogDatabase.getInstance().getAllCourses();
        MonthLoadRVAdapter mlrvAdapter = new MonthLoadRVAdapter(CatalogDatabase.getInstance().getAllCourses());
        mlRecyclerView.setAdapter(mlrvAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        //If view is YEARLY SPINNER:
//        if(parent.getId() == R.id.year_spinner) {
//            String testSpin = parent.getItemAtPosition(position).toString();
//            Toast.makeText(this, "Selected: " + testSpin + ": " + position, Toast.LENGTH_LONG).show();
//            //Set int for WHERE in search
//            //Re-run query and refill Arraylist - no results should Toastmsg
//            //Refresh RecyclerView
//        }
////        Toast.makeText(this, view + "Selected: " + position, Toast.LENGTH_LONG).show();
//        //If view is MONTHLY SPINNER:
//
//            //Set int for WHERE in search
//            //Re-run query and refill Arraylist - no results should Toastmsg
//            //Refresh RecyclerView
//        //TODO: QUERY: COURSE, JOIN TERM FOR TERM NAME
//        // ARRAYLIST: CATALOGDB
//        // CLASS/OBJECT: COURSE
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class MonthLoadRVAdapter extends RecyclerView.Adapter<MonthLoadRVAdapter.MonthLoadViewHolder>{

        private List<Course> localCourseInfo;
        public MonthLoadRVAdapter(List<Course> inputCourses){localCourseInfo = inputCourses;}

        @Override
        public MonthLoadViewHolder onCreateViewHolder(ViewGroup vg, int viewType){
            View view = LayoutInflater.from(vg.getContext()).inflate(R.layout.monthly_workload_item, vg, false);
            return new MonthLoadViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MonthLoadViewHolder holder, int position) {
            holder.getCourseInfo().setText(localCourseInfo.get(position).getCourseName());
        }

        @Override
        public int getItemCount() {
            return localCourseInfo.size();
        }

        class MonthLoadViewHolder extends RecyclerView.ViewHolder{
            private TextView courseInfo;

            public MonthLoadViewHolder(View view){
                super(view);
//                courseInfo = (TextView) findViewById(R.id.mon_work_item);
                courseInfo = findViewById(R.id.mon_work_item);
            }

            public TextView getCourseInfo(){
                return courseInfo;
            }
        }

    }


}