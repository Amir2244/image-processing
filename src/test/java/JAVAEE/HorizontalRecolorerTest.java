package JAVAEE;

import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class HorizontalRecolorerTest extends TestCase {

    private HorizontalRecolorer horizontalRecolorer;
    private BufferedImage originalImage;
    private BufferedImage resultImage;

    public void setUpImages(String originalImagePath, String resultImagePath) throws Exception {
        File originalImageFile = new File(originalImagePath);
        File resultImageFile = new File(resultImagePath);
        if (!originalImageFile.exists()) {
            throw new IllegalArgumentException("Original image path does not exist: " + originalImagePath);
        }
        originalImage = ImageIO.read(originalImageFile);
        if (originalImage == null) {
            throw new IllegalArgumentException("Failed to load original image from path: " + originalImagePath);
        }
        resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        ImageIO.write(resultImage, "jpg", resultImageFile);
        horizontalRecolorer = new HorizontalRecolorer();
    }

    public void testRecolorMultithreaded_horizontalDivision() throws Exception {
        String originalImagePath = "src/main/resources/many-flowers.jpg";
        String resultImagePath = "./out/recolored-flowers.jpg";
        setUpImages(originalImagePath, resultImagePath);
        int numberOfThreads = 4;
        horizontalRecolorer.recolor(originalImage, resultImage, numberOfThreads);
        for (int x = 0; x < resultImage.getWidth(); x++) {
            for (int y = 0; y < resultImage.getHeight(); y++) {
                int resultRgb = resultImage.getRGB(x, y);
                assertTrue("The result image should have been modified", resultRgb != 0);
            }
        }
        ImageIO.write(resultImage, "jpg", new File(resultImagePath));
    }
}
