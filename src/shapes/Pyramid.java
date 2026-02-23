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
    double calcBaseArea()
    {
        return side * side;
    }

    @Override
    double calcVolume()
    {
        return (side * side * getHeight()) / 3.0;
    }
}
