package AlgorithmDE;

import Common.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Main {
    static private Parameters parameters;

    public static void main(String[] args) {
        boolean isConnected = false;
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        double[][] outputArray;

        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(4335);
                socket = serverSocket.accept();
                System.out.printf("Connected to Connector!");
                isConnected = true;
                inputStream = new ObjectInputStream(socket.getInputStream());
                parameters = (Parameters) inputStream.readObject();
                outputArray = new double[parameters.getPopulationCount()][];
                //TODO: generate new population and copy it to array
                outputArray[0] = new double[]{1, 1, 1};
                outputArray[3] = new double[]{2, 2, 2};
                outputArray[1] = new double[]{2, 0, 0};
                outputArray[2] = new double[]{5.6, 0.22, 0.33};
                //sending result
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(outputArray);
                socket.close();
                outputStream.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}