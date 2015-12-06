/**
ListNode
Used by a LinkedList
Holds a value and a pointer to another ListNode
Creates a chain
Only linked in one direction

@author	Daria Fradkin
@version	November 10, 2015
*/

public class ListNode<E>
{
	private E value;
	private ListNode<E> next;
	
	/**
	Constructor containing the value for the ListNode to hold.
	Pointer will be null
	
	@param	item	value
	*/
	public ListNode(E item)
	{
		value = item;
		next = null;
	}
	
	/**
	Constructor
	
	@param	item	value
	@param	n	pointer
	*/
	public ListNode(E item, ListNode<E> n)
	{
		value = item;
		next = n;
	}
	
	/**
	Returns value
	
	@return	value
	*/
	public E getValue() //getValue
	{
		return value;
	}
	
	/**
	Sets value
	
	@param	item	value
	*/
	public void setValue(E item) //setValue
	{
		value = item;
	}
	
	/**
	returns next
	
	@return	next
	*/
	public ListNode<E> getNext()
	{
		return next;
	}
	
	/**
	Sets next
	
	@param	node	next
	*/
	public void setNext(ListNode<E> node)
	{
		next = node;
	}
	
	/**
	ToString method
	
	@return	String representation of value
	*/
	public String toString()
	{
		return value.toString();
	}
}