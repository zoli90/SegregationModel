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
        
        Agent testAgent = new Agent(new Cell (0,0, 'A'), 0.5f);
        
        //instantiate an Tests object and start running the tests
        Tests at = new Tests();
        
        at.testChecker(testAgent);
        at.testGetters(testAgent);        
        at.testDecision(testAgent);
        at.testUpdate(testAgent);

        //instantiate a simulation object and start running the tests        
        SegregationModel sm = new SegregationModel();
        at.testSetupSim(sm);
        
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
        testAgent.setCell(1, 1, 'A');
        
        //test: find all 5 neighboring empty cells, 2 cell of the same color and 1 cell of different color
        
        testAgent.getCells(testArray1);
        if (testAgent.getNumberOfEmptyCells() == 5){
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ", UID: 1) failed! Number of percieved empty cells: " + testAgent.getNumberOfEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getNumberOfSameColorCells()== 2){
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ", UID: 7) failed! Number of percieved same color cells: " + testAgent.getNumberOfSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getNumberOfDifferentColorCells()== 1){
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ", UID: 8) failed! Number of percieved different color cells: " + testAgent.getNumberOfDifferentColorCells());
        }
        testCaseCounter++;        
        
        //test: find all 3 neighboring empty cells, 1 cell of the same color and 1 cell of different color when the Agent is on the edge of the map.
        //here the array should be replaced but for the sake of simplicity, we'll use the same array but with a different position.
        
        testAgent.setCell(1, 0, 'A');       
        
        testAgent.getCells(testArray1);
        if (testAgent.getNumberOfEmptyCells() == 3){
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ", UID: 6) failed! Number of percieved empty cells: " + testAgent.getNumberOfEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getNumberOfSameColorCells()== 1){
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ", UID: 9) failed! Number of percieved same color cells: " + testAgent.getNumberOfSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getNumberOfDifferentColorCells()== 1){
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ", UID: 10) failed! Number of percieved different color cells: " + testAgent.getNumberOfDifferentColorCells());
        }
        testCaseCounter++;

        //create a 4x3 test coordinate system and assign the Agent's position
        
        char[][] testArray2 = {{'A','A','A'},
                               {'A','B','A'},
                               {'A','A','A'},
                               {'_','_','B'}};
        testAgent.setCell(1, 1, 'B');
        
        //test: find 0 neighboring empty cell, 0 cell of the same color and 8 cell of different color

        testAgent.getCells(testArray2);
        if (testAgent.getNumberOfEmptyCells() == 0){
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ", UID: 11) failed! Number of percieved empty cells: " + testAgent.getNumberOfEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getNumberOfSameColorCells()== 0){
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ", UID: 12) failed! Number of percieved same color cells: " + testAgent.getNumberOfSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getNumberOfDifferentColorCells()== 8){
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ", UID: 13) failed! Number of percieved different color cells: " + testAgent.getNumberOfDifferentColorCells());
        }
        testCaseCounter++;
        
        //test: find 1 neighboring empty cell, 0 cell of the same color and 2 cells of different color when the Agent is in the corner of the map.
        //here the array should be replaced but for the sake of simplicity, we'll use the same array but with a different position.
        
        testAgent.setCell(2, 3, 'A');
        
        testAgent.getCells(testArray2);
        if (testAgent.getNumberOfEmptyCells() == 1){
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfEmptyCells Test (#" + testCaseCounter + ", UID: 14) failed! Number of percieved empty cells: " + testAgent.getNumberOfEmptyCells());
        }
        testCaseCounter++;
        
        if (testAgent.getNumberOfSameColorCells()== 0){
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfSameColorCells Test (#" + testCaseCounter + ", UID: 15) failed! Number of percieved same color cells: " + testAgent.getNumberOfSameColorCells());
        }
        testCaseCounter++;

        if (testAgent.getNumberOfDifferentColorCells()== 2){
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNumberOfDifferentColorCells Test (#" + testCaseCounter + ", UID: 16) failed! Number of percieved different color cells: " + testAgent.getNumberOfDifferentColorCells());
        }
        testCaseCounter++;
        
    }
    
    public void testDecision(Agent testAgent) {
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent getNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getNextStep Test (#" + testCaseCounter + ", UID: 17) failed! Next step data(x, y, color): " + testAgent.getNextStep().getX() + ", " + testAgent.getNextStep().getY() + ", " + testAgent.getNextStep().getColor());
        }
        testCaseCounter++;
        
        char[][] testArray2 = {{'A','A','A'},
                               {'_','B','B'},
                               {'_','A','A'},
                               {'_','_','B'}};
        
        testAgent.setCell(2, 0, 'A');
        
        testAgent.getCells(testArray2);
        testAgent.setNextStep();
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 18) failed! Next step data(x, y, color): " + testAgent.getNextStep().getX() + ", " + testAgent.getNextStep().getY() + ", " + testAgent.getNextStep().getColor());
        }
        testCaseCounter++;       
        
        
        testAgent.setCell(1, 0, 'A');
        testAgent.setHomophility(0.01f);
        
        testAgent.getCells(testArray2);
        testAgent.setNextStep();
        
        if (testAgent.getNextStep()== null){
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 19) failed! Next step data(x, y, color): " + testAgent.getNextStep().getX() + ", " + testAgent.getNextStep().getY() + ", " + testAgent.getNextStep().getColor());
        }
        testCaseCounter++;  

        testAgent.setCell(1, 2, 'A');
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
            System.out.println("Agent setNextStep Test (#" + testCaseCounter + ", UID: 20) failed! Next step data(x, y, color): " + testAgent.getNextStep().getX() + ", " + testAgent.getNextStep().getY() + ", " + testAgent.getNextStep().getColor());
            System.out.println("randomOutput = " + randomOutput);
            System.out.println("randomly chosen cell by second RNG (x, y, color): " + remainingCells.get(randomOutput).getX() + ", " + remainingCells.get(randomOutput).getY() + ", " + remainingCells.get(randomOutput).getColor());
        }
        testCaseCounter++;
       
    }
    
    public void testUpdate(Agent testAgent) {

        testAgent.setCell(0, 0, 'A');
        testAgent.setHomophility(1f);        
        char[][] testArrayInput =      {{'A','B','_'},
                                        {'_','B','_'},
                                        {'_','_','_'},
                                        {'_','_','_'}};
        
        char[][] testExpectedOutput =  {{'_','B','_'},
                                        {'A','B','_'},
                                        {'_','_','_'},
                                        {'_','_','_'}};
        
        testAgent.getCells(testArrayInput);
        testAgent.setNextStep();
        char[][] testArrayOutput = testAgent.setPosition(testArrayInput);
        
        if (Arrays.deepEquals(testExpectedOutput, testArrayOutput)){
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ", UID: 21) failed! Actual output: " + Arrays.deepToString(testArrayOutput));
        }
        testCaseCounter++;          
        
        testAgent.setCell(0, 0, 'A');
        testAgent.setHomophility(1f);        
        char[][] testArrayInput2 =      {{'A','B','_'},
                                        {'A','B','_'},
                                        {'_','_','_'},
                                        {'_','_','_'}};
        
        char[][] testExpectedOutput2 =  {{'A','B','_'},
                                        {'A','B','_'},
                                        {'_','_','_'},
                                        {'_','_','_'}};
        
        testAgent.getCells(testArrayInput2);        
        testAgent.setNextStep();
        char[][] testArrayOutput2 = testAgent.setPosition(testArrayInput2);
        
        if (Arrays.deepEquals(testExpectedOutput2, testArrayOutput2)){
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent setPosition Test (#" + testCaseCounter + ", UID: 21) failed! Actual output: " + Arrays.deepToString(testArrayOutput2));
        }
        testCaseCounter++;        
        
    }

    public void testSetupSim(SegregationModel sim) {
        
        SegregationModel.numberOfColors = 'A';
        SegregationModel.mapHeigth = 2;
        SegregationModel.mapLength = 2;
        SegregationModel.mapPoints = 4;
        SegregationModel.map = new char[2][2];
        SegregationModel.agentCount = new ArrayList<Integer>();
        SegregationModel.agentCount.add(3);
        
        sim.setupSimulation();
        
        System.out.println("SegregationModel setupSimulation Test (#" + testCaseCounter + ", UID: 22). Generated output: " + Arrays.deepToString(SegregationModel.map));
        
        
        SegregationModel.numberOfColors = 'B';
        SegregationModel.mapHeigth = 3;
        SegregationModel.mapLength = 3;
        SegregationModel.mapPoints = 9;
        SegregationModel.map = new char[3][3];
        SegregationModel.agentCount = new ArrayList<Integer>();
        SegregationModel.agentCount.add(1);
        SegregationModel.agentCount.add(1);
        
        sim.setupSimulation();
        
        System.out.println("SegregationModel setupSimulation Test (#" + testCaseCounter + ", UID: 23). Generated output: " + Arrays.deepToString(SegregationModel.map));
    
        SegregationModel.numberOfColors = 'D';
        SegregationModel.mapHeigth = 4;
        SegregationModel.mapLength = 1;
        SegregationModel.mapPoints = 4;
        SegregationModel.map = new char[4][1];
        SegregationModel.agentCount = new ArrayList<Integer>();
        SegregationModel.agentCount.add(1);
        SegregationModel.agentCount.add(1);
        SegregationModel.agentCount.add(1);
        SegregationModel.agentCount.add(1);
        
        sim.setupSimulation();
        
        System.out.println("SegregationModel setupSimulation Test (#" + testCaseCounter + ", UID: 24). Generated output: " + Arrays.deepToString(SegregationModel.map));
    
        SegregationModel.numberOfColors = 'A';
        SegregationModel.mapHeigth = 1;
        SegregationModel.mapLength = 1;
        SegregationModel.mapPoints = 1;
        SegregationModel.map = new char[1][1];
        SegregationModel.agentCount = new ArrayList<Integer>();
        
        sim.setupSimulation();
        
        System.out.println("SegregationModel setupSimulation Test (#" + testCaseCounter + ", UID: 25). Generated output: " + Arrays.deepToString(SegregationModel.map));        
    }
    
}
