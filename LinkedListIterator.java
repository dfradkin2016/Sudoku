/**
LinkedListIterator

@author	Daria Fradkin
@version	November 18, 2015
*/

import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class LinkedListIterator<E> implements Iterator<E>
{
		private ListNode<E> curr;
		
		/**
		Constructor. Initializes to another vector
	
		@param	v	Other vector
		*/
		public LinkedListIterator(ListNode<E> head)
		{
			curr = head;
		}
		
		/**
		Checks if there is another value
		
		@return	Whether there is another value
		*/
		public boolean hasNext()
		{
			//return false;
			//System.out.println("HERE");
			if (curr == null) //?????
			{
				//System.out.println(curr + "false");
				return false;
			}
			//System.out.println(curr + "true");
			return true;
		}
		
		/**
		Returns next value
		
		@return	Next value of type E
		*/	
		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException("No next term. Currently at " + curr.toString());
			E temp = curr.getValue();
			curr = curr.getNext();
			return temp;
		}
}