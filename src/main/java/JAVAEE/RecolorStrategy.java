package JAVAEE;

import java.awt.image.BufferedImage;

interface RecolorStrategy {
    void recolor(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException;
}