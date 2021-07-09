/**
 * Last name: Truong
 * First name: Annabel
 * Student ID: 12091566
 * Period: 3
 * 
 * Last name: Phan
 * First name: Kaylyn
 * Student ID: 12091327
 * Period: 3
 * 
 * Last name: Tanlimco
 * First name: Averi
 * Student ID: 12091267
 * Period: 3
 * 
 * Driver class that runs the MastermindGame class
 * Creates MastermindGame object and runs runMastermind using that object
 * @author Annabel Truong, Kaylyn Phan, Averi Tanlimco
 *
 */
public class MastermindDriver {

	/**
	 * Main method that creates MastermindGame object
	 * Calls MastermindGame's runMastermind method using the object
	 * Written by Annabel
	 * 
	 * @param args - argument for main
	 * @author Annabel Truong
	 */
	public static void main(String[] args) {
		//Creates MastermindGame object to call runMastermind
		MastermindGame game = new MastermindGame();
		game.runMastermind();
	}

}

