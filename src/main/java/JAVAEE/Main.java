package JAVAEE;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static final String SOURCE_FILE = "src/main/resources/many-flowers.jpg";
    public static final String DESTINATION_FILE = "./out/many-flowers.jpg";

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
/*
switch the strategy you like here
*/
        RecolorStrategy strategy = new BlockRecolorer();

        long startTime = System.currentTimeMillis();
/*
change the number of threads here
*/
        strategy.recolor(originalImage, resultImage, 4);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println("Duration: " + duration + " ms");
    }
}