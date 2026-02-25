package utilities;
import java.util.Comparator;
import shapes.*;

public class SortUtility 
{
	public static void bubbleSort(Shape[] arr, Comparator<Shape> comparator) 
	{
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) 
        {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) 
            {
                if (comparator.compare(arr[j], arr[j + 1]) < 0) 
                {
                    Shape temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
	}
    

	public static void selectionSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	public static void insertionSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	public static void mergeSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	public static void quickSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	public static void heapSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
}