package Client;

import Common.AlgoritmType;
import Common.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Main {
    private static double F = 0.5;
    private static double CR = 0.3;
    private static int PopulationCount = 300;
    private static int Generations = 200;

    public static void main(String[] args) {
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        boolean isConnected = false;
        double [][] outputArray = new double[PopulationCount][3];
        Parameters parameters;
        parameters = new Parameters(F, CR, PopulationCount, Generations, AlgoritmType.DE, false);

        while (!isConnected){
            try{
                socket = new Socket("localHost", 4445);
                System.out.println("Connected with Connector!");
                isConnected = true;
                //sending parameters
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(parameters);
                //receive result
                //TODO: Make waitForResult method
                Thread.sleep(5000);
                socket = new Socket("localHost", 4665);
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputArray = (double[][]) inputStream.readObject();
                socket.close();
                outputStream.flush();
            }
            catch (SocketException e){ e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            catch (ClassNotFoundException e) { e.printStackTrace(); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        for (double[] number : outputArray) {
            System.out.println("X = " + number[0] + " Y = " + number[1] + " Result = " + number[2]);
        }
        double avg = 0;
        double sum = 0;
        for (double[] number : outputArray){
            sum += number[2];
        }
        avg = sum / PopulationCount;
        System.out.println(avg);

    }
}
