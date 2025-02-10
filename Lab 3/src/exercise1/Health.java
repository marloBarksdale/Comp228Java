package exercise1;

public class Health extends Insurance {


    public Health(double cost) {

        super("Health", cost);
    }

    @Override
    public void setInsuranceCost(double cost) {

        this.monthlyCost = cost;
    }

    @Override
    public void displayInfo() {

    }
}
