//Zac Thamer
//Period 3
//3/8/16
import java.util.Scanner;
import java.util.Random;
public class ACSLABC 
{
	static String grid [] [] = new String [6][6];


	static String matchingABC [] = new String [6];
	static String [] tripleABC = new String [3];

	public static void copyGrid(String oldGrid [] [], String newGrid [] [])
	{
		for (int r = 0; r < 6; r++)
		{
			for (int c = 0; c < 6; c++)
			{
				newGrid [r][c] = oldGrid [r][c];
			}
		}
	}

	public static void initialGrid()
	{
		for (int r = 0; r < 6; r++)
		{
			for (int c = 0; c < 6; c++)
			{
				grid [r][c] = "-";
			}
		}
	}

	// returns the grid Y index
	public static int getRowFromPos(int pos)
	{
		return (int)( (pos-1) / 6.0);
	}

	// returns the grid X index
	public static int getColFromPos(int pos)
	{
		return ( (pos-1) % 6);
	}

	public static void printGrid()
	{
		for (int r = 0; r < 6; r++)
		{
			System.out.println();
			for (int c = 0; c < 6; c++)
			{
				System.out.print(grid[r][c]);
			}
		}
		System.out.println();
	}

	public static void menuSort(int pos, String letter)
	{
		if (pos <= 6)
		{
			grid [0] [pos-1] = letter;
		}
		else if (pos > 6 && pos <= 12)
		{
			grid [1] [pos - 7] = letter;
		}
		else if (pos > 12 && pos <= 18)
		{
			grid [2] [pos - 13] = letter;
		}
		else if (pos > 18  && pos <= 24)
		{
			grid [3] [pos - 19] = letter; 
		}
		else if (pos > 24  && pos <= 30)
		{
			grid [4] [pos - 25] = letter;
		}
		else if (pos > 30  && pos <= 36)
		{
			grid [5] [pos - 31] = letter;

		}
	}

	public static void outsideLetters()
	{
		String [] abc = new String [3];
		abc [0] = "A";
		abc [1] = "B";
		abc [2] = "C";

		for (int col = 0; col < 6; col++)
		{
			for (int r = 0; r < 6; r++)
			{
				for (int c=0;c<3;c++)
				{
					if ( (col<1 || col>4) || (r<1 || r>4) )
					{
						if (grid [r] [0].equalsIgnoreCase(abc[c]) && grid [r] [1].equalsIgnoreCase("Z"))
							grid [r] [2] = abc[c];
						else if (grid [r] [0].equalsIgnoreCase(abc[c]))
							grid [r] [1] = abc[c];

						if (grid [r] [5].equalsIgnoreCase(abc[c]) && grid [r] [4].equalsIgnoreCase("Z" ))
							grid [r] [3] = abc[c];
						else if (grid [r] [5].equalsIgnoreCase(abc[c]))
							grid [r] [4] = abc[c];


						if (grid [0] [col].equalsIgnoreCase(abc[c]) && grid [1] [col].equalsIgnoreCase("Z"  ))
							grid [2] [col] = abc[c];
						else if (grid [0] [col].equalsIgnoreCase(abc[c]))
							grid [1] [col] = abc[c];

						if (grid [5] [col].equalsIgnoreCase(abc[c]) && grid [4] [col].equalsIgnoreCase("Z" ) )
							grid [3] [col] = abc[c];
						else if (grid [5] [col].equalsIgnoreCase(abc[c]))
							grid [4] [col] = abc[c];
					}
				}
			}
		}
	}

	public static boolean check()
	{
		matchingABC[0] = "ABC";
		matchingABC[1] = "ACB";
		matchingABC[2] =  "BAC";
		matchingABC[3] =  "BCA"; 
		matchingABC[4] =  "CAB"; 
		matchingABC[5] =  "CBA"; 

		for (int rc = 1; rc < 5; rc++)
		{
			// create strings from rows and columns, ignore Z
			String tmp1 = "";
			if (!grid[rc][1].equalsIgnoreCase("Z"))
				tmp1 +=grid[rc][1];
			if (!grid[rc][2].equalsIgnoreCase("Z"))
				tmp1 +=grid[rc][2];
			if (!grid[rc][3].equalsIgnoreCase("Z"))
				tmp1 +=grid[rc][3];
			if (!grid[rc][4].equalsIgnoreCase("Z"))
				tmp1 +=grid[rc][4];

			String tmp2 = "";
			if (!grid[1][rc].equalsIgnoreCase("Z"))
				tmp2 +=grid[1][rc];
			if (!grid[2][rc].equalsIgnoreCase("Z"))
				tmp2 +=grid[2][rc];
			if (!grid[3][rc].equalsIgnoreCase("Z"))
				tmp2 +=grid[3][rc];
			if (!grid[4][rc].equalsIgnoreCase("Z"))
				tmp2 +=grid[4][rc];

			// check row and col strings to see if legal
			boolean tmp1OK=false;
			boolean tmp2OK=false;
			for (int i = 0; i < 6; i++)
			{	
				if (tmp1OK == false)
					if (tmp1.equalsIgnoreCase(matchingABC[i]))
						tmp1OK = true;
				if (tmp2OK == false)
					if (tmp2.equalsIgnoreCase(matchingABC[i]))
						tmp2OK = true;
			}
			if (tmp1OK==false || tmp2OK==false)
				return false;
		}
		return true;
	}

	public static void random(int pos)
	{
		Random rn = new Random();
		for (int r = 1; r < 5; r++)
		{
			for (int c = 1; c < 5; c ++  )
			{
				if (grid [r] [c].equalsIgnoreCase("-"))
				{
					int sub = rn.nextInt(3)+1;
					if (sub == 1)
						grid [r][c] = "A";
					else if (sub == 2)
						grid [r][c] ="B";
					else if (sub == 3)
						grid [r][c] = "C";

				}
			}
		}


	}

	public static void outputPrint(int pos)
	{
		int r = getRowFromPos(pos);
		int c = getColFromPos(pos);
		System.out.println("Output: " + grid[r][c]);
	}

	public static void main(String[]args)
	{
		Scanner input = new Scanner (System.in);

		initialGrid();
		int pos = 0;;
		// first four numbers
		System.out.println("Enter the first four filled cells:");
		for (int i = 0; i < 4; i ++)
		{
			System.out.print("Filled: ");
			int firstFour = input.nextInt();
			menuSort(firstFour, "Z");
			// fill the cells with this number
		}

		System.out.println("Enter the amount of letters: ");
		int letterAmount = input.nextInt();

		System.out.println("Enter a location and a letter in that location");

		System.out.println();



		for (int counter = 0; counter < letterAmount; counter++)
		{
			System.out.println("Letter: ");
			String letter = input.next();

			System.out.print("Location Number: ");
			pos = input.nextInt();


			menuSort(pos, letter);


		}

		System.out.println();
		System.out.println("Index: ");
		int finalPos = input.nextInt(); 

		printGrid();
		outsideLetters();
		printGrid();

		String newGrid [][] = new String [6][6];
		copyGrid(grid, newGrid);
		boolean result;
		int counter=0;
		do 
		{
			random(pos);
			//			printGrid();
			result = check();
			if (result == false)
				copyGrid(newGrid, grid);
			counter++;
		}
		while (result == false);

		System.out.println("It took this many tries:" + counter);
		printGrid();
		outputPrint(finalPos);
		input.close();

	}
}

