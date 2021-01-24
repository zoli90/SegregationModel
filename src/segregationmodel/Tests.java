/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author zoli
 */
public class Tests {
        //create a counter for the number of performed test cases
        int testCaseCounter = 1;
    
    public static void main(String[] args) {
        
        //create an Agent and set some of its variables
        
        Agent testAgent = new Agent();
        
        testAgent.setHomophility(0.5f);
        
        //instantiate an Tests object and start running the tests
        Tests at = new Tests();
        
        at.testChecker(testAgent);
        at.testGetters(testAgent);        
        at.testDecision(testAgent);
        at.testUpdate(testAgent);
        
    }
    
    public void testChecker(Agent testAgent) {
        //create 3 different cells to be put in different ArrayLists
        Cell cell1 = new Cell(0,0,'A');
        Cell cell2 = new Cell(0,1,'A');
        Cell cell3 = new Cell(0,1,'A');
        Cell cell4 = new Cell(0,0,'A');
        Agent.checkCellCode testresult;
        
        //create and test an empty ArrayList
        ArrayList<Cell> empty = new ArrayList<Cell>();
        
        testresult = testAgent.checkCells(empty);
        
        if (testresult == Agent.checkCellCode.ARRAYLIST_TOO_SHORT) {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ", UID: 2) failed! Reported output: " + testresult);
        }
        testCaseCounter++;
        
        //create and test an ArrayList containing 2 identical cells
        ArrayList<Cell> duplicates = new ArrayList<Cell>();
        duplicates.add(cell1);
        duplicates.add(cell2);
        duplicates.add(cell3);
        testAgent.checkCells(duplicates);
        
        testresult = testAgent.checkCells(duplicates);
        
