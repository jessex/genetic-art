package jessex.geneticart.engine;

import java.util.ArrayList;

public class Picture {

    public ArrayList<Polygon> polygons;      //List of unique polygons
    private int points;                      //Amount of unique points
    private boolean modified;                //Has been modified recently


    public Picture() {
        this.polygons = new ArrayList<Polygon>();
        this.modified = true;
        this.points = 0;
    }

    //Recalculate total points in picture
    public int sumPoints() {
        int count = 0;
        for (int i=0; i<polygons.size(); i++)
            count += polygons.get(i).points.size();
        this.points = count;
        return count;
    }

    //ACCESSORS
    public int getPoints() {
        return this.points;
    }
    public boolean isModified() {
        return this.modified;
    }

    //MUTATORS
    public void setPoints(int points) {
        this.points = points;
    }
    public void setModified(boolean b) {
        this.modified = b;
    }

    //Polygon Mutations
    public void removeRandomPolygon() {
        if (polygons.size() > Settings.minPolygons) {
            polygons.remove(Randoms.randomInt(polygons.size()-1));
            this.modified = true;
        }
    }

    public void addRandomPolygon() {
        if (polygons.size() < Settings.maxPolygons) {
            polygons.add(Randoms.randomInt(polygons.size()-1), new Polygon());
            this.modified = true;
        }
    }

    public void moveRandomPolygon() {
        if (polygons.size() > 0) {
            Polygon poly = polygons.remove(Randoms.randomInt(polygons.size()-1));
            polygons.add(Randoms.randomInt(polygons.size()-1), poly);
            this.modified = true;
        }
    }



}
