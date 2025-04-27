import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

final class FileHandler {
    /* ********************************** **
    * This code is from my miniproject 
    * for procedural programming
    * AUTHOR: Marlon Pires
    * DATE: 10/12/2024
    * DESCRIPTION: 
    * This code is a class used for
    * all file handling.
    ** ********************************** */
    
    // File locations and directory names
    final static String CurDirectory = System.getProperty("user.dir") + "/data/";
    final static String HorseFile = CurDirectory + "horses.csv";


    
    // Tests whether or not a file exists
    //
    public static boolean checkFileExists(String fileName)
    {
        File f = new File(fileName);
        return f.exists();
    } // END checkFileExists

    
    // Writes the text to a file with the name fileName. Must include extension
    //
    public static void writeLineToFile(String fileName, String text) throws IOException
    {
        PrintWriter outputStream = new PrintWriter(new FileWriter(fileName));
        outputStream.println(text);
        outputStream.close();
        return;
    } // END writeToFile

    
    // Writes the text to a file with the name fileName. Must include extension
    //
    public static void writeTextToFile(String fileName, String[] text) throws IOException
    {
        PrintWriter outputStream = new PrintWriter(new FileWriter(fileName));
        int textLines = text.length;
        String fileInput = "";
    
        if (textLines >= 1)
        {
            for (int i = 0; i < textLines - 1; i++)
            {
                fileInput += text[i];
                fileInput += "\n";
            }
        
            fileInput += text[textLines - 1];
            outputStream.println(fileInput);
        }
        else
        {
            outputStream.print(fileInput);
        }
        
        outputStream.close();
        
        return;
    } // END writeTextToFile

    
    // Returns the number of lines in a file, returning -1 if file does not exist
    //
    public static int getFileLines(String fileName) throws IOException
    {
        int count;
        if (checkFileExists(fileName))
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            count = 0;
            
            while (inputStream.readLine() != null)
            {
                count++;
            }
            
            inputStream.close();
        }
        else
        {
            count = -1;
        }
        
        return count;
    } // END getFileLines
    
    // Reads from a text file, returning an array of each line. Returns an empty array if the file does not exist
    //
    public static String[] readFromFile(String fileName) throws IOException
    {
        String[] fileArray = {};
        
        if (checkFileExists(fileName))
        {
            BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
            String currentLine = inputStream.readLine();
            int currentIndex = 0;
    
            fileArray = new String[getFileLines(fileName)];
            
            while (currentLine != null)
            {
                fileArray[currentIndex] = currentLine;
                currentLine = inputStream.readLine();
                currentIndex++;
            }
            inputStream.close();
        }
    
        return fileArray;
    } // END readFromFile


    // Adds a string to the end of a file on a new line
    //
    public static void appendLineToFile(String fileName, String text) throws IOException
    {
        String[] fileText;
        String[] textToWrite;
        int fileLines = getFileLines(fileName);
    
        if (fileLines > 0)
        {
            textToWrite = new String[fileLines + 1];
            fileText = readFromFile(fileName);
    
            for (int i = 0; i < fileLines; i++)
            {
                textToWrite[i] = fileText[i];
            }
    
            textToWrite[fileLines] = text;
            writeTextToFile(fileName, textToWrite);
        }
        else
        {
            writeLineToFile(fileName, text);
        }
        
        return;
    } // END appendLineToFile

    
    // Writes the contents of the originalFile to the newFile
    //
    public static void copyFile(String originalFile, String newFile) throws IOException
    {
        String[] file = readFromFile(originalFile);
        writeTextToFile(newFile, file);
        
        return;
    } // END copyFile

    
    // Removes a line from a file by specifying which line number to remove. First line is line 0
    // Does not remove a line if the line number does not exist
    //
    public static void removeLineFromFile(String fileName, int lineNumber) throws IOException
    {
        String[] curFile = readFromFile(fileName);
        int fileLines = curFile.length;
        String[] newFile = new String[0];
    
        if (fileLines > 0) // incase file is empty
        {
             newFile = new String[fileLines - 1];
        }
            
        if ((lineNumber >= 0) && (lineNumber < fileLines))
        {
            for (int i = 0; i < lineNumber; i++)
            {
                newFile[i] = curFile[i];
                
            }
    
            for (int i = lineNumber + 1; i < fileLines; i++)
            {
                newFile[i - 1] = curFile[i];
            }
    
            writeTextToFile(fileName, newFile);
        }
            
        else
        {
            System.out.println("Invalid line number!");
        }
        
        return;
    } // END removeLineFromFile

    /* *** FILE HANDILNG FOR DATA *** */

    public static void appendHorse(Horse h) throws IOException { 
        appendLineToFile(HorseFile, h.toString());
    }

    public static Horse[] readHorses() throws IOException {
        String[] strHorseArr = readFromFile(HorseFile);
        Horse[] horses = new Horse[strHorseArr.length];
        for (int i = 0; i < horses.length; i++) {
            horses[i] = new Horse(strHorseArr[i]);
        }
        return horses;
    }

}