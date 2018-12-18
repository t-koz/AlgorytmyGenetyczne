package AlgorithmDE;

import Common.AlgoritmType;
import Common.OptimizationFunctions;

import java.text.DecimalFormat;

public class Point {
    public double X;
    public double Y;
    DecimalFormat df = new DecimalFormat("+#,##0.0000000000;-#");


    public Point(double X, double Y) {
        this.X = Double.valueOf(df.format(X));
        this.Y = Double.valueOf(df.format(Y));
    }

    private double getRosenBrock() {
        double temp = Math.pow(1 - getX(), 2);
        double temp2 = Math.pow(getY() - (getX() * getX()), 2);
        return temp + temp2;
    }
    public double getResult(OptimizationFunctions functionType){
        switch (functionType){
            case Rosenbrock:
                return getRosenBrock();
            case Beale:
                return 2;
            default: return 0;
        }
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
}
