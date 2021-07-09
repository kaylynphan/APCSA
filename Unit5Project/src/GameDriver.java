/*
 * Seona Magdum, Kaylyn Phan, Manvika Satish
 * AP CSA
 * Period 3
 * 3 December 2020
 */

import java.util.Scanner; 

/**
 * The GameDriver class allows the user to choose between running a game or running a simulation based on the Monty Hall Problem.
 * @author Seona Magdum, Kaylyn Phan, Manvika Satish
 * @since 3 December 2020
 */
public class GameDriver {
	//private data fields
	private static int numGames;
	private static int numWins;
	private static int numLosses;
	
	/**
	 * Method that runs a Monty Hall Game Simulator
	 * @author Seona Magdum
	 */
	public static void runSimulation()
	{
		GameSimulator gameSimulator = new GameSimulator(1000);
		gameSimulator.run();
	}
	
	/**
	 * Method that runs a single round of a Monty Hall Game
	 * @author Kaylyn Phan
	 * @param scanner - Scanner object
	 */
	public static void playOneGame(Scanner scanner)
	{
		Game game = new Game();
		System.out.print("Pick a door between 1 to 3: ");
		int initialPick = scanner.nextInt();
		if(initialPick > 3 || initialPick < 1)
		{
			System.out.println("Invalid choice, quitting game");
			return;
		}
		int goatDoor = game.getRandomGoatDoor(initialPick);
		System.out.println("One of the goats are behind Door: " + goatDoor);
		System.out.print("Do you want to change the door? Enter 1 to change or 2 to keep your initial choice: ");
		int changePick = scanner.nextInt();
		int finalPick = initialPick;
		if (changePick == 1)
		{
			finalPick = 6 - initialPick - goatDoor;
		}
		boolean win = game.pickDoor(finalPick);
		if(win)
		{
			System.out.println("You win! Behind Door " + finalPick + " was the car.");
			numWins++;
		}
		else
		{
			System.out.println("You lost. Behind Door " + finalPick + " was a goat.");
			numLosses++;
		}
		numGames++;
		System.out.println("Number of games played: " + numGames);
		System.out.println("Number of wins: " + numWins);
		System.out.println("Number of losses: " + numLosses);
	}
	
	/**
	 * Method that continues playing Monty Hall games until the user wishes to stop
	 * @author Kaylyn Phan
	 * @param scanner - Scanner object
	 */
	public static void playGames(Scanner scanner)
	{
		while(true)
		{
			playOneGame(scanner);
			System.out.print("Do you want to continue playing? Enter 1 to continue or 2 to stop: ");
			int play = scanner.nextInt();
			if(play != 1)
			{
				System.out.println("Thanks for playing.");
				return;
			}	
		}
	}
	
	/**
	 * Main method prompts user to choose between playing a game or running a simulation. Based on the selection, the method runs the game or simulation.
	 * @author Manvika Satish
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Do you want to (1) play game or (2) run simulation? : " );
		int choice = scanner.nextInt();
		if(choice == 1)
		{
			playGames(scanner);
		}
		else if (choice == 2)
		{
			runSimulation();
		}
		else
		{
			System.out.println("Invalid choice, quitting game");
		}
	}
}
