
/**
 * Write a description of class Graph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//TO-DO
//-Lines
//-Points (dots)
//-

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Rectangle;
import java.lang.Math;

public class Graph
{
    //Ref point of Graphics
    Graphics2D g2;
    
    //Size
    private int sizeX;
    private int sizeY;

    
    //Interval between lines
    private double xInterval;
    private double yInterval;
    
    public Graph(Graphics2D rG, int zX, int zY, double xInter, double yInter)
    {
        sizeX = zX;
        sizeY = zY;
        g2 = rG;
        xInterval = xInter;
        yInterval = yInter;
    }
     public Graph(int zX, int zY, double xInter, double yInter)
    {
        sizeX = zX;
        sizeY = zY;
        xInterval = xInter;
        yInterval = yInter;
    }
    

    
    public void drawCartisianGraph()
    {
        g2.setColor(Color.BLACK);
        int linesAcross = (int)Math.floor((double)sizeX/xInterval);
        for(int i = 0; i <= linesAcross; i++)
        {
            Line2D.Double line = new Line2D.Double(xInterval * i, 0, xInterval * i, sizeY);
            g2.draw(line);
        }
        
        int linesDown = (int)Math.floor((double)sizeY/yInterval);
        for(int i = 0; i <= linesDown; i++)
        {
            Line2D.Double line = new Line2D.Double(0, yInterval * i, sizeY, yInterval * i);
            g2.draw(line);
        }
    }
    
    public void drawPolarGraph() 
    {
        double polarInterval = xInterval; //in radians (pi/4 pi/3 etc...)
        double maxRadius = yInterval;
        
        g2.setColor(Color.BLACK);
          
        for(double theta = 0; theta <= (2 * Math.PI); theta+=polarInterval)
        {
            Line2D.Double line = new Line2D.Double(.5 * Math.cos(theta) + sizeX, .5 * Math.sin(theta) + sizeY, maxRadius * Math.cos(theta) + sizeX, maxRadius * Math.sin(theta) + sizeY);
            g2.draw(line);
        }
        
        Ellipse2D.Double innerRadCircle = new Ellipse2D.Double(3.5, 3.5, 1, 1);
        g2.setColor(Color.WHITE);
        g2.draw(innerRadCircle);
        g2.setColor(Color.BLACK);
        g2.draw(innerRadCircle);
        
        for(double circle = maxRadius; circle > 0; circle--)
        {
            Ellipse2D.Double radCircle = new Ellipse2D.Double(sizeX - circle, sizeY - circle, circle * 2, circle * 2);
            g2.draw(radCircle);
        }
    }
    
    public void drawInitialGraph()
    {
        double polarInterval = xInterval; //in radians (pi/4 pi/3 etc...)
        double maxRadius = yInterval;
        
        g2.setColor(Color.BLACK);
          
        for(double theta = 0; theta <= (2 * Math.PI); theta+=polarInterval)
        {
            Line2D.Double line = new Line2D.Double(sizeX, sizeY, maxRadius * Math.cos(theta) + sizeX, maxRadius * Math.sin(theta) + sizeY);
            g2.draw(line);
        }
        
        Ellipse2D.Double innerRadCircle = new Ellipse2D.Double(3.5, 3.5, 1, 1);
        g2.setColor(Color.WHITE);
        g2.fill(innerRadCircle);
        g2.setColor(Color.BLACK);
        g2.draw(innerRadCircle);
        
        for(double circle = maxRadius; circle > 0; circle--)
        {
            Ellipse2D.Double radCircle = new Ellipse2D.Double(sizeX - circle, sizeY - circle, circle * 2, circle * 2);
            g2.draw(radCircle);
        }
    }
    public void plot(double r, double th)
    {
        double x = Math.cos(th) * r + 3.9;
        double y = Math.sin(th) * r + 3.9;
        Ellipse2D.Double point = new Ellipse2D.Double(x, y, .2, .2);
        g2.fill(point);
    }
    
    public void plot(double r, double th, Color c)
    {
        g2.setColor(c);
        double x = Math.cos(th) * r + 3.9;
        double y = Math.sin(th) * r + 3.9;
        Ellipse2D.Double point = new Ellipse2D.Double(x, y, .2, .2);
        g2.fill(point);
        g2.setColor(Color.black);
    }
    
    public void erase()
    {
        g2.setColor(Color.white);
        Rectangle2D rect = new Rectangle(0,0, 20, 20);
        g2.fill(rect);
        g2.setColor(Color.black);
        drawInitialGraph();
    }
}
