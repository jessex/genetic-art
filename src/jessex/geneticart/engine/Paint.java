package jessex.geneticart.engine;

public class Paint {

    private int red, green, blue;

    public Paint() {
        //Generate random RGB values
        this.red = Randoms.randomInt(255);
        this.green = Randoms.randomInt(255);
        this.blue = Randoms.randomInt(255);
    }

    public int getRed() {
        return this.red;
    }
    public int getGreen() {
        return this.green;
    }
    public int getBlue() {
        return this.blue;
    }

    public void setRed(int r) {
        this.red = r;
    }
    public void setGreen(int g) {
        this.green = g;
    }
    public void setBlue(int b) {
        this.blue = b;
    }


}
