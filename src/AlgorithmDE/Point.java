package AlgorithmDE;

public class Point {
    public double X;
    public double Y;

    public Point(double X, double Y){
        this.X = X;
        this.Y = Y;
    }

    public double getRosenBrock() {
        double temp =  Math.pow(1 - getX(), 2);
        double temp2 = Math.pow(getY() - (getX() * getX()), 2);
        return temp + temp2;
    }

    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
}
