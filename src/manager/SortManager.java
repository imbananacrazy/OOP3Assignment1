package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Comparator;

import shapes.*;
import utilities.SortUtility;

/**
 * The SortManager class is responsible for managing the
 * loading, sorting, and displaying of shape data
 *
 * <p>
 * This class reads shape data from a file, stores the shapes in an array,
 * calculates their height, volume, and base area, sorts them using a algorithm defined in the run configuration arguments and comparator, and prints
 * selected results along with the sorting time
 * </p>
 *
 * <p>
 * Sorting algorithms include bubble sort, selection sort, insertion sort,
 * merge sort, quick sort, and heap sort
 * </p>
 *
 * @author our names
 */
public class SortManager
{
	/** Array of shapes loaded from the input file. */
    Shape[] shapes;
    
    /** Path of the input file containing shape data. */
    String filePath;
    
    /** Comparison type: 'h' = height, 'a' = area, 'v' = volume. */
    char compareType;
    
    /** Sort type: 'b' = bubble, 's' = selection, 'i' = insertion, 
     *  'm' = merge, 'q' = quick, 'z' = heap. */
    char sortType;

    /**
     * Constructor: reads arguments defined in run configuration and parses them into their respective variables on class creation.
     * @param args the arguments defined in run configuration (fileName, sortType, compareType).
     */
    public SortManager(String[] args)
    {
        for(int i = 0; i < args.length; i++)
        {
            String arg = args[i].toLowerCase();

            if(arg.startsWith("-f")) filePath = arg.substring(2);
            else if(arg.startsWith("-t")) compareType = arg.substring(2).charAt(0);
            else if(arg.startsWith("-s")) sortType = arg.substring(2).charAt(0);
            else
            {
            	System.out.println("Invalid file name argument.");
            	return;
            }
        }

        if(filePath == null || compareType == 0 || sortType == 0)
        {
            System.out.println("Invalid arguments.");
            System.exit(1);
        }

        loadShapes();
        sortShapes();
    }

    /**
     * Loads shapes defined in fileName into an array of size defined in the first line of fileName. Display error is fileName is not found in /res folder.
     */
    void loadShapes()
    {
        try
        {
        	File file = new File(filePath);
            Scanner input = new Scanner(file);

            int total = input.nextInt();
            shapes = new Shape[total];

            for(int i = 0; i < total; i++)
            {
                String type = input.next();

                if(type.equalsIgnoreCase("Cylinder"))
                {
                    double h = input.nextDouble();
                    double r = input.nextDouble();
                    shapes[i] = new Cylinder(h, r);
                }
                else if(type.equalsIgnoreCase("Cone"))
                {
                    double h = input.nextDouble();
                    double r = input.nextDouble();
                    shapes[i] = new Cone(h, r);
                }
                else if(type.equalsIgnoreCase("Pyramid"))
                {
                    double h = input.nextDouble();
                    double s = input.nextDouble();
                    shapes[i] = new Pyramid(h, s);
                }
                else if(type.equalsIgnoreCase("SquarePrism"))
                {
                    double h = input.nextDouble();
                    double s = input.nextDouble();
                    shapes[i] = new SquarePrism(h, s);
                }
                else if(type.equalsIgnoreCase("TriangularPrism"))
                {
                    double h = input.nextDouble();
                    double s = input.nextDouble();
                    shapes[i] = new TriangularPrism(h, s);
                }
                else if(type.equalsIgnoreCase("PentagonalPrism"))
                {
                    double h = input.nextDouble();
                    double s = input.nextDouble();
                    shapes[i] = new PentagonalPrism(h, s);
                }
                else if(type.equalsIgnoreCase("OctagonalPrism"))
                {
                    double h = input.nextDouble();
                    double s = input.nextDouble();
                    shapes[i] = new OctagonalPrism(h, s);
                }
            }

            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(1);
        }
    }


    /**
     * Sorts shapes based on run configuration arguments.
     * 
     * <p>
     * After sorting is complete, it prints results and time it took to sort in ms.
     * </p>
     * 
     * <p>
     * Comparison types:
     * <ul>
     *     <li>h = height</li>
     *     <li>a = base area</li>
     *     <li>v = volume</li>
     * </ul>
     * 
     * Sorting algorithms:
     * <ul>
     *     <li>b = bubble sort</li>
     *     <li>s = selection sort</li>
     *     <li>i = insertion sort</li>
     *     <li>m = merge sort</li>
     *     <li>q = quick sort</li>
     *     <li>z = heap sort</li>
     * </ul>
     * </p>
     */
    void sortShapes()
    {
        Comparator<Shape> comparator;

        // choose comparison type
        if(compareType == 'h') comparator = (s1, s2) -> s1.compareTo(s2);
        else if(compareType == 'a') comparator = new BaseAreaComparator();
        else comparator = new VolumeComparator();

        long startTime = System.nanoTime();

        // choose sorting algorithm
        if(sortType == 'b') SortUtility.bubbleSort(shapes, comparator);
        else if(sortType == 's') SortUtility.selectionSort(shapes, comparator);
        else if(sortType == 'i') SortUtility.insertionSort(shapes, comparator);
        else if(sortType == 'm') SortUtility.mergeSort(shapes, comparator);
        else if(sortType == 'q') SortUtility.quickSort(shapes, comparator);
        else if(sortType == 'z') SortUtility.heapSort(shapes, comparator);
        else
        {
            System.out.println("Invalid sort type.");
            return;
        }

        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1000000.0;

        printResults();
        System.out.println("Sorting Time: " + timeMs + " ms");
    }

    /** Prints the first, last, and every 1000th shape and its corresponding data defined in configuration arguments (compareType) */
    void printResults() 
    {
        if (shapes.length == 0) return;
        
        if(compareType == 'h')
        {
        	System.out.printf("First element is: %25s%25s%f%n", shapes[0].getClass().getName(), "Height: ", shapes[0].getHeight());

            for (int i = 999; i < shapes.length; i += 1000) 
            {
                System.out.printf("%dth element: %25s%25s%f%n", i + 1, shapes[i].getClass().getName(), "Height: ", shapes[i].getHeight());
            }

            int lastIndex = shapes.length - 1;
            System.out.printf("Last element is: %25s%25s%f%n", shapes[lastIndex].getClass().getName(), "Height: ", shapes[lastIndex].getHeight());
        }
        else if(compareType == 'a')
        {
        	System.out.printf("First element is: %25s%25s%f%n", shapes[0].getClass().getName(), "Area: ", shapes[0].calcBaseArea());

            for (int i = 999; i < shapes.length; i += 1000) 
            {
                System.out.printf("%dth element: %25s%25s%f%n", i + 1, shapes[i].getClass().getName(), "Area: ", shapes[i].calcBaseArea());
            }

            int lastIndex = shapes.length - 1;
            System.out.printf("Last element is: %25s%25s%f%n", shapes[lastIndex].getClass().getName(), "Area: ", shapes[lastIndex].calcBaseArea());
        }
        else
        {
        	System.out.printf("First element is: %25s%25s%f%n", shapes[0].getClass().getName(), "Volume: ", shapes[0].calcVolume());

            for (int i = 999; i < shapes.length; i += 1000) 
            {
                System.out.printf("%dth element: %25s%25s%f%n", i + 1, shapes[i].getClass().getName(), "Volume: ", shapes[i].calcVolume());
            }

            int lastIndex = shapes.length - 1;
            System.out.printf("Last element is: %25s%25s%f%n", shapes[lastIndex].getClass().getName(), "Volume: ", shapes[lastIndex].calcVolume());
        }
    }
}
