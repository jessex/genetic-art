package jessex.geneticart.engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SourceImage {

    public BufferedImage image;        //Source image which is evolution goal
    public int[][][] pixels;
    private int height, width;         //Height and Width of image


    //Creates an empty image buffer to manipulate
    public SourceImage(int width, int height) {
        this.image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        this.height = height;
        this.width = width;
        this.pixels = new int[width][height][4];
    }

    //Creates a buffer of the image found at filename
    public SourceImage(String filename) {
        try {
            this.image = ImageIO.read(new File(filename));
        } catch (IOException ex) {
            System.err.println("Error: Could not find image");
        }
        this.height = this.image.getHeight();
        this.width = this.image.getWidth();
        
        if (this.height > Settings.maxHeight || this.width > Settings.maxWidth)
            System.err.println("Please provide a smaller image: max size is "
                    + Settings.maxWidth + " x " + Settings.maxHeight);

        this.pixels = getPixelARGBs(this.image);
    }

    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }

    //Examines image by pixel to determine the ARGB colors values of each pixel
    public int[][][] getPixelARGBs(BufferedImage image) {
        int x = image.getWidth();
        int y = image.getHeight();
        int[][][] rgb = new int [x][y][4];

        for (int i=0; i<y; i++) {
            for (int j=0; j<x; j++) {
                int pix = image.getRGB(j, i);
                rgb[j][i] = getARGBFromPixel(pix);
            }
        }
        return rgb;
    }

    //Separates the portions of the RGBA color space word to obtain our values
    //Most common 32 bit pixel layout has alpha as the 8 highest bits, red as
    //the next 8, green as the next 8 and blue as the 8 lowest bits
    private int[] getARGBFromPixel(int pixel) {
        int[] argb = new int[4];
        argb[0] = (pixel >> 24) & 0xff; //Alpha
        argb[1] = (pixel >> 16) & 0xff; //Red
        argb[2] = (pixel >> 8) & 0xff; //Green
        argb[3] = (pixel) & 0xff; //Blue
        return argb;
    }


}
