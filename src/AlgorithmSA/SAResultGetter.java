package AlgorithmSA;

import Common.OptimizationFunctions;
import Common.Parameters;

public class SAResultGetter {
    Parameters parameters;
    int populationCount;
    OptimizationFunctions fun;

    public SAResultGetter(Parameters parameters){
        this.parameters = parameters;
        this.populationCount = parameters.getPopulationCount();
        this.fun = parameters.getFun();
        //TODO: pass arguments to new generation
        Generation generation = new Generation();
        generation.GenerateResultPopulation();
    }

    public double[][] GetOutputPopulationToArray() {
        //TODO: make new Class Point and convert to array
        double [][] arr = new double[10][];
        return arr;
    }
}
