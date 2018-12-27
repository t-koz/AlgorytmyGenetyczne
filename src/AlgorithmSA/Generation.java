package AlgorithmSA;

import Common.OptimizationFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    private int PopulationCount;
    private int Repeats;
    private OptimizationFunctions function;
    private double startTemp;
    private double newTemperatureMultipler;
    private List<Point> startList = new ArrayList<>();
    private List<Point> tempList = new ArrayList<>();

    public Generation(int PopulationCount, int Repeats, OptimizationFunctions function, double startTemp, double newTemperatureMultipler){
        this.PopulationCount = PopulationCount;
        this.Repeats = Repeats;
        this.function = function;
        this.startTemp = startTemp;
        this.newTemperatureMultipler = newTemperatureMultipler;
    }

    public void GenerateResultPopulation() {
        GenerateStartPopulation();
        for (int i = 0; i < Repeats; i++){
            GenerateNewRandomPopulation();
            startList = SelectBetterUnits();
            SetNewTemperature();
            tempList.clear();
        }
    }

    private void SetNewTemperature() {
        
    }

    private List<Point> SelectBetterUnits() {
        return  null;
    }

    private void GenerateNewRandomPopulation() {

    }

    private void GenerateStartPopulation() {
        Random random = new Random();
        double rangeMax = setRangeForRandomGeneratedNumbers();
        double rangeMin = -rangeMax;
        for (int i = 0; i < PopulationCount; i++){
            startList.add(new Point(rangeMin +(rangeMax - rangeMin) * random.nextDouble(),rangeMin +(rangeMax - rangeMin) * random.nextDouble()));
        }
    }

    private double setRangeForRandomGeneratedNumbers() {
        switch (function){
            case Matyas:
                return 3;
            case Booth:
                return  3.5;
            case Beale:
                return 4.5;
            case Rosenbrock:
                return 1;
            default: return 1;
        }
    }
}
