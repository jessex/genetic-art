package jessex.geneticart.engine;

public class Point {

    private int x;
    private int y;

    public Point() {
        this.x = Randoms.randomInt(Settings.maxWidth);
        this.y = Randoms.randomInt(Settings.maxHeight);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


}
