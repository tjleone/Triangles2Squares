/*
 *  Squares.java
 *  
 *  Based on code from Section 1.3 of
 *  Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 
 *  2nd Edition, Chichester: John Wiley.
 *  
 */

import java.awt.*;
import java.awt.event.*;

/**
 * This program draws a spiral of 50 triangles inside each other.
 */
@SuppressWarnings("serial")
public class Squares extends Frame {

    /**
     * The main method for the Squares program.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        new Squares();
    }

    /**
     * Constructs a frame with a CvSquareSpiral canvas inside.
     */
    private Squares() {
        super("Squares: 10 squares inside each other");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(600, 400);
        add("Center", new CvSquareSpiral());
        setVisible(true);
    }
}
