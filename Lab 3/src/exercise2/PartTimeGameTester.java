package exercise2;

import java.util.Scanner;

public class PartTimeGameTester extends GameTester {


    public PartTimeGameTester() {
        this.name = "Part Time";
        calculateSalary();
    }

    @Override
    public void calculateSalary() {

        Scanner input = new Scanner(System.in);
        double hours = 0;
        while (true) {

            System.out.print("Enter the number of hours: ");
            if (!input.hasNextDouble()) {
                System.out.println("Please enter a valid number");
                input.next();
                continue;

            }

            hours = input.nextDouble();

            if (hours < 0) {
                System.out.println("Please enter a positive number");
                continue;
            }
            break;

        }


        this.salary = hours * 20;
    }
}
