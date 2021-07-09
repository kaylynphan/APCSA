import images.APImage;
import images.Pixel;
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
 * Class that contains methods and attributes to manipulate images in various ways.
 * Allows the user to choose which image to use and to choose which effect they want to implement.
 * 
 * @author Kaylyn Phan, Annabel Truong, Averi Tanlimco
 *
 */
public class ImageManipulator {
	private APImage image;
	private Scanner scan = new Scanner(System.in);
	private boolean quit = false;
	private static final Pixel BLACK = new Pixel(0,0,0);
	private static final Pixel WHITE = new Pixel(255,255,255);

	/**
	 * Empty constructor that creates a new ImageManipulator object.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public ImageManipulator() {	
	}
	
	/**
	 * Method that repeatedly runs an ImageManipulator program until the user wishes to quit.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public void run() {
		System.out.println("Welcome!");
		while (!quit) {
			setImage(getImageName());
			/*
			 * When getImage() is called, the user has the option to quit.
			 * So, there is an if-statement added here.
			 */
			if (quit) {
				return;
			}
			printMenu();
			process();
		}
		
	}
	/**
	 * Method that sets and displays the image that will be edited.
	 * Written by Kaylyn
	 * @param imageName - name of image to be edited
	 * @author Kaylyn Phan
	 */
	public void setImage(String imageName) {
		/*
		 * If-statement added to avoid displaying error if the user chooses to quit.
		 */
		if (quit) {
			return;
		}
		image = new APImage(imageName);
		System.out.println("Opening original image...");
		image.draw();
	}
	
	/**
	 * Method that prompts the user to either select an image to edit, select the default image, or quit.
	 * Written by Kaylyn
	 * @return name of image to be edited
	 * @author Kaylyn Phan
	 */
	public String getImageName() {
		String imageName = "";
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Would you like to import a photo or use a default image?\nEnter A to import, B to use default image, or C to quit: ");
			String response = scan.next();
			validInput = true;
			if (response.equalsIgnoreCase("C")) {
				quit();
			} else if (response.equalsIgnoreCase("B")) {
				//default image
				imageName = "smokey.jpg";
			} else if (response.equalsIgnoreCase("A")) {
				imageName = getImageFileName();
			} else {
				//if user does not enter A, B, or C, validInput set to false and loop continues
				validInput = false;
				System.out.println("Error: Invalid Input");
			}
		}
		return imageName;
	}
	
	/**
	 * Method that prompts the user to enter an image file name and returns a valid image name.
	 * Written by Kaylyn
	 * @return image name
	 * @author Kaylyn Phan
	 */
	public String getImageFileName() {
		String imageName = "";
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Enter the name of your image file: ");
			try {
				imageName = scan.next();
				//if imageName is invalid, creating an APImage will fail
				image = new APImage(imageName);
				validInput = true;
			} catch (Exception e) {
				validInput = false;
				//if the user cannot find a valid file name, they are given the option to use a default image
				System.out.print("Would you like to use the default file? Enter Y for Yes or N for No: ");
				String response = scan.next();
				if (response.equalsIgnoreCase("Y")) {
					//default image
					return "smokey.jpg";
				} else if (response.equalsIgnoreCase("N")) {
					System.out.println("Okay. Please input an existing file name.");
				} else {
					System.out.println("Could not read. Please try entering an existing file name.");
				}
					
			}
		}
		return imageName;
	}
	
	/**
	 * Method that prints the menu.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public void printMenu() {
		System.out.println("-------------------------\nMenu\n-------------------------\nSelect one of the following options to process your image or enter \"Q\" to quit.");
		System.out.println("A: Convert to Black and White\nB: Convert to Grayscale\nC: Convert to Luminance Grayscale\n" +
							"D: Rotate Image\nE: Create Sepia Effect\nF: Darken or Brighten\n" +
							"G: Apply Color Filter\nH: Posterize\nI: Create Photographic Negative\n" +
							"J: Show Edge Detector\nK: Sharpen\nL: Blur\nM: Shrink\nN: Enlarge\n" + 
							"Q: Quit");
	}
	
	/**
	 * Method that processes an image based on the menu option the user selects.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public void process() {
		System.out.print("Please enter a letter from A through N or Q to Quit: ");
		String option = scan.next();
		
		//each option prints a message notifying the user that their image is being processed and then displays the processed image
		if (option.equalsIgnoreCase("A")) {
			System.out.println("Converting to Black and White...");
			bAndW().draw();
		} else if (option.equalsIgnoreCase("B")) {
			System.out.println("Converting to Grayscale...");
			grayscale().draw();
		} else if (option.equalsIgnoreCase("C")) {
			System.out.println("Converting to Luminance Grayscale...");
			luminanceGrayscale().draw();
		} else if (option.equalsIgnoreCase("D")) {
			System.out.println("Rotating Image...");
			rotate();
		} else if (option.equalsIgnoreCase("E")) {
			System.out.println("Creating Sepia Effect...");
			sepia().draw();
		} else if (option.equalsIgnoreCase("F")) {
			System.out.println("Darkening/Brightening Image...");
			shade().draw();
		} else if (option.equalsIgnoreCase("G")) {
			System.out.println("Applying Color Filter...");
			colorFilter().draw();
		} else if (option.equalsIgnoreCase("H")) {
			System.out.println("Posterizing Image...");
			posterize().draw();
		} else if (option.equalsIgnoreCase("I")) {
			System.out.println("Creating Photographic Negative...");
			photographicNegative().draw();
		} else if (option.equalsIgnoreCase("J")) {
			System.out.println("Showing Edge Detector...");
			showEdgeDetector().draw();
		} else if (option.equalsIgnoreCase("K")) {
			System.out.println("Sharpening Image...");
			sharpen().draw();
		} else if (option.equalsIgnoreCase("L")) {
			System.out.println("Blurring Image...");
			blur().draw();
		} else if (option.equalsIgnoreCase("M")) {
			System.out.println("Shrinking Image...");
			shrink().draw();
		} else if (option.equalsIgnoreCase("N")) {
			System.out.println("Enlargening Image...");
			enlarge().draw();
		} else if (option.equalsIgnoreCase("Q")) {
			System.out.println("Quitting...");
			quit();
		} else {
			System.out.println("Error: Invalid input.");
			//an invalid input will prompt the user again to select from the menu
			process();
		}
	}	
	
	/**
	 * Method that quits the program.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public void quit() {
		scan.close();
		System.out.println("Goodbye!");
		//setting quit to true will cause any running methods to halt
		quit = true;
	}
	
	/**
	 * Method that turns the image into black and white
	 * Written by Annabel
	 * @atuhor Annabel Truong
	 * @return newImage, the black and white image
	 */
	public APImage bAndW() {
		APImage newImage = image.clone();
		for (Pixel p : newImage) {
			posterizePixel(p, BLACK, WHITE);	
		}
		return newImage;
	}
	
	/**
	 * Method to convert image to grayscale
	 * Written by Averi
	 * @return newImage - APImage object that has grayscale
	 * @author Averi Tanlimco
	 */
	public APImage grayscale() {
		int avg = 0;
		Pixel p;
		APImage newImage = image.clone();
		//loops through each pixel in image
		for(int i = 0; i < newImage.getHeight(); i++) {
			for (int k = 0; k < newImage.getWidth(); k++) {
				//finds average of rgb values and sets each values to average
				p = newImage.getPixel(k, i);
				avg = getLuminance(p);
				p = new Pixel(avg,avg,avg);
				newImage.setPixel(k, i, p);
			}
		}
		return newImage;
	}
	
	/**
	 * Method that creates and returns the photographic negative of the original image.
	 * Written by Kaylyn
	 * @return an APImage object that is the photographic negative of the original image
	 * @author Kaylyn Phan
	 */
	public APImage photographicNegative() {
		int avg = 0;
		Pixel p;
		APImage newImage = image.clone();
		for(int i = 0; i < newImage.getHeight(); i++) {
			for (int k = 0; k < newImage.getWidth(); k++) {
				p = newImage.getPixel(k, i);
				avg = getLuminance(p);
				//subtract luminance from 255 for photographic negative effect
				p = new Pixel(255-avg,255-avg,255-avg);
				//add new Pixel onto the image
				newImage.setPixel(k, i, p);
			}
		}
		return newImage;
	}

	/**
	 * Method that returns the luminance of a pixel.
	 * Written by Kaylyn
	 * @param p - the Pixel to find the luminance of
	 * @return the luminance of p
	 * @author Kaylyn Phan
	 */
	public int getLuminance(Pixel p) {
		//luminance is the average of a pixel's R, G, and B values
		return (p.getRed() + p.getBlue() + p.getGreen()) / 3;
	}
	
	/**
	 * Method that creates and returns a luminance grayscale version of the original image.
	 * Written by Kaylyn
	 * @return an APImage that is the original image edited into luminance grayscale.
	 * @author Kaylyn Phan
	 */
	public APImage luminanceGrayscale() {
		APImage newImage = image.clone();
		Pixel p;
		int lum = 0;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) { 
				p = image.getPixel(j, i);
				//applies true luminance effect to once Pixel at a time
				lum = getTrueLuminance(p);
				p = new Pixel(lum,lum,lum);
				//adds edited Pixel onto the new image
				newImage.setPixel(j, i, p);
			}
		}
		return newImage;
	}
	
	/**
	 * Finds the true luminance of a Pixel using relative luminance proportions for red, blue, and green.
	 * Written by Kaylyn
	 * @param p - Pixel to find the true luminance of
	 * @return the true luminance of p
	 */
	public int getTrueLuminance(Pixel p) {
		//apply luminance proportions as weights
		double red = p.getRed() * 0.587;
		double green = p.getGreen() * 0.299;
		double blue = p.getBlue() * 0.114;
		return (int)((red + green + blue)/3);
	}
	
	/**
	 * Method that asks the user how they want to rotate the image and rotates the image accordingly.
	 * Written by Kaylyn
	 * @author Kaylyn Phan
	 */
	public void rotate() {
		System.out.println("Would you like to rotate left, right, or down?");
		System.out.print("Enter 1 for Left, 2 for Right, or 3 for Down: ");
		String response = scan.next();
		if (response.equals("1")) {
			rotatedLeftImage();
		} else if (response.equals("2")) {
			rotatedRightImage();
		} else if (response.equals("3")) {
			rotatedDownImage();
		} else {
			System.out.println("Error. Please enter 1, 2, or 3");
			rotate();
		}
	
	}
	
	/**
	 * Rotates the image left 90 degrees
	 * Written by Annabel
	 * @return newImage, the image rotated left 90 degrees
	 * @author Annabel Truong
	 */
	public APImage rotateLeft() {
		APImage oldImage = image.clone();
		//gets height and width of old image for rows and cols of new
		int newCols = oldImage.getHeight();
		int newRows = oldImage.getWidth();
		int oldColIndex = newRows-1; //newRows-1 gets the last index of the oldImage's columns
		int oldRowIndex = 0;
		APImage newImage = new APImage(newCols, newRows);
		 //replaces new pixels with pixels from oldImage with set and getPixel method
		for (int r = 0; r < newRows; r++) {
			for (int c = 0; c < newCols; c++) {
				newImage.setPixel(c, r, oldImage.getPixel(oldColIndex, oldRowIndex));
				oldRowIndex++;
			}
			oldColIndex--;  
			oldRowIndex = 0;
		}
		return newImage;
	}
	
	/**
	 * draws the rotated image
	 * Written by Annabel
	 * @author Annabel Truong
	 */
	public void rotatedLeftImage() {
		APImage rotatedLeft = rotateLeft();
		rotatedLeft.draw();
	}


	/**
	 * Rotates the image 90 degrees to the right
	 * Written by Annabel
	 * @return newImage, the image rotated to the right
	 * @author Annabel Truong
	 */
	public APImage rotateRight() {
		APImage oldImage = image.clone();
		//gets height and width of old image for rows and cols of new
		int newCols = oldImage.getHeight();
		int newRows = oldImage.getWidth();
		int oldColIndex = 0; 
		int oldRowIndex = newCols-1; //newCols-1 gets the last index of the oldImage's columns
		APImage newImage = new APImage(newCols, newRows);
		 //replaces new pixels with old ones from the oldImage
		for (int r = 0; r < newRows; r++) {
			for (int c = 0; c < newCols; c++) {
				newImage.setPixel(c, r, oldImage.getPixel(oldColIndex, oldRowIndex));
				oldRowIndex--;
			}
			oldColIndex++;  
			oldRowIndex = newCols-1;
		}
		return newImage;
	}
	
	/**
	 * draws the rotated image
	 * Written by Annabel
	 * @author Annabel Truong
	 */
	public void rotatedRightImage() {
		APImage rotatedRight = rotateRight();
		rotatedRight.draw();
	}


	/** 
	 * Rotates the image 180 degrees or down
	 * Written by Annabel
	 * @return newImage, the image rotated 180 degrees
	 * @author Annabel Truong
	 */
	public APImage rotateDown() {
		 //gets height and width of old image for rows and cols of new
		int cols = image.getWidth();
		int rows = image.getHeight();
		int oldXPixel = cols-1;  //gets the value of the “first” old x pixel
		int oldYPixel = rows-1; // the value of the “first” old y pixel
		APImage newImage = new APImage(cols, rows);
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				newImage.setPixel(x, y, image.getPixel(oldXPixel, oldYPixel));
				oldXPixel--;
			}
			oldYPixel--;
			oldXPixel = cols-1;
		}
		return newImage;
	}

	/**
	 * draws the rotated image
	 * Written by Annabel
	 * @author Annabel Truong
	 */
	public void rotatedDownImage() {
		APImage rotatedDown = rotateDown();
		rotatedDown.draw();
	}

	/**
	 * Turns a grayscale image into an old fashioned image (sepia)
	 * Written by Averi
	 * @return newImage - an APImage object colored as a sepia image
	 * @author Averi Tanlimco
	 */
	public APImage sepia() {
		//calls grayscale method to turn image into grayscale
		APImage newImage = grayscale();
		//loops through each pixel in the image 
		for(int i = 0; i < newImage.getHeight(); i++) {
			for (int k = 0; k < newImage.getWidth(); k++) {
				Pixel p = newImage.getPixel(k, i);
				//depending on the pixel's red value, adjust red and blue values accordingly
				if (p.getRed() < 63){
					p.setRed((int)(p.getRed() * 1.1));
					p.setBlue((int)(p.getBlue() * 0.9));
				}
				else if (p.getRed() < 192){
					p.setRed((int)(p.getRed() * 1.15));
					p.setBlue((int)(p.getBlue() * 0.85));
				}
				else{
					p.setRed((int)(p.getRed() * 1.08));
					p.setBlue((int)(p.getBlue() * 0.93));
				}
				//checks that rgb value does not exceed upper or lower limit
				checkWhiteLimit(p);
				checkBlackLimit(p);
				newImage.setPixel(k, i, p);
			}
		}	
		return newImage;
	}

	/**
	 * Creates a darkened or brightened image based on user input
	 * Written by Averi
	 * @return newImage - an APImage object darkened or brightened
	 * @author Averi Tanlimco
	 */
	public APImage shade() {
		//makes copy of original image
		APImage newImage = image.clone();
		//prompts user to provide brightening/darkening factor
		int f = getFactor("the shade factor (positive value for brighten, negative value for darken)", false);
		//loops through each pixel to darken/brighten by the user input
		for(int i = 0; i < newImage.getHeight(); i++) {
			for (int k = 0; k < newImage.getWidth(); k++) {
				Pixel p = newImage.getPixel(k, i);
				shadePixel(p, f);
				newImage.setPixel(k, i, p);
			}
		}
		return newImage;
	}
	
	/**
	 * Increases each rgb value by the same amount
	 * If the input is a negative, it decreases the rgb value
	 * Written by Averi
	 * @param p - pixel to be modified
	 * @param degree - increase or decrease in rgb value
	 * @author Averi Tanlimco
	 */
	public void shadePixel(Pixel p, int degree) {
		//increases each rgb value by the same degree parameter
		p.setRed(p.getRed() + degree);
		p.setGreen(p.getGreen() + degree);
		p.setBlue(p.getBlue() + degree);
		//checks that rgb value does not exceed upper or lower limit
		checkBlackLimit(p);
		checkWhiteLimit(p);
	}
	
	/**
	 * Increases each rgb value of a pixel by different amounts based on user input
	 * Written by Averi
	 * @return newImage - APImage object that returns a color filtered image
	 * @author Averi Tanlimco
	 */
	public APImage colorFilter() {
		APImage newImage = image.clone();
		//gets user input to increase each rgb value by different amounts
		int r = getFactor("the red filter value", false);
		int g = getFactor("the green filter value", false);
		int b = getFactor("the blue filter value", false);
		//loops through each pixel to change each rgb value by specified amount
		for(int i = 0; i < newImage.getHeight(); i++) {
			for (int k = 0; k < newImage.getWidth(); k++) {
				Pixel p = newImage.getPixel(k, i);
				p.setRed(p.getRed()+r);
				p.setGreen(p.getGreen()+g);
				p.setBlue(p.getBlue()+b);
				//checks that rgb value does not exceed upper or lower limit
				checkWhiteLimit(p);
				checkBlackLimit(p);
				newImage.setPixel(k, i, p);
			}
		}
		return newImage;
	}
	
	/**
	 * Posterizes the image and converts it to only two randomized colors
	 * Written by Annabel
	 * @return newImage, the image after posterizing process
	 * @author Annabel Truong
	 */
	public APImage posterize() {
		//generates random colors by calling randomize()
		Pixel color1 = randomize();
		Pixel color2 = randomize();
		//calls getLuminance to check which color is darker
		if (getLuminance(color1) > getLuminance(color2)) {
			Pixel temp = color1.clone();
			color1 = color2.clone();
			color2 = temp;
		}
		APImage newImage = image.clone();
		//goes thru newImage to replace it with the right color
		for (Pixel p : newImage) {
			posterizePixel(p,color1,color2);
		}
		return newImage;
	}

	/**
	 * Changes pixels to color1 (darker) or color2 (lighter).
	 * Precondition: color1 is darker than color2
	 * Written by Kaylyn
	 * @param p - pixel to posterize
	 * @param color1 - darker randomized pixel
	 * @param color2 - brighter randomied pixel
	 * @author Kaylyn Phan
	 */
	public void posterizePixel(Pixel p, Pixel color1, Pixel color2) {
		if (getLuminance(p) < 128) {
			//set Pixels with a luminance under 128 to color1
			p.setRed(color1.getRed());
			p.setGreen((color1.getGreen()));
			p.setBlue(color1.getBlue());
		} else {
			//set Pixels with a luminance of 128 or higher to color2
			p.setRed(color2.getRed());
			p.setGreen((color2.getGreen()));
			p.setBlue(color2.getBlue());
		}
	}

	/**
	 * generates the random color for posterizing
	 * written by Annabel
	 * @return p Pixel
	 * @author Annabel Truong
	 */
	public Pixel randomize() {
	 	//Math.random() to generate random value for RGB
		int red = (int) (Math.random()*256);
		int green = (int) (Math.random()*256);
		int blue = (int) (Math.random()*256);
		Pixel p = new Pixel(red, green, blue);
		return p;
	}
	
	/**
	 * Method that displays the original image's edges using black pixels on a white background.
	 * Written by Kaylyn
	 * @return an APImage that shows the original image's edges
	 * @author Kaylyn Phan
	 */
	public APImage showEdgeDetector() {
		APImage newImage = new APImage(image.getWidth(),image.getHeight());
		Pixel p;
		//run edge detection method
		boolean[][] edges = edgeDetection();
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (edges[i][j] == true) {
					//set edges to black
					p = BLACK.clone();
				} else {
					//set edges to white
					p = WHITE.clone();
				}
				newImage.setPixel(j,i,p);
			}
		}
		return newImage;		
	}
	
	/**
	 * Method that creates and returns a sharpened version of the original image using a user entered degree of sharpness.
	 * Written by Kaylyn
	 * @return an APImage that is a sharpened version of the original image
	 * @author Kaylyn Phan
	 */
	public APImage sharpen() {
		APImage newImage = image.clone();
		//find pixels to darken
		boolean[][] edges = edgeDetection();
		//prompt user for degree of sharpness
		int degree = getFactor("degree of sharpness", true);
		//darken identified pixels
		Pixel p;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (edges[i][j] == true) {
					p = image.getPixel(j, i);
					//multiply degree by -1 to apply a darkening effect
					shadePixel(p,-1 * degree);
					newImage.setPixel(j,i,p);
				}
			}
		}
		return newImage;	
	}
	
	/**
	 * Method that detects the edges of an image using a user entered luminance threshold.
	 * Written by Kaylyn
	 * @return a 2D boolean array that represents the Pixels of the original image. "True" represents a Pixel that is an edge, and "False" represents a Pixel that is not an edge.
	 * @author Kaylyn Phan
	 */
	public boolean[][] edgeDetection() {
		//prompt user for threshold of edge detection
		int threshold = getFactor("threshold for edge detection", true);
		//2D array is filled with "false" as default boolean value
		boolean[][] edges = new boolean[image.getHeight()][image.getWidth()];
		Pixel p;
		Pixel pLeft;
		Pixel pBelow;
		/*
		 * If the difference between the luminance of p and pLeft or the
		 * difference between the luminance of p and pBelow is greater than or
		 * equal to the threshold, that pixel is an edge and is marked as 
		 * "true" on the edgeDetector 2D Array.
		 */
		for (int i = 0; i < image.getHeight()-1; i++) {
			for (int j = 1; j < image.getWidth(); j++) {
				p = image.getPixel(j, i);
				pLeft = image.getPixel(j-1,i);
				pBelow = image.getPixel(j,i+1);
				
				if (Math.abs(getLuminance(p) - getLuminance(pLeft)) > threshold && Math.abs(getLuminance(p) - getLuminance(pBelow)) > threshold) {
					edges[i][j] = true;
				}
			}
		}
		return edges;
	}
	
	/**
	 * Verifies input is a number
	 * Written by Averi
	 * @author Averi Tanlimco
	 */
	public void checkIfNumber() {
		//takes in user input
		boolean isNum = scan.hasNextInt();
		//prompts user to input an integer if the user doesn't
		while (isNum == false) {
			System.out.print("Not a number. Please input an integer: ");
			String s = scan.next();
			isNum = scan.hasNextInt();
		}
	}
	
	/**
	 * Verifies input is positive
	 * Written by Averi
	 * @return factor - user inputted factor
	 * @author Averi Tanlimco
	 */
	public int checkIfPositive() {
		int factor = 0;
		//ensures input is a number
		checkIfNumber();
		factor = scan.nextInt();
		//prompts user to input an positive int if the user doesn't
		while (factor <= 0) {
			System.out.print("Not positive. Please input a positive integer: ");
			checkIfNumber();
			factor = scan.nextInt();
		}
		return factor;
	}
	
	/**
	 * gets factor input from user and checks if it meets criteria
	 * @param s - customized input string
	 * @param needPos - true if factor must be positive, false if factor can be negative
	 * @return factor - factor to be used in effect methods
	 * @author Averi Tanlimco
	 */
	public int getFactor(String s, boolean needPos) {
		int factor;
		System.out.print("Please input " + s + ": ");
		//if factor must be positive, call checkIfPositive
		if (needPos == true) {
			factor = checkIfPositive();
		}
		//if factor can be either, call checkIfNumber
		else {
			checkIfNumber();
			factor = scan.nextInt();
		}
		return factor;
	}

	/**
	 * If a rgb value exceeds 255, set that value to 255
	 * Written by Averi
	 * @param p - checked pixel
	 * @author Averi Tanlimco
	 */
	public void checkWhiteLimit(Pixel p) {
		//checks white limit for red
		if (p.getRed() > 255) {
			p.setRed(255);
		}
		//checks white limit for green
		if (p.getGreen() > 255) {
			p.setGreen(255);
		}
		//checks white limit for blue
		if (p.getBlue() > 255) {
			p.setBlue(255);
		}

	}
	
	/**
	 * If a rgb value is less than 0, set that value to 0
	 * Written by Averi
	 * @param p - checked pixel
	 * @author Averi Tanlimco
	 */
	public void checkBlackLimit(Pixel p) {
		//checks black limit for red
		if (p.getRed() < 0) {
			p.setRed(0);
		}
		//checks black limit for green
		if (p.getGreen() < 0) {
			p.setGreen(0);
		}
		//checks black limit for blue
		if (p.getBlue() < 0) {
			p.setBlue(0);
		}

	}

	/**
	 * Method creates and returns a blurred version of the original image.
	 * Written by Kaylyn
	 * @return an APImage that is a blurred version of the original image
	 * @author Kaylyn Phan
	 */
	public APImage blur() {
		Pixel p;
		APImage newImage = image.clone();
		//pixels on the border cannot be blurred
		for (int i = 1; i < image.getHeight() - 2; i++) {
			for (int j = 1; j < image.getWidth() - 2; j++) {
				//identify surrounding pixels
				Pixel[] pixels = adjacentPixels(j,i);
				//blur each pixel individually and add to newImage
				p = blurredPixel(pixels);
				newImage.setPixel(j, i, p);
			}
		}
		return newImage;
	}
	
	/**
	 * Method that takes in an array of Pixels and returns a Pixel blurred between them.
	 * Precondition: pixels contains 4 Pixel objects which are adjacent to a central Pixel.
	 * Written by Kaylyn
	 * @param pixels - array of Pixels
	 * @return a Pixel that has the average of the red, green, and blue values of pixels
	 * @author Kaylyn Phan
	 */
	public Pixel blurredPixel(Pixel[] pixels) {
		//find average red, green, and blue values across 4 pixels
		int averageRed = (pixels[0].getRed() + pixels[1].getRed() + pixels[2].getRed() + pixels[3].getRed()) / 4;
		int averageGreen = (pixels[0].getGreen() + pixels[1].getGreen() + pixels[2].getGreen() + pixels[3].getGreen()) / 4;
		int averageBlue = (pixels[0].getBlue() + pixels[1].getBlue() + pixels[2].getBlue() + pixels[3].getBlue()) / 4;
		Pixel blurredPixel = new Pixel(averageRed, averageGreen, averageBlue);
		return blurredPixel;
	}
	
	/**
	 * Method that takes in the coordinates of a pixel and returns an array of 4 adjacent pixels.
	 * Precondition: the location is not on the edge of the image
	 * Written by Kaylyn
	 * @param j - the column of the pixel
	 * @param i - the row of the pixel
	 * @return an array containing four adjecent Pixels
	 * @author Kaylyn
	 */
	public Pixel[] adjacentPixels(int j, int i) {
		//pixels contains the left, right, up, and down adjacent Pixels
		Pixel[] pixels = {image.getPixel(j-1,i), image.getPixel(j+1,i), image.getPixel(j,i-1), image.getPixel(j, i+1)};
		return pixels;
	}

	/**
	 * Shrinks image using user input
	 * Written by Averi
	 * @return newImage - shrunken image
	 * @author Averi Tanlimco
	 */
	public APImage shrink() {
		APImage oldImage = image.clone();
		APImage newImage;
		int shrinkFactor = getFactor("the shrink factor", true);
		//sets dimensions of new image
		int newWidth = (oldImage.getWidth() / shrinkFactor) + oldImage.getWidth()%2;
		int newHeight = (oldImage.getHeight() / shrinkFactor)+ oldImage.getHeight()%2;
		newImage = new APImage(newWidth, newHeight);
		//loops through old image to put pixels into new image
		int j = 0;
		int l = 0;
		for(int i = 0; i < oldImage.getHeight(); i+=shrinkFactor) {
			l=0;
			for (int k = 0; k < oldImage.getWidth(); k+=shrinkFactor) {
				newImage.setPixel(l, j, oldImage.getPixel(k, i));
				l++;
			}
			j++;
		}
		
		return newImage;
	}
	
	/**
	 * Enlarges image using user input
	 * Written by Averi
	 * @return newImage - enlarged image
	 * @author Averi Tanlimco
	 */
	public APImage enlarge() {
		APImage oldImage = image.clone();
		APImage newImage;
		int enlargeFactor = getFactor("the enlargement factor", true);
		//sets dimensions of new image
		int newWidth = oldImage.getWidth() * enlargeFactor;
		int newHeight = oldImage.getHeight() * enlargeFactor;
		newImage = new APImage(newWidth, newHeight); 
		int j = 0;
		int l = 0;
		//enlarges height by factor
		for(int x = 0; x < enlargeFactor;x++) {
			for(int i = 0; i < oldImage.getHeight(); i++) {
				l=0;
				//enlarges width by factor
				for (int k = 0; k < oldImage.getWidth()/2; k++) {
					for (int z = 0; z < enlargeFactor;z++) {
						//looping meets in the middle
						newImage.setPixel(l+z, j+x, oldImage.getPixel(k, i));
						newImage.setPixel(((newImage.getWidth()-1)-(l+z)), j+x, oldImage.getPixel(((oldImage.getWidth()-1)-k), i));
					}
					l+=enlargeFactor;
				}
				j+=enlargeFactor;
			}
			j = 0;
			l = 0;
		}
		return newImage;
	}
}



