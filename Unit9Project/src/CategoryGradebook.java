import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a Gradebook class that uses weighted points with categories
 * @author Felix Zhu
 */
public class CategoryGradebook extends Gradebook {
	
	private String fileName = "weighted.csv";
	private Category[] allCategories;
	
	/**
	 * CategoryGradebook constructor
	 * @param fileName name of text file with data
	 */
	public CategoryGradebook(String filename) {
		super();
		ArrayList<Student> studentList = super.getStudentList();
		try {
			File f = new File(filename);
			fileName = filename;
			Scanner scan = new Scanner(f);
			if (!scan.hasNext()) {
				System.out.println("Empty data file");
			} else {
				String[] line1 = scan.nextLine().split(",");
				int numCategories = Integer.parseInt(line1[1]);
				allCategories = new Category[numCategories];
				for (int i = 0; i < numCategories; i++) {
					String[] line = scan.nextLine().split(",");
					allCategories[i] = new Category(line[0], Integer.parseInt(line[1]));
				}
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
				String[] astCategories = scan.nextLine().split(",");
				Category[] assignmentCategories = new Category[astCategories.length - 1];
							// -1 because first string is "Category"
				for (int i = 1; i < astCategories.length; i++) {
					String catName = astCategories[i];
					for (Category c : allCategories) {
						if (c.getName().equals(catName)) {
							assignmentCategories[i - 1] = c;
							break;
						}
					}
				}
				while (scan.hasNext()) { // while loop scans for unlimited students
					String[] line = scan.nextLine().split(",");
					Student s = new Student(Integer.parseInt(line[0]), allCategories);
					for (int i = 0; i < assignmentNames.length; i++) {
						s.addAssignment(new CategoryAssignment(assignmentNames[i], 
								possiblePoints[i], Integer.parseInt(line[i + 1]), assignmentCategories[i]));
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
		double[] catEarnedPoints = new double[allCategories.length];
		int[] catPossiblePoints = new int[allCategories.length];
		ArrayList<Assignment> assignmentList = super.getStudent(studentID).getAssignmentList();
		for (Assignment a : assignmentList) {
			String cat = ((CategoryAssignment) a).getCategory().getName();
			int catIndex = -1;
			for (int i = 0; i < allCategories.length; i++) {
				if (allCategories[i].getName().equals(cat)) {
					catIndex = i;
					break;
				}
			}
			catEarnedPoints[catIndex] += a.getEarnedPoints();
			catPossiblePoints[catIndex] += a.getPossiblePoints();
		}
		double[] categoryGrades = new double[allCategories.length];
		double weightSum = 0;
		for (int i = 0; i < allCategories.length; i++) {
			if (catPossiblePoints[i] > 0) {
				categoryGrades[i] = catEarnedPoints[i] / catPossiblePoints[i];
				weightSum += allCategories[i].getWeight();
			}
		}
		double grade = 0;
		for (int i = 0; i < allCategories.length; i++) {
			grade += categoryGrades[i] * (allCategories[i].getWeight() / weightSum);
		}
		return grade * 100;
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
	 * Drops the assignment that will improve the grade the most in a given category for a given student
	 * @param studentID ID of student who is getting an assignment dropped
	 * @param categoryName name of category that will have an assignment dropped
	 */
	public void dropLowestInCategory(int studentID, String categoryName) {
		Student s = super.getStudent(studentID);
		if (s == null) {
			return;
		}
		double maxGrade = calculateGrade(studentID);
		int dropIndex = -1;
		ArrayList<Assignment> list = s.getAssignmentList();
		for (int i = 0; i < list.size(); i++) {
			Assignment a = list.get(i);
			if (((CategoryAssignment) a).getCategory().getName().equals(categoryName)) {
				list.remove(i);
				if (calculateGrade(studentID) > maxGrade) {
					maxGrade = calculateGrade(studentID);
					dropIndex = i;
				}
				list.add(i, a);
			}
		}
		list.remove(dropIndex);
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
			System.out.println(a.getName() + " (" + ((CategoryAssignment) a).getCategory().getName() + "):\t" + 
								a.getEarnedPoints() + "/" + a.getPossiblePoints() + " = " + astGrade);
		}
		String grade = percentString(calculateGrade(studentID));
		System.out.println("Overall grade: " + grade);
	}
	
	/**
	 * Method that prints the gradebook in a readable format
	 */
	public void printGradebook() {
		System.out.println("Category Gradebook");
		System.out.println(String.format("%-10s%-10s", "Category", "Percent"));
		for (Category c : allCategories) {
			System.out.println(String.format("%-10s%-10s", c.getName(), c.getWeight()));
		}
		System.out.println(String.format("%-10s%-10s", "Total", 100));
		// end of category key
		System.out.print(String.format("%-20s", "Assignment name"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", a.getName()));
		}
		System.out.println(String.format("%-10s", "Grade"));
		// end of assignment names
		System.out.print(String.format("%-20s", "Possible points"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", a.getPossiblePoints()));
		}
		System.out.println();
		// end of possible points
		System.out.print(String.format("%-20s", "Category"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", ((CategoryAssignment) a).getCategory().getName()));
		}
		System.out.println();
		// end of categories
		for (Student s : super.getStudentList()) {
			System.out.print(String.format("%-20s", "Student " + s.getID()));
			for (Assignment a : s.getAssignmentList()) {
				System.out.print(String.format("%-8s", a.getEarnedPoints()));
			}
			System.out.println(String.format("%-10s", percentString(calculateGrade(s.getID()))));
		}
		// end of all students
		System.out.print(String.format("%-20s", "Average percent"));
		for (Assignment a : super.getStudent(1).getAssignmentList()) {
			System.out.print(String.format("%-8s", percentString(super.calcAvgGradeForOneAssignment(a.getName()))));
		}
		System.out.println(String.format("%-10s", percentString(calcAvgGrade())));
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
			content += "Number of categories," + allCategories.length + "\n";
			for (Category c : allCategories) {
				content += c.getName() + "," + c.getWeight() + "\n";
			}
			content += "Assignment name,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += a.getName() + ",";
			}
			content += "Grade\nPossible points,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += a.getPossiblePoints() + ",";
			}
			content += "\nCategory,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += ((CategoryAssignment) a).getCategory().getName() + ",";
			}
			content += "\n";
			for (Student s : super.getStudentList()) {
				content += "Student " + s.getID() + ",";
				for (Assignment a : s.getAssignmentList()) {
					content += a.getEarnedPoints() + ",";
				}
				content += percentString(calculateGrade(s.getID())) + "\n";
			}
			content += "Average percent,";
			for (Assignment a : super.getStudent(1).getAssignmentList()) {
				content += String.format("%.1f", super.calcAvgGradeForOneAssignment(a.getName())) + "%,";
			}
			content += percentString(calcAvgGrade());
			fw.write(content);
			fw.close();
			System.out.println("Updated csv file '" + newFileName + "' has been created.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isCategory(String categoryName) {
		for (Category c: allCategories) {
			if (c.getName().contentEquals(categoryName)) {
				return true;
			}
		}
		return false;
	}
}