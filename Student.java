package com.epam;

public class Student {
    private String name;
    private GradeBook studentGradeBook;


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

    public String passExam(Teacher examiner, String subject) {
        String results = "";
        results += examiner.takeExam(this, subject);
        return results;
    }
}
