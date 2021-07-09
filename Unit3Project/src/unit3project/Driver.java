package unit3project;
/*
 * Kaylyn Phan and Manvika Satish
 * AP CSA
 * Period 3
 * 10/25/2020
 */

import java.awt.*;
import javax.swing.*;

public class Driver {

	/**
	 * Converts an integer score in String format to int
	 * @author Kaylyn Phan
	 * @param score - an integer score stored as a String
	 * @return an int that is the converted numeric String
	 */
	public static int stringToInt(String score) {
		int sc = 0;
		int index = score.length() - 1;
		double power = 0.0;
		int curr = 0;
		while (index >= 0) {
			curr = (int)(score.charAt(index)) - 48;
			sc += (int)(Math.pow(10, power)) * curr;
			index--;
			power++;
		}
		return sc;
	}
	
	/**
	 * Returns a statement indicating the highest average between the averages of two students
	 * @author Manvika Satish
	 * @param stu1 - first student
	 * @param stu2 - second student
	 * @return a String that is a statement that includes the highest average score and the name of the student that earned it
	 */
	public static String highestAverage(Student stu1, Student stu2) {
		if (stu1.getAvg() > stu2.getAvg()) {
			return stu1.getName() + " has the highest average of " + stu1.getAvg();
		}
		return stu2.getName()+ " has the highest average of " + stu2.getAvg();
	}
	
	/**
	 * Returns a statement indicating the highest score between the highest scores of two students.
	 * @author Manvika Satish
	 * @param stu1 - first student
	 * @param stu2 - second student
	 * @return a String that is a statement that includes the highest score and the name of the student that earned it
	 */
	
	public static String highestScore(Student stu1, Student stu2) {
		if (stu1.getMax() > stu2.getMax()) {
			return stu1.getName() + " has the highest score of " + stu1.getMax();
		}
		return stu2.getName() + " has the highest score of " + stu2.getMax();
	}
	
	/**
	 * Returns a statement indicating the lowest score between the lowest scores of two students.
	 * @author Manvika Satish
	 * @param stu1 - first student
	 * @param stu2 - second student
	 * @return a String that is a statement that includes the lowest score and the name of the student that earned it
	 */
	public static String lowestScore(Student stu1, Student stu2) {
		if (stu1.getMin() < stu2.getMin()) {
			return stu1.getName() + " has the lowest score of " + stu1.getMin();
		}
		return stu2.getName() + " has the lowest score of " + stu2.getMin();
	}
	
	/**
	 * Displays input window with labels and text fields
	 * @author Kaylyn Phan
	 * @param panel - GUI that contains text fields
	 * @param nameField - text field to collect a student's name
	 * @param score1Field - text field to collect a student's first test score
	 * @param score2Field - text field to collect a student's second test score
	 * @param score3Field - text field to collect a student's third test score
	 */
	public static void drawInputWindow(JPanel panel, JTextField nameField,
		JTextField score1Field, JTextField score2Field, JTextField score3Field) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel nameLabel = new JLabel("Enter student name:");
		nameLabel.setForeground(Color.pink);
		nameLabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		panel.add(nameLabel);
		panel.add(nameField);
		
		JLabel scoreLabel = new JLabel("Enter three scores:");
		scoreLabel.setForeground(Color.red);
		scoreLabel.setFont(new Font("Courier", Font.BOLD, 20));
		
	    panel.add(Box.createHorizontalStrut(15)); // a spacer
	    panel.add(scoreLabel);
	    
		panel.add(score1Field);
		panel.add(score2Field);
		panel.add(score3Field);
	}
	
	/**
	 * Displays summary information: each student's name, average score, highest score, and lowest score, and, 
	 * the highest average, highest score and lowest score between the two students
	 * @author - Manvika Satish
	 * @param stud1 - first student
	 * @param stud2 - second student
	 */
	public static void drawSummaryWindow(Student stud1, Student stud2) {
		javax.swing.UIManager.put("OptionPane.messageFont",  new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 20));
		String s1 = (stud1.getName() + "\nAverage: " + stud1.getAvg() + "\nHighest Score: " + stud1.getMax() + "\nLowest Score: " + stud1.getMin());
	    String s2 = (stud2.getName() + "\nAverage: " + stud2.getAvg() + "\nHighest Score: " + stud2.getMax() + "\nLowest Score: " + stud2.getMin());
	    
	    String highestAvg = highestAverage(stud1, stud2);
	    String highestSc = highestScore(stud1, stud2);
	    String lowestSc = lowestScore(stud1, stud2);
	    
	    JOptionPane.showMessageDialog(null, (s1 + "\n" + s2 + "\n\n" + highestAvg + "\n" + highestSc + "\n" + lowestSc), "Summary", 1); 
	}
	
	/**
	 * Collects and stores student information in a new Student object
	 * @author Kaylyn Phan
	 * @param panel - GUI window that contains text fields
	 * @param num - Student number (either a 1 or 2)
	 * @param nameField - text field to collect a student's name
	 * @param score1Field - text field to collect a student's first test score
	 * @param score2Field - text field to collect a student's second test score
	 * @param score3Field - text field to collect a student's third test score
	 * @return a Student with specified name and three test scores
	 */
	public static Student logNewStudent(JPanel panel, int num, JTextField nameField, 
		JTextField score1Field, JTextField score2Field, JTextField score3Field) {
		Student newStudent = new Student();
		nameField.setText("");
	    score1Field.setText("");
	    score2Field.setText("");
	    score3Field.setText("");
	    
	    int result = JOptionPane.showConfirmDialog(null, panel,
		        "Student " + num, JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String name = nameField.getText();
			
			//get scores and convert them from String to int
			int sc1 = stringToInt(score1Field.getText());
			int sc2 = stringToInt(score2Field.getText());
			int sc3 = stringToInt(score3Field.getText());
			
			newStudent = new Student(name, sc1, sc2, sc3);
		}
		return newStudent;
	}
	
	/**
	* Creates a GUI for input, and calls methods to display the input and summary windows
	* @author Manvika Satish
	*/
	public static void inputAndSummaryWindow() {
		JTextField nameField = new JTextField(5);
		JTextField score1Field = new JTextField(5);
	    JTextField score2Field = new JTextField(5);
	    JTextField score3Field = new JTextField(5);
	    
	    JPanel myPanel = new JPanel();
	    drawInputWindow(myPanel, nameField, score1Field, score2Field, score3Field);
	    
	    Student stud1 = logNewStudent(myPanel, 1, nameField, score1Field, score2Field, score3Field);
	    Student stud2 = logNewStudent(myPanel, 2, nameField, score1Field, score2Field, score3Field);
			
		//for debugging
		//System.out.println(stud1.toString());
		//System.out.println(stud2.toString());
		
		drawSummaryWindow(stud1, stud2);	
	}
	
	/**
	 * Calls the inputandSummaryWindow() method to collect and display student information
	 * @author Manvika Satish
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
	    inputAndSummaryWindow();
	}
}
