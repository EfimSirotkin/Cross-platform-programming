package com.epam.lab2.classes;

public class HeadOfDepartment extends Employee {

    University university;

    public HeadOfDepartment() {
    }

    public HeadOfDepartment(String name) {
        super(name);
    }

    public HeadOfDepartment(String name, University university) {
        super(name);
        this.university = university;
    }

    public University getSupervisedUniversity() {
        return this.university;
    }

    public void setSupervisedUniversity(University university) {
        this.university = university;
    }

    public ExamAssignation AssignExam() {
        int decision = (int) (Math.random() * 1000);
        if (decision % 2 == 0)
            return ExamAssignation.ASSIGNED;
        else
            return ExamAssignation.NOT_ASSIGNED;
    }

    enum ExamAssignation {ASSIGNED, NOT_ASSIGNED}
}

