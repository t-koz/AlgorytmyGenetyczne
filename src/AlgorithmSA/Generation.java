package AlgorithmSA;

import Common.OptimizationFunctions;

public class Generation {
    private int PopulationCount;
    private int Repeats;
    private OptimizationFunctions function;
    private double startTemp;
    private double newTemperatureMultipler;

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
            SelectBetterUnits();
            SetNewTemperature();
        }
    }

    private void SetNewTemperature() {
        
    }

    private void SelectBetterUnits() {
    }

    private void GenerateNewRandomPopulation() {

    }

    private void GenerateStartPopulation() {

    }
}
