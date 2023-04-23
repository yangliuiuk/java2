package geo;
public class Rectangle extends GeometricObject{
    private double x;
    private double y;
    private double width;
    private double height;

    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public boolean contains(double x, double y) {
        return ((Math.abs(this.x - x) <= width / 2) && (Math.abs(this.y - y) <= height / 2));
    }

    public boolean contains(Rectangle r) {
        return this.contains(r.x - r.width / 2, r.y - r.height / 2) 
                && this.contains(r.x - r.width / 2, r.y + r.height / 2)
                && this.contains(r.x + r.width / 2, r.y - r.height / 2)
                && this.contains(r.x + r.width / 2, r.y + r.height / 2);
    }

    public boolean overlaps(Rectangle r) {
        return Math.abs(this.x - r.x) <= (this.width + r.width) / 2
                && Math.abs(this.y - r.y) <= (this.height + r.height) / 2;
    }













}