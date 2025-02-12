package exercise2;

public abstract class GameTester {

    protected String name;
    protected boolean fullTime=false;
    protected double salary;







    public abstract void calculateSalary();


    public String toString(){

        return String.format("Name: %s | Salary: $%.2f", name, salary);
    }

}
