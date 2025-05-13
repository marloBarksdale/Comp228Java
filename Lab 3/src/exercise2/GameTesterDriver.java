package exercise2;

import java.util.Scanner;

public class GameTesterDriver {


    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);

        System.out.println("[1] Full Time\n[2] Part Time\n[0] Exit");


        GameTester gameTester = null;
        while (true) {
            System.out.print("Please make a selection: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter an integer. 1, 2 or 0");
                input.next();
                continue;
            }
            int selection = input.nextInt();

            if (selection == 0) {
                System.out.println("Goodbye!");
                break;
            } else if (selection < 0 || selection > 2) {
                System.out.println("Please enter an integer. 1, 2 or 0");

            } else {
                gameTester = switch (selection) {
                    case 1 -> new FullTimeGameTester();
                    case 2 -> new PartTimeGameTester();
                    default -> null;
                };
                break;
            }


        }

        if (gameTester != null) {

            System.out.println(gameTester);
        }

    }
}
