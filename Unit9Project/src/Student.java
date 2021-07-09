import java.util.ArrayList;

/**
 * Student class keeps track of an ID, assignment list, and grade
 * @author Sriman Gattepalli
 */
public class Student {

	private final int id;
	private ArrayList<Assignment> assignmentList;
	private Category[] categories;  // only needed for Students with CategoryAssignments
	
	/**
	 * Constructor initializes the ID number and Assignment ArrayList
	 * @param i ID number as int
	 */
	public Student(int i) {
		id = i;
		assignmentList = new ArrayList<Assignment>();
	}
	
	/**
	 * Constructor initializes the ID number, Assignment ArrayList, and categories array
	 * @param i ID number as int
	 */
	public Student(int i, Category[] cats) {
		id = i;
		assignmentList = new ArrayList<Assignment>();
		categories = cats;
	}
	
	/**
	 * Add an Assignment (or CategoryAssignment) to the assignment list
	 * @param a Assignment to be added
	 */
	public void addAssignment(Assignment a) {
		assignmentList.add(a);
	}
	
	/**
	 * Getter method for ID number
	 * @return ID number as int
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter method for assignment list
	 * @return ArrayList of Assignments
	 */
	public ArrayList<Assignment> getAssignmentList() {
		return assignmentList;
	}
}