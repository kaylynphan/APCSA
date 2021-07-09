import java.util.Scanner;
public class GradebookDriver {
	static Scanner scan = new Scanner(System.in);
	static boolean quit = false;
	
	public static void main(String[] args) {
		Gradebook gradebook;
		boolean weighted = isWeighted();
		String name = getFileName(weighted);
		if (weighted) {
			gradebook = new CategoryGradebook(name);
		} else {
			gradebook = new TotalPointsGradebook(name);
		}
		while (!quit) {
			menu(gradebook);
		}
			//import file
			
			
			//present menu with options:
			
			/*1. view student grades:
			show all student grades + highlight grades over 100
			show class grade average
			give option to look at specific student -> show list of assignment scores under that student
			*/
			
			/*2. view list of assignments:
			show all assignments + class average for each assignment
			show class grade average
			give option to look at specific assignment -> show list of student scores under that assignment 
				+ highlight assignment scores over 100
			*/
			
			/*3. Add student
			 * enter scores for assignments?
			 */
			
			/*4. Remove an assignment
			 */
			
			/*5. Drop lowest assignment in a category for every student
			 * print category grade before and after
			 * print student overall grades before and after
			 */
	
	}
	
	public static boolean isWeighted() {
		System.out.print("Are you using a (1) weighted gradebook or a (2) unweighted gradebook? ");
		int input = scan.nextInt();
		if (input == 1) {
			return true;
		} else if (input == 2) {
			return false;
		} else {
			System.out.println("Couldn't identify input. We chose an unweighted gradebook by default.");
			return false;
		}
	}
	
	public static String getFileName(boolean weighted) {
		System.out.print("Would you like to (1) use an example gradebook or (2) import a new gradebook? ");
		int input = scan.nextInt();
		if (input == 2) {
			System.out.println("Enter the name of your file: ");
			return scan.next();
		} else {
			if (input != 1) {
				System.out.print("Couldn't identify input. ");
			}
			System.out.println("Using example gradebook...");
			if (weighted) {
				return "data - weighted.csv";
			} else {
				return "data - unweighted.csv";
			}
		}
	}
	
	public static void menu(Gradebook g) {
		System.out.println("--------------------------\nMenu\n--------------------------");
		System.out.println("(1) View Entire Gradebook");
		System.out.println("(2) Export Gradebook as a csv File");
		System.out.println("(3) View List of Students");
		System.out.println("(4) View Scores Over 100");
		System.out.println("(5) Add Student");
		System.out.println("(6) Remove Student");
		System.out.println("(7) Remove an assignment");
		System.out.println("(8) Drop Assignment With Lowest Score in a Category for Each Student");
		System.out.println("(9) Quit");
		System.out.print("Enter an option 1 through 9: ");
		int option;
		boolean validInput = false;
		while (!validInput) {
			option = scan.nextInt();
			validInput = true;
			if (option == 1) {
				g.printGradebook();
			} else if (option == 2) {
				g.createUpdatedCSVFile();
			} else if (option == 3) {
				viewSingleStudent(g);
			} else if (option == 4) {
				g.checkForExtraCredit();
			} else if (option == 5) {
				g.addStudent(newStudent(g));
				menu(g);
			} else if (option == 6) {
				removeStudent(g);
			} else if (option == 7) {
				removeAssignment(g);
			} else if (option == 8) {
				dropLowestScoreInCategory(g);
			} else if (option == 9) {
				quit();
			} else {
				validInput = false;
				System.out.print("Invalid input. Please enter an option 1 through 9: ");
			}
		}
	}
	
	public static void viewSingleStudent(Gradebook g) {
		g.printListOfStudents();
		System.out.print("Enter a student ID to view student grades, or -1 to return to Menu: ");
		int input = scan.nextInt();
		if (input == -1) {
			menu(g);
		} else {
			g.printScoresForOneStudent(input);
		}
	}
	
	public static Student newStudent(Gradebook g) {
		System.out.print("Enter the new student's ID number: ");
		Student s = new Student(scan.nextInt());
		System.out.println("Please enter the amount of points the student scored on each assignment.");
		for (Assignment a: g.getListOfAssignments()) {
			System.out.print(a.getName() + "(" + a.getPossiblePoints() + " possible points): ");
			int earnedPoints = scan.nextInt();
			s.addAssignment(new Assignment(a.getName(), a.getPossiblePoints(), earnedPoints));
		}
		return s;
	}
	
	public static void removeStudent(Gradebook g) {
		System.out.print("Enter a student's ID number to remove them: ");
		int id = scan.nextInt();
		g.removeStudent(id);
	}
	
	public static void removeAssignment(Gradebook g) {
		System.out.print("Enter the assignment name: ");
		String a = scan.next();
		g.dropAssignment(a);
	}
	
	public static void dropLowestScoreInCategory(Gradebook g) {
		if (g instanceof CategoryGradebook) {
			System.out.print("Enter the category: ");
			String c = scan.next();
			if (!((CategoryGradebook) g).isCategory(c)) {
				System.out.println("A " + c + " category does not exist.");
				return;
			}
			System.out.print("Enter the ID of the student you would like to drop the assignment for, or -1 for all students: ");
			int id = scan.nextInt();
			if (id == -1) {
				for (Student s: g.getStudentList()) {
					((CategoryGradebook) g).dropLowestInCategory(s.getID(), c);
				}
			} else {
				((CategoryGradebook) g).dropLowestInCategory(id, c);
			}
		} else {
			System.out.println("Not a weighted gradebook.");
		}
	}
	
	public static void quit() {
		quit = true;
		scan.close();
		System.out.println("Goodbye.");
	}
	
	
	
	
}
