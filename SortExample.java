package algorithms;

public class SortExample 
{	
	public enum SORT_TYPE
	{
		SELECTION(0),
		INSERTION(1),
		SHELL(2),
		MERGE(3);
		
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
			default:
				break;
		}
	}
	
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
			h = h/3;
		}
	}
	
    public static void merge_sort(Comparable[] a) 
    {
        Comparable[] aux = new Comparable[a.length];
        inner_sort(a, aux, 0, a.length-1);
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
	
	public static void main(String[] args) 
	{
		String[] a = {"asd", "hjk", "hau", "lkj"};
		Integer[] b = {3,2,1,5,6,8,7};
		
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
	}
}
