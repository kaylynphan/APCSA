import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a Gradebook class that uses unweighted points
 * @author Felix Zhu
 */
public class TotalPointsGradebook extends Gradebook {
	
	private String fileName = "unweighted.csv";
	
	/**
	 * TotalPointsGradebook constructor
	 * @param fileName name of text file with data
	 */
	public TotalPointsGradebook(String filename) {
		super();
		ArrayList<Student> studentList = super.getStudentList();
		try {
			File f = new File(filename);
			fileName = filename;
			Scanner scan = new Scanner(f);
			if (!scan.hasNext()) {
				System.out.println("Empty data file");
			} else {
				String[] astNames = scan.nextLine().split(",");
				String[] assignmentNames = new String[astNames.length - 1];
								// -1 because first string is "Assignment"
				for (int i = 1; i < astNames.length; i++) {
					assignmentNames[i - 1] = astNames[i];
				}
				String[] posPoints = scan.nextLine().split(",");
				int[] possiblePoints = new int[posPoints.length - 1];
								// -1 because first string is "Possible Points"
				for (int i = 1; i < posPoints.length; i++) {
					possiblePoints[i - 1] = Integer.parseInt(posPoints[i]);
				}
				while (scan.hasNext()) { // while loop scans for unlimited students
					String[] line = scan.nextLine().split(",");
					Student s = new Student(Integer.parseInt(line[0]));
					for (int i = 0; i < assignmentNames.length; i++) {
						s.addAssignment(new Assignment(assignmentNames[i], 
										possiblePoints[i], Integer.parseInt(line[i + 1])));
					}
					studentList.add(s);
				}
			}
		} catch (IOException e) {
			System.out.println("Invalid file name");
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Calculates and returns the percent grade for a given student
	 * @param studentID ID of a student whose grade is being calculated
	 * @return grade as double
	 */
	public double calculateGrade(int studentID) {
		int totalPoints = super.calcTotalPossiblePoints();
		int earnedPoints = calcTotalEarnedPointsForOneStudent(studentID);
		return (double) earnedPoints / totalPoints * 100;
	}
	
	/**
	 * Calculates and returns the average total earned points for all students in this gradebook
	 * @return average total earned points for entire Gradebook
	 */
	public double calcAvgTotalEarnedPoints() {
		double sum = 0;
		for (Student s : super.getStudentList()) {
			sum += calcTotalEarnedPointsForOneStudent(s.getID());
		}
		return sum / super.getStudentList().size();
	}
	
	/**
	 * Calculates and returns the average grade for all students in this gradebook
	 * @return average grade for entire gradebook
	 */
	public double calcAvgGrade() {
		double sum = 0;
		for (Student s : super.getStudentList()) {
			sum += calculateGrade(s.getID());
		}
		return sum / super.getStudentList().size();
	}
	
	/**
	 * Calculates and returns the total earned points by given student
	 * @return total earned points of given student
	 */
	public int calcTotalEarnedPointsForOneStudent(int studentID) {
		// this calculation's numbers are only shown for unweighted gradebook in sample data,
		// which is why this method doesn't belong in the parent class
		Student s = super.getStudent(studentID);
		if (s == null) {
			return -1;
		}
		int earnedPoints = 0;
		for (Assignment a : s.getAssignmentList()) {
			earnedPoints += a.getEarnedPoints();
		}
		return earnedPoints;
	}
	
	/**
	 * Helper method to convert a double to a readable percentage with 2 decimal places
	 * @param value double to be converted
	 * @return String version of percentage with 2 decimal places
	 */
	private String percentString(double value) {
		return String.format("%.2f", value) + "%";
	}
	
	/**
	 * Prints the given assignment score and grade for given student
	 * @param studentID ID of a student
	 * @param astName name of assignment
	 */
	public void printScoresForOneStudent(int studentID) {
		Student s = super.getStudent(studentID);
		if (s == null) {
			return;
		}
		ArrayList<Assignment> list = s.getAssignmentList();
		System.out.println("Scores for student " + studentID + ":");
		for (Assignment a : list) {
			String astGrade = percentString(100.0 * a.getEarnedPoints() / a.getPossiblePoints());
			System.out.println(a.getName() + ":\t" + a.getEarnedPoints() + "/" + 
								a.getPossiblePoints() + " = " + astGrade);
		}
		String grade = percentString(calculateGrade(studentID));
		System.out.println("Overall grade: " + calcTotalEarnedPointsForOneStudent(studentID) + "/" + 
							super.calcTotalPossiblePoints() + " = " + grade);
	}
	
	/**
	 * Method that prints the gradebook in a readable format
	 */
	public void printGradebook() {
		System.out.println("Total Points Gradebook");
		System.out.print(String.format("%-20s", "Assignment name"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", a.getName()));
		}
		System.out.println(String.format("%-10s%-10s", "Total", "Grade"));
		// end of assignment names
		System.out.print(String.format("%-20s", "Possible points"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", a.getPossiblePoints()));
		}
		System.out.println(String.format("%-10s", super.calcTotalPossiblePoints()));
		// end of possible points
		for (Student s : super.getStudentList()) {
			System.out.print(String.format("%-20s", "Student " + s.getID()));
			for (Assignment a : s.getAssignmentList()) {
				System.out.print(String.format("%-8s", a.getEarnedPoints()));
			}
			System.out.println(String.format("%-10s%-10s", calcTotalEarnedPointsForOneStudent(s.getID()), 
											percentString(calculateGrade(s.getID()))));
		}
		// end of all students
		System.out.print(String.format("%-20s", "Average percent"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", percentString(super.calcAvgGradeForOneAssignment(a.getName()))));
		}
		System.out.println(String.format("%-10s%-10s", calcAvgTotalEarnedPoints(), percentString(calcAvgGrade())));
		// end of averages
		super.checkForExtraCredit();
	}
	
	/**
	 * Creates a new, updated .csv file with calculated averages
	 */
	public void createUpdatedCSVFile() {
		try {
			String newFileName = "";
			String[] split = fileName.split("\\.");
			newFileName += split[0] + "_updated.csv";
			File f = new File(newFileName);
			f.createNewFile();
			FileWriter fw = new FileWriter(f);
			String content = "";
			content += "Assignment name,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += a.getName() + ",";
			}
			content += "Total,Grade\nPossible points,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += a.getPossiblePoints() + ",";
			}
			content += super.calcTotalPossiblePoints() + "\n";
			for (Student s : super.getStudentList()) {
				content += "Student " + s.getID() + ",";
				for (Assignment a : s.getAssignmentList()) {
					content += a.getEarnedPoints() + ",";
				}
				
				content += calcTotalEarnedPointsForOneStudent(s.getID()) + "," +
							percentString(calculateGrade(s.getID())) + "\n";
			}
			content += "Average percent,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += String.format("%.1f", super.calcAvgGradeForOneAssignment(a.getName())) + "%,";
			}
			content += calcAvgTotalEarnedPoints() + "," + percentString(calcAvgGrade());
			fw.write(content);
			fw.close();
			System.out.println("Updated csv file '" + newFileName + "' has been created.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}