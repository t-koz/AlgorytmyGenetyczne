package AlgorithmDE;


import Client.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Main {
    private double F;
    private double CR;
    private int PopulationCount;
    private int Generation;

    public static void main(String[] args) {
        Parameters parameters;
        boolean isConnected = false;
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        double [][] outputArray;

        while (!isConnected) {
            try {
                socket = new Socket("localhost", 4445);
                System.out.printf("Connected to Connector!");
                isConnected = true;
                inputStream = new ObjectInputStream(socket.getInputStream());
                parameters = (Parameters) inputStream.readObject();
                outputArray = new double[parameters.getPopulationCount()][];
                //TODO: generate new population and copy it to array
                outputArray[0] = new double[]{1, 1, 1};
                outputArray[3] = new double[]{2, 2, 2};
                outputArray[1] = new double[]{0, 0, 0};
                outputArray[2] = new double[]{5.6, 0.22, 0.33};
                //sending result
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(outputArray);
                socket.close();
                outputStream.flush();

            } catch (IOException e) {
            }catch (ClassNotFoundException e) { }
        }
    }
}