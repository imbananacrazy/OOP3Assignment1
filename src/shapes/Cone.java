package shapes;

public class Cone extends Shape
{
double radius;
	
	public Cone(double height, double radius)
	{
		super(height);
		this.radius = radius;
	}
	
	public double getRadius()
	{
		return radius;
	}

	@Override
	public double calcBaseArea() 
	{
		return Math.PI * radius * radius;
	}

	@Override
	public double calcVolume() 
	{
		return calcBaseArea() * (getHeight()/3);
	}
}
