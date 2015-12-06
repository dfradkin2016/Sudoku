/**
GameBoard class.
Used to solve a Sudoku board.
Needs to read a CSV and turn into an int array.
Blanks are stored as zeroes. 
Needs to be able to find the most constrained spot and return the possible options.
I didn't use some of the required methods, but included them.

@author	Daria Fradkin
@version	November 30, 2015
*/
import java.io.*;
import java.util.Scanner;

public class GameBoard
{
	private int[][] values;
	
	/**
	Default constructor.
	Will always read the initial GameBoard from ExampleGB.csv
	*/
	public GameBoard()
	{
		values = new int[9][9];
		ReadCSV();
	}
	
	/**
	Copy constructor that changes value in one spot.
	Needs to copy values in a for loop because it's pass by reference.
	
	@param	other	old GameBoard
	@param	row	row of spot
	@param	col	col of spot
	@param	n	value to put into spot
	*/
	public GameBoard(GameBoard other, int row, int col, int n)
	{
		values = new int[9][9];
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				values[i][j] = other.values[i][j];
			}
		}
		values[row][col]=n;
	}
	
	/**
	ReadCSV method called by default constructor.
	Reads from ExampleGB.csv
	*/
	private void ReadCSV()
	{
		String pathname = "ExampleGB.csv";
		File file = new File(pathname);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		//svalues holds the board initially as a String
		String[][] svalues = new String[9][9];
		int count = 0;
		while( input.hasNextLine() )
		{
			svalues[count] = input.nextLine().split(",");
			count++;
		}
		//Changes values from String to int.
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				//Dashes become zeroes
				if (svalues[i][j].equals("-"))
				{
					values[i][j] = 0;
				}
				else if (Integer.parseInt(svalues[i][j]) > 0 && Integer.parseInt(svalues[i][j]) < 10)
				{
					values[i][j] = Integer.parseInt(svalues[i][j]);
				}
				//Anything else becomes a zero.
				else
				{
					values[i][j] = 0;
				}
			}
		}
	}
	
	/**
	toString method
	Zeroes print as blanks.
	
	@return	String representation of list.
	*/
	public String toString()
	{
		String output = "";
		output+= "-------------------------------------" + "\n";
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (values[i][j] != 0)
					output += "| " + values[i][j] + " ";
				else
					output += "|   ";
			} 
			output += "| \n" + "-------------------------------------" + "\n";
		}
		return output;
	}
	
	/**
	Finds the most constrained spot.
	If it is unsolvable, the spot it finds will have zero options.
	If multiple spots are tied for most constrained, it will return the first by row.
	Run-time issue = calculate options one extra time when called another time
	
	@return	The most-constrained spot represented by an array [row,column]
	*/
	public int[] mostConstrained()
	{
		int[] spot = new int[2];
		int numOptions = 10;
		for (int r = 0; r < 9; r++)
		{
			for (int c = 0; c < 9; c++)
			{
				if (values[r][c] == 0)
				{
					if (options(r,c).size() < numOptions)
					{
						//values[r][c]=10;
						spot[0] = r;
						spot[1] = c;
						numOptions = options(r,c).size();
					}
				}
			}
		}		
		return spot;
	}
	/**
	Gives options in a given spot.
	
	@param	row	row of spot to check
	@param	col	col of spot to check
	@return	Vector of all options
	*/
	public Vector<Integer> options(int row, int col)
	{
		Vector<Integer> options = new Vector<Integer>();
		//adds numbers 1 through 9
		for (int i = 1; i < 10; i++)
		{
			options.add(i);
		}
		//removes values using other methods
		for (int n : colValues(col))
		{
			options.remove(new Integer(n));
		}
		for (int n : rowValues(row))
		{
			options.remove(new Integer(n));
		}
		for (int n : boxValues(row, col))
		{
			options.remove(new Integer(n));
		}
		return options;
	}
	
	/**
	Gives the numbers in the given column.
	In other words, what cannot go into that column.
	
	@param	col	Column of spot
	@return	Vector of values in that column.
	*/
	private Vector<Integer> colValues(int col)
	{
		Vector<Integer> options = new Vector<Integer>();
		for (int i = 0; i < 9; i++)
		{
			options.add(values[i][col]);
		}
		return options;
	}
	
	/**
	Gives the numbers in the given row.
	In other words, what cannot go into that row.
	
	@param	row	Row of spot
	@return	Vector of values in that row.
	*/
	private Vector<Integer> rowValues(int row)
	{
		Vector<Integer> options = new Vector<Integer>();
		for (int i = 0; i < 9; i++)
		{
			options.add(values[row][i]);
		}
		return options;
	}
	
	/**
	Gives the numbers in the given box.
	In other words, what cannot go into that box.
	
	@param	row	Row of spot
	@param	col	Column of spot
	@return	Vector of values in the box with that spot.
	*/
	private Vector<Integer> boxValues(int row, int col)
	{
		Vector<Integer> options = new Vector<Integer>();
		int r = row - row % 3;
		int c = col - col % 3;
		//i and j dont actually matter. Instead use r and c that start at different points
		//but still need to go three times
		for (int i = 0; i < 3; i++)
		{
			r = row - row % 3;
			for (int j = 0; j < 3; j++)
			{
				options.add(values[r][c]);
				r++;
			}
			c++;
		}
		return options;
	}
	
	/**
	Places value at a given spot.
	
	@param	r	Row of spot
	@param	c	Column of spot
	@param	n	Value to change to
	*/
	public void place(int r, int c, int n)
	{
		values[r][c] = n;
	}
	
	/**
	Gets value at a given spot.
	
	@param	r	Row of spot
	@param	c	Column of spot
	@return	Value at spot
	*/
	public int get(int r, int c)
	{
		return values[r][c];
	}
	
	/**
	Removes value at spot. 
	Changes value to zero.
	
	@param	r	Row of spot
	@param	c	Column of spot
	*/
	public void remove(int r, int c)
	{
		values[r][c] = 0;
	}
	
	/**
	Checks if value can be placed there.
	
	@param	r	Row of spot
	@param	c	Column of spot
	@param	n	Value to check
	@return	true if it can be placed
	*/
	public boolean canPlace(int r, int c, int n)
	{
		if (values[r][c] != 0)
			return false;
		for (int num : options(r,c))
		{
			if (num == n)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	Checks if values is full.
	
	@return	true if solved
	*/
	public boolean solved()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (values[i][j] == 0)
					return false;
			}
			
		}
		return true;
	}
}