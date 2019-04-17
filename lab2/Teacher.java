package com.epam;

public class Teacher extends Employee {
    private String teachingSubject;

    public Teacher(){
        this.setEmployeeName("Unknown");
    }

    public Teacher(String name){
        this.setEmployeeName(name);
    }

    public Teacher(String name, String teachingSubject){
        this.setEmployeeName(name);
        this.teachingSubject = teachingSubject;
    }
    public Teacher(Teacher source){
        this.setEmployeeName(source.getEmployeeName());
        this.teachingSubject = source.getTeachingSubject();
    }


    public void setTeachingSubject(String subject) {

        this.teachingSubject = subject;
    }

    public String getTeacherName() {

        return this.getEmployeeName();
    }
    public String getTeachingSubject() {
        return this.teachingSubject;
    }
    public String getTeacherInformation() {
        String information = this.getTeacherName() + "     -      "+
                this.getTeachingSubject();
        return information;
    }

    public String takeExam(Student student, String subject) {
        int grade = student.getStudentGradeBook().getSubjectGrade(subject);
        int minimum =0;
        int maximum =10;
        int examGrade = minimum + (int)(Math.random()*maximum);
        student.getStudentGradeBook().setSubjectGrade(subject, examGrade);
        if(examGrade >= grade) {

            return student.getName() + " успешно сдал " + subject +
                    ", получив " + examGrade + "\nПреподаватель: "+ this.getEmployeeName();
        }
        else {
            return student.getName() + " получил " + examGrade + " за экзамен по предмету '"+
                    subject + "'\nК сожалению, не сдал..\nПреподаватель: "+ this.getEmployeeName();

        }

    }
}
