package shapes;

public class PentagonalPrism extends Prism
{
	public PentagonalPrism(double height, double side)
	{
		super(height, side);
	}

	@Override
	double calcBaseArea() 
	{
		return (5*getSide() * getSide()*Math.tan(Math.toRadians(54)))/4;
	}
}
