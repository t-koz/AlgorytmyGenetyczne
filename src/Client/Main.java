package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
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
        //TODO create object to send

        while (!isConnected){
            try{
                socket = new Socket("localHost", 4445);
                System.out.println("Connected!");
                isConnected = true;
                //sending
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                //TODO: send specific object
                outputStream.writeObject("Here comes parameters!");
                //receive
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
