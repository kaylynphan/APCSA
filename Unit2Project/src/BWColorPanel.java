/*
 * Kaylyn Phan and Manvika Satish
 * AP CSA
 * Period 3
 * Unit 2 Project
 */

import java.awt.*;
import javax.swing.*;


public class BWColorPanel extends JPanel {
	
	/**
	 * @author - Kaylyn Phan
	 * Creates a panel and sets its background color
	 * @param backColor - background color
	 */
	public BWColorPanel(Color backColor) {
		setBackground(backColor);
		// sets size of panel
		setPreferredSize(new Dimension(80, 90));
	}
	
	/** 
	 * @author - Kaylyn Phan
	 * Method that draws a light gray rectangle in the center of the panel
	 * @param g - Graphics object that draws on panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// sets pen color to gray and creates rectangle for illusion
		g.setColor(Color.lightGray);
		g.fillRect(getWidth()/3, getHeight()/3, getWidth()/3, getHeight()/3);
	}
	
}