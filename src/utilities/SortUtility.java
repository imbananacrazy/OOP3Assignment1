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
	
	public static void mergeSort(Shape[] arr, Comparator<Shape> comp) {
        mergeSortRecursive(arr, 0, arr.length - 1, comp);
    }

    private static void mergeSortRecursive(Shape[] arr, int left, int right,
                                           Comparator<Shape> comp) {

        if (left < right) {

            int mid = (left + right) / 2;

            mergeSortRecursive(arr, left, mid, comp);
            mergeSortRecursive(arr, mid + 1, right, comp);

            merge(arr, left, mid, right, comp);
        }
    }

    private static void merge(Shape[] arr, int left, int mid, int right,
                              Comparator<Shape> comp) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        Shape[] L = new Shape[n1];
        Shape[] R = new Shape[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {

            if (comp.compare(L[i], R[j]) > 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }

	
	 public static void quickSort(Shape[] arr, Comparator<Shape> comp) {
        quickSortRecursive(arr, 0, arr.length - 1, comp);
    }

    private static void quickSortRecursive(Shape[] arr, int low, int high,
                                           Comparator<Shape> comp) {

        if (low < high) {

            int pi = partition(arr, low, high, comp);

            quickSortRecursive(arr, low, pi - 1, comp);
            quickSortRecursive(arr, pi + 1, high, comp);
        }
    }

    private static int partition(Shape[] arr, int low, int high,
                                 Comparator<Shape> comp) {

        Shape pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (comp.compare(arr[j], pivot) > 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
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