package jessex.geneticart.engine;

public class Settings {

    public static int maxWidth = 200;
    public static int maxHeight = 200;
    public static int picWidth, picHeight;


    //COMPONENT TOTAL RANGES
    public static final int MAXPOLMIN = 25;
    public static final int MAXPOLMAX = 250;
    public static int maxPolygons = 100;

    public static final int MINPOLMIN = 1;
    public static final int MINPOLMAX = 15;
    public static int minPolygons = 5;

    public static final int MAXPIMIN = 7;
    public static final int MAXPIMAX = 15;
    public static int maxPolyPoints = 10;

    public static final int MINPIMIN = 3;
    public static final int MINPIMAX = 6;
    public static int minPolyPoints = 3;

    //MUTATION RATES AND VALUES
    public static double picAddRate = 0.005;
    public static double parMax = 0.005;
    public static double picDelRate = 0.005;
    public static double pdrMax = 0.005;
    public static double picMoveRate = 0.005;
    public static double pmrMax = 0.005;
    
    public static double polyAddRate = 0.005;
    public static double oarMax = 0.005;
    public static double polyDelRate = 0.005;
    public static double odrMax = 0.005;

    public static double pointMaxRate = 0.005;
    public static double marMax = 0.005;
    public static double pointMidRate = 0.005;
    public static double mirMax = 0.005;
    public static int pointMidIntensity = 20;
    public static double pointMinRate = 0.005;
    public static double mnrMax = 0.005;
    public static int pointMinIntensity = 5;

    public static double colorAlphaRate = 0.005;
    public static int maxColorTransparency = 75;
    public static int minColorTransparency = 25;
    public static double colorRate = 0.005;
    public static double crMax = 0.005;
    public static int colorIntensity = 100;

}
