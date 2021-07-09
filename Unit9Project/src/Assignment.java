/**
 * Assignment class keeps track of information for Assignment objects
 * @author Sriman Gattepalli
 */
public class Assignment {
	
	private String name;
	private int possiblePoints;
	private int earnedPoints;
	
	/**
	 * Assignment constructor
	 * @param n - Name of assignment
	 * @param p - Number of possible points
	 * @param e - Number of points earned
	 */
	public Assignment(String n, int p, int e) {
		name = n;
		possiblePoints = p;
		earnedPoints = e;
	}
	
    /**
	 * Getter method for name of assignment
	 * @return name of assignment
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for possible points
	 * @return number of possible points
	 */
	public int getPossiblePoints(){
		return possiblePoints;
	}
	
	/**
	 * Getter method for earned points
	 * @return - Number of points earned
	 */
	public int getEarnedPoints() {
		return earnedPoints;
	}
}

