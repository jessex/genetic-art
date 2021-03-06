package jessex.geneticart.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import jessex.geneticart.engine.*;

public class Illustrator {

    //Draws the polygons of the passed picture onto a blank image and returns
    //the picture as a Graphics2D object which can be drawn onto a canvas
    public static Graphics2D getGraphics(Picture pic, int width, int height) {
        SourceImage img = new SourceImage(width, height);
        Graphics2D g = img.image.createGraphics();

        int size = pic.polygons.size();
        for (int i=0; i<size; i++) {
            Polygon p = pic.polygons.get(i);
            int n = p.points.size();
            int[] x = new int[n];
            int[] y = new int[n];
            for (Point c : p.points) {
                x[i] = c.getX();
                y[i] = c.getY();
            }
            Paint c = p.color;
            g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(),
                    c.getAlpha()));
            g.fillPolygon(x, y, n);
        }
        return g;
    }



}
