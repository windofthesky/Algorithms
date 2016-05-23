package algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item>
{
    /*
     *The size of the queue.
     */
	private int m_num;
	
	/*
     *The first node of the queue.
     */
	private Node m_first;
	
	/*
     *The last node of the queue.
     */
	private Node m_last;
	
	private class Node
	{
		private Item item;
		private Node next;
	}
	
	public LinkedQueue()
	{
		this.m_first = null;
		this.m_last = null;
		this.m_num = 0;
		assert check();
	}

	public boolean isEmpty()
	{
		return (this.m_first == null);
	}
	
	public int size()
	{
		return this.m_num;
	}
	
	public Item peak()
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException("Exception: Queue Underflow!");
		}
		else
		{
			return this.m_first.item;
		}
	}
	
	/*
     *Insert node into the queue.
     */
	public void enqueue(Item item)
	{
		Node oldlast = this.m_last;
		this.m_last = new Node();
		this.m_last.item = item;
		this.m_last.next = null;
		
		if(this.isEmpty())
		{
			this.m_first = this.m_last;
		}
		else
		{
			oldlast.next = this.m_last;
		}
		this.m_num++;
		assert check();
	}
	
	/*
     *Get the latest node out of the queue.
     */
	public Item dequeue()
	{
		if(this.isEmpty())
		{
			throw new NoSuchElementException("Exception: Queue Underflow!");
		}
		else
		{
			Item item = this.m_first.item;
			this.m_first = this.m_first.next;
			this.m_num--;
			
			if(this.isEmpty())
			{
				this.m_last = null;
			}
			assert check();
			return item;
		}
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		for(Item item:this)
		{
			builder.append(item + " ");
		}
		
		return builder.toString();
	}
	
	// check internal invariants
    private boolean check() 
    {
        if (this.m_num < 0) 
        {
            return false;
        }
        else if (this.m_num == 0) 
        {
            if (this.m_first != null) return false;
            if (this.m_last  != null) return false;
        }
        else if (this.m_num == 1) 
        {
            if (this.m_first == null || this.m_last == null) return false;
            if (this.m_first != this.m_last)                 return false;
            if (this.m_first.next != null)            return false;
        }
        else 
        {
            if (this.m_first == null || this.m_last == null) return false;
            if (this.m_first == this.m_last)      return false;
            if (this.m_first.next == null) return false;
            if (this.m_first.next  != null) return false;

            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = this.m_first; x != null && numberOfNodes <= this.m_num; x = x.next) 
            {
                numberOfNodes++;
            }
            if (numberOfNodes != this.m_num) return false;

            // check internal consistency of instance variable last
            Node lastNode = this.m_first;
            while (lastNode.next != null) 
            {
                lastNode = lastNode.next;
            }
            if (this.m_last != lastNode) return false;
        }

        return true;
    } 
	
	/*
     *Implement the iterator interface.
     */
    public Iterator<Item> iterator()
    {
    	return new ListIterator();
    }
    
    /*
     *The embedded class for interface hasNext()/remove()/next(0.
     */
    private class ListIterator implements Iterator<Item>
    {
    	private Node current = m_first;
    	
    	public boolean hasNext()
    	{
    		return (current!=null);
    	}
    	
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    	
    	public Item next()
    	{
    		if(!hasNext())
    		{
    			throw new NoSuchElementException();
    		}
    		else
    		{
    			Item item = current.item;
    			current = current.next;
    			return item;
    		}
    	}
    }
    
    /*
     *Test function.
     */
	public static void main(String[] args) 
	{
        LinkedQueue<String> q = new LinkedQueue<String>();
        
        q.enqueue("abc");
        q.enqueue("edf");
        q.enqueue("hik");
        System.out.println(q.size() + " left on queue)");
        System.out.println("Item 0 is :" + q.dequeue());
        System.out.println("Item 1 is :" + q.dequeue());
        System.out.println("Item 2 is :" + q.dequeue());
        System.out.println("(" + q.size() + " left on queue)");
	}
}
