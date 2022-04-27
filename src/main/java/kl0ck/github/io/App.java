package kl0ck.github.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import static java.lang.System.out;

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
        System.out.println("path = " + path);

        if (filePath.isDirectory()) {
            System.out.println("isDirectory = " + filePath.isDirectory());

            try (Stream<Path> files = Files.list(filePath.toPath())) {
                out.println(Arrays.toString(files.toArray()));
    
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (filePath.isFile()) {
            System.out.println("isFile = " + filePath.isFile());

        } else {
            throw new RuntimeException();
        }

        // TODO Auto-generated method stub
        
    }

}
