package exercise3;


import java.util.Scanner;

public class ProcessMortgage {

    static Scanner sc = new Scanner(System.in);
    static Mortgage[] mortgages = new Mortgage[3];


    public static void main(String[] args) {


        System.out.println("Hello. Please enter your information for 3 mortgages: ");
        for (int i = 0; i < mortgages.length; i++) {

            int mortgageType = getMortgageType();
            double currentPrimeRate = getCurrentPrimeRate();
            String name = getCustomerName();
            int mortgageNumber = getMortgageNumber();
            double mortgageAmount = getMortgageAmount();
            int mortgageTerm = getMortgageTerm();


            if (mortgageType == 1) {

                mortgages[i] = new PersonalMortgage(mortgageNumber, name, mortgageAmount, mortgageTerm, currentPrimeRate);
            } else {
                mortgages[i] = new BusinessMortgage(mortgageNumber, name, mortgageAmount, mortgageTerm, currentPrimeRate);
            }
        }

        getMortgageInfo();


    }

    public static double getCurrentPrimeRate() {


        System.out.print("Enter current prime rate percentage(e.g.  5 for 0.05  or 7.4 for 0.074 ): ");

        while (true) {

            if (!sc.hasNextDouble()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
                continue;
            }


            double primeRate = sc.nextDouble();
            if (primeRate < 0) {
                System.out.print("Prime rate must be positive: ");
                continue;
            }

            return primeRate / 100;


        }
    }

    public static int getMortgageType() {

        System.out.println("\n\n[1] Personal Mortgage (2% + prime)\n[2] Business Mortgage(1% + prime)\n");

        System.out.print("Please make a selection: ");
        while (true) {


            if (!sc.hasNextInt()) {
                System.out.print("Please enter an integer. 1 or 2: ");
                sc.next();
                continue;
            }
            int selection = sc.nextInt();


            if (selection != 1 && selection != 2) {
                System.out.print("Please enter an integer. 1 or 2: ");
                continue;

            }

            return selection;


        }
    }

    public static int getMortgageTerm() {

        System.out.print("Please enter the term: ");
        while (true) {


            if (!sc.hasNextInt()) {
                System.out.print("Please enter an integer: ");
                sc.next();
                continue;
            }

            return sc.nextInt();

        }


    }

    public static double getMortgageAmount() {


        System.out.print("Enter mortgage amount: ");
        double mortgageAmount = 0;
        while (true) {


            if (!sc.hasNextDouble()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
                continue;
            }


            mortgageAmount = sc.nextDouble();
            if (mortgageAmount < 0) {
                System.out.print("Mortgage amount must be a positive integer: ");
                continue;
            }

            return mortgageAmount;


        }
    }

    public static int getMortgageNumber() {

        System.out.print("Enter mortgage number: ");
        int mortgageNumber = 0;
        while (true) {


            if (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number. No decimals: ");
                sc.next();
                continue;
            }

            mortgageNumber = sc.nextInt();
            if (mortgageNumber < 0) {
                System.out.print("Mortgage number must be a positive integer: ");
                continue;
            }

            return mortgageNumber;
        }
    }


    public static String getCustomerName() {

        sc.nextLine();
        System.out.print("Please enter your customer name: ");

        while (true) {

            String customerName = sc.nextLine();
            if (customerName.trim().isEmpty()) {
                System.out.print("The name cannot be empty:");


                continue;
            }

            return customerName.trim();
        }


    }


    public static void getMortgageInfo() {


        for (Mortgage mortgage : mortgages) {

            System.out.println(mortgage);
        }
    }


}
