package Connector;

import Client.Parameters;

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
        Parameters parameters;
        boolean isConnected = false;
        double [][] arrayToSendForClient;

        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(4445);
                socket = serverSocket.accept();
                System.out.println("Connected to client!");
                isConnected = true;
                //receiving parameters
                inputStream = new ObjectInputStream(socket.getInputStream());
                parameters = (Parameters) inputStream.readObject();
                
                arrayToSendForClient = new double[parameters.getPopulationCount()][];
                //send result
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
