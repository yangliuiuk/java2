package geo;
public class Circle extends GeometricObject{
    private double x;
    private double y;
    private double radius;

    public Circle() {
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    public Circle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    public double getPerimeter() {
        return Math.PI * 2 * this.radius;
    }

    public boolean contains(double x, double y) {
        return Geometry.distance(this.x, this.y, x, y) < this.radius;
    }

    public boolean contains(Circle c) {
        return Geometry.distance(this.x, this.y, c.x, c.y) 
                + c.radius < this.radius;
    }

    public boolean overlaps(Circle c) {
        return Geometry.distance(this.x, this.y, c.x, c.y) <= this.radius 
                + c.radius;
    }




}