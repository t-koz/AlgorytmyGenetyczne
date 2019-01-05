package GraphDrawer;

import net.ericaro.surfaceplotter.JSurfacePanel;


import javax.swing.*;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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


    public void testSomething() {
        JSurfacePanel jsp = new JSurfacePanel();
        jsp.setTitleText("lll");

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().add(jsp, BorderLayout.CENTER);
        jf.pack();
        jf.setSize(500, 500);
        jf.setVisible(true);
//
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
        jsp.setModel(sm);
        sm.doRotate();
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
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Drawer().testSomething();
            }
        });
        SendJFrame();
    }

    private static void GetResults() {
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
        System.out.println("Getting JFrame from drawer");
        while (!isConnected){
            try{
                serverSocket = new ServerSocket(5855);
                socket = serverSocket.accept();
                System.out.println("Connected to Connector!");
                isConnected = true;
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(jf);
                socket.close();
                objectOutputStream.flush();
            }catch (IOException e) {
                System.out.printf(".");
            }
        }
    }

}