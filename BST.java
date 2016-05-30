package algorithms;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value>
{
	private Node root;
	
	private class Node
	{
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int N;
		
		public Node(Key key, Value val, int N)
		{
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public BST()
	{
	}
	
	public boolean isEmpty()
	{
		return (size() == 0);
	}
	
	public int size()
	{
		return size(root);
	}
	
	private int size(Node x)
	{
		if(x == null)
		{
			return 0;
		}
		else
		{
			return (x.N);
		}
	}
	
	public boolean contains(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to contains() is null!");
		}
		
		return (get(k)!=null);
	}
	
	public Value get(Key k)
	{
		return get(root, k);
	}
	
	private Value get(Node x, Key k)
	{
		if(x == null)
		{
			return null;
		}
		
		int cmp = k.compareTo(x.key);
		if(cmp < 0)
		{
			return get(x.left, k);
		}
		else if(cmp > 0)
		{
			return get(x.right, k);
		}
		else
		{
			return x.val;
		}
	}
	
	public void put(Key k, Value v)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: first argument to put() is null!");
		}
		if(v == null)
		{
			delete(k);
			return;
		}
		
		root = put(root, k, v);
		assert check();
	}
	
	private Node put(Node x, Key k, Value v)
	{
		if(x == null)
		{
			return new Node(k, v, 1);
		}
		
		int cmp = k.compareTo(x.key);
		if(cmp < 0)
		{
			x.left = put(x.left, k, v);
		}
		else if(cmp > 0)
		{
			x.right = put(x.right, k, v);
		}
		else
		{
			x.val = v;
		}
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMin()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: Symbol table underflow!");
		}
		
		root = deleteMin(root);
		assert check();
	}
	
	private Node deleteMin(Node x)
	{
		if(x.left == null)
		{
			return x.right;
		}
		
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void deleteMax()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: Sysbol table underflow!");
		}
		
		root = deleteMax(root);
		assert check();
	}
	
	private Node deleteMax(Node x)
	{
		if(x.right == null)
		{
			return x.left;
		}
		
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public void delete(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to delete() is null!");
		}
		
		root  = delete(root, k);
		assert check();
	}
	
	private Node delete(Node x, Key k)
	{
		if(x == null)
		{
			return null;
		}
		
		int cmp = k.compareTo(x.key);
		
		if(cmp < 0)
		{
			x.left = delete(x.left, k);
		}
		else if(cmp > 0)
		{
			x.right = delete(x.right, k);
		}
		else
		{
			if(x.right == null)
			{
				return x.left;
			}
			
			if(x.left == null)
			{
				return x.right;
			}
			
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Key min()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: called min() with empty sybol table!");
		}
		
		return min(root).key;
	}
	
	private Node min(Node x)
	{
		if(x.left == null)
		{
			return x;
		}
		else
		{
			return min(x.left);
		}
	}
	
	public Key max()
	{
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: called max() with empty symbol table!");
		}
		
		return max(root).key;
	}
	
	private Node max(Node x)
	{
		if(x.right == null)
		{
			return x;
		}
		else
		{
			return max(x.right);
		}
	}
	
	public Key floor(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to floor() is null!");
		}
		
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: called floor() with empty symbol table!");
		}
		
		Node x = floor(root, k);
		
		if(x == null)
		{
			return null;
		}
		else
		{
			return x.key;
		}
	}
	
	private Node floor(Node x, Key k)
	{
		if(x == null)
		{
			return null;
		}
		
		int cmp = k.compareTo(x.key);
		
		if(cmp == 0)
		{
			return x;
		}
		if(cmp > 0)
		{
			return floor(x.left, k);
		}
		
		Node t = floor(x.right,k);
		
		if(t != null)
		{
			return t;
		}
		else
		{
			return x;
		}	
	}
	
	public Key ceiling(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to ceiling() is null!");
		}
		
		if(isEmpty())
		{
			throw new NoSuchElementException("Exception: called ceiling() with empty symbol table!");
		}
		
		Node x = ceiling(root, k);
		
		if(x == null)
		{
			return null;
		}
		else
		{
			return x.key;
		}
	}
	
	private Node ceiling(Node x, Key k)
	{
		if(x == null)
		{
			return null;
		}
		
		int cmp = k.compareTo(x.key);
		
		if(cmp == 0)
		{
			return x;
		}
		if(cmp < 0)
		{
			Node t = ceiling(x.left, k);
			
			if(t != null)
			{
				return t;
			}
			else
			{
				return x;
			}
		}
		
		return ceiling(x.right, k);	
	}
	
	public Key select(int k)
	{
		if(k < 0 || k >= size())
		{
			throw new IllegalArgumentException();
		}
		
		Node x = select(root, k);
		return x.key;
	}
	
	private Node select(Node x, int k)
	{
		if(x == null)
		{
			return null;
		}
		
		int t = size(x.left);
		
		if(t > k) 
		{
			return select(x.left, k);
		}
		else if(t < k)
		{
			return select(x.right, k);
		}
		else
		{
			return x;
		}
	}

	public int rank(Key k)
	{
		if(k == null)
		{
			throw new NullPointerException("Exception: argument to rank() is null!");
		}
		
		return rank(k, root);
	}
	
	private int rank(Key k, Node x)
	{
		if(x == null)
		{
			return 0;
		}
		
		int cmp = k.compareTo(x.key);
		
		if(cmp < 0)
		{
			return rank(k, x.left);
		}
		else if(cmp > 0)
		{
			return rank(k, x.right)+size(x.left)+1;
		}
		else
		{
			return size(x.left);
		}
	}
	
	public Iterable<Key> keys()
	{
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi)
	{
	    if(lo == null)
	    {
	    	throw new NullPointerException("Exception: first argument to keys() is null!");
	    }
	    
	    if(hi == null)
	    {
	    	throw new NullPointerException("Exception: second argument to keys() is null!");
	    }
	    
	    Queue<Key> queue = new Queue<Key>();
	    keys(root, queue, lo, hi);
	    return queue;
	}
	
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi)
	{
		if(x == null)
		{
			return;
		}
		
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		
		if(cmplo < 0)
		{
			keys(x.left, queue, lo, hi);
		}
		
		if(cmplo <= 0 && cmphi >= 0)
		{
			queue.enqueue(x.key);
		}
		
		if(cmphi > 0)
		{
			keys(x.right, queue, lo, hi);
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
		
		if(contains(hi))
		{
			return rank(hi) - rank(lo) + 1;
		}
		else
		{
			return rank(hi) - rank(lo);
		}
	}

    public int height() 
    {
        return height(root);
    }
    
    private int height(Node x) 
    {
        if (x == null) 
        {
        	return -1;
        }
        
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<Key> levelOrder() 
    {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        
        while (!queue.isEmpty()) 
        {
            Node x = queue.dequeue();
            if (x == null) 
            {
            	continue;
            }
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        
        return keys;
    }

    private boolean check() 
    {
        if (!isBST())            
        	System.out.println("Not in symmetric order");
        	
        if (!isSizeConsistent())
        	System.out.println("Subtree counts not consistent");
        	
        if (!isRankConsistent()) 
        	System.out.println("Ranks not consistent");
        	
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    private boolean isBST() 
    {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) 
    {
        if (x == null) 
        	return true;
        	
        if (min != null && x.key.compareTo(min) <= 0) 
        	return false;
        	
        if (max != null && x.key.compareTo(max) >= 0) 
        	return false;
        	
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    } 

    private boolean isSizeConsistent() 
    { 
    	return isSizeConsistent(root); 
    }
    
    private boolean isSizeConsistent(Node x) 
    {
        if (x == null) 
        	return true;
        	
        if (x.N != size(x.left) + size(x.right) + 1) 
        	return false;
        	
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    } 

    private boolean isRankConsistent() 
    {
        for (int i = 0; i < size(); i++)
        {    
        	if (i != rank(select(i))) 
        		return false;
        }
        
        for (Key key : keys())
        {    
        	if (key.compareTo(select(rank(key))) != 0) 
        		return false;
        }
        
        return true;
    }

    //---------Test code--------
    public static void main(String[] args) 
    { 
        BST<String, Integer> st = new BST<String, Integer>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("c", 3);
        st.put("f", 4);
        st.put("d", 5);
        st.put("t", 6);
        st.put("s", 7);
        st.put("w", 8);


        for (String s : st.levelOrder())
            System.out.println(s + " " + st.get(s));

        System.out.println("---------------------------------");

        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}

