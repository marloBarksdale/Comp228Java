package exercise3;

public class BusinessMortgage extends Mortgage {


    public BusinessMortgage(int mortgageNumber, String customerName, double mortgageAmount, int mortgageTerm, double interestRate) {
        super(mortgageNumber, customerName, mortgageAmount, mortgageTerm, interestRate+0.01);
    }
}
