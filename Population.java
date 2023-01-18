import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *	Population reads data from a text file and depending on user
 * 	input sorts it with a certain sort and prints out certain data thats sorted.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Marcus Cao		
 *	@since	1/17/2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	// instance of sortmethods
	private SortMethods sorter;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	 
	 public Population()
	 {
		cities = new ArrayList<City>();
		sorter = new SortMethods();
	 }
	 
	 	
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
	 
	 /**
	 *	Runs the program prompts the user for input and calls certain methods
	 *  depending on the users input, keeps on running until 9 is entered.
	 */
	 public void run()
	 {
		 int input = 0;
		 fillCities(); // fill the cities array
		 printIntroduction(); // print introduction message
		 System.out.println(cities.size() + " cities in database\n"); // print size of cities array
		
		 do
		 {
			printMenu();
			input = Prompt.getInt("Enter selection");
			if(!(input == 9 || (input <= 6 && input >= 1))) // if invalid input print error message prompt again
				System.out.println("ERROR: " + input + " is not a valid choice");
				
			System.out.println();
			// depending on user input call a method
			switch(input)
			 {
				case 1: 
					selectSortPop();
					break;
				case 2:
					mergeSortPop();
					break;
				case 3: 
					insertSortName();
					break;
				case 4:
					mergeSortName();
					break;
				case 5: 
					mostPopByState();
					break;
				case 6:
					cityNamePopSort();
					break;
				case 9:
					System.out.println("Thank you for using Population!");
					break;
			}
		} while (input != 9);
		 	 // when input not 9 keep on running
		 
	 }
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println("");
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("\n1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}

	/**
	 *	Reads in the text file and passes in certain data to the City constructor where it will be stored 
	 */
	public void fillCities()
	{
		Scanner inFile = FileUtils.openToRead(DATA_FILE);
		inFile.useDelimiter("[\t\n]"); // to make sure every .next has a piece of data
		// not a whole line, so it can easily be saved within variables. 
		
		String token = new String("");	
		int counter = 0;
		String name = "";
		String state = "";
		String designation = ""; 
		int population = 0;
		while(inFile.hasNext())
		{
			token = inFile.next(); // next piece of info
			counter++; // to track which peice of info is currently being saved
			
			// depending on what counter is categorize the read in info
			if(counter % 4 == 1)
			{
				state = token;
			}
			else if(counter % 4 == 2)
			{
				name = token;
			}
			else if(counter % 4 == 3)
			{
				designation = token;
			}
			else if(counter % 4 == 0)
			{
			
				population = Integer.valueOf(token.trim());
				// pass info saved from line into constructor of new city object
				// add city object to list
				City city = new City(name,state,designation, population);
				cities.add(city);
			}
			
		}
		inFile.close(); // close file
	}
	
	/**	uses selection sort to sort the population and prints 50
	 */
	 
	public void selectSortPop()
	{
		System.out.println("Fifty least populous cities");
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
		long startMillisec = System.currentTimeMillis();
		sorter.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();
		
		for(int i = 1; i < 51 ; i ++)
		{
			System.out.printf("%5s%s%n", (i + ": "),cities.get(i-1));
			
		}
		
		System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds");
	}
	
	/**	uses merge sort to sort the population and prints 50
	 */
	public void mergeSortPop()
	{
		System.out.println("Fifty most populous cities");
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
		long startMillisec = System.currentTimeMillis();
		sorter.mergeSort(cities);
		long endMillisec = System.currentTimeMillis();
		
		for(int i = 1; i < 51 ; i ++)
		{
			System.out.printf("%5s%s%n", (i + ": "),cities.get(i-1));
			
		}
		
		System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds");
		
		
	}
	
	/**	uses merge sort to sort the population of a certain inputed state and prints 50
	 */
	public void mostPopByState()
	{
		sorter.mergeSort(cities);
		String input = " ";
		int counter = 1;
		do
		{
			input = Prompt.getString("Enter the state name(ie.Alabama)");
			for(City temp : cities)
			{
				if(temp.getState().equals(input))
				{
					counter++;
				}
			}
			if(counter == 1)
				System.out.println("ERROR: " + input + " is not a valid");
		} while (counter == 1);
		counter = 1;
		System.out.println("Fifty most populous cities in " + input);
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
						
		for(City temp : cities)
		{
			if(temp.getState().equals(input) && counter < 51 )
			{
				System.out.printf("%5s%s%n", (counter + ": "),temp);
				counter++;
			}
		}
	}
	
	/**	uses merge sort to sort the population of a certain city and prints 
	 */
	public void cityNamePopSort()
	{
		sorter.mergeSort(cities);
		String input = " ";
		int counter = 1;
		do
		{
			input = Prompt.getString("Enter city name");
			for(City temp : cities)
			{
				if(temp.getCity().equals(input))
				{
					counter++;
				}
			}
			if(counter == 1)
				System.out.println("ERROR: " + input + " is not a valid");
		} while (counter == 1);
		counter = 1;
		System.out.println("City " + input + " by population" );
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
						
		for(City temp : cities)
		{
			if(temp.getCity().equals(input) )
			{
				System.out.printf("%5s%s%n", (counter + ": "),temp);
				counter++;
			}
		}
	}
	/**	uses insertion sort to sort the name of 50 cities and prints 
	 */
	public void insertSortName()
	{
		System.out.println("Fifty cities sorted by name");
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
		long startMillisec = System.currentTimeMillis();
		sorter.insertionSort(cities);
		long endMillisec = System.currentTimeMillis();
		
		for(int i = 1; i < 51 ; i ++)
		{
			System.out.printf("%5s%s%n", (i + ": "),cities.get(i-1));
			
		}
		
		System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds");
		
		
	}
	
	/**	uses merge sort to sort the name of 50 cities and prints 
	 */
	public void mergeSortName()
	{
		System.out.println("Fifty cities storted by name descending");
		System.out.printf("%10s %21s %22s %20s%n", "State", "City", "Type",
						"Population");
		long startMillisec = System.currentTimeMillis();
		sorter.mergeSortName(cities);
		long endMillisec = System.currentTimeMillis();
		
		for(int i = 1; i < 51 ; i ++)
		{
			System.out.printf("%5s%s%n", (i + ": "),cities.get(i-1));
			
		}
		
		System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds");
		
		
	}
	
}
