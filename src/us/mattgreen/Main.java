package us.mattgreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner keyboard;
    private Cookbook cookbook;
    private MealFacts mealFacts;
    private List<MealFacts> facts;

    public Main() {
        keyboard = new Scanner(System.in);
        cookbook = new Cookbook();
        //mealFacts = new MealFacts();
        facts = new ArrayList<MealFacts>();
        int temp, tcal;

        FileInput indata = new FileInput("meals_data.csv");

        String line;
        String current = "start";

        System.out.println("Reading in meals information from file...");
        
        /*
        if((current == "dummy") && ((line = indata.fileReadLine()) != null)){
            String[] fields = line.split(",");
            
                System.out.println("current is " + current);
                System.out.println("next is " + fields[0]);
                //Make new category. Accumulate data.
                mealFacts = new MealFacts(fields[0]);
                temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                System.out.println("the counter is " + temp);
                current = fields[0];
            }
        */
        
        while ((line = indata.fileReadLine()) != null) {
            String[] fields = line.split(",");
            
            if(fields[0].equals(current)){
                //Stop Accumulating and summarize.
                temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                tcal = mealFacts.getCal();
                System.out.println("the counter is " + temp
                    + "\tcalories are " + tcal);
                /*
                System.out.println("current is " + current);
                System.out.println("next is " + fields[0]);
                //Make new category. Accumulate data.
                mealFacts = new MealFacts(fields[0]);
                temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                System.out.println("the counter is " + temp);
                current = fields[0];
                */
            }
            else if(current.equals("start")){
                System.out.println("current is " + current);
                System.out.println("next is " + fields[0]);
                //Make new category. Accumulate data.
                mealFacts = new MealFacts(fields[0]);
                temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                tcal = mealFacts.getCal();
                System.out.println("the counter is " + temp
                    + "\tcalories are " + tcal);
                current = fields[0];
            }
            else{
                //Stop Accumulating and summarize.
                //temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                //System.out.println("the counter is " + temp);
                
                System.out.println("current is " + current);
                System.out.println("next is " + fields[0]);
                System.out.println("The median is " + mealFacts.getMedian());
                System.out.println(mealFacts.toString());
                //Make new category. Accumulate data.
                mealFacts = new MealFacts(fields[0]);
                temp = mealFacts.setAccum(Integer.parseInt(fields[2]));
                tcal = mealFacts.getCal();
                System.out.println("the counter is " + temp
                    + "\tcalories are " + tcal);
                current = fields[0];
                
            }
            
            cookbook.addElementWithStrings(fields[0], fields[1], fields[2]);
            
        }
        System.out.println("The median is " + mealFacts.getMedian());
        System.out.println(mealFacts.toString());

        runMenu();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void printMenu() {
        System.out.println("");
        System.out.println("Select Action");
        System.out.println("1. List All Items");
        System.out.println("2. List All Items by Meal");
        System.out.println("3. Search by Meal Name");
        System.out.println("4. Do Control Break");
        System.out.println("5. Exit");
        System.out.print("Please Enter your Choice: ");
    }

    private void runMenu() {
        boolean userContinue = true;

        while (userContinue) {
            printMenu();

            String ans = keyboard.nextLine();
            switch (ans) {
                case "1":
                    cookbook.printAllMeals();
                    break;
                case "2":
                    listByMealType();
                    break;
                case "3":
                    searchByName();
                    break;
                case "4":
                    // doControlBreak();
                    break;
                case "5":
                    userContinue = false;
                    break;
            }
        }

        System.out.println("Goodbye");
        System.exit(0);
    }

    private void listByMealType() {
        // Default value pre-selected in case
        // something goes wrong w/user choice
        MealType mealType = MealType.DINNER;

        System.out.println("Which Meal Type");

        // Generate the menu using the ordinal value of the enum
        for (MealType m : MealType.values()) {
            System.out.println((m.ordinal() + 1) + ". " + m.getMeal());
        }

        System.out.print("Please Enter your Choice: ");
        String ans = keyboard.nextLine();

        try {
            int ansNum = Integer.parseInt(ans);
            if (ansNum <= MealType.values().length) {
                // Modified from original <= in place of <.
                mealType = MealType.values()[ansNum - 1];
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Meal Type " + ans + ", defaulted to " + mealType.getMeal() + ".");
        }

        cookbook.printMealsByType(mealType);
    }

    private void searchByName() {
        keyboard.nextLine();
        System.out.print("Please Enter Value: ");
        String ans = keyboard.nextLine();
        cookbook.printByNameSearch(ans);
    }
}
