/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;
import java.io.*;
import java.util.*;

/**
 *
 * @author zoli
 */
public class CommandLineInterface {
    
    public void runCLI() {
        System.out.println("Hello and welcome to the Java implementation of Schelling's Segregation Model.");
        System.out.println("Please set the parameters for the simulation.");
        System.out.println("------------------------------------------");
        SegregationModel.lastColor = setlastColor();
        
        SegregationModel.mapLength = setMapLength();
        SegregationModel.mapHeigth = setMapHeigth();
        SegregationModel.mapPoints = SegregationModel.mapLength * SegregationModel.mapHeigth;
        SegregationModel.map = new char[SegregationModel.mapHeigth][SegregationModel.mapLength];
        
        SegregationModel.agentCount = new ArrayList<Integer>(setAgentCount());
        SegregationModel.homophilityList = new ArrayList<Float>(setAgentHomophility());
        
        SegregationModel.tickTimeInMilliseconds = setTickTime();
        SegregationModel.numberOfTicks = setNumberOfTicks();
        
        SegregationModel.hasSeed = setSeedBoolean();
        if (SegregationModel.hasSeed == true)
            SegregationModel.seed = setSeed();
        
        String notUsedString = getUserInput("Thank you. Press any key to start.");
        
    }
    
    public char setlastColor() {
        boolean inputIsValid = false;
        char output = 'A';
        String input;
        int maximumNumber = (int) SegregationModel.LAST_COLOR - 64;
        
        do { 
            input = getUserInput("Please enter the number of groups to be created (Minimum is 2. Maximum is " + Integer.toString(maximumNumber) + "): ");
            try {
                int inputInt = Integer.parseInt(input);
                if (inputInt > 1 && inputInt <= maximumNumber) {
                    inputIsValid = true;
                    SegregationModel.numberOfColors = inputInt;
                    output = (char) (inputInt + 64);
                } else {
                    System.out.println("Invalid input.");
                }    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
    public boolean setSeedBoolean() {
        boolean inputIsValid = false;
        boolean output = false;
        String input;
        
        do { 
            input = getUserInput("Would you like to enter a seed? Please type yes or no. ");
            if (input.equals("yes") ) {
                output = true;
                inputIsValid = true;
            } else if (input.equals("no")) {
                output = false;
                inputIsValid = true;                
            } else {
                System.out.println("Invalid input.");                
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
    public int setMapLength() {
        boolean inputIsValid = false;
        int output = 0;
        String input;
        
        do { 
            input = getUserInput("Please enter the map length. (Minimum is 4. Maximum is 1000.): ");
            try {
                output = Integer.parseInt(input);
                if (output >= 4 && output <= 1000) {
                    inputIsValid = true;
                } else {
                    System.out.println("Invalid input.");
                }    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
    public int setMapHeigth() {
        boolean inputIsValid = false;
        int output = 0;
        String input;
        
        do { 
            input = getUserInput("Please enter the map height. (Minimum is 4. Maximum is 1000.): ");
            try {
                output = Integer.parseInt(input);
                if (output >= 4 && output <= 1000) {
                    inputIsValid = true;
                } else {
                    System.out.println("Invalid input.");
                }    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
    public ArrayList<Integer> setAgentCount() {
        boolean inputIsValid = false;
        int inputInt = 0;
        ArrayList<Integer> output = new ArrayList<Integer>();
        final int MINIMUM = 2; 
        String input;
        int occupiedSpace = 0;
        
        for (int i = 0; i < SegregationModel.numberOfColors; i++) {            
                                
            do {
                int maximum = (SegregationModel.mapPoints - 1 - occupiedSpace - (SegregationModel.numberOfColors - i - 1) * MINIMUM);
                input = getUserInput("Please enter the number of agents of group '" + ((char) (65 + i)) + "'. (Minimum is 2. Maximum is "+ maximum +".): ");
                try {
                    inputInt = Integer.parseInt(input);
                    if (inputInt >= 2 && inputInt <= maximum) {
                        inputIsValid = true;
                        output.add(inputInt);
                        occupiedSpace += inputInt;
                    } else {
                        System.out.println("Invalid input.");
                    }    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }

            } while (!inputIsValid);
            inputIsValid = false;
            
        }
        
        return output;
    }
    
    public ArrayList<Float> setAgentHomophility() {
        boolean inputIsValid = false;
        float inputFloat = 0f;
        ArrayList<Float> output = new ArrayList<Float>();
        String input;
        
        System.out.println("");
        System.out.println("Homophility is a number between 0 and 1 that shows the attraction to agents of the same type (or more precisely: the repulsion by different agents)");
        System.out.println("For example if an agent has 1 neighbor of same color and 2 neighbor of different color, then the ratio of same neighbors are 33,3%, or 0.33");
        System.out.println("If the agent's homophility is 0.5, or 50%, then the agent moves away. There are not enough neighbors of the same color.");
        System.out.println("");
        
        for (int i = 0; i < SegregationModel.numberOfColors; i++) {
            do {
                input = getUserInput("Please enter the homophility of agents of group '" + ((char) (65 + i)) + "'. (Minimum is 0. Maximum is 1. Use '.' for decimal point): ");
                try {
                    inputFloat = Float.parseFloat(input);
                    if (inputFloat >= 0.0f && inputFloat <= 1.0f) {
                        inputIsValid = true;
                        output.add(inputFloat);                
                    } else {
                        System.out.println("Invalid input.");
                    }    
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }

            } while (!inputIsValid);
            inputIsValid = false;
        }
        
        return output;
    }
    
    public int setTickTime() {
        boolean inputIsValid = false;
        int output = 0;
        String input;
        
        do { 
            input = getUserInput("Please enter the time amount while a frame of the simulation is displayed, in milliseconds. (Minimum is 1. Maximum is 10,000.): ");
            try {
                output = Integer.parseInt(input);
                if (output >= 1 && output <= 10000) {
                    inputIsValid = true;
                } else {
                    System.out.println("Invalid input.");
                }    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
public int setNumberOfTicks() {
        boolean inputIsValid = false;
        int output = 0;
        String input;
        
        do { 
            input = getUserInput("Please enter the number of frames to be simulated. (Minimum is 2. Maximum is 10000.): ");
            try {
                output = Integer.parseInt(input);
                if (output >= 2 && output <= 10000) {
                    inputIsValid = true;
                } else {
                    System.out.println("Invalid input.");
                }    
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }    
        
    public long setSeed() {
        boolean inputIsValid = false;
        long output = 0;
        String input;
        
        do { 
            input = getUserInput("Please enter the seed, without commas. (Minimum is -9,223,372,036,854,775,808. Maximum is: 9,223,372,036,854,775,807): ");
            try {
                output = Long.parseLong(input);
                //here it makes no sense to further validate output, like if it's bigger than Long.MIN_VALUE
                inputIsValid = true;  
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
            
        } while (!inputIsValid);
        
        return output;
    }
    
    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader (new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }
}
