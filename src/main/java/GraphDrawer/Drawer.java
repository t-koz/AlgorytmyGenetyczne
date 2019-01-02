package GraphDrawer;

import net.ericaro.surfaceplotter.JSurfacePanel;


import javax.swing.*;
import java.awt.BorderLayout;
import java.util.Random;


public class Drawer {

    public void testSomething() {
        JSurfacePanel jsp = new JSurfacePanel();
        jsp.setTitleText("lll");

        JFrame jf = new JFrame("test");
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
        sm.setValues(0f,1000f,0f,1000f,max, z1, z2);
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
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Drawer().testSomething();
            }
        });

    }

}