package com.beardleysebastian.studentschedulizer13;

public class Assessment {

    private int assessID;
    private int courseID;
    private String assessName;
    private String assessDescription; //For future expansion
    private String testOrDueDate;

    public Assessment() {
    }

    public Assessment(int assessID, int courseID, String assessName, String assessDescription, String testOrDueDate) {
        this.assessID = assessID;
        this.courseID = courseID;
        this.assessName = assessName;
        this.assessDescription = assessDescription;
        this.testOrDueDate = testOrDueDate;
    }

    public int getAssessID() {
        return assessID;
    }

    public void setAssessID(int assessID) {
        this.assessID = assessID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public String getAssessDescription() {
        return assessDescription;
    }

    public void setAssessDescription(String assessDescription) {
        this.assessDescription = assessDescription;
    }

    public String getTestOrDueDate() {
        return testOrDueDate;
    }

    public void setTestOrDueDate(String testOrDueDate) {
        this.testOrDueDate = testOrDueDate;
    }
}
