package AlgorithmDE;


import Common.Parameters;
import java.util.List;

public class Initiator {
    Parameters parameters;
    Generation generation;
    int populationCount;

    public Initiator(Parameters parameters){
        this.parameters = parameters;
        this.populationCount = parameters.getPopulationCount();
        generation = new Generation(parameters.getF(), parameters.getCR(), parameters.getPopulationCount(), parameters.getGenerations());
        generation.GenerateResultPopulation();
    }
    double [][] ConvertListToOutputArray(){
        List<Point> listPoints = generation.getActualPopulation();
        double [][] ArrayPoints = new double[populationCount][];
        int counter = 0;
        for (Point item: listPoints) {
            ArrayPoints[counter] = new double[] {item.getX(), item.getY(), item.getRosenBrock()};
            counter++;
        }
        return ArrayPoints;
    }
}
