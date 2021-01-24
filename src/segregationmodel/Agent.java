/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 *
 * @author zoli
 */
public class Agent {
    
    //Section 1: instance variables
    
    private float homophility; //float between 0 and 1 that shows the attraction to agents of the same type (or more precisely: the repulsion by different agents)
    
    private ArrayList<Cell> neighborCells = new ArrayList<>(); //a dynamic list of neighboring cells. The number of cells vary because at the edge of the map the agent has less than 8 neighboring cells.

    private Cell cell = new Cell(0,0,'A'); //an initialized cell object that will store the agent's color and coordinates.
    
    private Cell nextStep = null; //output for deciding where to move and input for changing position. After moving (or if there's nowhere to go) it is set back to null.
    
    public static Random randomGenerator = new Random(SegregationModel.seed);
    
    //Section 2: basic setters
    
    public void setColor(char colorInput) {
        if ((int) colorInput >= 65 && (int) colorInput <= SegregationModel.LAST_COLOR)
            cell.color = colorInput;
    }
    
    public void setHomophility(float homophilityInput) {
        
        if (homophilityInput >= 0f && homophilityInput <= 1f)
            homophility = homophilityInput;
        
    }    
    
    public void setX(int x) {
        cell.x = x;
    }
    
    public void setY(int y) {
        cell.y = y;
    }
    
    //Section 3: Methods that provide input for the Agent to process
    
    //this is a test method that can be inserted in methods manipulating ArrayList<Cell>s.
    
    public enum checkCellCode {
        ARRAYLIST_TOO_SHORT,
        DUPLICATE_CELLS_FOUND,
        ARRAYLIST_OK
    }
    
    public checkCellCode checkCells(ArrayList<Cell> cells) {
        
        int size = cells.size();
        checkCellCode output = checkCellCode.ARRAYLIST_TOO_SHORT;
        
        if (size < 2) {
            output = checkCellCode.ARRAYLIST_TOO_SHORT;
            return output;
            
        } else {
            
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(i != j && cells.get(i).x == cells.get(j).x && cells.get(i).y == cells.get(j).y) {
                        output = checkCellCode.DUPLICATE_CELLS_FOUND;
                        return output;                              
                    }
                }
            }
            output = checkCellCode.ARRAYLIST_OK;
            return output;    
        }
        
    }
    
    public void getCells(char[][] arr) {
        
        neighborCells.clear();
        //Do a nested for loop but do not go out of bounds
        for (int i = Math.max(0, cell.x - 1) ; i < Math.min(cell.x + 2, arr[0].length); i++) {
            for (int j = Math.max(0, cell.y - 1) ; j < Math.min(cell.y + 2, arr.length); j++) {
                
                if (!(i == cell.x && j == cell.y)) //skip the Agent's own position
                    neighborCells.add(new Cell(i, j, arr[j][i]));
                    
            }
        }
        if (checkCells(neighborCells) != checkCellCode.ARRAYLIST_OK)
            System.out.println("getCells method has created duplicate cells!");
    }

    public int getEmptyCells() {
        
        int output = 0;
        for (Cell cells : neighborCells) {
            if (cells.color == '_')
                output++;
        }
        
        return output;
        
    }
    
    public int getSameColorCells() {
        
        int output = 0;
        for (Cell cells : neighborCells) {
            if (cells.color == cell.color)
                output++;
        }
        
        return output;
        
    }
    
    public int getDifferentColorCells() {
        
        int output = neighborCells.size() - getEmptyCells() - getSameColorCells();
        return output;
        
    }
    
    //Section 4: Decision making methods
    
    //getNextStep and getNeighborCells are just for testing purposes
    
    public Cell getNextStep() {
        return nextStep;
    }
    
    public ArrayList<Cell> getNeighborCells() {
        return neighborCells;
    }
    
    public void setNextStep() {
        
        int emptyCells = getEmptyCells();
        int sameColor = getSameColorCells();
        int differentColor = getDifferentColorCells();
        float colorRatio = (float) sameColor / (sameColor + differentColor);
        
        if (emptyCells == 0) {
            nextStep = null;
        } else if (colorRatio >= homophility) {
            nextStep = null;
        } else {
            removeOccopiedCells();
            nextStep = neighborCells.get(randomGenerator.nextInt(neighborCells.size()));
        }
        
    }
    
    public void removeOccopiedCells() {
        
        Iterator<Cell> it = neighborCells.iterator();
        while (it.hasNext()) {
            Cell testedCell = it.next();
            if (testedCell.color != '_')
                it.remove();
        }
        
        if (checkCells(neighborCells) != checkCellCode.ARRAYLIST_OK)
            System.out.println("getCells method has created duplicate cells!");        
        
    }
    
    //Section 5: change position, update everything that's needed
    
    public char[][] setPosition(char[][] arr) {
        
        arr[nextStep.y][nextStep.x] = cell.color;
        arr[cell.y][cell.x] = '_';
        cell.x = nextStep.x;
        cell.y = nextStep.y;
        nextStep = null;
        
        return arr;
        
    }
    
}
