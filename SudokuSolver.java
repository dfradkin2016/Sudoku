/**
Solves sudoku board using GameBoard class.
brute force algorithm

1. pop stack
 Is it solved or unsolvable
2. Find most constrained
3. push all those options on the stack

Solve is the static method that is called by main.
Instead of simply doing it in main, I wrote in solve so that I could return a board 
to stop it when it's solved.

@author	Daria Fradkin
@version	December 2, 2015
*/
public class SudokuSolver
{	
	/**
	Main method.
	Simply calls solve class and returns its time.
	Fastest time = 422 (for "hardest Sudoku")
	
	@param	args	na
	*/
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();		
		System.out.println(solve());
		long end = System.currentTimeMillis();
		System.out.println("Time = " + (end-start) );
	}
	
	/**
	Solve class.
	If unsolvable, returns initial board.
	
	@return	Either the solved board or the original if unsolvable
	*/
	public static GameBoard solve()
	{
		//instead of being a class field, stack is created here
		Stack<GameBoard> stack = new LinkedList<GameBoard>();
		stack.push(new GameBoard());
		GameBoard current;
		//spot is kept track of by an array with 2 values
		int[] spot = new int[2];
		Vector<Integer> options;
		while (true)
		{
			current = stack.pop();
			if (current.solved())
				return current;
			spot = current.mostConstrained();
			options = current.options(spot[0],spot[1]);
			for (int n : options)
			{
				stack.push(new GameBoard(current,spot[0],spot[1],n));
			}
			//if unsolvable, returns original
			if (stack.isEmpty())
			{
				//The default constructor will always return the original game board
				//from the CSV
				return new GameBoard();
			}
		}
	}
}