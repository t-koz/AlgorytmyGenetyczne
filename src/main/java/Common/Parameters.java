package Common;

import java.io.Serializable;

public class Parameters implements Serializable {
    double param1;
    double param2;
    int PopulationCount;
    int Generations;
    AlgoritmType type;
    boolean withGraph;

    public OptimizationFunctions getFun() {
        return fun;
    }

    OptimizationFunctions fun;

    public int getPopulationCount() {
        return PopulationCount;
    }

    public double getParam1() {
        return param1;
    }

    public double getParam2() {
        return param2;
    }

    public boolean isWithGraph() {
        return withGraph;
    }

    public int getGenerations() {
        return Generations;
    }

    public AlgoritmType GetAlgoritmType(){
        return type;
    }

    public Parameters(int PopulationCount, int Generations, AlgoritmType type, boolean withGraph, OptimizationFunctions fun, double param1, double param2) {
        this.param1 = param1;
        this.param2 = param2;
        this.PopulationCount = PopulationCount;
        this.Generations = Generations;
        this.type = type;
        this.withGraph = withGraph;
        this.fun = fun;
    }
}
