import java.util.ArrayList;
import java.util.Arrays;


/**
 * Write a description of class Horse here.
 * 
 * @author Marlon Pires
 * @version 0.3
 * @author Marlon Pires
 * @version 0.3
 */
public class Horse
{
    //Fields of class Horse
    private static int ConstructorFields = 3;
    private String horseName;
    private char horseSymbol;
    private double horseConfidence;
    private int distanceTravelled = 0;
    private boolean fallen = false;
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        confidenceInBounds(horseConfidence);
        this.horseConfidence = horseConfidence;
    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        fallen = true;
    }
    
    public double getConfidence()
    {
        return horseConfidence;
    }
    
    public int getDistanceTravelled()
    {
        return distanceTravelled;
    }
    
    public String getName()
    {
        return horseName;
    }
    
    public char getSymbol()
    {
        return horseSymbol;
    }
    
    public void goBackToStart()
    {
        distanceTravelled = 0; 
        fallen = false;
    }
    
    public boolean hasFallen()
    {
        return fallen;
    }

    public void moveForward()
    {
        distanceTravelled++;
    }

    public void setConfidence(double newConfidence)
    {
        confidenceInBounds(newConfidence);
        horseConfidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol)
    {
        
    }
    
}
