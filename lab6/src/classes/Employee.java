package classes;

public class Employee {
    private Integer salary;
    public Employee(String aSalary){
        this.salary = new Integer(aSalary);
    }
    public Integer getSalary(){
        return this.salary;
    }
}
