package com.beardleysebastian.studentschedulizer13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CatalogDatabase {

    private static CatalogDatabase CatalogDBInstance;
    private static List<Term> allTerms = new ArrayList<>();
    private static List<Course> allCourses = new ArrayList<>();
    private static List<Assessment> allAssessments = new ArrayList<>();
    private static List<AssessmentNote> allAssessmentNotes = new ArrayList<>();
    private static List<CourseNote> allCourseNotes = new ArrayList<>();
//    private static SQLiteDatabase

    //singleton instance
    public static CatalogDatabase getInstance() {
        if (CatalogDBInstance == null) {
            CatalogDBInstance = new CatalogDatabase();
        }
        return CatalogDBInstance;
    }

    private CatalogDatabase() {
    }

    //TODO add Context as parameter to getTerms, which will engage DatabaseHelper for Terms data
    //DatabaseHelper dbHelper = new DatabaseHelper(context);
    //dbHelper.getWriteableDatabase();
    //query
    //close db?
    public List<Term> getTerms() {
        return allTerms;
    } //Required???

    public List<Course> getAllCourses(){
        return allCourses;
    }

    public List<Assessment> getAllAssessments(){return allAssessments;}

    public List<AssessmentNote> getAllAssessmentNotes(){return allAssessmentNotes;}

    public List<CourseNote> getAllCourseNotes(){return allCourseNotes;}

    public Term getTerm(int termID) {
        for (Term term : allTerms) {
            if (term.getTermID() == termID) {
                return term;
            }
        }
        return null;
    }

    public Course getCourse(int courseID){
        for (Course course : allCourses){
            if (course.getCourseID() == courseID){
                return course;
            }
        }
        return null;
    }

    public Assessment getAssess(int assessID){
        for(Assessment assessment : allAssessments){
            if(assessment.getAssessID() == assessID){
                return assessment;
            }
        }
        return null;
    }

    public AssessmentNote getAssessNote(int assessNoteID){
        for(AssessmentNote targetAN : allAssessmentNotes){
            if(targetAN.getAssessNoteID() == assessNoteID){
                return targetAN;
            }
        }
        return null;
    }

    public CourseNote getCourseNote(int courseNoteID){
        for(CourseNote targetCN : allCourseNotes){
            if(targetCN.getCourseNoteID() == courseNoteID){
                return targetCN;
            }
        }
        return null;
    }

    public List<Term> getAllTerms2(Context callContext){
//        List<Term> allTerms = new ArrayList<>();
        allTerms.clear();
        SQLiteDatabase db = new DatabaseHelper(callContext).getReadableDatabase();
        //Do some stuff
//        String allTermsQuery = "SELECT * FROM " + DatabaseHelper.TABLE_TERMS + " ORDER BY datetime(" + DatabaseHelper.TERM_START + ") DESC";
        String allTermsQuery = "SELECT * FROM " + DatabaseHelper.TABLE_TERMS + " ORDER BY " + DatabaseHelper.TERM_START;
        Cursor cursor = db.rawQuery(allTermsQuery,null);
        if(cursor.moveToFirst()){
            do{
                Term addTerm = new Term();
                int id = cursor.getInt(0);
                addTerm.setTermID(id);
                String title = cursor.getString(1);
                addTerm.setTermTitle(title);
                String start = cursor.getString(2);
                addTerm.setTermStart(start);
                String end = cursor.getString(3);
                addTerm.setTermEnd(end);
                allTerms.add(addTerm);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return allTerms;
    }

    public boolean loadTermCourses(Context callContext, int termID){
        allCourses.clear();
        boolean coursesPresent = false;
        SQLiteDatabase db = new DatabaseHelper(callContext).getReadableDatabase();
        String allTermCoursesQuery = "SELECT * FROM " + DatabaseHelper.TABLE_COURSES +
                " WHERE " + DatabaseHelper.COURSE_TERM_ID + " = " + termID + " ORDER BY " + DatabaseHelper.COURSE_START;
        Cursor cursor = db.rawQuery(allTermCoursesQuery,null);
        if(cursor.moveToFirst()){
            coursesPresent = true;
            do{
                Course addCourse = new Course();
                int id = cursor.getInt(0);
                addCourse.setCourseID(id);
                int tID = cursor.getInt(1);
                addCourse.setTermID(tID);
                String cName = cursor.getString(2);
                addCourse.setCourseName(cName);
                String start = cursor.getString(3);
                addCourse.setTargetStart(start);
                String end = cursor.getString(4);
                addCourse.setTargetEnd(end);
                String status = cursor.getString(5);
                addCourse.setCourseStatus(status);
                String mentor = cursor.getString(6);
                addCourse.setCourseMentorName(mentor);
                String mPhone = cursor.getString(7);
                addCourse.setCourseMentorPhone(mPhone);
                String mEmail = cursor.getString(8);
                addCourse.setCourseMentorEmail(mEmail);
                allCourses.add(addCourse);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return coursesPresent;
    }

    public void loadCourseAssessment(Context callContext, int courseID){
        allAssessments.clear();
        SQLiteDatabase db = new DatabaseHelper(callContext).getReadableDatabase();
        String allCourseAssessQuery = "SELECT * FROM " + DatabaseHelper.TABLE_ASSESSMENTS +
                " WHERE " + DatabaseHelper.ASSESSMENT_COURSE_ID + " = " + courseID + " ORDER BY "
                + DatabaseHelper.ASSESSMENT_DATETIME;
        Cursor cursor = db.rawQuery(allCourseAssessQuery, null);
        if(cursor.moveToFirst()){
            do{
                Assessment addAssess = new Assessment();
                int id = cursor.getInt(0);
                addAssess.setAssessID(id);
                int cID = cursor.getInt(1);
                addAssess.setCourseID(cID);
                String aName = cursor.getString(2);
                addAssess.setAssessName(aName);
                String aDate = cursor.getString(3);
                addAssess.setTestOrDueDate(aDate);
                String aDesc = cursor.getString(4); //added 11-22
                addAssess.setAssessDescription(aDesc); //added 11-22
                allAssessments.add(addAssess);
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    public void loadAssessNotes(Context callContext, int assessID){
        allAssessmentNotes.clear();
        SQLiteDatabase db = new DatabaseHelper(callContext).getReadableDatabase();
        String allAssessNotesQuery = "SELECT * FROM " + DatabaseHelper.TABLE_ASSESSMENT_NOTES +
                " WHERE " + DatabaseHelper.ASSESSMENT_NOTE_ASSESSMENT_ID + " = " + assessID +
                " ORDER BY " + DatabaseHelper.ASSESSMENT_NOTES_TABLE_ID;
        Cursor cursor = db.rawQuery(allAssessNotesQuery,null);
        if(cursor.moveToFirst()){
            do{
                AssessmentNote addAssessNote = new AssessmentNote();
                int id = cursor.getInt(0);
                addAssessNote.setAssessNoteID(id);
                int aid = cursor.getInt(1);
                String text = cursor.getString(2);
                addAssessNote.setAssessNoteActual(text);
                allAssessmentNotes.add(addAssessNote);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void loadCourseNotes(Context callContext, int courseID){
        allCourseNotes.clear();
        SQLiteDatabase db = new DatabaseHelper(callContext).getReadableDatabase();
        String allCourseNotesQuery = "SELECT * FROM " + DatabaseHelper.TABLE_COURSE_NOTES +
                " WHERE " + DatabaseHelper.COURSE_NOTE_COURSE_ID + " = " + courseID +
                " ORDER BY " + DatabaseHelper.COURSE_NOTES_TABLE_ID;
        Cursor cursor = db.rawQuery(allCourseNotesQuery, null);
        if(cursor.moveToFirst()){
            do{
                CourseNote addCourseNote = new CourseNote();
                int id = cursor.getInt(0);
                addCourseNote.setCourseNoteID(id);
                int cid = cursor.getInt(1);
                String text = cursor.getString(2);
                addCourseNote.setCourseNoteActual(text);
                allCourseNotes.add(addCourseNote);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void insertNewTerm(Context inputContext, String termTitle, String termStart, String termEnd){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TERM_NAME, termTitle);
        contentValues.put(DatabaseHelper.TERM_START,termStart);
        contentValues.put(DatabaseHelper.TERM_END,termEnd);
        contentValues.put(DatabaseHelper.TERM_ACTIVE,0);
        db.insert(DatabaseHelper.TABLE_TERMS,null,contentValues);
    }

    public void insertNewCourse(Context inputContext, int termID, String courseName,
                                String courseStart, String courseEnd, String courseStatus,
                                String mentorName, String mentorEmail, String mentorPhone){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COURSE_TERM_ID,termID);
        contentValues.put(DatabaseHelper.COURSE_NAME, courseName);
        contentValues.put(DatabaseHelper.COURSE_START, courseStart);
        contentValues.put(DatabaseHelper.COURSE_END, courseEnd);
        contentValues.put(DatabaseHelper.COURSE_STATUS, courseStatus);
        contentValues.put(DatabaseHelper.COURSE_MENTOR, mentorName);
        contentValues.put(DatabaseHelper.COURSE_MENTOR_EMAIL, mentorEmail);
        contentValues.put(DatabaseHelper.COURSE_MENTOR_PHONE, mentorPhone);
        db.insert(DatabaseHelper.TABLE_COURSES,null,contentValues);
    }

    public void insertNewAssess(Context inputContext, int courseID, String assessName, String dueDate, String assessDesc){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ASSESSMENT_COURSE_ID,courseID);
        contentValues.put(DatabaseHelper.ASSESSMENT_NAME, assessName);
        contentValues.put(DatabaseHelper.ASSESSMENT_DATETIME, dueDate);
        contentValues.put(DatabaseHelper.ASSESSMENT_DESCRIPTION, assessDesc); //added 11-22
        db.insert(DatabaseHelper.TABLE_ASSESSMENTS,null,contentValues);
    }

    public void insertNewAssessNote(Context inputContext, int assessID, String noteText){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ASSESSMENT_NOTE_ASSESSMENT_ID,assessID);
        contentValues.put(DatabaseHelper.ASSESSMENT_NOTE_TEXT,noteText);
        db.insert(DatabaseHelper.TABLE_ASSESSMENT_NOTES,null,contentValues);
    }

    public void insertNewCourseNote(Context inputContext, int courseID, String noteText){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COURSE_NOTE_COURSE_ID,courseID);
        contentValues.put(DatabaseHelper.COURSE_NOTE_TEXT,noteText);
        db.insert(DatabaseHelper.TABLE_COURSE_NOTES,null,contentValues);
    }
    //updateTerm
    public void updateTerm(Context inputContext, int termID, String termTitle, String termStart, String termEnd){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String updWhere = DatabaseHelper.TERMS_TABLE_ID + " = ? ";
        String[] updWhereArgs = {String.valueOf(termID)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TERM_NAME, termTitle);
        contentValues.put(DatabaseHelper.TERM_START,termStart);
        contentValues.put(DatabaseHelper.TERM_END,termEnd);
        contentValues.put(DatabaseHelper.TERM_ACTIVE,0);
        db.update(DatabaseHelper.TABLE_TERMS,contentValues,updWhere,updWhereArgs);
    }
    //updateCourse
    public void updateCourse(Context inputContext, int termID, int courseID, String courseName,
                             String courseStart, String courseEnd, String courseStatus,
                             String mentorName, String mentorEmail, String mentorPhone){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String updWhere = DatabaseHelper.COURSES_TABLE_ID + " = ? ";
        String[] updWhereArgs = {String.valueOf(courseID)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COURSE_TERM_ID,termID);
        contentValues.put(DatabaseHelper.COURSE_NAME, courseName);
        contentValues.put(DatabaseHelper.COURSE_START, courseStart);
        contentValues.put(DatabaseHelper.COURSE_END, courseEnd);
        contentValues.put(DatabaseHelper.COURSE_STATUS, courseStatus);
        contentValues.put(DatabaseHelper.COURSE_MENTOR, mentorName);
        contentValues.put(DatabaseHelper.COURSE_MENTOR_EMAIL, mentorEmail);
        contentValues.put(DatabaseHelper.COURSE_MENTOR_PHONE, mentorPhone);
        db.update(DatabaseHelper.TABLE_COURSES,contentValues,updWhere,updWhereArgs);
    }
    //updateAssess
    public void updateAssess(Context inputContext, int assessID, String assessName, String dueDate
            , String assessDesc) {
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String updWhere = DatabaseHelper.ASSESSMENTS_TABLE_ID + " = ? ";
        String[] updWhereArgs = {String.valueOf(assessID)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ASSESSMENT_NAME, assessName);
        contentValues.put(DatabaseHelper.ASSESSMENT_DATETIME, dueDate);
        contentValues.put(DatabaseHelper.ASSESSMENT_DESCRIPTION, assessDesc); //added 11-22
        db.update(DatabaseHelper.TABLE_ASSESSMENTS,contentValues,updWhere,updWhereArgs);
    }
    public void updateAssessNote(Context inputContext, int assessNoteID, String noteText){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String updWhere = DatabaseHelper.ASSESSMENT_NOTES_TABLE_ID + " =? ";
        String[] updWhereArgs = {String.valueOf(assessNoteID)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.ASSESSMENT_NOTE_TEXT,noteText);
        db.update(DatabaseHelper.TABLE_ASSESSMENT_NOTES,contentValues,updWhere,updWhereArgs);
    }
    public void updateCourseNote(Context inputContext, int courseNoteID, String noteText){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String updWhere = DatabaseHelper.COURSE_NOTES_TABLE_ID + " =? ";
        String[] updWhereArgs = {String.valueOf(courseNoteID)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COURSE_NOTE_TEXT,noteText);
        db.update(DatabaseHelper.TABLE_COURSE_NOTES,contentValues,updWhere,updWhereArgs);
    }
    //deleteTerm
    public void deleteTerm(Context inputContext, int termID){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String delWhere = DatabaseHelper.TERMS_TABLE_ID + " = ? ";
        String[] delWhereArgs = {String.valueOf(termID)};
        db.delete(DatabaseHelper.TABLE_TERMS,delWhere,delWhereArgs);
    }
    public void deleteAssess(Context inputContext, int assessID){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String delWhere = DatabaseHelper.ASSESSMENTS_TABLE_ID + " = ? ";
        String[] delWhereArgs = {String.valueOf(assessID)};
        db.delete(DatabaseHelper.TABLE_ASSESSMENTS,delWhere,delWhereArgs);
    }
    public void deleteAssessNote(Context inputContext, int assessNoteID){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String delWhere = DatabaseHelper.ASSESSMENT_NOTES_TABLE_ID + " = ? ";
        String[] delWhereArgs = {String.valueOf(assessNoteID)};
        db.delete(DatabaseHelper.TABLE_ASSESSMENT_NOTES,delWhere,delWhereArgs);
    }
    public void deleteCourseNote(Context inputContext, int courseNoteID){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String delWhere = DatabaseHelper.COURSE_NOTES_TABLE_ID + " = ? ";
        String[] delWhereArgs = {String.valueOf(courseNoteID)};
        db.delete(DatabaseHelper.TABLE_COURSE_NOTES,delWhere,delWhereArgs);
    }

    public void deleteCourse(Context inputContext, int courseID){
//        delCourseAssess(inputContext,courseID);
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        String delWhere = DatabaseHelper.ASSESSMENT_COURSE_ID + " = ? ";
        String[] delWhereArgs = {String.valueOf(courseID)};
        db.delete(DatabaseHelper.TABLE_ASSESSMENTS,delWhere,delWhereArgs);
        delWhere = DatabaseHelper.COURSES_TABLE_ID + " = ? ";
        db.delete(DatabaseHelper.TABLE_COURSES,delWhere,delWhereArgs);
    }

    public void clearData(Context inputContext){
        SQLiteDatabase db = new DatabaseHelper(inputContext).getWritableDatabase();
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_TERMS);
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_COURSES);
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_ASSESSMENTS);
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_COURSE_NOTES);
        db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_ASSESSMENT_NOTES);
    }
}
