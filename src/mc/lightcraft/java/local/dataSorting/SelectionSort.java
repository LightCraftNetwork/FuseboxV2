package mc.lightcraft.java.local.dataSorting;

public class SelectionSort
{
	private SelectionSort()
	{
	}
	
	public static final int INCREASING  = 0;
	public static final int DECREASING = 1;

	private static void swap(double[] a, int i, int j)
	{
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	private static double[] selectionSortMax(double... a)
	{
		if (a.length <= 1) return a;
		int maxPos;
		double max;
		for (int i = 0; i < a.length - 1; i++)
		{
			max = a[i];
			maxPos = i;
			for (int j = i + 1; j < a.length; j++)
				if (max < a[j])
				{
					max = a[j];
					maxPos = j;
				}
			swap(a, i, maxPos);
		}
		return a;
	}
	
	private static double[] selectionSortMin(double... a)
	{
		if (a.length <= 1) return a;
		int minPos;
		double min;
		for (int i = 0; i < a.length - 1; i++)
		{
			min = a[i];
			minPos = i;
			for (int j = i + 1; j < a.length; j++)
				if (min > a[j])
				{
					min = a[j];
					minPos = j;
				}
			swap(a, i, minPos);
		}
		return a;
	}
	
	/**
	 * Sorts a list of double values in increasing or decreasing order. sortMode integers are static fields in the SelectionSort class.
	 * @param sortMode the mode that the sorter will use
	 * @param a the array of values you want sorted
	 * @return a new array  containing all the values in the requested order
	 */
	public static double[] selectionSort(int sortMode, double... a)
	{
		switch(sortMode) {
			case INCREASING:
				return selectionSortMin(a);
			case DECREASING:
				return selectionSortMax(a);
			default: 
				return a;
		}
	}
}
