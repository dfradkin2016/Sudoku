//Daria Fradkin
//November 16, 2015
//push
//peek
//pop
//check if its empty
//LIFO - Last in, first out
public interface Stack<E>
{
	void push(E item); //adds one to the top, beggining

	E pop(); //takes off top, returns head
	
	E peek(); //tells you what the top one is, head pointer
	
	boolean isEmpty();
}

//Stack<Character> calculator = new LinkedList<Character>(); //restrictive