        if (testresult == Agent.checkCellCode.DUPLICATE_CELLS_FOUND) {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ", UID: 3) failed! Reported output: " + testresult);
        }
        testCaseCounter++; 
        
        //create and test an ArrayList containing 2x2 identical cells
        ArrayList<Cell> duplicates2 = new ArrayList<Cell>();
        duplicates2.add(cell1);
        duplicates2.add(cell2);
        duplicates2.add(cell3);
        duplicates2.add(cell4);
        testAgent.checkCells(duplicates2);
        
        testresult = testAgent.checkCells(duplicates2);
        
        if (testresult == Agent.checkCellCode.DUPLICATE_CELLS_FOUND) {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ", UID: 4) failed! Reported output: " + testresult);
        }
        testCaseCounter++;         
        
        //create and test a correct ArrayList
        ArrayList<Cell> correct = new ArrayList<Cell>();
        correct.add(cell1);
        correct.add(cell2);
        testAgent.checkCells(correct);
        
        testresult = testAgent.checkCells(correct);
        
        if (testresult == Agent.checkCellCode.ARRAYLIST_OK) {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ", UID: 5) failed! Reported output: " + testresult);
        }
        testCaseCounter++;           
    }
    
    
    public void testGetters(Agent testAgent) {
        
        //create a 3x3 test coordinate system and assign the Agent's position
        
        testAgent.setColor('A');
        char[][] testArray1 = {{'_','A','_'},
                               {'B','A','_'},
                               {'_','_','A'}};
        testAgent.setX(1);
        testAgent.setY(1);
        
        //test: find all 5 neighboring empty cells, 2 cell of the same color and 1 cell of different color
        
        testAgent.getCells(testArray1);
        if (testAgent.getEmptyCells() == 5){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ", UID: 1) failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getSameColorCells()== 2){
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ", UID: 7) failed! Number of percieved same color cells: " + testAgent.getSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getDifferentColorCells()== 1){
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ", UID: 8) failed! Number of percieved different color cells: " + testAgent.getDifferentColorCells());
        }
        testCaseCounter++;        
        
        //test: find all 3 neighboring empty cells, 1 cell of the same color and 1 cell of different color when the Agent is on the edge of the map.
        //here the array should be replaced but for the sake of simplicity, we'll use the same array but with a different position.
        
        testAgent.setX(1);
        testAgent.setY(0);        
        
        testAgent.getCells(testArray1);
        if (testAgent.getEmptyCells() == 3){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ", UID: 6) failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getSameColorCells()== 1){
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ", UID: 9) failed! Number of percieved same color cells: " + testAgent.getSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getDifferentColorCells()== 1){
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ", UID: 10) failed! Number of percieved different color cells: " + testAgent.getDifferentColorCells());
        }
        testCaseCounter++;

        //create a 4x3 test coordinate system and assign the Agent's position
        
        testAgent.setColor('B');
        char[][] testArray2 = {{'A','A','A'},
                               {'A','B','A'},
                               {'A','A','A'},
                               {'_','_','B'}};
        testAgent.setX(1);
        testAgent.setY(1);
        
        //test: find 0 neighboring empty cell, 0 cell of the same color and 8 cell of different color

        testAgent.getCells(testArray2);
        if (testAgent.getEmptyCells() == 0){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ", UID: 11) failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getSameColorCells()== 0){
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ", UID: 12) failed! Number of percieved same color cells: " + testAgent.getSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getDifferentColorCells()== 8){
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ", UID: 13) failed! Number of percieved different color cells: " + testAgent.getDifferentColorCells());
        }
        testCaseCounter++;
        
        //test: find 1 neighboring empty cell, 0 cell of the same color and 2 cells of different color when the Agent is in the corner of the map.
        //here the array should be replaced but for the sake of simplicity, we'll use the same array but with a different position.
        
        testAgent.setX(2);
        testAgent.setY(3);
        
        testAgent.getCells(testArray2);
        if (testAgent.getEmptyCells() == 1){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ", UID: 14) failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getSameColorCells()== 0){
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getSameColorCells Test (#" + testCaseCounter + ", UID: 15) failed! Number of percieved same color cells: " + testAgent.getSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getDifferentColorCells()== 2){
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getDifferentColorCells Test (#" + testCaseCounter + ", UID: 16) failed! Number of percieved different color cells: " + testAgent.getDifferentColorCells());
        }
        testCaseCounter++;
        
    }
    
    public void testDecision(Agent testAgent) {
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent getNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNextStep Test (#" + testCaseCounter + ", UID: 17) failed! Next step data(x, y, color): " + testAgent.getNextStep().x + ", " + testAgent.getNextStep().y + ", " + testAgent.getNextStep().color);
        }
        testCaseCounter++;
        
        char[][] testArray2 = {{'A','A','A'},
                               {'_','B','B'},
                               {'_','A','A'},
                               {'_','_','B'}};
        
        testAgent.setX(2);
        testAgent.setY(0);
        
        testAgent.getCells(testArray2);
        testAgent.setNextStep();
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 18) failed! Next step data(x, y, color): " + testAgent.getNextStep().x + ", " + testAgent.getNextStep().y + ", " + testAgent.getNextStep().color);
        }
        testCaseCounter++;       
        
        
        testAgent.setX(1);
        testAgent.setY(0);
        testAgent.setColor('A');
        testAgent.setHomophility(0.01f);
        
        testAgent.getCells(testArray2);
        testAgent.setNextStep();
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 19) failed! Next step data(x, y, color): " + testAgent.getNextStep().x + ", " + testAgent.getNextStep().y + ", " + testAgent.getNextStep().color);
        }
        testCaseCounter++;  

        testAgent.setX(1);
        testAgent.setY(2);
        testAgent.setColor('A');
        testAgent.setHomophility(1f);
        
        testAgent.getCells(testArray2);
        testAgent.setNextStep();        
        
        ArrayList<Cell> remainingCells = testAgent.getNeighborCells();
        int arraySize = remainingCells.size();
        
        //instantiate a second, separate RNG for the testing the decision "algorithm". During the decision test, both Random object draw the first int using the exact same seed
        Random testRNG = new Random(SegregationModel.seed);
        int randomOutput = testRNG.nextInt(arraySize);
        
        if (remainingCells.get(randomOutput).equals(testAgent.getNextStep())){
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 20) failed! Next step data(x, y, color): " + testAgent.getNextStep().x + ", " + testAgent.getNextStep().y + ", " + testAgent.getNextStep().color);
            System.out.println("randomOutput = " + randomOutput);
            System.out.println("randomly chosen cell by second RNG (x, y, color): " + remainingCells.get(randomOutput).x + ", " + remainingCells.get(randomOutput).y + ", " + remainingCells.get(randomOutput).color);
        }
        testCaseCounter++;
       
    }
    
    public void testUpdate(Agent testAgent) {

        testAgent.setX(0);
        testAgent.setY(0);
        testAgent.setColor('A');
        testAgent.setHomophility(1f);        
        char[][] testArrayInput = {{'A','B','_'},
                               {'_','B','_'},
                               {'_','_','_'},
                               {'_','_','_'}};
        
        char[][] testExpectedOutput = {{'_','B','_'},
                               {'A','B','_'},
                               {'_','_','_'},
                               {'_','_','_'}};
        
        testAgent.setNextStep();
        char[][] testArrayOutput = testAgent.setPosition(testArrayInput);
        
        if (Arrays.deepEquals(testExpectedOutput, testArrayOutput)){
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ", UID: 21) failed! Actual output: " + Arrays.deepToString(testArrayOutput));
        }
        testCaseCounter++;          
        
        
        
    }
    
}
