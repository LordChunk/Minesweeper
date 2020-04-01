package minesweeper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Bob van der Putten & Job van Ooik
 * @version 2
 */
public class ConsoleIO
{
    private BufferedReader br;
    
    // The constructor constructs our
    // BufferedReader object:
    public ConsoleIO()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public String readInput()
    {
        // Give returnString a temp value so it 
        // resembles an error when nothing was read:
        String returnString = "";
        try
        {
        	while(returnString.trim().length() == 0) {        		
        		// Try to read a line of text:
        		returnString = br.readLine();
        	}
        }
        catch(Exception e)        
        {
            // If the read didn't work, put something in the console:
            writeOutput("AII - Something went wrong: " + e.getMessage());
        }
        return returnString;
    }
    
    public Integer readInputInt() {
    	String unparsedValue;
    	Integer parsedValue = null;
    	
    	while(parsedValue == null) {    		
    		try {
    			unparsedValue = readInput();
    			parsedValue = Integer.parseInt(unparsedValue);
    		} catch (Exception e) {
    			System.out.println("Your input was invalid. Please enter a valid value.");
    		}
    	}
    	
    	return parsedValue;
    }
    
    // This should be no problem for you:
    public void writeOutput(String message)
    {
        System.out.println(message);
    }
}