package exercise3;

public abstract class Mortgage implements MortgageConstants {


    private int mortgageNumber;
    private String customerName;
    private double mortgageAmount;
    private int mortgageTerm;
    private double interestRate;


    public Mortgage(int mortgageNumber, String customerName, double mortgageAmount, int mortgageTerm, double interestRate) {
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        setMortgageAmount(mortgageAmount);
        setMortgageTerm(mortgageTerm);
        this.interestRate = interestRate;


    }

    public int getMortgageNumber() {
        return mortgageNumber;
    }

    public void setMortgageNumber(int mortgageNumber) {
        this.mortgageNumber = mortgageNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(double mortgageAmount) {
        if (mortgageAmount > MAX_MORTGAGE_AMOUNT) {
            System.out.printf("$%.2f exceeds the max amount of $%.2f. You mortgage will be set to the maximum.\n", mortgageAmount, MAX_MORTGAGE_AMOUNT);
            this.mortgageAmount = MAX_MORTGAGE_AMOUNT;
        } else {
            this.mortgageAmount = mortgageAmount;
        }

    }

    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {

        if (mortgageTerm != SHORT_TERM && mortgageTerm != MEDIUM_TERM && mortgageTerm != LONG_TERM) {
            System.out.println("Invalid term. Setting to short-term (1 year).");
            this.mortgageTerm = SHORT_TERM;
        } else {
            this.mortgageTerm = mortgageTerm;
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double calculateTotalAmountOwed() {
        return mortgageAmount + (mortgageAmount * interestRate * mortgageTerm);
    }

    public String toString() {

        // Check the instance of mortgage for output
        String mortgageType;
        if (this instanceof BusinessMortgage) {
            mortgageType = "Business Mortgage";
        } else {
            mortgageType = "Personal Mortgage";
        }

        System.out.println("\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n");
        return String.format("Details for your %s number: %s.\n" +
                "Customer name: %s\n" +
                "Amount: $%.2f\n" +
                "Interest rate: %.2f%%\n" +
                "Mortgage Term: %d\n" +
                "Total owed:$%.2f", mortgageType, mortgageNumber, customerName, mortgageAmount, interestRate * 100, mortgageTerm, calculateTotalAmountOwed());
    }
}
