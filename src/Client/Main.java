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
    private static int PopulationCount = 10;
    private static int Generations = 2;

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
                socket = new Socket("localHost", 4665);
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputArray = (double[][]) inputStream.readObject();
                socket.close();
                outputStream.flush();
            }
            catch (SocketException e){ }
            catch (IOException e) { }
            catch (ClassNotFoundException e) { }
        }
        for (double[] number : outputArray) {
            System.out.println("X = " + number[0] + "Y = " + number[1] + "Result = " + number[2]);
        }


    }
}
