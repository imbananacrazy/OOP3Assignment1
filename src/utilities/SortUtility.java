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
    

	public static void selectionSort(Shape[] arr, Comparator<Shape> comp) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            int maxIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (comp.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            swap(arr, i, maxIndex);
        }
    }
	
	public static void insertionSort(Shape[] arr, Comparator<Shape> comp) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {

            Shape key = arr[i];
            int j = i - 1;

            while (j >= 0 && comp.compare(arr[j], key) < 0) {
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
	
	public static void heapSort(Shape[] shapes, Comparator<Shape> comparator)
	{
		
	}
}