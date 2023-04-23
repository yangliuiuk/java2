package geo;
public class Test {
    public static void main(String[] args){
        Circle c1 = new Circle(0, 0, 2);
        Circle c2 = new Circle(1, 0, 3);
        Rectangle r1 = new Rectangle(0, 0, 2, 2);
        Rectangle r2 = new Rectangle(0, 0, 1, 1);
        System.out.println(c1.contains(c2));
        System.out.println(r1.compareTo(r2));
    }
}