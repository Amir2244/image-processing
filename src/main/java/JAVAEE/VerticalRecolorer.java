package JAVAEE;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VerticalRecolorer implements RecolorStrategy {
    @Override
    public void recolor(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {
            int widthPerThread = originalImage.getWidth() / numberOfThreads;
            int height = originalImage.getHeight();

            for (int i = 0; i < numberOfThreads; i++) {
                final int threadIndex = i;
                executorService.submit(() -> {
                    int xOrigin = widthPerThread * threadIndex;
                    RecolorPixel.recolorImage(originalImage, resultImage, xOrigin, 0, widthPerThread, height);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
    }
}

