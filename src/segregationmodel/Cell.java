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
public class Cell {
    private int x;
    private int y;
    private char color;
      
    public Cell(int i, int j, char c) {
        x = i;
        y = j;
        color = c;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public char getColor() {
        return color;
    } 
    
    public void setCell(int i, int j, char c) {
        x = i;
        y = j;
        color = c;
    }
    
}
