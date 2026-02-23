package manager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shapes.Shape;

public class SortManager 
{
	Shape[] shapes;
	String fileName;
	char compareType;
	char sortType;
	
	public SortManager(String[] args)
	{
		for(String arg : args)
		{
			System.out.println(arg);
			arg = arg.toLowerCase();
			if(arg.startsWith("-f"))
			{
				fileName = arg.substring(2);
				System.out.println(fileName);
			}
			else if(arg.startsWith("-t"))
			{
				compareType = arg.substring(2).charAt(0);
				System.out.println(compareType);
			}
			else if(arg.startsWith("-s"))
			{
				sortType = arg.substring(2).charAt(0);
				System.out.println(sortType);
			}
		}
		
		LoadShapes();
		SortShapes();
	}
	
	public String getFileName() 
	{
		return fileName;
	}

	void LoadShapes()
	{
		File inputFile = new File( "res/" + getFileName());
		Scanner input = null;

		try
		{
			input = new Scanner( inputFile );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		
		//get first line in file
		System.out.println(input.nextInt());
		int numberOfShapes = input.nextInt();
		
		//set shapes array size to number written in first line in file
		shapes = new Shape[numberOfShapes];
	}
	
	void SortShapes()
	{
		
	}
}
