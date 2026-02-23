package shapes;

public abstract class Shape implements Comparable<Shape> 
{
	double height;
	
	public Shape(double height) 
	{
		this.height = height;
	}
	
	public double getHeight() 
	{
		return height;
	}
	
	abstract double calcBaseArea();
	abstract double calcVolume();

	@Override
    public int compareTo(Shape that) 
	{
        if(this.height < that.height) return -1;
        else if(this.height > that.height) return 1;
        else return 0;
    }
}
