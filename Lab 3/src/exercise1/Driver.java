package exercise1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {


    static List<Insurance> insuranceList = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            String type = getUserInsuranceTypeInput();
            if (type != null) {

                double cost = getUserInsuranceCostInput(type);

            }else{
                break;
            }



        }


    }


    public static void endInput() {

        int listSize = insuranceList.size();
        String message = String.format("%d insurance successfully added!", listSize);
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
            type = JOptionPane.showInputDialog("Enter the insurance type. Life or Health. Program will end if nothing is entered");
            if ((type == null) || (type.trim().isEmpty())) {

                endInput();
                break;

            } else if (type.trim().equalsIgnoreCase("LIFE")) {
                break;
            } else if (type.trim().equalsIgnoreCase("HEALTH")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Enter a valid insurance name. Life or Health");
                continue;
            }
        }

        if (type == null) {
            return type;
        } else {
            type = type.substring(0, 1).toUpperCase() + type.toLowerCase().substring(1);
            return type;
        }


    }


    public static double getUserInsuranceCostInput(String type) {


        String userInput = JOptionPane.showInputDialog(String.format("Enter the cost of your %s insurance", type), "0");
        double cost = 0;

        while(true){

            if ((userInput == null) || (userInput.trim().isEmpty())) {
//                System.out.println("Enter a valid number");
                endInput();
                break;

            } else if (isNumeric(userInput)) {
                cost = Double.parseDouble(userInput);
                break;
            } else   {
               JOptionPane.showMessageDialog(null,"Please enta valid number");
               continue;
            }


        }

        return cost;
    }


}
