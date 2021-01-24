/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;
import java.util.ArrayList;

/**
 *
 * @author zoli
 */
public class AgentTest {
        //create a counter for the number of performed test cases
        int testCaseCounter = 1;
    
    public static void main(String[] args) {
        
        //create an Agent and set some of its variables
        
        Agent testAgent = new Agent();
        
        testAgent.setColor('A');
        testAgent.setHomophility(0.5f);
        
        
        //instantiate an AgentTest object and start running the tests
        AgentTest at = new AgentTest();
        at.testChecker(testAgent);
        at.testGetters(testAgent);
        
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
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") failed! Reported output: " + testresult);
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
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") failed! Reported output: " + testresult);
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
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") failed! Reported output: " + testresult);
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
            System.out.println("Agent checkCells Test (#" + testCaseCounter + ") failed! Reported output: " + testresult);
        }
        testCaseCounter++;           
    }
    
    
    public void testGetters(Agent testAgent) {
        
        //create a 3x3 test coordinate system and assign the Agent's position
        
        char[][] testArray1 = {{'_','A','_'},
                               {'B','A','_'},
                               {'_','_','_'}};
        testAgent.setX(1);
        testAgent.setY(1);
        
        //test: find all 6 neighboring empty cells
        
        testAgent.getCells(testArray1);
        if (testAgent.getEmptyCells() == 6){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
        //test: find all 3 neighboring empty cells when the Agent is on the edge of the map.
        
        testAgent.setX(1);
        testAgent.setY(0);        
        
        testAgent.getCells(testArray1);
        if (testAgent.getEmptyCells() == 3){
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") passed.");
        } else {
            System.out.println("Agent getEmptyCells Test (#" + testCaseCounter + ") failed! Number of percieved empty cells: " + testAgent.getEmptyCells());
        }
        testCaseCounter++;
        
    }
    
    
    
}
