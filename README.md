

# Multithreaded Image Recoloring

## Overview

This project provides a multithreaded approach to recoloring images using Java. The application supports three different strategies for dividing work between threads:

1. **Horizontal Division**: The image is divided into horizontal stripes, and each thread processes one stripe.
2. **Vertical Division**: The image is divided into vertical slices, and each thread processes a slice.
3. **Block Division**: The image is divided into smaller blocks, where each block is processed by a separate thread. This approach is particularly useful for certain image features, such as edge detection or when dealing with regions of interest that are scattered across the image.

## Getting Started

### Requirements
- JDK 11 or later
- Maven for dependency management
- JUnit 3 for running unit tests

### Running the Application

1. Clone the repository.
2. Run `mvn clean install` to install dependencies.
3. Run the `Main` class to see the image processing in action. The output image will be saved in the `out/` directory.

### Testing

The project includes unit tests for each recoloring strategy:
- **HorizontalRecolorerTest**
- **VerticalRecolorerTest**
- **BlockRecolorerTest**

You can run these tests using Maven:
```
mvn test
```

### Recolor Strategies

#### 1. Horizontal Division
This strategy splits the image into horizontal stripes, each processed by a different thread. It works well for most general image processing tasks but can miss certain nuances in images, especially those that involve vertical or diagonal patterns.

#### 2. Vertical Division
This strategy divides the image into vertical slices. It is useful when horizontal continuity in the image is more important to the processing task, but it might not handle edges that span both horizontally and vertically very well.

#### 3. Block Division
In the block division approach, the image is split into smaller rectangular blocks, where each block is processed by a different thread. This method is ideal for tasks where precision around edges or specific regions is crucial. Since each thread works on a compact section of the image, the block division helps better process edges or fine details that span across both horizontal and vertical directions.

For instance, in image processing tasks such as **edge detection**, where the contours of objects are important, dividing the image into blocks ensures that the threads can process the entirety of a localized area, including both horizontal and vertical boundaries of edges. This gives the threads more context when processing pixels around the edges, improving the quality of the result.

## When Block Division is Better (Regardless of Image processing)

Apart from image processing, block division can be more efficient in other tasks that involve grid-based data, such as **matrix multiplication**.

In matrix multiplication, if we divide the work into smaller blocks, each thread can process a square block of the resulting matrix independently. This allows better handling of both row and column operations simultaneously, reducing the need for frequent thread synchronization and improving parallelization efficiency. This block approach enables threads to work on smaller sections of the matrix, reducing the complexity of managing shared data, and leading to better performance in distributed and parallel computing tasks.

