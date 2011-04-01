package jessex.geneticart.engine;

import java.util.ArrayList;

public class Polygon {

    public ArrayList<Point> points;             //List of vertices in polygon
    public Paint color;                         //RGBA color fill of polygon

    public Polygon() {
        this.points = new ArrayList<Point>();
        //Set up a random location
        Point origin = new Point();
        //Create polygon by setting up random points around origin
        for (int i=0; i<Settings.minPolyPoints; i++) {
            int randx = origin.getX() + Randoms.randomInt(0, 6)-3;
            randx = Math.min(Math.max(0, randx), Settings.picWidth);
            int randy = origin.getY() + Randoms.randomInt(0, 6)-3;
            randy = Math.min(Math.max(0, randy), Settings.picHeight);
            this.points.add(new Point(randx, randy));
        }
        this.color = new Paint();
    }

    public int[] getXCoords() {
        int size = points.size();
        int[] x = new int[size];
        for(int i=0; i<size; i++) x[i] = points.get(i).getX();
        return x;
    }

    public int[] getYCoords() {
        int size = points.size();
        int[] y = new int[size];
        for(int i=0; i<size; i++) y[i] = points.get(i).getY();
        return y;
    }


    //MUTATION
    //Mutation is to add a random point to the polygon between two other points
    private void addRandomMidPoint(Picture pic) {
        if (this.points.size() < Settings.maxPolyPoints) {
            int index = Randoms.randomInt(1, this.points.size()-1);
            int avgx = (this.points.get(index-1).getX() +
                    this.points.get(index).getX()) / 2;
            int avgy = (this.points.get(index-1).getY() +
                    this.points.get(index).getY()) / 2;
            this.points.add(index, new Point(avgx, avgy));
            pic.setModified(true);
        }
    }
    //Mutation is to delete a random point in the polygon
    private void removeRandomPoint(Picture pic) {
        if (this.points.size() > Settings.minPolyPoints) {
            this.points.remove(Randoms.randomInt(this.points.size()-1));
            pic.setModified(true);
        }
    }
    //Performs polygon-level and color mutations then passes off to points
    public void mutatePolygon(Picture pic) {
        if (Randoms.checkRatio(Settings.polyAddRate)) addRandomMidPoint(pic);
        if (Randoms.checkRatio(Settings.polyDelRate)) removeRandomPoint(pic);

        color.mutateColor(pic); //Mutate color
        for (Point p : points) p.mutatePoint(pic); //Mutate points in polygon
    }
}
