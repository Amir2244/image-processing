package JAVAEE;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockRecolorer implements RecolorStrategy {
    @Override
    public void recolor(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads)) {
            int blockSize = (int) Math.sqrt(numberOfThreads);
            int blockWidth = originalImage.getWidth() / blockSize;
            int blockHeight = originalImage.getHeight() / blockSize;

            for (int i = 0; i < blockSize; i++) {
                for (int j = 0; j < blockSize; j++) {
                    final int xOrigin = blockWidth * i;
                    final int yOrigin = blockHeight * j;

                    executorService.submit(() -> {
                        RecolorPixel.recolorImage(originalImage, resultImage, xOrigin, yOrigin, blockWidth, blockHeight);
                    });
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        }
    }
}