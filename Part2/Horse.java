
/**
 * Write a description of class Horse here.
 * 
 * @author Marlon Pires
 * @version 0.3
 */
/**
 * Write a description of class Horse here.
 * 
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

    public Horse(String[] horseArr) { //Constructor for comma-separarted string literal
        double confidence;
        
        if (horseArr.length != ConstructorFields) { //Checks the number of fields available
            throw new IllegalArgumentException("Wrong number of fields - only " + ConstructorFields + " can be accepted");
        }

        if (horseArr[0].length() != 1) {
            throw new IllegalArgumentException("Horse character field should be a single character!");
        }

        // No checks for second element as it is a name, which is a string
        
        try {
            confidence = Double.parseDouble(horseArr[2]);
        }
        catch (NumberFormatException e) {
            throw new NumberFormatException("The confidence field must be a boolean!");
        }

        horseSymbol = horseArr[0].charAt(0);
        horseName = horseArr[1];
        double tempHorseConfidence = Double.parseDouble(horseArr[2]);
        confidenceInBounds(tempHorseConfidence);
        horseConfidence = tempHorseConfidence;
    }

    public Horse(String s) {
        String[] horseArr = s.split(",");
        Horse tempHorse = new Horse(horseArr);
        horseName = tempHorse.getName();
        horseSymbol = tempHorse.getSymbol();
        horseConfidence = tempHorse.getConfidence();
    }

    //toString method for file handling
    @Override
    public String toString() {
        return horseSymbol + "," + horseName + "," + horseConfidence;
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
        horseSymbol = newSymbol;
    }

    private void confidenceInBounds(double testConfidence) {
        if (testConfidence > 1 || testConfidence < 0) {
            throw new IllegalArgumentException("Confidence must be within 0-1!");
        }
    }
}
