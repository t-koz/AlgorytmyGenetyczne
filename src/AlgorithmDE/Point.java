package AlgorithmDE;

public class Point {
    public double X;
    public double Y;
    public double RosenBrock;

    public Point(double X, double Y){
        this.X = X;
        this.Y = Y;
        RosenBrock = CountRosenBrock();
    }

    public double CountRosenBrock() {
        double temp =  Math.pow(1 - getX(), 2);
        double temp2 = Math.pow(getY() - (getX() * getX()), 2);
        return temp + temp2;
    }

    public double getRosenBrock() {
        return RosenBrock;
    }

    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
}
