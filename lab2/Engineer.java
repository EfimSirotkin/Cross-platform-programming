package com.epam;

public class Engineer extends Employee{
    private int auditory;

    public Engineer(){
        this.auditory = 0;
    }
    public Engineer(int auditory){
        this.auditory = auditory;
    }
    public Engineer(int auditory, String name){
        this(auditory);
        this.setEmployeeName(name);
    }
    public Engineer(Engineer source){
        this.auditory = source.auditory;
        this.setEmployeeName(source.getEmployeeName());
    }
    public int getEngineerAuditory(){
        return this.auditory;
    }
    public void setEngineerAuditory(int auditory){
        this.auditory = auditory;
    }

    public String getEngineerInformation() {
        return this.getEmployeeName() + "   -   " + this.getEngineerAuditory();
    }

}
