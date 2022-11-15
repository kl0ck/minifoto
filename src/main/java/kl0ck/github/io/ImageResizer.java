package kl0ck.github.io;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;

public class ImageResizer {

    public ImageResizer() {
    }

    public BufferedImage resize(BufferedImage image, Dimension newSize) throws IOException {
        Objects.requireNonNull(image);
        Objects.requireNonNull(newSize);

        return Scalr.resize(image, newSize.width, newSize.height);
    }

    public File resize(File inputFile, Dimension newSize) throws IOException {
        Objects.requireNonNull(inputFile);
        Objects.requireNonNull(newSize);

        BufferedImage image = resize(ImageIO.read(inputFile), newSize);

        CustomFileNameGenerator fileNameGenerator = new CustomFileNameGenerator();

        String newFileName = fileNameGenerator.createFileName(inputFile, newSize);

        String extension = FilenameUtils.getExtension(inputFile.getName());

        File outputFile = new File(newFileName);

        boolean sucesso = ImageIO.write(image, extension, outputFile);

        if (sucesso) {
            return outputFile;

        } else {
            throw new IOException("Extensão de arquivo inválida: " + extension);
        }
    }

}
