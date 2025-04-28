import java.util.*;

final class HorseManager {
    //Stores horses
    private static ArrayList<Horse> horses = new ArrayList<>(); 

    // Adds horse to end of the list
    public static void appendHorse(Horse h) {
        horses.add(h);
    }

    // Replaces horse at index position
    public static void setHorse(Horse h, int index) {
        horses.set(index, h);
    }

    // Creates a new array list to store horses, removing all previous horses
    public static void clearHorses() {
        horses = new ArrayList<>(); 
    }

    // Returns the horse at the index
    public static Horse getHorse(int index) {
        return horses.get(index);
    }

    // Returns the horses stored in the list
    public static Horse[] getHorses() {
        Horse[] horseArr = new Horse[horses.size()];
        for (int i = 0; i < horses.size(); i++) {
            horseArr[i] = horses.get(i);
        }
        return horseArr;
    }


    // resets all horses
    public static void horsesBackToStart() {
        for (Horse h : horses) {
            h.goBackToStart();
        }
    }


    //Prompts a user to input the values to create a new horse
    public static Horse inputHorse() {
        boolean horseMade = false;
        Horse newHorse = null;
        while (!horseMade) {
            String[] horseArr = new String[3];
            horseArr[1] = Helper.input("Enter the name for the horse");
            horseArr[0] = Helper.input("Enter a character for the horse");
            horseArr[2] = Helper.input("Enter a decimal value (0-1) for the confidence for the horse");
             
            try {
                newHorse = new Horse(horseArr);
                horseMade = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Please enter valid values for the horse!");
            }
        }
        return newHorse;
    }
}