//Daria Fradkin
//December 1, 2015

//JUST A GAMEBOARD RUNNER
//NOT FOR SOLVING SUDOKU

public class SudokuRunner
{
	public static void main(String [] args)
	{
		GameBoard example = new GameBoard();
		System.out.println(example);
		//System.out.println(example.mostConstrained()[0] + " " + example.mostConstrained()[1]);
		System.out.println(example.solved());
		System.out.println(example.boxValues(0,2));
		System.out.println(example.options(0,2));
	}
}