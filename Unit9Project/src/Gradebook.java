import java.util.ArrayList;

/**
 * The Gradebook class defines a list of Students that each have their own list of Assignments
 * It contains methods for accessing fields, making calculations, and checking for extra credit
 * @author Kaylyn Phan
 */
public class Gradebook {
	
	private ArrayList<Student> studentList;
	
	/**
	 * Constructor makes an empty Gradebook that initializes the fields
	 */
	public Gradebook() {
		studentList = new ArrayList<Student>();
	}
	
	/**
	 * Getter method that returns the list of Students
	 * @return the student list in the form of an ArrayList
	 */
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	/**
	 * Getter method to help convert from int ID to Student object
	 * @param studentID ID of student to fetch
	 * @return student with given ID
	 */
	public Student getStudent(int studentID) {
		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			if (s.getID() == studentID) {
				return s;
			}
		}
		System.out.println("Could not find student with ID " + studentID + ".");
		return null;
	}
	
	/**
	 * Adds a Student to the studentList
	 * @param s Student to add
	 */
	public void addStudent(Student s) {
		studentList.add(s);
	}
	
	/**
	 * Removes the Assignment located in the gradebook at the given index
	 * @param id ID of student to remove
	 */
	public void removeStudent(int id) {
		Student s = getStudent(id);
		if (s != null) {
			studentList.remove(s);
			System.out.println("Student " + id + " removed successfully.");
		}
	}
	
	/**
	 * Calculates and returns the total possible points of this gradebook
	 * @return total possible points as int
	 */
	public int calcTotalPossiblePoints() {
		int sum = 0;
		for (Assignment a : getStudent(1).getAssignmentList()) {
			sum += a.getPossiblePoints();
		}
		return sum;
	}
	
	/**
	 * Calculates and returns the average earned points by students for a given assignment
	 * @param assignmentName name of assignment to find average for
	 * @return average earned points for given assignment
	 */
	public double calcAvgGradeForOneAssignment(String assignmentName) {
		int sum = 0;
		int possiblePoints = 0;
		for (Student s : studentList) {
			for (Assignment a : s.getAssignmentList()) {
				if (a.getName().equals(assignmentName)) {
					sum += a.getEarnedPoints();
					possiblePoints = a.getPossiblePoints();
					break;
				}
			}
		}
		return sum * 100.0 / studentList.size() / possiblePoints;
	}
	
	public void dropAssignment(String astName) {
		boolean found = false;
		for (Student s: studentList) {
			ArrayList<Assignment> assignmentList = s.getAssignmentList();
			for (int i = 0; i < assignmentList.size(); i++) {
				if (assignmentList.get(i).getName().contentEquals(astName)) {
					assignmentList.remove(i);
					found = true;
				}
			}
		}
		if (found) {
			System.out.println("Assignment " + astName + " has been successfully dropped.");
		} else {
			System.out.println("Assignment " + astName + " could not be found.");
		}
	}
	
	/**
	 * Checks for any >100% scores across the entire gradebook and prints a warning for each found
	 */
	public void checkForExtraCredit() {
		boolean found = false;
		for (Student s : studentList) {
			for (Assignment a : s.getAssignmentList()) {
				if (a.getEarnedPoints() > a.getPossiblePoints()) {
					System.out.println("Extra credit notice: student " + s.getID() + " scored " + 
									a.getEarnedPoints() + "/" + a.getPossiblePoints() + " on " + a.getName());
					found = true;
				}
			}
		}
		if (!found) {
			System.out.println("No extra credit found.");
		}
	}
	
	public void printListOfStudents() {
		for (Student s : studentList) {
			System.out.println(String.format("%-20s", "Student " + s.getID()));
		}
	}
	
	public ArrayList<Assignment> getListOfAssignments() {
		return studentList.get(0).getAssignmentList();
	}
	
	public void printGradebook() {
	}
	
	public void createUpdatedCSVFile() {
	}

	public void printScoresForOneStudent(int input) {
		
	}
}
