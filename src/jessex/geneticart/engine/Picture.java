package jessex.geneticart.engine;

import java.util.ArrayList;

public class Picture {

    public ArrayList<Polygon> polygons;      //List of unique polygons
    private boolean modified;                //Has been modified recently


    public Picture() {
        this.polygons = new ArrayList<Polygon>();
        this.modified = true;
    }

    //ACCESSORS
    public boolean isModified() { return this.modified; }

    //SETTERS
    public void setModified(boolean b) { this.modified = b; }

    //MUTATION
    //Mutation is to delete a random polygon
    private void removeRandomPolygon() {
        if (polygons.size() > Settings.minPolygons) {
            polygons.remove(Randoms.randomInt(polygons.size()-1));
            this.modified = true;
        }
    }
    //Mutation is to add (in a random spot) a new random polygon
    private void addRandomPolygon() {
        if (polygons.size() < Settings.maxPolygons) {
            polygons.add(Randoms.randomInt(polygons.size()-1), new Polygon());
            this.modified = true;
        }
    }
    //Mutation is to move a random polygon to a random "layer" in the image
    private void moveRandomPolygon() {
        if (polygons.size() > 0) {
            Polygon poly = polygons.remove(Randoms.randomInt(polygons.size()-1));
            polygons.add(Randoms.randomInt(polygons.size()-1), poly);
            this.modified = true;
        }
    }
    //Performs possible picture-level mutations then passes off to polygons
    public void mutatePicture() {
        if (Randoms.checkRatio(Settings.picAddRate)) addRandomPolygon();
        if (Randoms.checkRatio(Settings.picDelRate)) removeRandomPolygon();
        if (Randoms.checkRatio(Settings.picMoveRate)) moveRandomPolygon();

        for (Polygon poly : polygons) poly.mutatePolygon(this);
    }



}
