package algorithms;

public class FixedCapacityStack<Item> 
{
	private Item[] m_array;
	private int m_size;
	private int m_cap;
	
	@SuppressWarnings("unchecked")
	public FixedCapacityStack(int capacity)
	{
		//Generic type array is not allowed in Java. 
		this.m_array = (Item[]) new Object[capacity];
		this.m_cap = capacity;
	}
	
	private void resize(int resize)
	{
		Item[] temp = (Item[]) new Object[resize];
		
		for(int i = 0; i < this.m_size; i++)
		{
			temp[i] = this.m_array[i];
		}
		this.m_cap = resize;
		this.m_array = temp;
	}
	
	public boolean isFull()
	{
		return (m_size == m_cap);
	}
	
	public boolean isEmpty()
	{
		return this.m_size==0;
	}
	
	public int size()
	{
		return this.m_size;
	}
	
	public Item pop()
	{
		return this.m_array[--m_size];
	}
	
	public void push(Item item)
	{
		if(!isFull())
		{
			this.m_array[m_size++] = item;
		}
		else
		{
			this.resize(this.m_cap*2);
			this.m_array[m_size++] = item;
		}
	}
	

	public static void main(String[] args) 
	{
	    FixedCapacityStack<String> s_test = new FixedCapacityStack<String>(10);
	    s_test.push("abc");
	    s_test.push("def");
	    s_test.push("hig");
	    s_test.push("khl");
	    for(int i = s_test.m_size; i>0; i--)
	    {
	    	System.out.println("Item is :" + s_test.pop());
	    	System.out.println("Current size is :" + s_test.size());
	    }
	}
}
