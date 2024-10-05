package JAVAEE;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HorizontalRecolorer implements RecolorStrategy {
    @Override
    public void recolor(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {
            int width = originalImage.getWidth();
            int heightPerThread = originalImage.getHeight() / numberOfThreads;

            for (int i = 0; i < numberOfThreads; i++) {
                final int threadIndex = i;
                executorService.submit(() -> {
                    int yOrigin = heightPerThread * threadIndex;
                    RecolorPixel.recolorImage(originalImage, resultImage, 0, yOrigin, width, heightPerThread);
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
    }

}