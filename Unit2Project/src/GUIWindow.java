/*
 * Kaylyn Phan and Manvika Satish
 * AP CSA
 * Period 3
 * Unit 2 Project
 */

import java.awt.*;
import java.util.Random;
import javax.swing.*;


public class GUIWindow {
	
	/**
	 * @author - Kaylyn Phan
	 * Method that opens input dialog box and prompts user for number of rows and columns
	 * @return number of rows and number of columns separated by a space
	 */
	public static String getInput() {
		// random number from 49-57 - ASCII values for '1' through '9'
		Random rand = new Random();
		int randomNum1 = rand.nextInt(8) + 49;
		int randomNum2 = rand.nextInt(8) + 49;
		
		String randomRows = "" + (char)(randomNum1);
		String randomCols = "" + (char)(randomNum2);
		
		// displays random suggested dimensions in input dialog boxes
		String  inputStr = JOptionPane.showInputDialog("Numberƒofƒrows", randomRows);
	    if (inputStr  == null) return "0"; 
	    String rows = inputStr;
	    inputStr = JOptionPane.showInputDialog("Numberƒofƒcolumns", randomCols);
	    if (inputStr  == null) return "0";
	    String cols = inputStr;
	    
	    return rows + " " + cols;
	}
	
	/** 
	 * @author - Kaylyn Phan
	 * Method that generates one random color panel
	 * @return A 50x50 ColorPanelRandomColor object with a random background color
	 */
	public static ColorPanelRandomColor createRandomColorPanel() {
		Random gen = new Random();
		int  red = gen.nextInt(256);
	    int  green = gen.nextInt(256);
	    int  blue = gen.nextInt(256);
	    Color backColor = new Color(red,green,blue);
	          
	    ColorPanelRandomColor panel = new ColorPanelRandomColor(backColor,50,50);
	    return panel;	
	}
	
	/**
	 * @author - Manvika Satish
	 * Method that creates a grid of random color panels
	 * @param pane - Container object that contains checker board
	 * @param rows - number of rows
	 * @param cols - number of columns
	 */
	public static void randomColorCheckerBoard(Container pane, int rows, int cols) {
	    pane.setLayout(new GridLayout(rows,cols));
	    for (int  i = 1; i<= rows*cols; i++){
	       pane.add(createRandomColorPanel());
	    }
	}
	
	/**
	 * @author - Manvika Satish
	 * Method that creates GUI window with grid of random color panels
	 * Packs GUI and sets visibility to true
	 */
	public static void guiWindowRandomCheckeredBoard() {
		String input = getInput();
		int rows = Integer.parseInt(input.substring(0, input.indexOf(" ")));
	    int cols = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
	    
		JFrame theGUI = new JFrame();
	    theGUI.setTitle("GUI Window");
	    theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container pane = theGUI.getContentPane();
	    randomColorCheckerBoard(pane, rows, cols);
	    theGUI.pack();   
	    theGUI.setVisible(true);
	}
	
	/**
	 * @author - Kaylyn Phan
	 * Method that creates 2 BWColorPanel objects
	 * @param pane - Container object that contains induced contrast illusion
	 */
	public static void inducedContrast(Container pane) {	
		BWColorPanel blackPanel = new BWColorPanel(Color.black);
		BWColorPanel whitePanel = new BWColorPanel(Color.white);
		pane.add(blackPanel);
		pane.add(whitePanel);	
	}
	
	/**
	 * @author - Manvika Satish
	 * Method that creates GUI window with induced contrast illusion
	 * Packs GUI and sets visibility to true
	 * Does NOT return anything
	 */
	public static void guiWindowInducedContrast() {
		JFrame theGUI = new JFrame();
	    theGUI.setTitle("Induced Contrast");
	    theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container pane = theGUI.getContentPane();
	    pane.setLayout(new GridLayout(1,2));
	    inducedContrast(pane);
	    theGUI.pack();
	    theGUI.setVisible(true);
	}

	/**
	 * @author - Manvika Satish
	 * Main method, creates 2 GUI windows (with random color checker board and induced contrast illusion)
	 * by calling other methods.
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
	    guiWindowRandomCheckeredBoard();
	    guiWindowInducedContrast();
	}
}