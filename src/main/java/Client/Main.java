package Client;

import Common.AlgoritmType;
import Common.OptimizationFunctions;
import Common.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
//    private static double param1 = 0.5; //F for DE, between 0.5 and 0.9
//    private static double param2 = 0.3; //CR for DEa, between 0 and 1
    private static double param1 = 2.3; //startTemp for SA
    private static double param2 = 0.9; //temperature multipler for SA, between 0.85 and 0.95
    private static int PopulationCount = 20;
    private static int Generations = 50;
    private static boolean isWithGraph = true;
    private static boolean isConnected = false;
    private static double[][] outputArray = new double[PopulationCount][3];
    private static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);


    public static void main(String[] args) {
        Parameters parameters = new Parameters(PopulationCount, Generations, AlgoritmType.DE, isWithGraph, OptimizationFunctions.Booth, param1, param2);

        SendParametersToConnector(parameters, 4445);
        RecieveResultDataFromConnector(4665);
        PrintResultsData();
        RecievePlotFromConnector(isWithGraph, 5454);
        ShowImage();
    }

    private static void ShowImage() {
        JFrame screen = new JFrame("Plot");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(800, 800);
        screen.getContentPane().setLayout(new FlowLayout());
        screen.getContentPane().add(new JLabel(new ImageIcon(img)));
        screen.setVisible(true);
    }

    private static void SendParametersToConnector(Parameters parameters, int port) {
        //sending parameters
        System.out.printf("Waiting for connector...");
        Socket socket;
        ObjectOutputStream outputStream;
        while (!isConnected) {
            try {
                socket = new Socket("localHost", port);
                System.out.println("Getting plot!");
                isConnected = true;
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(parameters);
                socket.close();
                outputStream.flush();
            } catch (IOException e) {
                System.out.printf(".");
            }
        }
    }

    private static void RecieveResultDataFromConnector(int port) {
        isConnected = false;
        System.out.printf("Waiting for results...");
        Socket socket;
        ObjectInputStream inputStream;
        //receive result
        while (!isConnected) {
            try {
                socket = new Socket("localHost", port);
                System.out.println("Connected to Connector!");
                isConnected = true;
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputArray = (double[][]) inputStream.readObject();
                socket.close();
            } catch (IOException e) {
                System.out.printf(".");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void PrintResultsData() {
        System.out.println("Got Results!");
        double sum = 0;
        for (double[] number : outputArray) {
            System.out.println("X = " + number[0] + " Y = " + number[1] + " Result = " + number[2]);
            sum += number[2];
        }
        System.out.printf("Result average: ");
        System.out.println(sum / PopulationCount);
    }

    private static void RecievePlotFromConnector(boolean isWithGraph, int port) {
        if (isWithGraph) {
            Socket socket;
            isConnected = false;
            System.out.println("Getting Plot...");
            while (!isConnected) {
                try {
                    socket = new Socket("localHost", port);
                    System.out.println("Connected to Connector!");
                    isConnected = true;
                    img = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
                    File outputFile = new File("wykresClient.png");
                    try {
                        ImageIO.write(img, "png", outputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    socket.close();
                } catch (IOException e) {
                    System.out.printf(".");
                }
            }
        }
    }
}
