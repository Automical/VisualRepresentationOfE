
import java.awt.*;
import javax.swing.*;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.awt.geom.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

/**
 * Class Viewer - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */

import java.awt.Graphics2D;

import java.lang.Math;
public class Viewer extends JApplet implements ActionListener
{
    // instance variables - replace the example below with your own
    Graph gr;

    private static final int XMAX = 8;
    private static final int XMIN = 0;
    
    private static final int YMAX = 8;
    private static final int YMIN = 0;
    
    private double r = 0;
    private double th = 0;
    
    TextField t1 = new TextField(5);
    TextField t2 = new TextField(5);
    
    TextField d1 = new TextField(10);
    TextField d2 = new TextField(10);
    
    private boolean pi = true;
    private boolean clearIt = false;
    private boolean first = true;
    
     /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {

        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        JPanel buttonPanel = new JPanel();
        Button b1 = new Button("π");
        Button b2 = new Button("e");
        Button b3 = new Button("Graph");
        Button b4 = new Button("Clear");
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(t1);
        buttonPanel.add(t2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        Container contentHolder = getContentPane();
        contentHolder.add(buttonPanel, BorderLayout.SOUTH);
        t1.setText("r");
        t2.setText("θ");
        
        JPanel dataPanel = new JPanel();
        dataPanel.add(d1);
        dataPanel.add(d2);
        Container cH = getContentPane();
        cH.add(dataPanel, BorderLayout.NORTH);
        d1.setText("Radius:");
        d2.setText("θ:");
    }
    public void actionPerformed(ActionEvent e)
    {
        Button source = (Button)e.getSource();
        if(source.getLabel() == "π"){     
            pi = true;
        }
        if(source.getLabel() == "e"){
            pi = false;
        }
        if(source.getLabel() == "Graph"){
            r = Double.parseDouble(t1.getText());
            th = Double.parseDouble(t2.getText());
             d1.setText("Radius: " + r);
            if(pi == true) {
            th = th * Math.PI;
            d2.setText("θ: " + t2.getText() + "π");
        }else{ 
            th = th * 2.71828182845904523536028747135266249775724709369995;
            d2.setText("θ: " + t2.getText() + "e");
        }
        first = false;
            repaint();
        }
        if(source.getLabel() == "Clear"){
            clearIt = true;
            repaint();
        }
    }
    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        
        
        //Needed for the polar graph
        double xscale = (getWidth()-1.0)/(XMAX-XMIN);
        double yscale = (getHeight()-1.0)/(YMIN-YMAX);
        g2.scale(xscale,yscale);
        g2.translate(-XMIN,-YMAX);
        g2.setStroke(new BasicStroke(0));
        //Needed for the polar graph
                
        gr = new Graph(g2,4,4,(Math.PI / 36),4); //pi over 6 is the best
        if(first == true){ 
        gr.drawInitialGraph();
    }else {gr.drawPolarGraph();}
        if(r <= 4){
        if(pi == true){
        gr.plot(r, th, Color.green);
    }else{
        gr.plot(r, th, Color.red);
    }
}else{
    d1.setText("NOT");
    d2.setText("VALID");
}
        if(clearIt == true)
        {
            gr.erase();
            clearIt = false;
        }
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                 {"firstParameter",    "1-10",    "description of first parameter"},
                 {"status", "boolean", "description of second parameter"},
                 {"images",   "url",     "description of third parameter"}
        };
        return paramInfo;
    }
}
