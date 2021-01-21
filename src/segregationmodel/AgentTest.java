/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;

/**
 *
 * @author zoli
 */
public class AgentTest {
    
    public static void main(String[] args) {
        
    testGetters();
        
    }
    
    public static void testGetters() {
        
        //create an Agent and set some of its variables
        
        Agent testAgent = new Agent();
        
        testAgent.setColor('A');
        testAgent.setHomophility(0.5f);
        
        //create a 3x3 test coordinate system and assign the Agent's position
        
        char[][] testArray1 = {{'_','A','_'},
                               {'B','A','_'},
                               {'_','_','_'}};
        testAgent.setX(1);
        testAgent.setY(1);
        
        //test #1: find all 6 neighboring empty cells
        
        if (testAgent.getEmptyCells(testArray1) == 6){
            System.out.println("Test #1 passed.");
        } else {
            System.out.println("Test #1 failed! Number of percieved empty cells:" + testAgent.getEmptyCells(testArray1));
        }
        
        
        
        
    }
    
    
    
}
