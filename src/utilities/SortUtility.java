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
    

	public static void selectionSort(Shape[] arr, Comparator<Shape> comp) 
	{
	    int n = arr.length;

	    for (int i = 0; i < n - 1; i++) {

	        int maxIndex = i;

	        for (int j = i + 1; j < n; j++) 
	        {
	            if (comp.compare(arr[j], arr[maxIndex]) > 0) maxIndex = j;
	        }

	        Shape temp = arr[i];
	        arr[i] = arr[maxIndex];
	        arr[maxIndex] = temp;
	    }
	}
	
	public static void insertionSort(Shape[] arr, Comparator<Shape> comp) 
	{
        int n = arr.length;

        for (int i = 1; i < n; i++) 
        {

            Shape key = arr[i];
            int j = i - 1;

            while (j >= 0 && comp.compare(arr[j], key) < 0) 
            {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
	
	public static void mergeSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	public static void quickSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
	
	//edited gfg
	public static void heapSort(Shape[] shapes, Comparator<Shape> comparator)
	{
	    int n = shapes.length;

	    for (int i = n / 2 - 1; i >= 0; i--) heapify(shapes, n, i, comparator);

	    for (int i = n - 1; i > 0; i--) {

	        Shape temp = shapes[0];
	        shapes[0] = shapes[i];
	        shapes[i] = temp;

	        heapify(shapes, i, 0, comparator);
	    }
	}

	static void heapify(Shape[] arr, int n, int i, Comparator<Shape> comparator)
	{
	    int smallest = i;

	    int left = 2 * i + 1;
	    int right = 2 * i + 2;

	    if (left < n && comparator.compare(arr[left], arr[smallest]) < 0) smallest = left;
	    if (right < n && comparator.compare(arr[right], arr[smallest]) < 0) smallest = right;

	    if (smallest != i) 
	    {

	        Shape temp = arr[i];
	        arr[i] = arr[smallest];
	        arr[smallest] = temp;

	        heapify(arr, n, smallest, comparator);
	    }
	}
}