package exercise1;

public abstract class Insurance {


    protected String typeOfInsurance;
    protected double monthlyCost;


    public String getTypeOfInsurance(){
        return typeOfInsurance;
    }


    public double getMonthlyCost() {
        return monthlyCost;
    }


    public abstract void setInsuranceCost(double cost);

    public abstract void displayInfo();



}
