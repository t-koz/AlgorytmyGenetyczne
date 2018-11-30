package PopulationGenerator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverSender {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;
        String receivedText;
        boolean isConnected = false;
        //TODO get array size from received object
        int TempPopulationCount = 10;
        double [][] arrayToSendForClient = new double[TempPopulationCount][3];
        //TODO delete this hardcoded array
        arrayToSendForClient[0] = new double[]{1, 1, 1};
        arrayToSendForClient[3] = new double[]{2, 2, 2};
        arrayToSendForClient[1] = new double[]{0, 0, 0};
        arrayToSendForClient[2] = new double[]{5.6, 0.22, 0.33};

        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(4445);
                socket = serverSocket.accept();
                System.out.println("Connected to client!");
                isConnected = true;
                //receiving
                inputStream = new ObjectInputStream(socket.getInputStream());
                receivedText = (String) inputStream.readObject();
                System.out.println(receivedText);
                //send
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(arrayToSendForClient);
                //close
                socket.close();
                outputStream.flush();
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
            }
        }
    }
}
