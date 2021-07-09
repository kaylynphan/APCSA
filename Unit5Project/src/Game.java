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
public class Game {
	private int carDoor;
	private int goatDoor1;
	private int goatDoor2;
	
	/**
	 * Constructor that creates a new Game
	 */
	public Game()
	{
		carDoor = (int)(Math.random()*3+ 1);
		if(carDoor == 1)
		{
			goatDoor1 = 2;
			goatDoor2 = 3;
		}
		else if(carDoor == 2)
		{
			goatDoor1 = 1;
			goatDoor2 = 3;
		}
		else 
		{
			goatDoor1 = 1;
			goatDoor2 = 2;
		}
	}
	
	/**
	 * Method that returns a random number indicating a door that is different from the player's initial choice. For example, if a player initially chose Door 3, the method would return 1 or 2.
	 * @param initialPlayerPick - the player's initial door choice
	 * @return a random integer betwee 1 and 3, inclusive, but is not equal to initialPlayerPick
	 */
	public int getRandomGoatDoor(int initialPlayerPick)
	{
		if(initialPlayerPick == goatDoor1)
		{
			return goatDoor2;
		}
		else if(initialPlayerPick == goatDoor2)
		{
			return goatDoor1;
		}
		else
		{
			int randomGoatDoor = (int)Math.random()*2 +1;
			if(randomGoatDoor == 1)
			{
				return goatDoor1;
			}
			else
			{
				return goatDoor2;
			}
		}
			
	}
	
	/**
	 * Method that returns true if the user wins the game.
	 * @param finalDoorPick - the player's final door choice
	 * @return true if the player's chosen door has the car behind it, false otherwise
	 */
	public boolean pickDoor(int finalDoorPick)
	{
		return (finalDoorPick == carDoor);
			
	}
	
}
