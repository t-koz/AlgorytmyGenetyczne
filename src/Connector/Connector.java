package Connector;

import Common.AlgoritmType;
import Common.Parameters;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.Serializable;


public class Connector {
    static Parameters parameters;
    static boolean isConnected = false;
    static double [][] arrayToSendForClient;


    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        Socket socketForAlgorithm;
        ObjectInputStream inputStream;
        ObjectInputStream inputStreamForAlgorithm;
        ObjectOutputStream outputStream;
        ObjectOutputStream outputStreamForAlgorithm;
        isConnected = false;

        GetParameterFromClient();
//        Thread.sleep(1000);
        SendParametersToDE();
        SendResultToClient();
    }

    private static void SendParametersToDE() {
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;

        while (!isConnected){
            try {
                socket = new Socket("localhost", 4335);
                System.out.println("Connected to DE!");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                parameters = new Parameters(1, 2, 10, 4, AlgoritmType.DE, true);
                outputStream.writeObject(parameters);
                //receiving parameters for client
                inputStream = new ObjectInputStream(socket.getInputStream());
                arrayToSendForClient  = (double[][]) inputStream.readObject();

//                serverSocket = new ServerSocket(4335);
                //close
                socket.close();
                outputStream.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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

    private static void SendResultToClient() {
        ServerSocket serverSocket;
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        while (!isConnected){
            try {
                serverSocket = new ServerSocket(4665);
                socket = serverSocket.accept();
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(arrayToSendForClient);
                //close
                socket.close();
                outputStream.flush();
            }
            catch (IOException e) {e.printStackTrace();}
        }

    }
}