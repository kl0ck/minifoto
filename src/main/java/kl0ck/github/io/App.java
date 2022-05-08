package kl0ck.github.io;

import static java.lang.System.out;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class App implements Runnable {

    private final File file;
    private final Dimension size;

    App(File file, Dimension size) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(size);

        if (!file.exists()) {
            throw new IllegalArgumentException("O arquivo ou diretório não existe: " + file.getAbsolutePath());
        }

        this.file = file;
        this.size = size;
    }

    @Override
    public void run() {
        if (file.isDirectory()) {
            System.out.println("isDirectory = " + file.isDirectory());

/*             try (Stream<Path> files = Files.list(filePath.toPath())) {
                out.println(Arrays.toString(files.toArray()));
    
            } catch (IOException e) {
                e.printStackTrace();
            }
 */
        } else if (file.isFile()) {
            System.out.println("isFile = " + file.isFile());

            try {

                BufferedImage image = ImageUtils.resize(file, size);
                String extension = FilenameUtils.getExtension(file.getAbsolutePath());
                
                // Concatena nome do arquivo, tempo atual em milisegundos, e a extensao original.
                String newFileName = String.format("%s-%s-%s.%s", 
                    // Remove extensao do nome do arquivo original, para nao ficar no meio do novo nome.
                    StringUtils.removeEnd(file.getAbsolutePath(), "." + extension), 
                    Converter.toString(size),
                    System.currentTimeMillis(),
                    extension);

                ImageIO.write(image, extension, new File(newFileName));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new RuntimeException(file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            out.println("Argumentos: caminho, tamanho");
            out.println(String.format("Exemplo: \"C:\\Users\\Pedro\\Pictures\\foto.jpg\" \"512x512\""));
            out.println();
            System.exit(0);
        }

        File file = new File(args[0]);
        Dimension size = Converter.toDimension(args[1]);

        System.out.println("file = " + file);
        System.out.println("size = " + size);

        App app = new App(file, size);

        app.run();
    }

}
