package kl0ck.github.io;

import static java.lang.System.out;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;

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
            out.println("isDirectory = " + file.isDirectory());

            Path dir = file.toPath();

            try (Stream<Path> files = Files.list(dir)) {
                out.println(Arrays.toString(files.toArray()));

                // FIXME

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (file.isFile()) {
            out.println("isFile = " + file.isFile());

            try {
                ImageResizer imageResizer = new ImageResizer();

                imageResizer.resize(file, size);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else {
            throw new RuntimeException(file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            out.println("Argumentos: caminho, tamanho");
            out.println(String.format("Exemplo: \"C:\\Users\\Carlos\\Pictures\\foto.jpg\" \"512x512\""));
            out.println();
            System.exit(0);
        }

        File file = new File(args[0]);
        Dimension size = DimensionConverter.toDimension(args[1]);

        out.println("file = " + file);
        out.println("size = " + size);

        SwingUtilities.invokeLater(new App(file, size));
    }

}
