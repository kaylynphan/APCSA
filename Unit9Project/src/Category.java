/**
 * Category objects store category name and percent weight
 * @author Sriman Gattepalli
 */
class Category {
	
	private final String categoryName;
	private final int percentWeight;
	
	/**
	 * Category constructor
	 * @param n category name
	 * @param w category weight
	 */
	public Category(String n, int w) {
		categoryName = n;
		percentWeight = w;
	}
	
	/**
	 * Getter method for category name
	 * @return category name as string
	 */
	public String getName() {
		return categoryName;
	}
	
	/**
	 * Getter method for category weight
	 * @return category weight as int
	 */
	public int getWeight() {
		return percentWeight;
	}
}