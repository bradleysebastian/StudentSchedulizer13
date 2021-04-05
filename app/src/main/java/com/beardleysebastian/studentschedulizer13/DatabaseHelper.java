package com.beardleysebastian.studentschedulizer13;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //***CHANGE*** All "ID" columns MUST BE RENAMED "_ID" - lets you put SimpleCursorAdapter into ListView...
    private static final String DATABASE_NAME = "schedulizer_terms.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_TERMS = "terms_table";
    public static final String TERMS_TABLE_ID = "term_id";
    public static final String TERM_NAME = "term_name";
    public static final String TERM_START = "term_start";
    public static final String TERM_END = "term_end";
    public static final String TERM_ACTIVE = "term_active";
    public static final String TERM_CREATED = "created_date";
    public static final String[] TERMS_COLUMNS = {TERMS_TABLE_ID, TERM_NAME, TERM_START, TERM_END,
            TERM_ACTIVE, TERM_CREATED};

    public static final String TABLE_COURSES = "courses_table";
    public static final String COURSES_TABLE_ID = "course_id";
    public static final String COURSE_TERM_ID = "course_term_id";
    public static final String COURSE_NAME = "course_name";
    public static final String COURSE_DESCRIPTION = "course_description";
    public static final String COURSE_START = "course_start";
    public static final String COURSE_END = "course_end";
    public static final String COURSE_STATUS = "course_status";
    public static final String COURSE_MENTOR = "course_mentor";
    public static final String COURSE_MENTOR_PHONE = "mentor_phone";
    public static final String COURSE_MENTOR_EMAIL = "mentor_email";
    public static final String COURSE_NOTIFICATIONS = "notifications";
    public static final String COURSE_CREATED = "created_date";
    public static final String[] COURSES_COLUMNS = {COURSES_TABLE_ID, COURSE_TERM_ID , COURSE_NAME,
            COURSE_DESCRIPTION, COURSE_START, COURSE_END, COURSE_STATUS, COURSE_MENTOR,
            COURSE_MENTOR_PHONE, COURSE_MENTOR_EMAIL, COURSE_NOTIFICATIONS, COURSE_CREATED};

    public static final String TABLE_COURSE_NOTES = "course_notes_table";
    public static final String COURSE_NOTES_TABLE_ID = "course_notes_id";
    public static final String COURSE_NOTE_COURSE_ID = "course_id";
    public static final String COURSE_NOTE_TEXT = "course_notes_text";
    public static final String COURSE_NOTE_CREATED = "course_notes_created";
    public static final String[] COURSE_NOTES_COLUMNS = {COURSE_NOTES_TABLE_ID, COURSE_NOTE_COURSE_ID,
            COURSE_NOTE_TEXT, COURSE_NOTE_CREATED};

    public static final String TABLE_ASSESSMENTS = "assessment_table";
    public static final String ASSESSMENTS_TABLE_ID = "assessment_id";
    public static final String ASSESSMENT_COURSE_ID = "course_id";
    public static final String ASSESSMENT_CODE = "assessment_code";
    public static final String ASSESSMENT_NAME = "assessment_name";
    public static final String ASSESSMENT_DESCRIPTION = "assessment_desc";
    public static final String ASSESSMENT_DATETIME = "assessment_date";
    public static final String ASSESSMENT_NOTIFICATIONS = "notifications";
    public static final String ASSESSMENT_CREATED = "created_date";
    public static final String[] ASSESSMENTS_COLUMNS = {ASSESSMENTS_TABLE_ID, ASSESSMENT_COURSE_ID,
            ASSESSMENT_CODE, ASSESSMENT_NAME, ASSESSMENT_DESCRIPTION, ASSESSMENT_DATETIME, ASSESSMENT_NOTIFICATIONS,
            ASSESSMENT_CREATED};

    public static final String TABLE_ASSESSMENT_NOTES = "assessment_notes_table";
    public static final String ASSESSMENT_NOTES_TABLE_ID = "assessment_notes_id";
    public static final String ASSESSMENT_NOTE_ASSESSMENT_ID = "assessment_id";
    public static final String ASSESSMENT_NOTE_TEXT = "assessment_notes_text";
    public static final String ASSESSMENT_NOTE_CREATED = "created date";
    public static final String[] ASSESSMENT_NOTES_COLUMNS = {ASSESSMENT_NOTES_TABLE_ID, ASSESSMENT_NOTE_ASSESSMENT_ID,
            ASSESSMENT_NOTE_TEXT, ASSESSMENT_NOTE_CREATED};

    private static final String TERMS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TERMS + "(" +
                    TERMS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TERM_NAME + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE, " +
                    TERM_ACTIVE + " INTEGER, " +
                    TERM_CREATED + " TEXT DEFAULT CURRENT_TIMESTAMP" +
                    ")";

    private static final String COURSES_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSES + "(" +
                    COURSES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COURSE_TERM_ID + " INTEGER, " +
                    COURSE_NAME + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR + " TEXT, " +
                    COURSE_MENTOR_PHONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    COURSE_CREATED + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    " FOREIGN KEY (" + COURSE_TERM_ID + ") REFERENCES " + TABLE_TERMS + "(" + TERMS_TABLE_ID + ")" +
                    ")";

    private static final String COURSES_NOTES_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSE_NOTES + "(" +
                    COURSE_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COURSE_NOTE_COURSE_ID + " INTEGER, " +
                    COURSE_NOTE_TEXT + " TEXT, " +
                    COURSE_NOTE_CREATED + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + COURSE_NOTE_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSES_TABLE_ID + ")" +
                    ")";

    private static final String ASSESSMENTS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENTS + "(" +
                    ASSESSMENTS_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    ASSESSMENT_NAME + " TEXT, " +
//                    ASSESSMENT_DESCRIPTION + " TEXT, " +
//                    ASSESSMENT_CODE + " TEXT, " +
                    ASSESSMENT_DATETIME + " TEXT, " +
//                    ASSESSMENT_NOTIFICATIONS + " INTEGER, " +
                    ASSESSMENT_DESCRIPTION + " TEXT, " + //Re-added 11/22
                    ASSESSMENT_CREATED + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + ASSESSMENT_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COURSES_TABLE_ID + ")" +
                    ")";

    private static final String ASSESSMENT_NOTES_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ASSESSMENT_NOTES + "(" +
                    ASSESSMENT_NOTES_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ASSESSMENT_NOTE_ASSESSMENT_ID + " INTEGER, " +
                    ASSESSMENT_NOTE_TEXT + " TEXT, " +
                    ASSESSMENT_NOTE_CREATED + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (" + ASSESSMENT_NOTE_ASSESSMENT_ID + ") REFERENCES " + TABLE_ASSESSMENTS + "(" + ASSESSMENTS_TABLE_ID + ")" +
                    ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TERMS_TABLE_CREATE);
        db.execSQL(COURSES_TABLE_CREATE);
        db.execSQL(COURSES_NOTES_TABLE_CREATE);
        db.execSQL(ASSESSMENTS_TABLE_CREATE);
        db.execSQL(ASSESSMENT_NOTES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENT_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TERMS);
    }
}
