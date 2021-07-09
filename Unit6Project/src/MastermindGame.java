import java.util.Scanner;

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
 * Game class that contains attributes and methods to run the mastermind game
 * Class takes in user input as a string, converts it into an integer array 
 * Then compares and returns feedback based on comparison
 * 
 * @author Annabel Truong, Kaylyn Phan, Averi Tanlimco
 *
 */
public class MastermindGame {
	private int[] secretArray = new int[5];
	private int[] guessArray = new int[5];
	private int numTries = 0;
	private int numFound = 0;
	private int numPosition = 0;
	private Scanner scan = new Scanner(System.in);

	/**
	 * get method to get the secret array 
	 * written by Annabel
	 * 
	 * @return secretArray - array with digits of secret number
	 * @author Annabel Truong
	 */
	public int[] getSecretArray() {
		return secretArray;
	}

	/**
	 * get method to get the user's guess arrays 
	 * written by Annabel
	 * 
	 * @return guessArray - array with digits of guess number
	 * @author Annabel Truong
	 */
	public int[] getGuessArray() {
		return guessArray;
	}

	/**
	 * get method to get the number of tries 
	 * written by Annabel
	 * 
	 * @return numTries - number of tries
	 * @author Annabel Truong
	 */
	public int getNumTries() {
		return numTries;
	}

	/**
	 * get method to get the number found in the secret array and guess array
	 * written by Annabel
	 * 
	 * @return numFound - number of digits found in both arrays
	 * @author Annabel Truong
	 */
	public int getNumFound() {
		return numFound;
	}

	/**
	 * get method to get the position of the number 
	 * written by Annabel
	 * 
	 * @return numPosition - number of guess digits that match position in secret
	 * @author Annabel Truong
	 */
	public int getNumPosition() {
		return numPosition;
	}

	/**
	 * sets the secret array to the given array 
	 * written by Annabel
	 * 
	 * @param array - array of integers
	 * @author Annabel Truong
	 */
	public void setSecretArray(int[] array) {
		secretArray = array;
	}

	/**
	 * sets the guess array to the given array 
	 * written by Annabel
	 * 
	 * @param array - array of integers
	 * @author Annabel Truong
	 */
	public void setGuessArray(int[] array) {
		guessArray = array;
	}

	/**
	 * sets the number of tries to the given num 
	 * written by Annabel
	 * 
	 * @param num - integer num that represents the number of tries
	 * @author Annabel Truong
	 */
	public void setNumTries(int num) {
		numTries = num;
	}

	/**
	 * sets the number found to the given num 
	 * written by Annabel
	 * 
	 * @param num - integer num that is the number found
	 * @author Annabel Truong
	 */
	public void setNumFound(int num) {
		numFound = num;
	}

	/**
	 * sets the position of the number to the given num 
	 * written by Annabel
	 * 
	 * @param num - integer num that is the index of the num
	 * @author Annabel Truong
	 */
	public void setNumPosition(int num) {
		numPosition = num;
	}

	/**
	 * Takes in user guess and converts it to numbers to store in an int array 
	 * Error check for incorrect length (not 5 digits) 
	 * Checks for digits found and correct position 
	 * Increments try number, digits found, correct position 
	 * Written by Averi 
	 * PRECONDITION: secretArray contains unique digits and no non-numbers
	 * 
	 * @author Averi Tanlimco
	 */
	public void checkNum() {
		// Takes in the guess and checks for length of 5
		String guess = takeGuessInput();
		guess = checkLength(guess);
		// Since length is 5, String gets converted to int array
		guessArray = stringToIntArray(guess);
		// Increments correct position and found digits
		checkPos();
		checkFound();
		// Increments try number after a valid guess
		numTries++;
	}

	/**
	 * Takes in user's input (guess) 
	 * Written by Averi
	 * 
	 * @return s - user guess in form of a String
	 * @author Averi Tanlimco
	 */
	public String takeGuessInput() {
		// Takes in user guess
		System.out.print("\nEnter your 5 digit guess: ");
		String s = scan.next();
		return s;
	}

	/**
	 * Checks to see if the length of the guess is 5 
	 * If it is not, then it prompts the user until the user inputs a valid guess 
	 * Written by Averi
	 * 
	 * @param s - User input to be checked
	 * @return initGuess - valid guess from user
	 * @author Averi Tanlimco
	 */
	public String checkLength(String s) {
		// Checks to see if the length is 5
		String initGuess = s;
		while (initGuess.length() != 5) {
			if (initGuess.length() != 5) {
				System.out.println("'" + initGuess + "' must have a length of 5 \n");
			}
			initGuess = takeGuessInput();
		}
		return initGuess;
	}

