package com.beardleysebastian.studentschedulizer13;

public class CourseNote {

    private int courseNoteID;
    private String courseNoteActual;

    public CourseNote() {
    }

    public CourseNote(int courseNoteID, String courseNoteActual) {
        this.courseNoteID = courseNoteID;
        this.courseNoteActual = courseNoteActual;
    }

    public int getCourseNoteID() {
        return courseNoteID;
    }

    public void setCourseNoteID(int courseNoteID) {
        this.courseNoteID = courseNoteID;
    }

    public String getCourseNoteActual() {
        return courseNoteActual;
    }

    public void setCourseNoteActual(String courseNoteActual) {
        this.courseNoteActual = courseNoteActual;
    }
}
