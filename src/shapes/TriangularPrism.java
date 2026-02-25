package shapes;

public class TriangularPrism extends Prism
{
	public TriangularPrism(double height, double side)
	{
		super(height, side);
	}

	@Override
	public double calcBaseArea() 
	{
		return (getSide() * getSide() * Math.sqrt(3))/4;
	}
}
