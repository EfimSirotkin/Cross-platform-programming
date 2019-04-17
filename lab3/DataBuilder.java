package com.epam.lab2.data;

import com.epam.lab2.classes.Student;


public final class DataBuilder {

    public DataBuilder() {}

    public final String getPassExamInformation(Student student, String subject){
        String information = student.getName();
        int examGrade = student.getStudentGradeBook().getSubjectGrade(subject);
        information += " успешно сдал предмет: ";
        information += subject;
        information += " на отметку ";
        information += examGrade;
        return information;
    }
    public final String getNotPassedExamInformation(Student student, String subject){
        String information = student.getName();
        int examGrade = student.getStudentGradeBook().getSubjectGrade(subject);
        information += " не сдал предмет: ";
        information += subject;
        information += ", получив ";
        information += examGrade;
        return information;
    }
}
