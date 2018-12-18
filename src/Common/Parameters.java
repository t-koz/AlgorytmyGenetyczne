package Common;

import java.io.Serializable;

public class Parameters implements Serializable {
    double F;
    double CR;
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

    public double getF() {
        return F;
    }

    public double getCR() {
        return CR;
    }

    public int getGenerations() {
        return Generations;
    }

    public Parameters(double F, double CR, int PopulationCount, int Generations, AlgoritmType type, boolean withGraph, OptimizationFunctions fun) {
        this.F = F;
        this.CR = CR;
        this.PopulationCount = PopulationCount;
        this.Generations = Generations;
        this.type = type;
        this.withGraph = withGraph;
        this.fun = fun;
    }
}
