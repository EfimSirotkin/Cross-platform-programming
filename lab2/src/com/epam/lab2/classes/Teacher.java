package com.epam.lab2.classes;

public class Teacher extends Employee {
    private String teachingSubject;

    public Teacher() {
        this.setEmployeeName("Unknown");
    }

    public Teacher(String name) {
        this.setEmployeeName(name);
    }

    public Teacher(String name, String teachingSubject) {
        this.setEmployeeName(name);
        this.teachingSubject = teachingSubject;
    }

    public Teacher(Teacher source) {
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
        String information = this.getTeacherName() + "     -      " +
                this.getTeachingSubject();
        return information;
    }

    public Result takeExam(Student student, String subject) {
        int grade = student.getStudentGradeBook().getSubjectGrade(subject);
        int minimum = 0;
        int maximum = 10;
        int examGrade = minimum + (int) (Math.random() * maximum);
        GradeBook studentGradebook = student.getStudentGradeBook();
        studentGradebook.setSubjectGrade(subject, examGrade);

        if (examGrade >= grade)
            return Result.PASSED;
        else
            return Result.NOT_PASSED;

    }

    enum Result {PASSED, NOT_PASSED}

}
