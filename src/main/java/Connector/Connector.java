package Connector;

import Common.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Connector {
    static Parameters parameters;
    static boolean isConnected = false;
    static double[][] arrayToSendForClient;

    public static void main(String[] args) {
        GetParameterFromClient();
        SendParametersToCorrectAlgorithm();
        SendResultToClient();
    }

    private static void GetParameterFromClient() {
        ServerSocket serverSocket;
        Socket socket;
        ObjectInputStream inputStream;
        isConnected = false;
        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(4445);
                socket = serverSocket.accept();
                System.out.println("Connected to client!");
                isConnected = true;
                //receiving parameters for client
                inputStream = new ObjectInputStream(socket.getInputStream());
                parameters = (Parameters) inputStream.readObject();
                socket.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void SendParametersToCorrectAlgorithm() {
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        int port = 0;
        switch (parameters.GetAlgoritmType()){
            case DE:
                port = 4335;
                break;
            case SA:
                port = 4993;
                break;
        }

        while (!isConnected) {
            try {
                socket = new Socket("localhost", port);
                System.out.println("Connected to " + parameters.GetAlgoritmType().toString());
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(parameters);
                //receiving parameters for client
                System.out.println("Getting results from " + parameters.GetAlgoritmType().toString());
                inputStream = new ObjectInputStream(socket.getInputStream());
                arrayToSendForClient = (double[][]) inputStream.readObject();
                socket.close();
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void SendResultToClient() {
        ServerSocket serverSocket;
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(4665);
                socket = serverSocket.accept();
                System.out.println("Sending Results to Client!");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(arrayToSendForClient);
                socket.close();
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
