package com.epam.lab2.classes;

import com.epam.lab2.data.DataBuilder;

import java.util.ArrayList;

public class Student {
    private String name;
    private GradeBook studentGradeBook;
    private ArrayList<Exam> studentExams = new ArrayList<Exam>(10);


    public Student(){
        this.name = "Unknown";
        this.studentGradeBook = new GradeBook();
    }
    public Student(String name) {

        this();
        this.name = name;
    }

    public Student(String name, GradeBook gradeBook){
        this.name = name;
        this.studentGradeBook = gradeBook;
    }
    public String getName(){

        return this.name;
    }
    public GradeBook getStudentGradeBook(){
        return this.studentGradeBook;
    }

    public String getStudentInformation(){

        String information = this.getName()+"\n"+this.getStudentGradeBook()
                .getGradeBookInformation();
        return information;
    }
    public void setGradeBookName(String name){
        this.getStudentGradeBook().setStudentName(name);
    }
    public void setStudentName(String name){
        this.name = name;
    }

    public String passExam(Teacher examiner, Exam exam) {
        String subject = exam.getExamSubject();
        switch(examiner.takeExam(this, subject))
        {
            case PASSED:
                exam.setExamState(true);
                return new DataBuilder().getPassExamInformation(this, subject);
            case NOT_PASSED:
                return new DataBuilder().getNotPassedExamInformation(this, subject);

        }
        return null;
    }

    public void setStudentExams(ArrayList<Exam> examsList){
        for(int i = 0; i < examsList.size();++i)
            this.studentExams.add(new Exam(false, examsList.get(i).getExamSubject()));
    }

    public ArrayList<Exam> getStudentExams(){
        return this.studentExams;
    }
    public Exam findStudentExam(String subject){
        for(Exam exam : this.studentExams){
            if(exam.getExamSubject().equals(subject))
                return exam;
        }
        return null;
    }
    public void setExamState(boolean state, String subject){
        for(Exam exam: this.studentExams)
            if(exam.getExamSubject().equals(subject))
                exam.setExamState(state);
    }
}
