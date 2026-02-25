package shapes;

public class Pyramid extends Shape
{
    double side;

    public Pyramid(double height, double side)
    {
        super(height);
        this.side = side;
    }

    public double getSide()
    {
        return side;
    }

    @Override
    public double calcBaseArea()
    {
        return side * side;
    }

    @Override
    public double calcVolume()
    {
        return (side * side * getHeight()) / 3.0;
    }
}
