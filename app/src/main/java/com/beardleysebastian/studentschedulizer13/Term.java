package com.beardleysebastian.studentschedulizer13;

public class Term {
    private int termID;
    private String termTitle;
    private String termDescription;  //No intended use yet
    private String termStart;  //DATETIME in SQLite as TEXT, input function DATETIME
    private String termEnd;  //DATETIME in SQLite as TEXT, input function DATETIME
    private int activeFlag;

//    public static final String TABLE_TERMS = "terms_table";
//    public static final String TERMS_TABLE_ID = "term_id";
//    public static final String TERM_NAME = "term_name";
//    public static final String TERM_START = "term_start";
//    public static final String TERM_END = "term_end";
//    public static final String TERM_ACTIVE = "term_active";
//    public static final String TERM_CREATED = "created_date";

    public Term() {}

    public Term(int termID, String termTitle, String termDescription, String termStart, String termEnd, int activeFlag) {
        this.termID = termID;
        this.termTitle = termTitle;
        this.termDescription = termDescription;
        this.termStart = termStart;
        this.termEnd = termEnd;
        this.activeFlag = activeFlag;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int id) {
        this.termID = id;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String name) {
        this.termTitle = name;
    }

    public String getTermDescription() {
        return termDescription;
    }

    public void setTermDescription(String description) {
        this.termDescription = description;
    }

    public String getTermStart() {
        return termStart;
    }

    public void setTermStart(String termStart) {
        this.termStart = termStart;
    }

    public String getTermEnd() {
        return termEnd;
    }

    public void setTermEnd(String termEnd) {
        this.termEnd = termEnd;
    }

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
    }
}
