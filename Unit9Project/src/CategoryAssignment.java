/**
 * CategoryAssignment class inherits from Assignment to include a category field
 * @author Sriman Gattepalli
 */
public class CategoryAssignment extends Assignment {
	
	private Category category;
	
	/**
	 * CategoryAssignment constructor
	 * @param n - Name of assignment
	 * @param p - Number of possible points
	 * @param e - Number of points earned
	 * @param cat - Category of assignment
	 */
	public CategoryAssignment(String n, int p, int e, Category cat) {
		super(n, p, e);
		category = cat;
	}
	
	/**
	 * Getter method for category of assignment
	 * @return category of assignment
	 */
	public Category getCategory() {
		return category;
	}
}
