//Daria Fradkin
//November 16, 2015

//FIFO - First in first out
public interface Queue<E>
{
	void offer(E item); //add, end
	
	E poll(); //take top off, returns head
	
	E peek(); //head pointer
	
	boolean isEmpty();
}