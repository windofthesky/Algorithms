package algorithms;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> 
{
	private TreeMap<Key, Value> st;
	
	public ST()
	{
		st = new TreeMap<Key, Value>();
	}
	
	public Value get(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("called get() with null key!");
		}
		else
		{
			return st.get(k);
		}
	}
	
	public void put(Key k, Value v)
	{
		if(k == null)
		{
			throw new NullPointerException("called put() with null key!");
		}
		else if(v == null)
		{
			st.remove(k);
		}
		else
		{
			st.put(k, v);
		}
	}
	
	public void delete(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("called delete() with null key!");
		}
		else
		{
			st.remove(k);
		}
	}
	
	public boolean contains(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("called contians() with null key!");
		}
		else
		{
			return st.containsKey(k);
		}
	}
	
	public int size()
	{
		return st.size();
	}
	
	public boolean isEmpty()
	{
		return st.size() == 0;
	}
	
	public Iterable<Key> keys()
	{
		return st.keySet();
	}
	
	public Iterator<Key> iterator()
	{
		return st.keySet().iterator();
	}
	
	public Key min()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("called min() with empty symbol table!");
		}
		else
		{
			return st.firstKey();
		}
	}
	
	public Key max()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("called max() with empty symbol table!");
		}
		else
		{
			return st.lastKey();
		}
	}
	
	public Key ceiling(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("called ceiling() with null key!");
		}
		
		Key rt_k = st.ceilingKey(k);
		
		if(rt_k == null)
		{
			throw new NoSuchElementException("All keys are less than " + k);
		}
		
		return rt_k;
	}
	
	public Key floor(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("called floor() with null key!");
		}
		
		Key rt_k = st.floorKey(k);
		
		if(rt_k == null)
		{
			throw new NoSuchElementException("all keys are greater than " + k);
		}
		
		return rt_k;
	}
	
	
	public static void main(String[] args) 
	{
        ST<String, Integer> st = new ST<String, Integer>();
        
        st.put("a", 1);
        st.put("b", 3);
        st.put("c", 3);
        st.put("d", 4);
        for(String s:st.keys())
        {
        	System.out.println(s);
        }
        System.out.println("Max " + st.max());
        System.out.println("Min " + st.min());
        System.out.println("Floor " + st.floor("c"));
        System.out.println("Ceiling " + st.ceiling("c"));
        System.out.println("C = " + st.get("c"));
        System.out.println("Size = " + st.size());
        st.delete("c");
        System.out.println("Size After Delete = " + st.size());
	}
}
