package jessex.geneticart.engine;

public class Paint {

    private int alpha, red, green, blue;

    //Constructs a random Paint RGBA color
    public Paint() {
        this(Randoms.randomInt(Settings.minColorTransparency,
                Settings.maxColorTransparency), Randoms.randomInt(255),
                Randoms.randomInt(255), Randoms.randomInt(255));
    }

    //Constructs a Paint RGBA color with given values
    public Paint(int a, int r, int g, int b) {
        this.alpha = a;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    //ACCESSORS
    public int getAlpha() { return this.alpha; }
    public int getRed() { return this.red; }
    public int getGreen() { return this.green; }
    public int getBlue() { return this.blue; }

    //SETTERS
    public void setAlpha(int a) { this.alpha = a; }
    public void setRed(int r) { this.red = r; }
    public void setGreen(int g) { this.green = g; }
    public void setBlue(int b) { this.blue = b; }

    //MUTATION
    public void mutateColor(Picture pic) {
        if (Randoms.checkRatio(Settings.colorAlphaRate)) {
            this.alpha = Randoms.randomInt(Settings.minColorTransparency,
                    Settings.maxColorTransparency);
            pic.setModified(true);
        }
        if (Randoms.checkRatio(Settings.colorRedRate)) {
            this.red = Randoms.randomInt(Math.max(0,this.red-
                    Settings.colorIntensity), Math.min(this.red+
                    Settings.colorIntensity,255));
            pic.setModified(true);
        }
        if (Randoms.checkRatio(Settings.colorGreenRate)) {
            this.green = Randoms.randomInt(Math.max(0,this.green-
                    Settings.colorIntensity), Math.min(this.green+
                    Settings.colorIntensity,255));
            pic.setModified(true);
        }
        if (Randoms.checkRatio(Settings.colorBlueRate)) {
            this.blue = Randoms.randomInt(Math.max(0,this.blue-
                    Settings.colorIntensity), Math.min(this.blue+
                    Settings.colorIntensity,255));
            pic.setModified(true);
        }
    }

}
