package exercise1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {


    static List<Insurance> insuranceList = new ArrayList<>();

    public static void main(String[] args) {


        createInsuranceList();
        String s = "";


       if(!insuranceList.isEmpty())  {
           for (Insurance insurance : insuranceList){
               s += insurance.toString() + "\n";
           }
           JOptionPane.showMessageDialog(null, s);
        }




    }


    public static void createInsuranceList() {

        while (true) {
            String type = getUserInsuranceTypeInput();
            if (type != null) {

                double cost = getUserInsuranceCostInput(type);

                if (cost == 0) {
                    break;
                }
                Insurance insurance = type.equalsIgnoreCase("Health") ? new Health(cost) : new Life(cost);

                //Call to show polymorphism
                insurance.setInsuranceCost(cost);

                insuranceList.add(insurance);
                JOptionPane.showMessageDialog(null, insurance.displayInfo(), "Successfully Added.", JOptionPane.INFORMATION_MESSAGE);

            } else {
                break;
            }


        }


    }


    public static void endInput() {

        int listSize = insuranceList.size();
        String message = listSize > 0 ? String.format("%d insurance successfully added!", listSize) : "You did not insert any data";
        JOptionPane.showMessageDialog(null, message);


    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getUserInsuranceTypeInput() {

        String type = null;
        while (true) {
            type = JOptionPane.showInputDialog("Enter Life or Health for the insurance type. \nHitting cancel or living the textbox blank will cancel the program.");
            if ((type == null) || (type.trim().isEmpty())) {
                //Call end input if the user hits cancel or enters an empty string
                type = null;
                endInput();
                break;
            } else if (type.trim().equalsIgnoreCase("LIFE")) {
                break;
            } else if (type.trim().equalsIgnoreCase("HEALTH")) {
                break;
            } else {

                JOptionPane.showMessageDialog(null, "Enter a valid insurance name. Life or Health");

            }
        }

        if (type != null) {
            type = type.substring(0, 1).toUpperCase() + type.toLowerCase().substring(1);
        }
        return type;


    }


    public static double getUserInsuranceCostInput(String type) {


        double cost = 0;

        while (true) {
            String userInput = JOptionPane.showInputDialog(String.format("Enter the cost of your %s insurance", type), "0");


            if ((userInput == null) || (userInput.trim().isEmpty())) {
                //Call end input if the user hits cancel or enters an empty string
                endInput();
                break;

            } else if (isNumeric(userInput)) {

                cost = Double.parseDouble(userInput);
                if (cost <= 0) {
                    JOptionPane.showMessageDialog(null, "Cost cannot be negative or zero");
                    continue;
                }
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Please enter valid number");

            }


        }

        return cost;
    }


}
