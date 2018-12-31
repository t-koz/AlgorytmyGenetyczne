package AlgorithmDE;

import Common.OptimizationFunctions;
import Common.Parameters;
import java.util.List;

public class ResultGetter {
    Parameters parameters;
    Generation generation;
    int populationCount;
    OptimizationFunctions fun;

    public ResultGetter(Parameters parameters) {
        this.parameters = parameters;
        this.populationCount = parameters.getPopulationCount();
        this.fun = parameters.getFun();
        generation = new Generation(parameters.getParam1(), parameters.getParam2(), parameters.getPopulationCount(), parameters.getGenerations(), parameters.getFun());
        generation.GenerateResultPopulation();
    }

    double[][] GetOutputPopulationInArray() {
        List<Point> listPoints = generation.getActualPopulation();
        double[][] ArrayPoints = new double[populationCount][];
        int counter = 0;
        for (Point item : listPoints) {
            ArrayPoints[counter] = new double[]{item.getX(), item.getY(), item.getResult(fun)};
            counter++;
        }
        return ArrayPoints;
    }
}
