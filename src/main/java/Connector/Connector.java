package Connector;

import Common.AlgoritmType;
import Common.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Connector {
    static Parameters parameters;
    static boolean isConnected = false;
    static double[][] arrayToSendForClient;
    static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args) {
        GetParameterFromClient();
        SendParametersToCorrectAlgorithm();
        SendDataToClient(4665);
        ConnectToDrawer(5555);
        if (parameters.isWithGraph()){
            SendImageToClient(5454);
        }
    }

    private static void SendImageToClient(int port) {
        ServerSocket serverSocket;
        isConnected = false;
        Socket socket;
        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                System.out.println("Sending Results to Client!");
                isConnected = true;
                ImageIO.write(img, "png", socket.getOutputStream());
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void ConnectToDrawer(int port) {
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        while (!isConnected) {
            try {
                socket = new Socket("localhost", port);
                System.out.println("Connected to Drawer");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(arrayToSendForClient);
                //receiving parameters for client
                System.out.println("Sending results to Drawer");
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
        isConnected = false;
        System.out.println("Getting plot from Drawer...");
        while (!isConnected){
            try {
                socket = new Socket("localhost", 5855);
                isConnected = true;
                img = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
                socket.close();
            }catch (IOException e){
                System.out.printf(".");
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

    private static void SendParametersToCorrectAlgorithm() {
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;

        int port = SetPropertyPort(parameters.GetAlgoritmType());
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
                System.out.printf(".");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static int SetPropertyPort(AlgoritmType algoritmType) {
        switch (algoritmType){
            case DE:
                return  4335;
            case SA:
                return  4993;
                default: return 4335;
        }
    }

    private static void SendDataToClient(int port) {
        ServerSocket serverSocket;
        isConnected = false;
        Socket socket;
        ObjectOutputStream outputStream;
        while (!isConnected) {
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                System.out.println("Sending Results to Client!");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(arrayToSendForClient);
                socket.close();
                outputStream.flush();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
