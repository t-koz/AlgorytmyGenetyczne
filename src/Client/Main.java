package Client;

import Common.AlgoritmType;
import Common.OptimizationFunctions;
import Common.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    private static double F = 0.5;
    private static double CR = 0.3;
    private static int PopulationCount = 20;
    private static int Generations = 50;
    private static OptimizationFunctions fun = OptimizationFunctions.Matyas;

    public static void main(String[] args) {
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        boolean isConnected = false;
        double[][] outputArray = new double[PopulationCount][3];
        Parameters parameters;
        parameters = new Parameters(F, CR, PopulationCount, Generations, AlgoritmType.SA, false, fun);

        //sending parameters
        System.out.printf("Waiting for connector...");
        while (!isConnected) {
            try {
                socket = new Socket("localHost", 4445);
                System.out.println("Connected with Connector!");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(parameters);
                socket.close();
                outputStream.flush();
            } catch (IOException e) {
                System.out.printf(".");
            }
        }
        isConnected = false;
        System.out.printf("Waiting for results...");
        //receive result
        while (!isConnected) {
            try {
                socket = new Socket("localHost", 4665);
                System.out.println("Connected to Connector!");
                isConnected = true;
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputArray = (double[][]) inputStream.readObject();
                socket.close();
            } catch (IOException e) {
                System.out.printf(".");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Got Results!");
        for (double[] number : outputArray) {
            System.out.println("X = " + number[0] + " Y = " + number[1] + " Result = " + number[2]);
        }
        double sum = 0;
        for (double[] number : outputArray) {
            sum += number[2];
        }
        System.out.printf("Result average: ");
        System.out.println(sum / PopulationCount);
    }
}
