package jessex.geneticart.engine;

public class Point {

    private int x;
    private int y;

    public Point() {
        this(Randoms.randomInt(Settings.picWidth),
                Randoms.randomInt(Settings.picHeight));
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
        this.x = Randoms.randomInt(Settings.picWidth);
        this.y = Randoms.randomInt(Settings.picHeight);
        pic.setModified(true);
    }
    private void movePointMidRange(Picture pic) {
        this.x = Randoms.randomInt(Math.max(0,
                this.x-Settings.pointMidIntensity), Math.min(this.x+
                Settings.pointMidIntensity, Settings.picWidth));
        this.y = Randoms.randomInt(Math.max(0,
                this.y-Settings.pointMidIntensity), Math.min(this.y+
                Settings.pointMidIntensity, Settings.picWidth));
        pic.setModified(true);
    }
    private void movePointMinRange(Picture pic) {
        this.x = Randoms.randomInt(Math.max(0,
                this.x-Settings.pointMinIntensity), Math.min(this.x+
                Settings.pointMinIntensity, Settings.picWidth));
        this.y = Randoms.randomInt(Math.max(0,
                this.y-Settings.pointMinIntensity), Math.min(this.y+
                Settings.pointMinIntensity, Settings.picWidth));
        pic.setModified(true);
    }

    public void mutatePoint(Picture pic) {
        if (Randoms.checkRatio(Settings.pointMaxRate)) movePointMaxRange(pic);
        if (Randoms.checkRatio(Settings.pointMidRate)) movePointMidRange(pic);
        if (Randoms.checkRatio(Settings.pointMinRate)) movePointMinRange(pic);
    }
}
