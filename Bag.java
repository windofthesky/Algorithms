package algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item>
{
	private Node<Item> m_first;
	private int m_num;
	
	//linked list inner static class.
	private static class Node<Item>
	{
		private Item item;
		private Node<Item> next;
	}
	
	public Bag()
	{
		this.m_first = null;
		this.m_num = 0;
	}
	
	public boolean isEmpty()
	{
		return m_first==null;
	}
	
	public int size()
	{
		return m_num;
	}
	
	public void add(Item item)
	{
		Node<Item> old_first = m_first;
		m_first = new Node<Item>();
		m_first.item = item;
		m_first.next = old_first;
		m_num+=1;
	}
	
	public Iterator<Item> iterator()
	{
		return new ListIterator<Item>(m_first);
	}
	
	@SuppressWarnings("hiding")
	private class ListIterator<Item> implements Iterator<Item>
	{
	    private Node<Item> m_current;
	    
	    public ListIterator(Node<Item> first)
	    {
	    	m_current = first;
	    }
	    
	    public boolean hasNext()
	    {
	    	return m_current!=null;
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
	    	
	    	Item item = m_current.item;
	    	m_current = m_current.next;
	    	return item;
	    }
	}
	
	/*
	 * Test the Bag class.
	 */
	public static void main(String[] args) 
	{
        Bag<String> b_test = new Bag<String>();
        b_test.add("abc");
        b_test.add("def");
        b_test.add("ghi");
        b_test.add("opq");
        b_test.add("rst");
        
        System.out.println("Current size is : " + b_test.size());
        
        for(String s:b_test)
        {
        	System.out.println("Item: " + s);
        }
	}
}
