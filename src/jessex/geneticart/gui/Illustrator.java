package jessex.geneticart.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import jessex.geneticart.engine.*;

public class Illustrator {

    public static Graphics2D getGraphics(Picture pic, int width, int height) {
        SourceImage img = new SourceImage(width, height);
        Graphics2D g = img.image.createGraphics();

        for (int i=0; i<pic.polygons.size(); i++) {
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