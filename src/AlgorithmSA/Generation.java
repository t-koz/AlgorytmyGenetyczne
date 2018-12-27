package AlgorithmSA;

import Common.OptimizationFunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    private int PopulationCount;
    private int Repeats;
    private OptimizationFunctions function;
    private double actualTemp;
    private double newTemperatureMultipler;
    private List<Point> startList = new ArrayList<>();
    private List<Point> tempList = new ArrayList<>();

    public Generation(int PopulationCount, int Repeats, OptimizationFunctions function, double actualTemp, double newTemperatureMultipler){
        this.PopulationCount = PopulationCount;
        this.Repeats = Repeats;
        this.function = function;
        this.actualTemp = actualTemp;
        this.newTemperatureMultipler = newTemperatureMultipler;
    }

    public void GenerateResultPopulation() {
        startList = GenerateRandomPopulation();
        for (int i = 0; i < Repeats; i++){
            tempList = GenerateRandomPopulation();
            startList = SelectBetterUnits();
            SetNewTemperature();
            tempList.clear();
        }
    }

    private void SetNewTemperature() {
        
    }

    private List<Point> SelectBetterUnits() {
        List<Point> newGenerationList = new ArrayList<>();
        Random random = new Random();
        double currentResult;
        double randomDouble;
        for (int i = 0; i < PopulationCount; i++) {
            currentResult = tempList.get(i).getResult(function) - startList.get(i).getResult(function);
            if (currentResult < 0){
                newGenerationList.add(tempList.get(i));
            }else {
                randomDouble = random.nextDouble();
                if (randomDouble < Math.exp((-currentResult) / actualTemp)){
                    newGenerationList.add(tempList.get(i));
                }else {
                    newGenerationList.add(startList.get(i));
                }
            }
        }
        return newGenerationList;
    }

    private List<Point> GenerateRandomPopulation() {
        List<Point> listToReturn = new ArrayList<>();
        Random random = new Random();
        double rangeMax = setRangeForRandomGeneratedNumbers();
        double rangeMin = -rangeMax;
        for (int i = 0; i < PopulationCount; i++){
            listToReturn.add(new Point(rangeMin +(rangeMax - rangeMin) * random.nextDouble(),rangeMin +(rangeMax - rangeMin) * random.nextDouble()));
        }
        return listToReturn;
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
