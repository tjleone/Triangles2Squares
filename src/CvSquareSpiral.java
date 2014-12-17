/*
 *  CvSquareSpiral.java
 *  
 *  Based on code from Section 1.3 of
 *  Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 
 *  2nd Edition, Chichester: John Wiley.
 *  
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Canvas for drawing the spiral of squares.
 */
@SuppressWarnings("serial")
public class CvSquareSpiral extends Canvas {

    /**
     * ratio of starting (biggest) square side to Math.min(maxX, maxY)
     * (bounding square's side)
     */
    final private static float DRAWING_TO_CANVAS_RATIO = 0.95F;

    /**
     * number of squares to draw in a spiral of squares
     */
    private int k;

    /**
     * a scale factor to determine how tight the spiral is. Must be between 0
     * and 1.
     */
    final private float q;
    /**
     * a scale factor to determine how tight the spiral is. Initialized to 1 -
     * q.
     */
    final private float p;
    
    /**
     * logical coordinate for vertex of square
     */
    private float xA, yA, xB, yB, xC, yC, xD, yD;

    /**
     * max device y coordinate
     */
    private int maxY;

    /**
     * center device x coordinate
     */
    private int xCenter;

    /**
     * center device y coordinate
     */
    private int yCenter;

    /**
     * Initializes a newly created CvSquares with a scale factor of 0.05F.
     */
    public CvSquareSpiral() {
        this(10, 0.2F);
    }

    /**
     * Initializes a newly created CvSquares
     *
     * @param q scale factor that determines how tight the spiral is.
     * @throws IllegalArgumentException if q &le; 0 or q &ge; 1
     */
    public CvSquareSpiral(float q) {
        this(10,q);
    }

    /**
     * Initializes a newly created CvSquares
     *
     * @param k number of squares in the square spiral.
     * @param q scale factor that determines how tight the spiral is.
     * @throws IllegalArgumentException if q &le; 0 or q &ge; 1
     */
    public CvSquareSpiral(int k, float q) {
        if (k <= 0) {
            throw new IllegalArgumentException(
                    "Illegal number of squares in spiral: " + k);
        }
        if (q <= 0 || q >= 1) {
            throw new IllegalArgumentException(
                    "Illegal fraction of side length: " + q);
        }
        
        this.k = k;
        this.q = q;
        this.p = 1 - q;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        initializeVertices(d.width - 1, d.height - 1);
        for (int i = 0; i < k; i++) {
            drawSquare(g);
            calculateNextVerticies();
        }
    }

    /**
     * Sets the vertices (xA, yA), (xB, yB) and (xC, yC) for the first (biggest)
     * square to be drawn.
     *
     * @param maxX largest device x coordinate on the canvas
     * @param maxY largest device y coordinate on the canvas
     */
    public void initializeVertices(int maxX, int maxY) {
        this.maxY = maxY;
        float side = DRAWING_TO_CANVAS_RATIO * Math.min(maxX, maxY);
        float sideHalf = 0.5F * side;
        xCenter = maxX / 2;
        yCenter = maxY / 2;
        xA = xD = xCenter - sideHalf;
        xB = xC = xCenter + sideHalf;
        yA = yB = yCenter - sideHalf;
        yC = yD = yCenter + sideHalf;
    }

    /**
     * Calculates the vertices (xA, yA), (xB, yB) and (xC, yC) for the next
     * (smaller rotated) square to be drawn.
     */
    public void calculateNextVerticies() {
        float xA1, yA1, xB1, yB1, xC1, yC1, xD1, yD1;
        xA1 = p * xA + q * xB;
        yA1 = p * yA + q * yB;
        xB1 = p * xB + q * xC;
        yB1 = p * yB + q * yC;
        xC1 = p * xC + q * xD;
        yC1 = p * yC + q * yD;
        xD1 = p * xD + q * xA;
        yD1 = p * yD + q * yA;
        xA = xA1;
        xB = xB1;
        xC = xC1;
        xD = xD1;
        yA = yA1;
        yB = yB1;
        yC = yC1;
        yD = yD1;
    }

    /**
     * Draws a square with vertices (xA, yA), (xB, yB) and (xC, yC)
     *
     * @param g the graphics context to use for drawing
     */
    public void drawSquare(Graphics g) {
        g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
        g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
        g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
        g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
    }

    /**
     * Converts x from a logical coordinate to a device coordinate.
     *
     * @param x a logical x coordinate
     * @return the corresponding device x coordinate
     */
    private int iX(float x) {
        return Math.round(x);
    }

    /**
     * Converts y from a logical coordinate to a device coordinate.
     *
     * @param y a logical y coordinate
     * @return the corresponding device y coordinate
     */
    private int iY(float y) {
        return maxY - Math.round(y);
    }
}
