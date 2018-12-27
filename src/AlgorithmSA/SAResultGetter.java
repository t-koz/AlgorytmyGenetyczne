package AlgorithmSA;

import Common.OptimizationFunctions;
import Common.Parameters;

import java.util.ArrayList;
import java.util.List;

public class SAResultGetter {
    Parameters parameters;
    int populationCount;
    OptimizationFunctions fun;
    Generation generation;

    public SAResultGetter(Parameters parameters){
        this.parameters = parameters;
        this.populationCount = parameters.getPopulationCount();
        this.fun = parameters.getFun();
        generation = new Generation(parameters.getPopulationCount(), parameters.getGenerations(), parameters.getFun(), parameters.getParam1(), parameters.getParam2());
        generation.GenerateResultPopulation();
    }

    public double[][] GetOutputPopulationToArray() {
        List<Point> listToSend = generation.getStartList();
        double[][] ArrayPoints = new double[populationCount][];
        int counter = 0;
        for (Point item : listToSend) {
            ArrayPoints[counter] = new double[]{item.getX(), item.getY(), item.getResult(fun)};
            counter++;
        }

        return ArrayPoints;
    }
}
