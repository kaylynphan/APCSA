/*
 * Seona Magdum, Kaylyn Phan, Manvika Satish
 * AP CSA
 * Period 3
 * 3 December 2020
 */

/**
 * The GameSimulator class runs a game simulation based on the Monty Hall Problem.
 * @author Kaylyn Phan and Manvika Satish
 * @since 3 December 2020
 */
public class GameSimulator {
	//private data fields
	private int numIterations;
	private int numWinsWithInitialPick;
	private int numWinsWithChangePick;
	
	/**
	 * Constructor that creates a GameSimulator intended to run a specified number of times.
	 * @author Manvika Satish
	 * @param numIterations - number of times the simulation will run
	 */
	public GameSimulator(int numIterations)
	{
		this.numIterations = numIterations;
		numWinsWithInitialPick = 0;
		numWinsWithChangePick = 0;
	}
	
	/**
	 * Accessor method for numIterations
	 * @author Manvika Satish
	 * @return the number of times the simulation will run
	 */
	public int getNumIterations()
	{
		return numIterations;
	}
	
	/**
	 * Accessor method for numWinsWithInitialPick
	 * @author Manvika Satish
	 * @return the number of times the simulation wins without changing its chosen door.
	 */
	public int getNumWinsWithInitialPick()
	{
		return numWinsWithInitialPick;
	}
	
	/**
	 * Accessor method for numWinsWithChangePick
	 * @author Manvika Satish
	 * @return the number of times the simulation wins after changing its chosen door.
	 */
	public int getNumWinsWithChangePick()
	{
		return numWinsWithChangePick;
	}
	
	/**
	 * Method that calculates and returns the winning probability as a percent.
	 * @author Kaylyn Phan
	 * @param numWins - the number of wins
	 * @param numTrials - the total number of trials
	 * @return the probability of winning
	 */
	public double getProbability(int numWins, int numTrials) {
		return (double)numWins / numTrials * 100;
	}
	
	/**
	 * Method that simulates one Monty Hall game and returns the result.
	 * @author Kaylyn Phan
	 * @param changePick - true if the player chooses to change their door, false otherwise
	 * @return true if the player wins the game, false otherwise
	 */
	private boolean runOneSimulation(boolean changePick)
	{
		Game game = new Game();
		GamePlayer player = new GamePlayer();
		int initialPick = player.getInitialPick();
		int goatDoor = game.getRandomGoatDoor(initialPick);
		int finalPick = player.getFinalPick(goatDoor, changePick);
		boolean win = game.pickDoor(finalPick);
		return win;
	}

	/**
	 * Method that runs the simulation.
	 * @author Kaylyn Phan
	 */
	public void run()
	{
		int numTimesChangePick = (int)(Math.random()*999) + 1;
		int numTimesKeepInitialPick = 1000 - numTimesChangePick;
		

		for (int i = 0; i < numTimesChangePick; i++) {
			boolean win = runOneSimulation(true);
			if (win) {
				numWinsWithChangePick++;
			}
		}
		
		for (int i = 0; i < numTimesKeepInitialPick; i++) {
			boolean win = runOneSimulation(false);
			if (win) {
				numWinsWithInitialPick++;
			}
		}
		
		System.out.println("Out of 1000 games: ");
		System.out.println("We did not change our pick " + numTimesKeepInitialPick + " times");
		System.out.println("We changed our pick " + numTimesChangePick + " times");
		System.out.println("Number of wins with Inital Pick: " + numWinsWithInitialPick);
		System.out.println("Number of wins with Change Pick: " + numWinsWithChangePick);
		System.out.printf("Probability of winning with initial pick: %.2f percent\n", getProbability(numWinsWithInitialPick, numTimesKeepInitialPick));
		System.out.printf("Probability of winning with change pick: %.2f percent\n", getProbability(numWinsWithChangePick, numTimesChangePick));
		
		
	}
}
