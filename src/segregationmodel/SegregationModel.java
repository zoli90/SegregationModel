/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segregationmodel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//for Windows 10 ANSI escape codes compatibility
import com.sun.jna.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;
/**
 *
 * @author zoli
 */
public class SegregationModel {

    /**
     * @param args the command line arguments
     */

    //constants for color control
    public static final String RED_BOLD = "\033[1;31m" + "B" + "\033[0m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m" + "C" + "\033[0m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m" + "A" + "\033[0m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m" + "D" + "\033[0m";   // BLUE
    
    //important variables
    public static final char LAST_COLOR = 'D';
    public static char numberOfColors = 'D';
    public static long seed = 1;
    public static int mapLength = 200;
    public static int mapHeigth = 40;
    public static int mapPoints = mapLength * mapHeigth;
    public static char[][] map = new char[mapHeigth][mapLength];
    public static ArrayList<Integer> agentCount = new ArrayList<Integer>(Arrays.asList(100, 100, 100, 200));
    public static ArrayList<Float> homophilityList = new ArrayList<Float>(Arrays.asList(0.5f, 0.5f, 0.5f, 0.5f));    
    public static int tickTimeInMilliseconds = 200;
    public static int numberOfTicks = 1000;
    public static ArrayList<Agent> agents = new ArrayList<Agent>();
    
    
    public static void main(String[] args) {
        
        //for Windows 10 ANSI escape codes compatibility
            if(System.getProperty("os.name").startsWith("Windows"))
        {
            // Set output mode to handle virtual terminal sequences
            Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
            DWORD STD_OUTPUT_HANDLE = new DWORD(-11);
            HANDLE hOut = (HANDLE)GetStdHandleFunc.invoke(HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

            DWORDByReference p_dwMode = new DWORDByReference(new DWORD(0));
            Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
            GetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, p_dwMode});

            int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
            DWORD dwMode = p_dwMode.getValue();
            dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
            Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
            SetConsoleModeFunc.invoke(BOOL.class, new Object[]{hOut, dwMode});
        }
          
        //Initialization of the simulation
        
        SegregationModel sm = new SegregationModel();
        sm.setupSimulation();
        
        try {
        	for (int i = 0; i < numberOfTicks; i++) {
            	sm.refreshScreen();
            	sm.performTurn();
            	TimeUnit.MILLISECONDS.sleep(tickTimeInMilliseconds);
        	}
        } catch (InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }
        
        
    }
    
    
    public void setupSimulation() {
        
        //This method places the agents.
        //First an ArrayList is created for choosing coordinates randomly.
        ArrayList<Cell> placementList = new ArrayList<Cell>();
        for (int i = 0; i < mapHeigth; i++) {
            for (int j = 0; j < mapLength; j++) {
                placementList.add(new Cell(j, i, '_'));
            }
        }
        
        Random randomGenerator = new Random(seed);
        //Then choose cells randomly and set them by colour in the array.
        for (int i = 0; i < agentCount.size(); i++) {
            for (int j = 0; j < agentCount.get(i); j++) {
                int randomposition = randomGenerator.nextInt(placementList.size());
                map[placementList.get(randomposition).getY()][placementList.get(randomposition).getX()] = (char) (65 + i);
                agents.add(new Agent(new Cell(placementList.get(randomposition).getX(), placementList.get(randomposition).getY(), ((char) (65 + i))), homophilityList.get(i)));
                placementList.remove(randomposition);
                
            }
        }
        
        //Find all remaining unoccupied points on the map and set it to '_'
        for (int i = 0; i < mapHeigth; i++) {
            for (int j = 0; j < mapLength; j++) {
                if (map[i][j] == '\u0000')
                    map[i][j] = '_';
            }
        }
        
        
        
    }
    
    public void performTurn() {
        
        for (Agent anAgent : agents) {
            anAgent.getCells(map);
            anAgent.setNextStep();
            anAgent.setPosition(map);
        }
        
    }
    
    public void refreshScreen() {
        //clear screen
        System.out.print("\033[H\033[2J");
        
        //print the array char by char
        for (int i = 0; i < mapHeigth; i++) {
            for (int j = 0; j < mapLength; j++) {
                
                switch (map[i][j]) {
                    case '_':
                        System.out.print('_');
                        break;
                    case 'A':
                        System.out.print(YELLOW_BOLD);
                        break;
                    case 'B':
                        System.out.print(RED_BOLD);
                        break;       
                    case 'C':
                        System.out.print(GREEN_BOLD);
                        break;       
                    case 'D':
                        System.out.print(BLUE_BOLD);
                        break;                       
                    }
            
            }
            System.out.println(""); //just for a line break
        }        
    }
    
}
