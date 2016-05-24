package algorithms;

public class SortExample 
{	
	//-----------Enum of Sort Type---------------------
	public enum SORT_TYPE
	{
		SELECTION(0),
		INSERTION(1),
		SHELL(2),
		MERGE(3),
		MERGE_BU(4),
		QSORT(5);
		
		private int type;
		
		SORT_TYPE(int n)
		{
			this.type = n;
		}
		
		private int getType()
		{
			return this.type;
		}

	}
	
	//----------------Organizer Function-----------------
	public static void sort(Comparable[] a, SORT_TYPE type)
	{
		switch(type)
		{
			case SELECTION:
				selection(a);
				break;
			case INSERTION:
				insertion(a);
				break;
			case SHELL:
				shell(a,3);
				break;
			case MERGE:
				merge_sort(a);
				break;
			case MERGE_BU:
				merge_sort_buttomup(a);
			case QSORT:
				qsort(a);
			default:
				break;
		}
	}
	
	//-------------------------Selection Sort-------------------
	public static void selection(Comparable[] a)
	{
		int length = a.length;
		
		for(int i = 0; i < length; i++)
		{
			int min = i;
			for(int j = i+1; j < length; j++)
			{
				if(less(a[j], a[i]))
				{
					min = j;
				}
			}
			exchange(a, i, min);
		}
	}
	
	//------------------Insertion Sort-------------------
	public static void insertion(Comparable[] a)
	{
		int length = a.length;
		
		for(int i = 1; i<length; i++)
		{
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
			{
				exchange(a, j, j-1);
			}
		}
	}
	
	//------------Shell Sort-----------------------------
	public static void shell(Comparable[] a, int seg_length)
	{
		int length = a.length;
		int h = 1;
		
		while(h < length/seg_length)
		{
			h = seg_length*h + 1;
		}
		
		while(h >= 1)
		{
			for(int i = h; i<length; i++)
			{
				for(int j = i; j>=h && less(a[j], a[j-h]); j-=h)
				{
					exchange(a, j, j-h);
				}
			}
			h = h/seg_length;
		}
	}
	
	//------------------Merge Sort---------------------------
    public static void merge_sort(Comparable[] a) 
    {
        Comparable[] aux = new Comparable[a.length];
        inner_sort(a, aux, 0, a.length-1);
    }
    
    public static void merge_sort_buttomup(Comparable[] a)
    {
    	int length = a.length;
    	Comparable[] aux = new Comparable[length];
    	
    	for(int n = 1; n < length; n+=n)
    	{
    		for(int i = 0; i<length-n; i+=(n+n))
    		{
    			int lo = i;
    			int m = i+n-1;
    			int hi = Math.min(i+n+n-1, length-1);
    			merge(a, aux, lo, m, hi);
    		}
    	}
    }
	
    private static void inner_sort(Comparable[] a, Comparable[] aux, int lo, int hi) 
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        inner_sort(a, aux, lo, mid);
        inner_sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
	
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) 
    {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) 
        {
            if(i > mid)     
            {
            	a[k] = aux[j++];
            }
            else if (j > hi)               
            {
            	a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) 
            {
            	a[k] = aux[j++];
            }
            else                           
            {
            	a[k] = aux[i++];
            }
        }
    }
    
    //---------------Quick Sort--------------------------
    private static int partition(Comparable[] a, int lo, int hi)
    {
    	int i = lo;
    	int j = hi+1;
    	
    	Comparable v = a[lo];
    	
    	while(true)
    	{
    		while(less(a[++i], v))
    		{
    			if(i == hi)
    			{
    				break;
    			}
    		}
    		
    		while(less(v, a[--j]))
    		{
    			if(j == lo)
    			{
    				break;
    			}
    		}
    		
    		if(i>=j)
    		{
    			break;
    		}
    		
    		exchange(a, i, j);
    	}
    	exchange(a, lo, j);
    	return j;
    }
    
    private static void inner_qsort(Comparable[] a, int lo, int hi)
    {
    	if(hi <= lo)
    	{
    		return;
    	}
    	int j = partition(a, lo, hi);
    	inner_qsort(a, lo, j-1);
    	inner_qsort(a, j+1, hi);
    }
    
    public static void qsort(Comparable[] a)
    {
    	inner_qsort(a, 0, a.length-1);
    }
	
    //----------------Assistant Function----------------
	private static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}
	
	private static void exchange(Comparable[] a, int i, int j)
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static void show(Comparable[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			if(less(a[i], a[i-1]))
			{
				return false;
			}
		}
		return true;
	}
	
	//------------Test Function--------------
	public static void main(String[] args) 
	{
		String[] a = {"asd", "hjk", "hau", "lkj"};
		Integer[] b = {3,2,1,5,6,8,7};
		
		//----------------------String Array----------------------
		sort(a, SORT_TYPE.SELECTION);
		assert isSorted(a);
		show(a);
		
		sort(a, SORT_TYPE.INSERTION);
		assert isSorted(a);
		show(a);
		
		sort(a, SORT_TYPE.SHELL);
		assert isSorted(a);
		show(a);
		
		sort(a, SORT_TYPE.MERGE);
		assert isSorted(a);
		show(a);
		
		sort(a, SORT_TYPE.QSORT);
		assert isSorted(a);
		show(a);
		
		//---------------Integer Array---------------------
		sort(b, SORT_TYPE.SELECTION);
		assert isSorted(b);
		show(b);
		
		sort(b, SORT_TYPE.INSERTION);
		assert isSorted(b);
		show(b);
		
		sort(b, SORT_TYPE.SHELL);
		assert isSorted(b);
		show(b);
		
		sort(b, SORT_TYPE.MERGE);
		assert isSorted(b);
		show(b);
		
		sort(b, SORT_TYPE.MERGE_BU);
		assert isSorted(b);
		show(b);
		
		sort(b, SORT_TYPE.QSORT);
		assert isSorted(b);
		show(b);
	}
}
