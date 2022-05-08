package kl0ck.github.io;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ImageUtils {

    private ImageUtils() {
    }

    public static BufferedImage resize(File imgFile, Dimension newSize) throws IOException {
        Objects.requireNonNull(imgFile);
        Objects.requireNonNull(newSize);

        BufferedImage img = ImageIO.read(imgFile);

        return Scalr.resize(img, newSize.width, newSize.height);
    }
    
}
