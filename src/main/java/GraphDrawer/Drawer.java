package GraphDrawer;

import net.ericaro.surfaceplotter.JSurfacePanel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Drawer {
    static ServerSocket serverSocket;
    static Socket socket;
    static boolean isConnected = false;
    static ObjectInputStream objectInputStream;
    static ObjectOutputStream objectOutputStream;
    static double [][] results;
    static JFrame jf;
    static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    public static void testSomething() {
        JSurfacePanel jsp = new JSurfacePanel();
        jsp.setTitleText("lll");
        jf = new JFrame();
//        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(jsp, BorderLayout.CENTER);
        jf.pack();
        jf.setSize(800, 800);
        jf.setVisible(true);

        Random rand = new Random();
        int max = 100;
        int max1 = 100;
        int min1 = 1;
        float[][] z1 = new float[max][max];
        float[][] z2 = new float[max][max];
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                z1[i][j] = rand.nextFloat() * 20 - 10f;
                z2[i][j] = rand.nextFloat() * 20 - 10f;
            }
        }
        ArraySurfaceModel sm = new ArraySurfaceModel();
        sm.setValues(0f,1000f,0f,1000f, max, z1, z2);
        sm.doRotate();
        jsp.setModel(sm);
        jf.paint(img.getGraphics());
        jf.setVisible(false);
        jf.dispose();
    }

    public static float f1(float x, float y) {
        // System.out.print('.');
        return (float) (Math.sin(x * x + y * y) / (x * x + y * y));
        // return (float)(10*x*x+5*y*y+8*x*y -5*x+3*y);
    }

    public static float f2(float x, float y) {
        return (float) (Math.sin(x * x - y * y) / (x * x + y * y));
        // return (float)(10*x*x+5*y*y+15*x*y-2*x-y);
    }

    public static void main(String[] args) {
        GetResults();
        testSomething();
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
                ImageIO.write(img,"jpg", socket.getOutputStream());
                socket.close();
            }catch (NotSerializableException e){
                e.printStackTrace();
            }catch (IOException e) {
                System.out.printf(".");
            }
        }
    }

}