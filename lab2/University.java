package com.epam;

import java.util.ArrayList;

public class University {
    private String universityName;
    private ArrayList<Student> students;
    private ArrayList<GradeBook> gradeBooks;
    private ArrayList<Employee> employees;

    University() {
        this.universityName = "Unknown";
        this.students = new ArrayList<>(10);
        this.gradeBooks = new ArrayList<>(10);
        this.employees = new ArrayList<>(20);
    }

    University(String name) {
        this();
        this.universityName = name;
    }
    public University(University sourceUniversity){
        this.universityName = sourceUniversity.getUniversityName();
        this.employees = sourceUniversity.getEmployeesList();
        this.gradeBooks = sourceUniversity.getGradeBooksList();
        this.students = sourceUniversity.getStudentsList();
    }

    public void enrollStudent(Student student) {
        this.students.add(student);
    }

    public void enrollStudent(String studentName) {
        Student tempStudent = new Student(studentName);
        this.giveGradeBook(tempStudent);
        this.students.add(tempStudent);
    }

    public void giveGradeBook(Student student) {
        //student.getStudentGradeBook().setStudentName(student.getName());
        int[] grades = new int[10];
        int minimum = 0;
        int maximum = 10;
        for (int a : grades)
            a = minimum + (int) (Math.random() * maximum);

        student.getStudentGradeBook().setGrades(grades);
        student.getStudentGradeBook().setGradeBookNumber(
                minimum + (int) Math.random() * (int) Math.pow(maximum, 3));
    }

    public String getUniversityName() {
        return this.universityName;
    }

    public String getStudentInformation(String name) {
        for (Student a : this.students) {
            if (a.getName().equals(name)) {
                return a.getStudentInformation();
            }
        }
        return "No such student";
    }

    public void hireTeachers() {
        DataHolder dataHolder = new DataHolder();
        String subjects[] = dataHolder.getUniversitySubjects();

        String[] teachersNames = dataHolder.getTeachersNames();
        for (int i = 0; i < subjects.length; ++i) {
            this.employees.add(new Teacher(teachersNames[i], subjects[i]));
        }

    }

    public void hireEngineers() {
        DataHolder dataHolder = new DataHolder();
        ArrayList<Engineer> newEngineers = new ArrayList<Engineer>(10);
        String[] teachersNames = dataHolder.getEngineersNames();
        int[] auditories = {320, 509, 512, 512, 509, 509, 220, 312, 409, 506};
        for (int i = 0; i < auditories.length; ++i) {
            newEngineers.add(new Engineer(auditories[i], teachersNames[i]));
            this.employees.add(newEngineers.get(i));
        }
    }

    public String[] getEmployeeInformation() {
        String[] employeeInformation = new String[20];
        Employee currentEmployee;
        for (int i = 0; i < employeeInformation.length; ++i) {
            currentEmployee = this.employees.get(i);
            employeeInformation[i] += currentEmployee.getEmployeeName();
            employeeInformation[i] += " , ";
            if (currentEmployee instanceof Teacher) {
                employeeInformation[i] += ((Teacher) currentEmployee).getTeachingSubject();
            }
            if (currentEmployee instanceof Engineer) {
                employeeInformation[i] += ((Engineer) currentEmployee).getEngineerAuditory();
            }
        }
        return employeeInformation;
    }

    public Teacher findTeacher(String name) {
        for (Employee emp : this.employees) {
            if (emp.getEmployeeName() == name && emp instanceof Teacher)
                return (Teacher) emp;
        }
        System.out.println("No such teacher was found among employees");
        return new Teacher();
    }

    public Teacher findTeacherBySubject(String subject) {
        for (Employee emp : this.employees) {
            if (emp instanceof Teacher) {
                String teachingSubject = ((Teacher) emp).getTeachingSubject();
                if (teachingSubject.equals(subject))
                    return (Teacher) emp;
            }
        }
        return null;
    }

    public Student findStudent(String name) {
        for (Student stud : this.students) {
            if (stud.getName().equals(name))
                return stud;
        }
        System.out.println("No such student was found among students");
        return new Student();
    }

    public ArrayList<Student> getStudentsList() {
        return this.students;
    }

    public ArrayList<Teacher> getTeachersList() {
        ArrayList<Teacher> teachersList = new ArrayList<>(this.employees.size() / 2);
        for (Employee person : this.employees) {
            if (person instanceof Teacher) {
                teachersList.add((Teacher) person);
            }
        }
        return teachersList;
    }

    public ArrayList<Engineer> getEngineersList() {
        ArrayList<Engineer> engineersList = new ArrayList<>(this.employees.size() / 2);
        for (Employee person : this.employees) {
            if (person instanceof Engineer) {
                engineersList.add((Engineer) person);
            }

        }
        return engineersList;
    }

    public boolean isEqualUniversityName(String universityName){
        return this.universityName.equals(universityName);
    }

    public ArrayList<Employee> getEmployeesList() {
        return this.employees;
    }

    public ArrayList<GradeBook> getGradeBooksList() {
        return this.gradeBooks;
    }
}

