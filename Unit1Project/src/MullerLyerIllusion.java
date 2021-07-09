/* Kaylyn Phan
 * AP CSA Period 3
 * Unit 1 Project - Using Variables in a Graphics Program
 * 28 August 2020
 */

import javax.swing.*;
import java.awt.*;

public class MullerLyerIllusion extends JPanel{

	public MullerLyerIllusion(Color backColor) {
		setBackground(backColor);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//variables for center of panel
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		int linesLength = 200;
		int distBetweenLines = 40;
		int arrowTailLength = 15;
		
		//coordinates for two horizontal lines
		int linesX1 = centerX - linesLength / 2;
		int linesX2 = centerX + linesLength / 2;
		int linesY1 = centerY - distBetweenLines / 2;
		int linesY2 = centerY + distBetweenLines / 2;
		
		//coordinates for two arrows
		int arrowsX1 = linesX1 + arrowTailLength;
		int arrowsX2 = linesX2 - arrowTailLength;
		int arrowsY1 = linesY1 + arrowTailLength;
		int arrowsY2 = linesY1 - arrowTailLength;
		
		//coordinates for two tails
		int tailsX1 = linesX1 - arrowTailLength;
		int tailsX2 = linesX2 + arrowTailLength;
		int tailsY1 = linesY2 - arrowTailLength;
		int tailsY2 = linesY2 + arrowTailLength;
	
		
		g.setColor(Color.blue);
		
		//draw first horizontal line
		g.drawLine(linesX1, linesY1, linesX2, linesY1);
		//draw left arrow
		g.drawLine(linesX1, linesY1, arrowsX1, arrowsY1);
		g.drawLine(linesX1, linesY1, arrowsX1, arrowsY2);
		//draw right arrow
		g.drawLine(linesX2, linesY1, arrowsX2, arrowsY1);
		g.drawLine(linesX2, linesY1, arrowsX2, arrowsY2);
		
		g.setColor(Color.green);
		
		//draw second horizontal line
		g.drawLine(linesX1, linesY2, linesX2, linesY2);
		//draw left tail
		g.drawLine(linesX1, linesY2, tailsX1, tailsY1);
		g.drawLine(linesX1, linesY2, tailsX1, tailsY2);
		//draw right tail
		g.drawLine(linesX2, linesY2, tailsX2, tailsY1);
		g.drawLine(linesX2, linesY2, tailsX2, tailsY2);
		
		//Use this vertical line as a guide for centering
		//g.drawLine(centerX, centerY - 100, centerX, centerY + 100);
		
		//draw "Hello World"
		g.setColor(Color.magenta);
		g.drawString("Hello, World!", centerX - 40, centerY - 100);
		
		//draw center coordinates
		g.setColor(Color.orange);
		g.drawString("(" + centerX + "," + centerY + ")", centerX - 30, centerY - 80);
		
		//draw rectangle around text elements
		int rectangleX = centerX - 45;
		int rectangleY = centerY - 120;
		int rectangleWidth = 90;
		int rectangleHeight = 50;
		g.setColor(Color.pink);
		g.drawRect(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
		
	}

	public static void main(String[] args) {
		JFrame theGUI = new JFrame("Muller-Lyer Illusion");
		theGUI.setSize(400,400);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MullerLyerIllusion panel = new MullerLyerIllusion(Color.white);
		Container pane = theGUI.getContentPane();
		pane.add(panel);
		
		theGUI.setVisible(true);
	}
}



