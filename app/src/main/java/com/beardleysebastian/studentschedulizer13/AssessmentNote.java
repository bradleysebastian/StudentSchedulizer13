package com.beardleysebastian.studentschedulizer13;

public class AssessmentNote {

    private int assessNoteID;
    private String assessNoteActual;

    public AssessmentNote() {
    }

    public AssessmentNote(int assessNoteID, String assessNoteActual) {
        this.assessNoteID = assessNoteID;
        this.assessNoteActual = assessNoteActual;
    }

    public int getAssessNoteID() {
        return assessNoteID;
    }

    public void setAssessNoteID(int assessNoteID) {
        this.assessNoteID = assessNoteID;
    }

    public String getAssessNoteActual() {
        return assessNoteActual;
    }

    public void setAssessNoteActual(String assessNoteActual) {
        this.assessNoteActual = assessNoteActual;
    }
}
