package utilities;
import java.util.Comparator;
import shapes.*;

public class SortUtility 
{
	public static <T> void bubbleSort(T[] arr, Comparator<? super T> comparator) 
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
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
    

	public static <T> void selectionSort(T[] arr, Comparator<? super T> comp) 
    {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) 
        {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) 
            {
                if (comp.compare(arr[j], arr[maxIndex]) > 0)
                    maxIndex = j;
            }

            T temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }
	
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comp) 
    {
        int n = arr.length;

        for (int i = 1; i < n; i++) 
        {
            T key = arr[i];
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
	public static <T> void heapSort(T[] arr, Comparator<? super T> comparator)
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, comparator);

        for (int i = n - 1; i > 0; i--) 
        {
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0, comparator);
        }
    }

	private static <T> void heapify(T[] arr, int n, int i, Comparator<? super T> comparator)
    {
        int smallest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(arr[left], arr[smallest]) < 0)
            smallest = left;

        if (right < n && comparator.compare(arr[right], arr[smallest]) < 0)
            smallest = right;

        if (smallest != i) 
        {
            T temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            heapify(arr, n, smallest, comparator);
        }
    }
}