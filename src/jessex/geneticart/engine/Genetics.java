package jessex.geneticart.engine;

public class Genetics {

    public SourceImage source;              //Source picture to evolve
    public SourceImage evolved;             //"Work in progress" picture

    public int prevFitness;                 //Fitness of previous generation
    public int nextFitness;                 //Fitness of next generation
    public Picture prevPic;                 //Previous generation Picture
    public Picture nextPic;                 //Next generation Picture

    //Calculates the fitness of the evolved image with respect to the source
    //Fitness is a sum of the absolute differences between the color values of
    //each pixel pair in the images (Alpha, Red, Blue, Green)
    public int getFitness(SourceImage source, SourceImage evolved) {
        int fitness = 0;
        for (int i=0; i<source.getHeight(); i++) {
            for (int j=0; j<source.getWidth(); j++) {
                int a = Math.abs(source.pixels[j][i][0] - evolved.pixels[j][i][0]);
                int r = Math.abs(source.pixels[j][i][1] - evolved.pixels[j][i][1]);
                int g = Math.abs(source.pixels[j][i][2] - evolved.pixels[j][i][2]);
                int b = Math.abs(source.pixels[j][i][3] - evolved.pixels[j][i][3]);
                fitness += a + r + g + b;
            }
        }
        return fitness;
    }

    


}
