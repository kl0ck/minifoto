package kl0ck.github.io;

import static java.lang.System.out;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;

public class App implements Runnable {

    final String path;
    final String size;

    public static void main(String[] args) {
        if (args.length < 2) {
            out.println("Argumentos: caminho, tamanho");
            out.println();
            out.println(String.format("Exemplo: \"C:\\Users\\Pedro\\Pictures\\foto.jpg\" \"512x512\""));
            System.exit(0);
        }

        String path = args[0];
        String size = args[1];

        System.out.println("path = " + path);
        System.out.println("size = " + size);

        App app = new App(path, size);

        app.run();
    }

    App(String path, String size) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(size);

        this.path = path;
        this.size = size;
    }

    @Override
    public void run() {
        File filePath = new File(path);

        if (filePath.isDirectory()) {
            System.out.println("isDirectory = " + filePath.isDirectory());

/*             try (Stream<Path> files = Files.list(filePath.toPath())) {
                out.println(Arrays.toString(files.toArray()));
    
            } catch (IOException e) {
                e.printStackTrace();
            }
 */
        } else if (filePath.isFile()) {
            System.out.println("isFile = " + filePath.isFile());

            try {

                BufferedImage img = resize(filePath, size);

                String ext = FilenameUtils.getExtension(path);

                ImageIO.write(img, ext, new File(path + System.currentTimeMillis() + "." + ext));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new RuntimeException(filePath.getAbsolutePath());
        }
    }

    public static BufferedImage resize(File imgFile, String newSize) throws IOException {
        Objects.requireNonNull(imgFile);
        Objects.requireNonNull(newSize);

        BufferedImage img = ImageIO.read(imgFile);

        Dimension dim = Converter.toDimension(newSize);

        return Scalr.resize(img, dim.width, dim.height);
    }

}
