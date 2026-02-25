package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Comparator;

import shapes.*;
import utilities.SortUtility;

public class SortManager
{
    Shape[] shapes;
    String fileName;
    char compareType;
    char sortType;

    public SortManager(String[] args)
    {
        for(int i = 0; i < args.length; i++)
        {
            String arg = args[i].toLowerCase();

            if(arg.startsWith("-f")) fileName = arg.substring(2);
            else if(arg.startsWith("-t")) compareType = arg.substring(2).charAt(0);
            else if(arg.startsWith("-s")) sortType = arg.substring(2).charAt(0);
            else
            {
            	System.out.println("Invalid file name argument.");
            	return;
            }
        }

        if(fileName == null || compareType == 0 || sortType == 0)
        {
            System.out.println("Invalid arguments.");
            System.exit(1);
        }

        loadShapes();
        sortShapes();
    }


    void loadShapes()
    {
        try
        {
        	File file = new File("res/" + fileName);
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


    private void printResults() 
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
