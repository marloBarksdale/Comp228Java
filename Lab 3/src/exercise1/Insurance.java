package exercise1;

public abstract class Insurance {


    protected String typeOfInsurance;
    protected double monthlyCost;


    public Insurance(String typeOfInsurance, double monthlyCost) {
        this.typeOfInsurance = typeOfInsurance;
        this.monthlyCost = monthlyCost;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }


    public double getMonthlyCost() {
        return monthlyCost;
    }


    public abstract void setInsuranceCost(double cost);

    public abstract String displayInfo();


    public String toString() {

        return typeOfInsurance + "     " + String.format(" $%.2f", monthlyCost);
    }
}
