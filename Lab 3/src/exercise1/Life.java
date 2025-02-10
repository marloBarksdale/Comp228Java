package exercise1;

public class Life extends Insurance {


    public Life(double cost) {

        super("Life", cost);


    }

    @Override
    public void setInsuranceCost(double cost) {
        this.monthlyCost = cost;
    }

    @Override
    public void displayInfo() {

    }
}
