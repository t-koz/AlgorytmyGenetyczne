package AlgorithmDE;

import java.text.DecimalFormat;

public class Point {
    public double X;
    public double Y;
    DecimalFormat df = new DecimalFormat("+#,##0.0000000000;-#");


    public Point(double X, double Y){
        this.X = Double.valueOf(df.format(X));
        this.Y = Double.valueOf(df.format(Y));
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
