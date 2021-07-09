/*
 * Kaylyn Phan and Manvika Satish
 * AP CSA
 * Period 3
 * Unit 2 Project
 */

import java.awt.*;
import javax.swing.*;

public class ColorPanelRandomColor extends JPanel {

	/**
	 * Creates panel and sets background color
	 * @author - Kaylyn Phan
	 * @param backColor - background color
	 */
	public ColorPanelRandomColor(Color backColor){
		setBackground(backColor);
	}
	/**
	 * Creates panel and sets background color and dimensions
	 * @param backColor - background color
	 * @param width - width
	 * @param height - height
	 */
	public ColorPanelRandomColor(Color backColor, int width, int height){
		setBackground(backColor);
		setPreferredSize(new Dimension(width, height));
	}
	
	/**
	 * @author - Manvika Satish
	 * Method that writes RGB values of each panel in its upper corner
	 * @param g - Graphics object that draws on panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// place RGB values at the top left
		int x = 0;
	    int y = 15;
		
	    // gets the R,G,B values of each panel
		int redNum = getBackground().getRed();
		int greenNum = getBackground().getGreen();
		int blueNum = getBackground().getBlue();
		
		// sets text color yellow
		g.setColor(Color.yellow);
		g.drawString((redNum + "," + greenNum + "," + blueNum) , x, y);
	}
}
