package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Comparator;

import shapes.*;

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

            if(arg.startsWith("-f"))
            {
                fileName = arg.substring(2);
            }
            else if(arg.startsWith("-t"))
            {
                compareType = arg.substring(2).charAt(0);
            }
            else if(arg.startsWith("-s"))
            {
                sortType = arg.substring(2).charAt(0);
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


    private void loadShapes()
    {
        try
        {
            File file = new File(fileName);
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


    private void sortShapes()
    {
        Comparator<Shape> comparator;

        // choose comparison type
        if(compareType == 'h')
        {
            comparator = (s1, s2) -> s1.compareTo(s2);
        }
        else if(compareType == 'a')
        {
            comparator = new BaseAreaComparator();
        }
        else
        {
            comparator = new VolumeComparator();
        }

        long startTime = System.nanoTime();

        // choose sorting algorithm
        if(sortType == 'b')
        {
            SortUtility.bubbleSort(shapes, comparator);
        }
        else if(sortType == 's')
        {
            SortUtility.selectionSort(shapes, comparator);
        }
        else if(sortType == 'i')
        {
            SortUtility.insertionSort(shapes, comparator);
        }
        else if(sortType == 'm')
        {
            SortUtility.mergeSort(shapes, comparator);
        }
        else if(sortType == 'q')
        {
            SortUtility.quickSort(shapes, comparator);
        }
        else if(sortType == 'z')
        {
            SortUtility.heapSort(shapes, comparator);
        }
        else
        {
            System.out.println("Invalid sort type.");
            return;
        }

        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1000000.0;
        System.out.println("Sorting Time: " + timeMs + " ms");

        printResults();
    }


    private void printResults()
    {
        if(shapes.length == 0)
        {
            return;
        }

        System.out.println("First Value: " + shapes[0].calcVolume());

        for(int i = 1000; i < shapes.length; i = i + 1000)
        {
            System.out.println("Index " + i + ": " + shapes[i].calcVolume());
        }

        System.out.println("Last Value: " + shapes[shapes.length - 1].calcVolume());
    }
}
