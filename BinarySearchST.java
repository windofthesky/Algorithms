package algorithms;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value>
{
	private static final int INIT_CAPACITY = 2;
	private Key[] keys;
	private Value[] vals;
	private int N = 0;
	
	public BinarySearchST()
	{
		this(INIT_CAPACITY);
	}

	public BinarySearchST(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}
	
	private void resize(int capacity)
	{
		assert capacity >= N;
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		
		for(int i = 0; i<keys.length; i++)
		{
			tempk[i] = keys[i];
			tempv[i] = vals[i];
		}
		
		keys = tempk;
		vals = tempv;
	}
	
	public int size()
	{
		return N;
	}
	
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public boolean contains(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to get() is null!");
		}
		
		return get(k)!=null;
	}
	
	public Value get(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to get() is null!");
		}
		
		if(isEmpty())
		{
			return null;
		}
		
		int i = rank(k);
		if(i<N && keys[i].compareTo(k) == 0)
		{
			return vals[i];
		}
		else
		{
			return null;
		}
	}
	
	public int rank(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to rank() is null!");
		}
		
		int lo = 0; 
		int hi = N-1;
		
		while(lo <= hi)
		{
			int mid = lo+(hi-lo)/2;
			int cmp = k.compareTo(keys[mid]);
			if(cmp < 0)
			{
				hi = mid - 1;
			}
			else if(cmp > 0)
			{
				lo = mid + 1;
			}
			else
			{
				return mid;
			}
		}
		return lo;
	}
	
	public void put(Key k, Value v)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to put() is null!");
		}
		
		if(v == null)
		{
			delete(k);
			return;
		}
		
		int i = rank(k);
		
		if(i < N && keys[i].compareTo(k)==0)
		{
			vals[i] = v;
		}
		
		if(N == keys.length)
		{
			resize(2 * keys.length);
		}
		
		for(int j = N; j > i; j--)
		{
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = k;
		vals[i] = v;
		N++;
		assert check();	
	}
	
	public void delete(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to delete() is null!");
		}
		
		if(isEmpty())
		{
			return;
		}
		
		int i = rank(k);
		
		if(i == N && keys[i].compareTo(k)==0)
		{
			return;
		}
		
		for(int j = i; j < N-1; j--)
		{
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		
		N--;
		keys[N] = null;
		vals[N] = null;
		
		if(N > 0 && N == keys.length/4)
		{
			resize(keys.length/2);
		}
		
		assert check();	
	}
	
    public void deleteMin() 
    {
        if (isEmpty())
        {
        	throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(min());
    }

    public void deleteMax() 
    {
        if (isEmpty())
        { 
        	throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(max());
    }
	
    public Key min()
    {
    	if(isEmpty())
    	{
    		return null;
    	}
    	return keys[0];
    }
    
    public Key max()
    {
    	if(isEmpty())
    	{
    		return null;
    	}
    	return keys[N-1];
    }
    
    public Key select(int k)
    {
    	if(k < 0 || k >= N)
    	{
    		return null;
    	}
    	return keys[k];
    }
    
    public Key floor(Key k)
    {
    	if(k == null)
    	{
    		throw new NullPointerException("Exception: argument to floor() is null!");
    	}
    	
    	int i = rank(k);
    	
    	if(i < N && k.compareTo(keys[i]) == 0)
    	{
    		return keys[i];
    	}
    	
    	if(i == 0)
    	{
    		return null;
    	}
    	else
    	{
    		return keys[i-1];
    	}
    }
    
    public Key ceiling(Key k)
    {
    	if(k == null)
    	{
    		throw new NullPointerException("Exception: argument to ceiling() is null!");
    	}
    	
    	int i = rank(k);
    	
    	if(i == N)
    	{
    		return null;
    	}
    	else
    	{
    		return keys[i];
    	}
    }
    
    public int size(Key lo, Key hi)
    {
       	if(lo == null)
    	{
    		throw new NullPointerException("Exception: first argument to size() is null!");
    	}
       	if(hi == null)
    	{
    		throw new NullPointerException("Exception: second argument to size() is null!");
    	}
       	
       	if(lo.compareTo(hi) > 0)
       	{
       		return 0;
       	}
       	if(contains(hi) && contains(lo))
       	{
       		return (rank(hi) - rank(lo) + 1);
       	}
       	else if(!contains(hi) && contains(lo))
       	{
       		return (rank(hi) - rank(lo));
       	}
       	else
       	{
       		throw new NoSuchElementException("Exception: both of the keys are not in the symbol table!");
       	}
    }
    
	public boolean check()
	{
		return isSorted() && rankCheck();
	}
	
	private boolean isSorted()
	{
		for(int i = 1; i<size(); i++)
		{
			if(keys[i].compareTo(keys[i-1]) < 0)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean rankCheck()
	{
        for (int i = 0; i < size(); i++)
        {
            if (i != rank(select(i))) 
            {
            	return false;
            }
        }
        for (int i = 0; i < size(); i++)
        {
        	if (keys[i].compareTo(select(rank(keys[i]))) != 0) 
        	{
        		return false;
        	}
        }
        return true;
	}
	
    public Iterable<Key> keys() 
    {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) 
        {
        	throw new NullPointerException("first argument to size() is null"); 
        }
        if (hi == null) 
        {
        	throw new NullPointerException("second argument to size() is null"); 
        }

        Queue<Key> queue = new Queue<Key>(); 
        // if (lo == null && hi == null) return queue;
        if (lo == null) 
        {
        	throw new NullPointerException("lo is null in keys()");
        }
        if (hi == null) 
        {
        	throw new NullPointerException("hi is null in keys()");
        }
        if (lo.compareTo(hi) > 0) 
        {
        	return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) 
        {
        	queue.enqueue(keys[i]);
        }
        if (contains(hi)) 
        {
        	queue.enqueue(keys[rank(hi)]);
        }
        return queue; 
    }
	
	public static void main(String[] args) 
	{
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(10);
        st.put("1", 1);
        st.put("3", 3);
        st.put("2", 2);
        st.put("4", 4);
        st.put("7", 7);
        st.put("6", 6);
        st.put("5", 5);
        for(String i:st.keys())
        {
        	System.out.println("Key "+ i + " value is " + st.get(i));
        }
        for(int i = 0; i<st.N; i++)
        {
        	System.out.println("Key---"+ i + " value is " + st.select(i));
        }
        System.out.println("2 is: " + st.get("2"));
        System.out.println("5 is: " + st.get("5"));
        System.out.println("7 is: " + st.get("7"));
	}
}
