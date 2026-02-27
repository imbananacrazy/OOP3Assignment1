package utilities;
import java.util.Comparator;
import shapes.*;

/**
 * The SortUtility class contains static generic sorting algorithms.
 * 
 * <p>
 * All sorting methods sort in descending order based on the
 * Comparator provided.
 * </p>
 * 
 * <p>
 * Supported algorithms:
 * Bubble Sort, Selection Sort, Insertion Sort,
 * Merge Sort, Quick Sort, and Heap Sort.
 * </p>
 * 
 * @author Your Names
 */
public class SortUtility 
{
	/**
     * Sorts an array using the Bubble Sort algorithm.
     * 
     * @param <T> the type of elements in the array
     * @param arr the array to be sorted
     * @param comparator the comparator used for comparison
     */
	public static <T> void bubbleSort(T[] arr, Comparator<? super T> comparator) 
    {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) 
        {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) 
            {
				// Compare adjacent elements
                if (comparator.compare(arr[j], arr[j + 1]) < 0) 
                {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
			// Stop early if no swaps happened
            if (!swapped) break;
        }
    }
	
    /**
     * Sorts an array using the Selection Sort algorithm.
     * 
     * @param <T> the type of elements
     * @param arr the array to sort
     * @param comp the comparator used for ordering
     */
	public static <T> void selectionSort(T[] arr, Comparator<? super T> comp) 
    {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) 
        {
            int maxIndex = i;

            for (int j = i + 1; j < n; j++) 
            {
				// Find the largest element for descending order
                if (comp.compare(arr[j], arr[maxIndex]) > 0)
                    maxIndex = j;
            }
			// Swap with current position
            T temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }

	/**
     * Sorts an array using the Insertion Sort algorithm.
     * 
     * @param <T> the type of elements
     * @param arr the array to sort
     * @param comp the comparator used for ordering
     */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comp) 
    {
        int n = arr.length;

        for (int i = 1; i < n; i++) 
        {
            T key = arr[i];
            int j = i - 1;
			
			// Shift elements to the right to make room
            while (j >= 0 && comp.compare(arr[j], key) < 0) 
            {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
	
	/**
     * Sorts an array using the Merge Sort algorithm.
     * 
     * @param <T> the type of elements
     * @param arr the array to sort
     * @param comp the comparator used for ordering
     */
	public static <T> void mergeSort(T[] arr, Comparator<? super T> comp) 
    {
        mergeSortRecursive(arr, 0, arr.length - 1, comp);
    }
	
	/**
     * Recursive helper method for Merge Sort.
     */
	static <T> void mergeSortRecursive(T[] arr, int left, int right, Comparator<? super T> comp) 
	{
		if (left < right) 
		{
			int mid = (left + right) / 2;

			mergeSortRecursive(arr, left, mid, comp);
			mergeSortRecursive(arr, mid + 1, right, comp);

			merge(arr, left, mid, right, comp);
		}
	}
	
	/**
     * Merges two sorted halves of an array.
     */
	@SuppressWarnings("unchecked")
	private static <T> void merge(T[] arr, int left, int mid, int right, Comparator<? super T> comp) 
	{
		int n1 = mid - left + 1;
		int n2 = right - mid;

		T[] L = (T[]) new Object[n1];
		T[] R = (T[]) new Object[n2];

		for (int i = 0; i < n1; i++) L[i] = arr[left + i];
		for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

		int i = 0, j = 0, k = left;

		while (i < n1 && j < n2) 
		{
			// Descending order
			if (comp.compare(L[i], R[j]) > 0)  arr[k++] = L[i++];
			else arr[k++] = R[j++];
		}

		while (i < n1) arr[k++] = L[i++];

		while (j < n2) arr[k++] = R[j++];
	}

	/**
     * Sorts an array using the Quick Sort algorithm.
     * 
     * @param <T> the type of elements
     * @param arr the array to sort
     * @param comp the comparator used for ordering
     */
	public static <T> void quickSort(T[] arr, Comparator<? super T> comp) 
    {
        quickSortRecursive(arr, 0, arr.length - 1, comp);
    }

	/**
     * Recursive helper for Quick Sort.
     */
	private static <T> void quickSortRecursive(T[] arr, int low, int high, Comparator<? super T> comp) 
	{
		if (low < high) 
		{
			int pi = partition(arr, low, high, comp);

			quickSortRecursive(arr, low, pi - 1, comp);
			quickSortRecursive(arr, pi + 1, high, comp);
		}
	}

	/**
     * Partitions the array around a pivot element.
     */
	private static <T> int partition(T[] arr, int low, int high, Comparator<? super T> comp) 
    {
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) 
        {
            if (comp.compare(arr[j], pivot) > 0) 
            {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
	
	/**
     * Sorts an array using the Heap Sort algorithm.
     * 
     * @param <T> the type of elements
     * @param arr the array to sort
     * @param comparator the comparator used for ordering
     */
	public static <T> void heapSort(T[] arr, Comparator<? super T> comparator)
    {
        int n = arr.length;
		
		// Build heap
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i, comparator);
		
		// Extract elements one by one
        for (int i = n - 1; i > 0; i--) 
        {
            T temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0, comparator);
        }
    }
	
	/**
     * Maintains the heap property.
     */
	private static <T> void heapify(T[] arr, int n, int i, Comparator<? super T> comparator)
    {
        int smallest = i;

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comparator.compare(arr[left], arr[smallest]) < 0) smallest = left;
        if (right < n && comparator.compare(arr[right], arr[smallest]) < 0) smallest = right;

        if (smallest != i) 
        {
            T temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            heapify(arr, n, smallest, comparator);
        }
    }
}
