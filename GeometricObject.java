package geo;
public abstract class GeometricObject implements Comparable<GeometricObject>{
    protected GeometricObject () {

    }

    public abstract double getArea();

    public abstract double getPerimeter();

    //public abstract boolean contains(double x, double y);

    //public abstract boolean contains(GeometricObject g);

    //public abstract boolean overlaps(GeometricObject g);


    public int compareTo(GeometricObject g) {
        if (this.getArea() > g.getArea()) {
            return 1;
        }
        else if (this.getArea() < g.getArea()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}