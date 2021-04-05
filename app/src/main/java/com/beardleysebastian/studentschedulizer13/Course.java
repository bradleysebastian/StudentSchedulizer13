package com.beardleysebastian.studentschedulizer13;

public class Course {

    private int courseID;
    private int termID;
    private String courseName;
    private String courseDescription;
    private String targetStart;
    private String targetEnd;
    private String courseStatus;
    private String courseMentorName;
    private String courseMentorPhone;
    private String courseMentorEmail;
    //Does this need an arraylist of assessments??  Probably not...

    public Course() {
    }

    public Course(int courseID, int termID, String courseName, String courseDescription,
                  String targetStart, String targetEnd, String courseStatus, String courseMentorName,
                  String courseMentorPhone, String courseMentorEmail) {
        this.courseID = courseID;
        this.termID = termID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.targetStart = targetStart;
        this.targetEnd = targetEnd;
        this.courseStatus = courseStatus;
        this.courseMentorName = courseMentorName;
        this.courseMentorPhone = courseMentorPhone;
        this.courseMentorEmail = courseMentorEmail;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getTargetStart() {
        return targetStart;
    }

    public void setTargetStart(String targetStart) {
        this.targetStart = targetStart;
    }

    public String getTargetEnd() {
        return targetEnd;
    }

    public void setTargetEnd(String targetEnd) {
        this.targetEnd = targetEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseMentorName() {
        return courseMentorName;
    }

    public void setCourseMentorName(String courseMentorName) {
        this.courseMentorName = courseMentorName;
    }

    public String getCourseMentorPhone() {
        return courseMentorPhone;
    }

    public void setCourseMentorPhone(String courseMentorPhone) {
        this.courseMentorPhone = courseMentorPhone;
    }

    public String getCourseMentorEmail() {
        return courseMentorEmail;
    }

    public void setCourseMentorEmail(String courseMentorEmail) {
        this.courseMentorEmail = courseMentorEmail;
    }
}
