package AlgorithmSA;

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
                return getBeale();
            case Booth:
                return getBooth();
            case Matyas:
                return getMatyas();
            default: return 0;
        }
    }

    private double getMatyas() {
        double first = 0.26 * (Math.pow(getX(), 2) + Math.pow(getY(), 2));
        double second = 0.48 * getX() * getY();
        return  first - second;
    }

    private double getBooth() {
        double first = Math.pow(getX() + 2 * getY() - 7, 2);
        double second = Math.pow(2 * getX() + getY() - 5, 2);
        return first + second;
    }

    private double getBeale() {
        double first = Math.pow(1.5 - getX() * getY(), 2);
        double second = Math.pow(2.25 - getX() + getX() * Math.pow(getY(), 2), 2);
        double third  = Math.pow(2.625 - getX() + getX() * Math.pow(getY(), 3), 2);
        return first + second + third;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
}