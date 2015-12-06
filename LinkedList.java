/**
LinkedList
Singly linked, head and tail pointer.
Important that each method only parsed through the list once to not slow down run time.
This meant saving previous list nodes when the pointer to it had to be changed.
A linked list is good for adding to the end beginning or end, but poor when specific 
indices are needed. Also efficient at removing. Unlike a vector, there is no need for a 
capacity. Each list nodes effectively stores the entire list (in front of it) with pointers.

@author	Daria Fradkin
@version	November 28, 2015
*/

import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Stack<E>, Queue<E>, Iterable<E>
{
	//first item in the LinkedList
	private ListNode<E> head; 
	private ListNode<E> tail;
	private int size;
	
	/**
	Default constructor.
	*/
	public LinkedList()
	{
		//don't actually need these
		head=null;
		tail=null;
		size=0;
	}
	
	/**
	Constructor that takes in the head list node
	
	@param	h	Head, must not point to anything
	*/
	public LinkedList (ListNode<E> h)
	{
		if (h.getNext() != null)
			throw new IllegalArgumentException("ListNode may not be pointing to another");
		head = h;
		tail = h;
		size = 1;
	}
	
	/**
	Copy constructor.
	
	@param	other	LinkedList to copy from
	*/
	public LinkedList(LinkedList<E> other)
	{
		if (other == null)
			throw new IllegalArgumentException("Copied LinkedList cannot be null");
		head = new ListNode<E>(other.get(0));
		ListNode<E> ln = head;
		//copy from old to ln (new)
		for(ListNode<E> old = other.head.getNext(); old != null; old = old.getNext())
		{
			ln.setNext(new ListNode<E>(old.getValue()));
			ln = ln.getNext();
			tail = ln;
		}
		size = other.size();
	}
	
	/*
	Other ways to implement size if you did not have it as a class field.
	public int size()
	{
		int count = 0;
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
			count++;
		return count;
	}
	
	public int size()
	{
		return size(head);
	}
	
	public int size(ListNode<E> node)
	{
		if (node == null)
			return 0;
		else
			return 1 + size(node.getNext());
	}
	*/
	
	/**
	Size
	
	@return	size
	*/
	public int size()
	{
		return size;
	}
	
	/**
	Add object to end of list
	
	@param	o	Object to add
	@return	true if add successfully
	*/
	//boolean for future implememntation
	public boolean add(E o)
	{
		return add(size, o);
	}
	
	/**
	Add to anywhere in the list
	
	@param	index	where to add
	@param	o	what to add
	@return	boolean	true if add successfully
	*/
	public boolean add(int index, E o)
	{
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		ListNode<E> obj = new ListNode<E>(o);
		//Case 1. if adding to first
		if (index == 0)
		{
			obj.setNext(head);
			head = obj;
		}
		//Case 2. adding to end (unless empty)
		else if (index == size)
		{
			tail.setNext(obj);
		}
		//Case 3. add to middle
		else
		{
			ListNode<E> prev = atPosition(index-1);
			obj.setNext(prev.getNext()); 
			prev.setNext(obj);
		}
		//calls for both Case 1 with empty and Case 2
		if (index == size) //called if empty or last
			tail = obj;
		size++;
		return true; 
	}
	
	/**
	Returns ListNode at a position. Different than get because it returns the ListNode.
	Used in code when needed to trace through.
	
	@param	index	where to find ListNode
	@return	ListNode at position
	*/
	private ListNode<E> atPosition(int index)
	{
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		ListNode<E> output = head;
		for (int i = 0; i < index; i++)
			output = output.getNext();
		return output; 
	}
	
	/**
	Get
	
	@param	i	index
	@return	value held in ListNode
	*/
	public E get(int i)
	{
		if (i > size || i < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + i + 
				", out of bounds. Size is " + size);
		}
		return atPosition(i).getValue();
	}
	
	/**
	Set, removes pointers to what was there before.
	
	@param	i	index
	@param	o	object
	@return	Value of old ListNode	
	*/
	public E set(int i, E o)
	{
		if (i > size || i < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + i + 
				", out of bounds. Size is " + size);
		}
		//Instead of making a new ListNode can also just change the value
		E output; //changed later
		ListNode<E> obj = new ListNode<E>(o);
		//if its the first
		if (i == 0)
		{
			obj.setNext(head.getNext());
			output = head.getValue();
			head = obj;
		}
		//doesn't work for tail because you have to parse through no matter what
		//adding to middle
		else
		{
			ListNode<E> prev = atPosition(i-1);
			output = prev.getNext().getValue();
			obj.setNext(prev.getNext().getNext());
			prev.setNext(obj);
		}
		//called if empty or adding to end
		if (i == size-1)
			tail = obj;
		return output; 
	}
	
	/**
	Checks if LinkedList contains this item.
	
	@param	o	what to check for
	@return	whether or not it contains
	*/
	public boolean contains(E o)
	{
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getValue().equals(o))//what if its null
				return true;
		}
		return false;
	}
	
	/**
	IndexOf an object. -1 if it does not contain it.
	
	@param	o	object to look for
	@return	index
	*/
	public int indexOf(E o)
	{
		int count = 0;
		for (ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if (curr.getValue().equals(o))//what if its null
				return count;
			count++;
		}
		return -1;
	}
	
	/**
	Removes object at an index
	
	@param	index	where to remove from
	@return	value stored at index previously
	*/
	public E remove(int index)
	{
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index, " + index + 
				", out of bounds. Size is " + size);
		}
		ListNode<E> obj; //stops the error
		//if removing first
		if (index == 0)
		{
			obj = head;	
			head = head.getNext();
			if (size == 1)
				tail = null;
		}
		//middle or end
		else
		{ 
			ListNode<E> prev = atPosition(index - 1);
			obj = prev.getNext();
			prev.setNext(obj.getNext());
			if (index == size - 1)
			{
				tail = prev;
			}
		}
		size --;
		return obj.getValue();
	}
	
	/**
	Remove specific object
	
	@param	o	object to remove
	@return	if the object exists in list
	*/
	public boolean remove(E o)//can't call indexOf because we need the one before
	{
		if (size == 0)
			return false;
		//first it checks head, because head doesn't have a previous
		if (o != null) //different operator if null
		{
			//System.out.println("here1");
			//System.out.println(o);
			//System.out.println(head.getValue());
			if (o.equals(head.getValue()))
			{
				//System.out.println("here2");
				head = head.getNext();
				size--;
				return true;
			}
		}
		else
		{
			if (o == head.getValue())
			{
				head = head.getNext();
				size--;
				return true;
			}
		}
		//else
		//starts at head for previous
		ListNode<E> prev = null;
		//stops when curr.getNext is object
		//need || because when curr is set equal to tail, it is trying to do getNext twice
		for (ListNode<E> curr = head; curr != null && curr.getNext() != null; curr = curr.getNext())
		{
			if (o != null)
			{
				if (o.equals(curr.getNext().getValue()))
				{
					prev = curr;
					curr = tail;
					//breaks the loop because tail.getNext() is always null
				}
			}
			else
			{
				if (o == curr.getNext().getValue())
				{
					prev = curr;
					curr = tail;
				}
			}
		}
		//if prev was never set equal to anything
		if ( null == prev)
		{
			return false;
		}
		//previous is the one before object
		prev.setNext(prev.getNext().getNext());
		if (prev.getNext() == null)
			tail = prev;
		size--;
		return true;
	}
	
	/**
	Removes first item
	
	@return	value of first item
	*/
	public E removeFirst()
	{
		if (size == 0)
		{
			throw new NoSuchElementException("List is empty");
		}
		return remove(0);
	}
	
	/**
	Removes last item
	
	@return	value of last item
	*/
	public E removeLast() //no way to do it faster because need the second to last item
	{
		if (size == 0)
		{
			throw new NoSuchElementException("List is empty");
		}
		return remove(size-1);
	}
	
	/**
	Adds to start of list
	
	@param	item	value to add to start
	*/
	public void addFirst(E item)
	{
		add(0, item);
	}

	/**
	Adds to end of list
	
	@param	item	value of item to add
	*/
	public void addLast(E item)
	{
		add(size, item);
	}
	
	/**
	Checks if list is empty
	
	@return	true if empty
	*/
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		return false;
	}	
	
	/**
	Clears list
	*/
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	toString method
	
	@return	String representation of list
	*/
	public String toString()
	{
		String output = "";
		ListNode<E> obj = head;
		for (int i = 0; i < size; i++)
		{
			output += obj.toString() + " ";
			obj = obj.getNext();
		}
		return output;
	}
	
	/**
	Adds item to beginning. (For Stack)
	Will be removed first.
	
	@param	item to add
	*/
	public void push(E item)
	{
		addFirst(item);
	}
	
	/**
	Removes first. (For Stack)
	
	@return	value removed
	*/
	public E pop()
	{
		return remove(0);
	}
	
	/**
	Returns value that will be removed. (For Stack and Queue)
	
	@return	value that would be removed
	*/
	public E peek()
	{
		return head.getValue();
	}
	
	/**
	Adds item to end. (For Queue)
	Will be removed last.
	
	@param	item to add
	*/
	public void offer(E item)
	{
		addLast(item);
	}
	
	/**
	Removes first. (For Queue)
	
	@return	value removed
	*/
	public E poll()
	{
		return remove(0);
	}
	
	/**
	Iterator
	
	@return	Iterator of type E
	*/
	public Iterator<E> iterator()
	{		
		return new LinkedListIterator<E>(head);
	}
}