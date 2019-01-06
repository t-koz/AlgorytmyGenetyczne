package GraphDrawer;

import org.math.plot.Plot3DPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Drawer {
    static ServerSocket serverSocket;
    static Socket socket;
    static boolean isConnected = false;
    static ObjectInputStream objectInputStream;
    static double [][] results;
    static JFrame jFrame;
    static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    public static void drawPlot() {
        int size = results.length;
        double [] X = new double[size];
        double [] Y = new double[size];
        double [] Z = new double[size];
        for(int i = 0; i < size; i++){
            X[i] = results[i][0];
            Y[i] = results[i][1];
            Z[i] = results[i][2];
        }
        Plot3DPanel plot3DPanel = new Plot3DPanel();
        plot3DPanel.rotate(0.4,0.3);
        plot3DPanel.addBarPlot("nazwa", X, Y, Z);
        jFrame = new JFrame("Panel wykresik");
        jFrame.setSize(800, 800);
        jFrame.setContentPane(plot3DPanel);
        jFrame.setVisible(true);
        jFrame.paint(img.getGraphics());
        jFrame.setVisible(false);
        jFrame.dispose();
    }

    public static void main(String[] args) {
        GetResults();
        drawPlot();
        SendJFrame();
    }

    private static void GetResults() {
        isConnected = false;
        System.out.println("Getting results from Connector");
        while (!isConnected){
            try{
                serverSocket = new ServerSocket(5555);
                socket = serverSocket.accept();
                System.out.println("Connected to Connector!");
                isConnected = true;
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                results = (double[][]) objectInputStream.readObject();
                socket.close();
            }catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private static void SendJFrame() {
        isConnected = false;
        System.out.println("Sending Screenshot to Connector");
        while (!isConnected){
            try{
                serverSocket = new ServerSocket(5855);
                socket = serverSocket.accept();
                System.out.println("Connected to Connector!");
                isConnected = true;
                ImageIO.write(img,"png", socket.getOutputStream());
                socket.close();
            }catch (NotSerializableException e){
                e.printStackTrace();
            }catch (IOException e) {
                System.out.printf(".");
            }
        }
    }
}