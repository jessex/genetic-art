package jessex.geneticart.engine;

public class Settings {

    public static int maxWidth = 200;
    public static int maxHeight = 200;

    //COMPONENT TOTAL RANGES
    public static int maxPolygons = 250;
    public static int minPolygons = 0;
    public static int maxPolyPoints = 10;
    public static int minPolyPoints = 3;
    public static int maxPicPoints = 1500;
    public static int minPicPoints = 0;

    //MUTATION RATES AND VALUES
    public static double picAddRate = 0.05;
    public static double picDelRate = 0.05;
    public static double picMoveRate = 0.05;
    public static double polyAddRate = 0.05;
    public static double polyDelRate = 0.05;
    public static double colorAlphaRate = 0.05;
    public static int maxColorTransparency = 75;
    public static int minColorTransparency = 25;
    public static double colorRedRate = 0.05;
    public static double colorGreenRate = 0.05;
    public static double colorBlueRate = 0.05;
    public static int colorIntensity = 100;

}