	/**
	 * Increments numPosition if guessArray and secretArray have matching numbers at the same index 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void checkPos() {
		// Increments position counter when guess position and number is correct
		for (int i = 0; i < guessArray.length; i++) {
			if (guessArray[i] == secretArray[i]) {
				numPosition++;
			}
		}
	}

	/**
	 * Checks to see if a number in the secretArray is also in the guessArray 
	 * If it is, set isFound to true If isFound is true, increment numFound 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void checkFound() {
		boolean isFound = false;
		// Increments found counter when guess contains a number in secretArray
		for (int k = 0; k < secretArray.length; k++) {
			for (int j = 0; j < guessArray.length; j++) {
				if (secretArray[k] == guessArray[j]) {
					isFound = true;
				}
			}
			if (isFound == true) {
				numFound++;
			}
			isFound = false;
		}
	}

	/**
	 * Method to print out all feedback 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void giveFeedback() {
		// Checks and prints all of the feedback
		checkFeedback();
		printFeedback();
		scan.close();
	}

	/**
	 * Formats the feedback for checkFeedback and printFeedback 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void feedbackFormatting() {
		// Formats output for each guess and outcome
		String feedbackTry = "\tTry number:" + String.format("%8s", numTries);
		String feedbackFound = "\tDigits found:" + String.format("%6s", numFound);
		String feedbackPos = "\tCorrect position:" + String.format("%2s", numPosition);
		String feedback = feedbackTry + "\n" + feedbackFound + "\n" + feedbackPos;
		System.out.println(feedback);
	}

	/**
	 * Prints try number, digits found, and correct position after calling checkNum
	 * Stops after 32 tries 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void checkFeedback() {
		// Checks user's guess
		checkNum();
		// Repeats until user guess matches or 32 tries are used up
		while ((numFound != 5 || numPosition != 5) && numTries != 32) {
			feedbackFormatting();
			numFound = 0;
			numPosition = 0;
			checkNum();
		}
	}

	/**
	 * Prints success message when all numbers are found and all positions are correct 
	 * Prints "ran out of tries" message when user does not find secret number after 32 tries 
	 * Written by Averi
	 * 
	 * @author Averi Tanlimco
	 */
	public void printFeedback() {
		// Prints winning or losing statement (if user runs out of guesses)
		if (numTries == 32) {
			if (numFound == 5 || numPosition == 5) {
				feedbackFormatting();
				System.out.println("\nYou won in " + numTries + " tries!");
			} else {
				feedbackFormatting();
				System.out.println("\nYou ran out of tries.");
			}
		} else if (numTries > 1 && numTries < 32) {
			feedbackFormatting();
			System.out.println("\nYou won in " + numTries + " tries!");
		} else {
			feedbackFormatting();
			System.out.println("\nYou won in " + numTries + " try!");
		}
	}

	/**
	 * Method that returns a generated secret number. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @return an array of five unique positive ints that make up the secret number
	 */
	public int[] generateSecretNum() {
		int[] result = new int[5];
		System.out.print("Select your own valid secret number or enter -1: ");
		String input = scan.next();
		// repeatedly promts user for input until it is valid
		while (!validInput(input)) {
			System.out.println("'" + input + "' is not a valid secret number");
			System.out.print("Select your own valid secret number or enter -1: ");
			input = scan.next();
		}
		if (input.equals("-1")) {
			result = generateRandomNum();
		} else {
			result = stringToIntArray(input);
		}
		return result;
	}

	/**
	 * Method that checks if an input is either "-1" or a String of five unique digits. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @param input - user input
	 * @return true if the input is valid, false otherwise
	 */
	public boolean validInput(String input) {
		if (input.contentEquals("-1")) {
			return true;
		}
		if (input.length() != 5) {
			return false;
		}
		if (!isNumericString(input)) {
			return false;
		}
		int[] inputArray = stringToIntArray(input);
		if (!uniqueDigits(inputArray)) {
			return false;
		}
		return true;
	}

	/**
	 * Method that checks if a String is comprised only of numerals. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @param input - user input
	 * @return true if the String is numeric, false otherwise
	 */
	public boolean isNumericString(String input) {
		boolean numeric = true;
		for (int i = 0; i < input.length(); i++) {
			// using ASCII values: 47 is '0' and 58 is '9'
			if (!(input.charAt(i) > 47 && input.charAt(i) < 58)) {
				numeric = false;
			}
		}
		return numeric;
	}

	/**
	 * Method that converts a numeric String into an int array. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @param input - user input
	 * @return the int array representation of the numeric string Precondition: the
	 *         String is made up of five numeric digits
	 */
	public int[] stringToIntArray(String input) {
		int[] result = new int[5];
		for (int i = 0; i < 5; i++) {
			// convert char to int
			result[i] = input.charAt(i) - 48;
		}
		return result;
	}

	/**
	 * Method that checks if an int array is filled with unique values. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @param input - array to check
	 * @return true if the int array has unique false, false otherwise
	 */
	public boolean uniqueDigits(int[] input) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length && j != i; j++) {
				if (input[i] == input[j]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Method that generates and returns an array of unique digits to be used as a secret number. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @return an array of five unique digits
	 */
	public int[] generateRandomNum() {
		// the array cannot be filled with default zeroes, or else it would be
		// impossible for a zero to be in the secret number
		// therefore, fill the array with -1
		int[] num = { -1, -1, -1, -1, -1 };
		for (int i = 0; i < 5; i++) {
			num[i] = generateRandomDigit(num);
		}
		return num;
	}

	/**
	 * Method that generates and returns a random and unique digit. 
	 * Written by Kaylyn
	 * 
	 * @author Kaylyn Phan
	 * @param avoid - an array of digits that the new digit must avoid in order to
	 *              be unique
	 * @return a random and unique int between 0 and 9, inclusive
	 */
	public int generateRandomDigit(int[] avoid) {
		int digit = 0;
		boolean unique = false;
		// repeatedly generates a random digit until the digit is unique to all those
		// before it
		while (!unique) {
			digit = (int) (Math.random() * 10);
			unique = true;
			// linear search for the digit
			for (int i = 0; i < avoid.length; i++) {
				if (avoid[i] == digit) {
					unique = false;
				}
			}
		}
		return digit;
	}

	/**
	 * Acts as the main method for the MastermindGame() class 
	 * Takes in initial user input and runs game according to user 
	 * Written by Annabel
	 * 
	 * @author Annabel Truong
	 */
	public void runMastermind() {
		//Runs the mastermind game
		System.out.println("Enter the secret number as a 5 digit number where all digits are unique, or enter -1 to have this program select the secret number.\n");
		secretArray = generateSecretNum();
		giveFeedback();
	}
}
