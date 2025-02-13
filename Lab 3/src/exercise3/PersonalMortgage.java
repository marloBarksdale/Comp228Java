package exercise3;

public class PersonalMortgage extends Mortgage {
    public PersonalMortgage(int mortgageNumber, String customerName, double mortgageAmount, int mortgageTerm, double interestRate) {
        super(mortgageNumber, customerName, mortgageAmount, mortgageTerm, interestRate + 0.02);
    }
}
