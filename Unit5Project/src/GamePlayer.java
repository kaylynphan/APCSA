/*
 * Seona Magdum, Kaylyn Phan, Manvika Satish
 * AP CSA
 * Period 3
 * 3 December 2020
 */

/**
 * The GamePlayer class supports an interactive game based on the Monty Hall Problem.
 * @author Seona Magdum
 * @since 3 December 2020
 */
public class GamePlayer {
	//private data fields
	private int initialPick;

	/**
	 * Constructor that creates a new GamePlayer and makes a random initial pick.
	 */
	public GamePlayer()
	{
		//selects a random number between 1 and 3
		initialPick = (int)(Math.random()*3+ 1);
	}
	
	/**
	 * Accessor method for initialPick
	 * @return the initial pick chosen randomly by the GamePlayer
	 */
	public int getInitialPick()
	{
		return initialPick;
	}
	
	/**
	 * Method that switches the chosen door if the player wishes to.
	 * @param goatDoor - the door revealed to have the goat inside
	 * @param changePick - true if the user chose to change their door
	 * @return the player's final chosen door
	 */
	public int getFinalPick(int goatDoor, boolean changePick)
	{
		if(changePick == false)
		{
			return initialPick;
		}
		return 6 - initialPick - goatDoor;
	}
	

}
