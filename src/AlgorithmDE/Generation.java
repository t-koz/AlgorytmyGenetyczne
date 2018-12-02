package AlgorithmDE;

import java.util.ArrayList;
import java.util.List;

public class Generation {
    private List<Point> ActualPopulation = new ArrayList<>();
    private double F;
    private double CR;
    private int PopulationCount;
    private int Repeats;

    public List<Point> getActualPopulation() {
        return ActualPopulation;
    }
    public Generation(double F, double CR, int PopulationCount, int Repeats){
        this.F = F;
        this.CR = CR;
        this.PopulationCount = PopulationCount;
        this.Repeats = Repeats;
    }

    public void GenerateResultPopulation() {
        
    }
}
