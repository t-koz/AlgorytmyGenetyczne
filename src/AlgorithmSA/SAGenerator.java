package AlgorithmSA;

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
        SAResultGetter resultGetter;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        double[][] outputArray;

        while (!isConnected){
            try{
                serverSocket = new ServerSocket(4993);
                socket = serverSocket.accept();
                System.out.println("Connected to Connector!");
                isConnected = true;
                inputStream = new ObjectInputStream(socket.getInputStream());
                parameters = (Parameters) inputStream.readObject();
                System.out.println("Generating populations...");
                resultGetter = new SAResultGetter(parameters);
                outputArray = resultGetter.GetOutputPopulationToArray();
                //sending results
                System.out.println("Sending results to Connector!");
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(outputArray);
                socket.close();
                outputStream.flush();
            }catch (IOException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}