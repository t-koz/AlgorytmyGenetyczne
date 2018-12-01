package Client;

public class Parameters {
    double F;
    double CR;
    int PopulationCount;
    int Generations;
    AlgoritmType type;
    boolean withGraph;

    public int getPopulationCount() {
        return PopulationCount;
    }

    public Parameters(double F, double CR, int PopulationCount, int Generations, AlgoritmType type, boolean withGraph){
        this.F = F;
        this.CR = CR;
        this.PopulationCount = PopulationCount;
        this.Generations = Generations;
        this.type = type;
        this.withGraph = withGraph;
    }
}
