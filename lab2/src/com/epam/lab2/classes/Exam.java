package com.epam.lab2.classes;

public class Exam {
    private boolean state;
    private String subject;

    public Exam() {
    }

    public Exam(boolean state) {
        this.state = state;
    }

    public Exam(String subject) {

        this.subject = subject;
    }

    public Exam(boolean state, String subject) {
        this(state);
        this.subject = subject;
    }

    public String getExamSubject() {
        return this.subject;
    }

    public boolean getExamState() {
        return this.state;
    }

    public void setExamState(boolean newState) {
        this.state = newState;
    }

    public void setExamSubject(String examSubject) {
        this.subject = examSubject;
    }

}
