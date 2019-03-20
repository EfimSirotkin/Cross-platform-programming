package com.epam;

public class GradeBook {
    private String studentName;
    private int number;
    private int[] grades;
    private String[] subjects;

    public GradeBook(){
        this.studentName = "Unknown";
        this.number = 0;
        this.grades = new int[10];
        for(int a: grades)
            a = 0;
        DataHolder dataHolder = new DataHolder();
        this.subjects = dataHolder.getUniversitySubjects();
    }

    public GradeBook(String name){
        this();
        this.studentName = name;
    }

    public GradeBook(GradeBook src){
        this.number = src.number;
        this.studentName = src.studentName;
        this.grades = new int[10];
        System.arraycopy(src.grades, 0, this.grades, 0, src.grades.length);
    }

    public GradeBook(String studentName, int number, int[] grades) {
        this();
        this.studentName = studentName;
        this.number = number;
        this.grades = new int[10];
        System.arraycopy(grades, 0, this.grades, 0, grades.length);
    }

    public void setSubjectGrade(String subject, int newGrade){
        int index =0;
        for(String a: this.subjects){
            if(a == subject){
                this.grades[index] = newGrade;
            }
            ++index;
        }
    }
    public void setGrades(){
        int minimum = 0;
        int maximum = 10;
        for(int i = 0; i< this.grades.length; ++i) {
            this.grades[i] = minimum +(int)(Math.random()*maximum);
        }
    }
    public void setGrades(int[] sampleGrades){
        System.arraycopy(sampleGrades,0,this.grades,0,sampleGrades.length);
    }

    public void setGradeBookNumber(){
        int minimum = 0;
        int maximum = 1_000_000;
        this.number = minimum +(int)(Math.random()*maximum);
    }
    public void setGradeBookNumber(int number){
        this.number = number;
    }
    public void setStudentName(String name){
        this.studentName = name;
    }

    public String getStudentName()
    {
        return this.studentName;
    }

    public int getGradeBookNumber(){
        return this.number;
    }
    public int[] getGradeBookGrades() {
        return this.grades;
    }
    public String getGradeBookInformation(){
        String subjectGrades = "*****\n";
            for(int i = 0; i <this.grades.length;++i){
                subjectGrades += this.subjects[i] + " - "+
                        this.grades[i] + "\n";
            }
            subjectGrades+="*****\n";
        return "Номер зачетки: " + this.number +
                "\nСтудент: " + this.studentName +
                "\nОтметки:\n"+ subjectGrades;
    }
    public int getSubjectGrade(String subject){
        int index=0;
        for(String a: this.subjects){
            if(a == subject){
                return this.grades[index];
            }
            ++index;
        }
        return 0;
    }

    public String[] getSubjects() {
        return this.subjects;
    }
}
