package jessex.geneticart.engine;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Genetics {

    public static final int MAXFITNESS = (Settings.picWidth *
            Settings.picHeight) * (255*3 + (Settings.maxColorTransparency -
            Settings.minColorTransparency));
    Robot rob;


    public SourceImage source;              //Source picture to evolve
    public SourceImage evolved;             //"Work in progress" picture

    public int prevFitness;                 //Fitness of previous generation
    public int currFitness;                 //Fitness of current generation
    public Picture prevPic;                 //Previous generation Picture
    public Picture currPic;                 //Current generation Picture

    public Genetics(SourceImage s) {
        prevFitness = currFitness = MAXFITNESS;
        source = s;
        evolved = new SourceImage(s.getWidth(), s.getHeight());
        prevPic = currPic = new Picture();
        try {
            rob = new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(Genetics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Calculates the fitness of the evolved image with respect to the source
     * Fitness is a sum of the absolute differences between the color values of
     * each pixel pair in the images (Alpha, Red, Blue, Green)
     */
    public int getFitness() {
        int fitness = 0;
        //Iterate through each column reading ARGB values of the pixels
        for (int i=0; i<source.getHeight(); i++) {
            for (int j=0; j<source.getWidth(); j++) {
                //Fitness is sum of absolute differences in ARGB values
                int a = Math.abs(source.pixels[j][i][0] - evolved.pixels[j][i][0]);
                int r = Math.abs(source.pixels[j][i][1] - evolved.pixels[j][i][1]);
                int g = Math.abs(source.pixels[j][i][2] - evolved.pixels[j][i][2]);
                int b = Math.abs(source.pixels[j][i][3] - evolved.pixels[j][i][3]);
                fitness += a + r + g + b;
            }
        }
        return fitness;
    }

    /*
     * Extracts the pixels of the most recently drawn image from the canvas
     * and compares its fitness to the previous image to determine which image
     * to move forward with at the next generation, either current or previous
     * Then mutates its choice and draws the new image (if necessary)
     */
    public boolean evolveCycle(jessex.geneticart.gui.DisplayGui.ImagePanel panel) {
        boolean improved;

        //Extract pixels from canvas and place in SourceImage (this.evolved)
        //BufferedImage bi = (BufferedImage) panel.createImage(Settings.maxWidth,
        //        Settings.maxHeight);
        //BufferedImage bi = rob.createScreenCapture(panel.getBounds());
        //evolved.image = bi;
        //evolved.pixels = evolved.getPixelARGBs(bi);
        
        Picture temp = prevPic;
        prevPic = currPic;
        currPic.setModified(false);
        currPic.mutatePicture();

        //Write mutation to buffer
        evolved.image = (BufferedImage) panel.createImage(panel.getWidth(),
                panel.getHeight());
        Graphics2D gc = evolved.image.createGraphics();
        int size = currPic.polygons.size();
        for (int i=0; i<size; i++) {
            jessex.geneticart.engine.Polygon p = currPic.polygons.get(i);
            int[] x = p.getXCoords();
            int[] y = p.getYCoords();
            jessex.geneticart.engine.Paint c = p.color;
            gc.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(),
                    c.getAlpha()));
            gc.fillPolygon(x, y, p.points.size());
        }
        evolved.pixels = evolved.getPixelARGBs(evolved.image);

        //Compare fitness of new image to that of last image
        int tempFit = prevFitness;
        prevFitness = currFitness;
        currFitness = getFitness();
        //System.out.println("*"+prevFitness);
        //System.out.println(currFitness);
        if (currFitness <= prevFitness) { //Improvement from previous generation
            improved = true;
        }
        else { //No improvement from previous generation
            currPic = prevPic;
            prevPic = temp;
            currFitness = prevFitness; //Reset fitness level to previous
            prevFitness = tempFit;
            improved = false;
        }

        return improved; //Return true if improvement from previous cycle
    }

    


}
