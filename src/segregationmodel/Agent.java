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
public class Agent {
    
    private float homophility;
    private Cell cell = new Cell(0,0,'A');
    
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
    
    public int getEmptyCells(char[][] arr) {
        
        ArrayList<Cell> neighborCells = new ArrayList<>();
        
        //Do a nested for loop but do not go out of bounds
        for (int i = Math.max(0, cell.x - 1) ; i < Math.min(cell.x + 2, arr[0].length); i++) {
            for (int j = Math.max(0, cell.y - 1) ; j < Math.min(cell.y + 2, arr.length); j++) {
                
                if (!(i == cell.x && j == cell.y)) //skip the Agent's own position
                    neighborCells.add(new Cell(i, j, arr[i][j]));
                    
            }
        }
        
        int output = 0;
        for (Cell cells : neighborCells) {
            if (cells.color == '_')
                output++;
        }
        
        return output;
        
    }
    
}
