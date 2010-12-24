package jessex.geneticart.engine;

public class Point {

    private int x;
    private int y;

    public Point() {
        this(Randoms.randomInt(Settings.maxWidth),
                Randoms.randomInt(Settings.maxHeight));
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //ACCESSORS
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    //SETTERS
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    //MUTATION
    private void movePointMaxRange(Picture pic) {
        this.x = Randoms.randomInt(Settings.maxWidth);
        this.y = Randoms.randomInt(Settings.maxHeight);
    }

    public void mutatePoint(Picture pic) {
        
    }






}
