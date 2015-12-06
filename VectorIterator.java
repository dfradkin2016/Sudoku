/**
VectorIterator

@author	Daria Fradkin
@version	October 25, 2015
*/

import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class VectorIterator<E> implements Iterator<E>
{
		private Vector<E> vector;
		private int curr;
		
		/**
		Constructor. Initializes to another vector
	
		@param	v	Other vector
		*/
		public VectorIterator(Vector<E> v)
		{
			vector = v;
			curr = 0;
		}
		
		/**
		Checks if there is another value
		
		@return	Whether there is another value
		*/
		public boolean hasNext()
		{
			return (curr < vector.size());
		}
		
		/**
		Returns next value
		
		@return	Next value of type E
		*/	
		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException("No next term. Currently at " + curr 
					+ ". Length is " + vector.size());
			curr++;
			return vector.get(curr - 1);
		}
}