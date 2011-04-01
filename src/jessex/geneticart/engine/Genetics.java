package jessex.geneticart.engine;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import jessex.geneticart.gui.Illustrator;

public class Genetics {

    public SourceImage source;              //Source picture to evolve
    public SourceImage evolved;             //"Work in progress" picture

    public int prevFitness;                 //Fitness of previous generation
    public int currFitness;                 //Fitness of current generation
    public Picture prevPic;                 //Previous generation Picture
    public Picture currPic;                 //Current generation Picture

    public Genetics(SourceImage s) {
        prevFitness = currFitness = 0;
        source = s;
        evolved = new SourceImage(s.getWidth(), s.getHeight());
        prevPic = currPic = new Picture();
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
    public boolean evolveCycle(JPanel panel) {
        boolean improved;

        //Extract pixels from canvas and place in SourceImage (this.evolved)
        BufferedImage bi = (BufferedImage) panel.createImage(Settings.picWidth,
                Settings.picHeight);
        evolved.image = bi;
        evolved.pixels = evolved.getPixelARGBs(bi);

        //Compare fitness of new image to that of last image
        prevFitness = currFitness;
        currFitness = getFitness();
        if (currFitness > prevFitness) { //Improvement from previous generation
            Picture temp = currPic;
            prevPic = temp;
            currPic.setModified(false); //Reset modified status
            currPic.mutatePicture();
            improved = true;
        }
        else { //No improvement from previous generation
            currPic = prevPic;
            currFitness = prevFitness; //Reset fitness level to previous
            currPic.setModified(false); //Reset modified status
            currPic.mutatePicture();
            improved = false;
        }
        
        //Take current Picture and paint to canvas via Illustrator
        if (currPic.isModified()) {
            //Draw if modified from previous
            panel.paint(Illustrator.getGraphics(currPic,
                    Settings.picWidth, Settings.picHeight));
        }

        return improved; //Return true if improvement from previous cycle
    }

    


}
