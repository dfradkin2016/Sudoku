/**
Vector
A Vector is a data structure that is very similar to an Array, but its size is dynamic. 
Build a Vector class that contains the following variables and methods:

@author	Daria Fradkin
@version	October 18, 2015
*/

//import java.util.NoSuchElementException;
import java.util.Iterator;
import java.lang.Iterable;

public class Vector<E> implements Iterable<E>
{
	private Object[] data;
	private int size; //(num objects)
	private int capacity; //data.length()
	
	/**
	Default constructor. Initializes to capacity 10.
	*/
	public Vector()
	{
		this(10);
	}
	
	/**
	Constructor. Initializes to initCapacity.
	
	@param	initCapacity	int value for length of data
	*/
	public Vector(int initCapacity)
	{
		if (initCapacity < 1)
			throw new IllegalArgumentException("Capacity must be greater than zero");
		data = new Object[initCapacity];
		size = 0;
		capacity = initCapacity;
	}
	
	
	/**
	Copy constructor
		
	@param	other	Vector to copy
	*/
	public Vector(Vector<E> other)
	{
		if (other == null)
			throw new IllegalArgumentException("Copied vector cannot be null");
		data = new Object[other.capacity];
		size = 0;
		for (int i = 0; i<other.size(); i++)
		{
			add(other.get(i));
		}
		capacity = data.length;
	}
	
	/**
	Add function.
	Calls the other add function
	
	@param	toAdd	E value to be added
	*/
	public void add(E toAdd)
	{
		add(size, toAdd);
	}
	
	/**
	Add function. Pushes everything over.
	
	@param	index	int where object needs to added
	@param	toAdd	E value to be added
	*/
	public void add(int index, E toAdd)
	{
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		if (size >= data.length)
		{
			increaseCapacity();
		}
		for (int i = size - 1; i >= index; i--)
		{
			data[i + 1]=data[i];
		}
		data[index]=toAdd;
		size++;
	}
	
	/**
	Doubles capacity
	*/
	private void increaseCapacity()
	{
		Object[] dataTemp = data; //copies everything back into data
		data = new Object[dataTemp.length * 2];
		for (int i = 0; i < dataTemp.length; i++)
		{
			data[i] = dataTemp[i];
		}
		capacity = data.length;
	}
	
	/**
	Get function.
	
	@param	index	index of E value to be returned
	@return	E object at the index
	*/
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " +size);
		}
		return (E) data[index];
	}
	
	/**
	Remove function.
	
	@param	index	int of object needed to be removed
	@return	removed E object 
	*/
	public E remove(int index)
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		E output = get(index); //no need to suppress warnings with get
		data[index] = null;
		for (int i = index + 1; i < size; i++)
		{
			data[i - 1]=data[i];//moves everything over
		}
		size--;
		return output;
	}
	
	/**
	Remove function.
	
	@param	obj	E value to be removed
	@return	boolean	whether it was found and successfully removed
	*/
	public boolean remove(E obj)
	//calls other remove function, indexOf
	{
		if (contains(obj) == false)
			return false;
		remove(indexOf(obj));
		return true;
	}
	
	/**
	Set function.
	
	@param	index	int of which one to change
	@param	obj	what to put into the spot
	@return	E value that was replaced
	*/
	public E set(int index, E obj)
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		E output = get(index);
		data[index] = obj;
		return output;
	}
	
	/**
	Returns size.
	
	@return	int size
	*/
	public int size()
	{
		return size;
	}
	
	/**
	Clears list.
	*/
	public void clear()
	{
		data = new Object[10];
		size = 0;
		capacity = 10;
	}
	
	/**
	Checks if list is empty.
		
	@return	true if list is empty.
	*/
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		return false;
	}
	
	/**
	Checks if vector contains value.
	
	@param	obj	object to check for	
	@return	true if list contains object
	*/
	public boolean contains(E obj)
	//uses indexOf
	{
		if (indexOf(obj) == -1)
			return false;
		return true;
	}
	
	/**
	Checks if index of object in list
	
	@param obj	Object to be checked for
	@return	index of object
	*/
	public int indexOf(E obj)
	{
		if (obj == null) //need to use different operator if null
		{
			for (int i = 0; i < size; i++)
			{
				if ( obj == get(i))
					return i;
			}
		}
		else
		{
			for ( int i = 0; i < size; i++)
			{
				if ( obj.equals(get(i))) //obj cannot be null
					return i;
			}
		}
		return -1;
	}
	
	
	/**
	Print function.
		
	@return String representation of list
	*/
	public String toString()
	{
		String r = "";
		for (int i = 0; i < size; i++)
			if (data[i] != null)
				r += data[i].toString() + " ";
			else
				r += "null ";
		r += "\n";
		return r;
	}
	
	/**
	Iterator
	
	@return	Iterator of type E
	*/
	public Iterator<E> iterator()
	{		
		return new VectorIterator<E>(this);
	}
}