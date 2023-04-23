package geo;
public class Geometry {
    /**
     * @return the distance between (x1, y1) and (x2, y2). */
    public static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}