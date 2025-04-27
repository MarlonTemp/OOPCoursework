import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.*;
import java.io.*;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @Author: Marlon Pires
 * @version 0.3
 */

public class Race
{
    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;

    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        /* REDUNDANT DUE TO HORSEHANDLER ARRAYLIST
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
        */
    }

    public static void main(String args[]) {
        boolean raceInitialised = false;
        final int DEFAULT_TRACK_LENGTH = 10;
        Race r = new Race(DEFAULT_TRACK_LENGTH);
        
        if (args.length == 1) {
            try {
                int temp = Integer.parseInt(args[0]);
                if (temp <= 0) {
                    throw new IllegalArgumentException("Enter a positive number!");
                }
                r = new Race(temp);
                raceInitialised = true;
            }
            catch (NumberFormatException e) {
                throw new NumberFormatException("Enter a positive number!");
            }
                
            
        }
        else if (args.length == 0) {
            r = new Race(DEFAULT_TRACK_LENGTH);
            raceInitialised = true;
        }
        
        if (raceInitialised) {
            r.startRace();
        }
        else {
            System.out.println("0 arguments: default length (" + DEFAULT_TRACK_LENGTH + ") \n 1 argument: int track length");
        }
    }

    
    /** UNUSED METHOD IN CODE - REPLACED BY SETHORSES
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    @Deprecated
    private void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
    {
        String userChoice = "";
        boolean continueLoop = true;
        while (continueLoop) {
            //declare a local variable to tell us when the race is finished
            boolean finished = false;
            Horse winner;
            //Menu allowing user to add horses, or begin race
            menu();
            
            setHorses();
            
            //reset all the lanes (all horses not fallen and back to 0). 
            HorseManager.horsesBackToStart();
                          
            while ((!finished) && (!allFallen()))
            {
                //move each horse
                for (Horse h : HorseManager.getHorses()) {
                    moveHorse(h);
                }
                            
                //print the race positions
                printRace();
                
                //if any of the three horses has won the race is finished
                if (raceWonByHorse())
                {
                    finished = true;
                }
               
                //wait for 100 milliseconds
                try{ 
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch(Exception e){}
            }
            if (raceWonByHorse()) {
                System.out.println("And the winner is... " + getWinner().getName());
            }
            else {
                System.out.println("No horse won!");
            }
            
            userChoice = Helper.input("Start another race? (y/n)");
            if (userChoice.equals("n")) {
                continueLoop = false;
            }
        }
        
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonByHorse()
    {
        for (Horse h : HorseManager.getHorses()) {
            if (h.getDistanceTravelled() >= raceLength)
            {
                return true;
            }
        }
        return false;
    }

    private Horse getWinner() {
        for (Horse h : HorseManager.getHorses()) {
            if (h.getDistanceTravelled() >= raceLength) {
                return h;
            }
        }
        return null;
    }

    private boolean allFallen() {
        boolean allHorsesFallen = true;
        for (Horse h : HorseManager.getHorses()) {
            if (!h.hasFallen()) {
                allHorsesFallen = false;
            }
        }
        return allHorsesFallen;
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        for (Horse h : HorseManager.getHorses()) {
            printLane(h);
            System.out.println();
        }
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2573');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

    private Horse pickHorseFromFile() throws IOException {
        Horse[] availableHorses;
        try {
            availableHorses = FileHandler.readHorses();
        }
        catch (IOException e) {
            System.out.println("File handling error at pickHorseFromfile");
            throw e;
        }

        int choice = -1;
        int numOfHorses = availableHorses.length;

        if (numOfHorses == 0) {
            return new Horse("N,NO_HORSES_AVAILABLE,0");
        }

        while (choice < 0 || choice >= numOfHorses) {
            System.out.println("Pick a horse from the list!");
            for (int i = 0; i < availableHorses.length; i++) {
                System.out.println((i + 1) + ") " + availableHorses[i].getName());
            }
            choice = Helper.inputInt("Pick a number") - 1;
            if (choice < 0 || choice >= numOfHorses) {
                System.out.println("Pick a number from the list!");
            }
        }
        return availableHorses[choice];
    }

    public void setHorses() {
        final int MAX_HORSES = 8;
        final int MIN_HORSES = 2;
        HorseManager.clearHorses();
        int numOfHorses = Helper.inputInt("Enter the number of horses you want");
        while (numOfHorses < 2 || numOfHorses > MAX_HORSES) {
            numOfHorses = Helper.inputInt("Please enter a number of horses between " + 2 + " and " + MAX_HORSES + "!");
        }

        for (int i = 0; i < numOfHorses; i++) {
            try {
                HorseManager.appendHorse(pickHorseFromFile());
            }
            catch (IOException e) {
                System.out.println("Error when accessing file!");
            }
        }
    }

    private void menu() {
        int menuChoice = 0;

        while (menuChoice != 2) {
            System.out.println("1) Add new horse");
            System.out.println("2) Start race");
            menuChoice = Helper.inputInt("Pick a menu option");
            
            if (menuChoice == 1) {
                Horse tempHorse = HorseManager.inputHorse();
                try {
                    FileHandler.appendHorse(tempHorse);
                }
                catch (IOException e) {
                    System.out.println("Error when writing horse, please try again");
                }
            }
        }
    }
}