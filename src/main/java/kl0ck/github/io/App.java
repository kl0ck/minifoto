package kl0ck.github.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;
import static java.lang.System.out;

public class App {

    public static void main(String[] args) throws IOException {
        //String path = args[0];
        //String size = args[1];

        File dir = new File(".");
        Stream<Path> files = Files.list(dir.toPath());
        out.println(Arrays.toString(files.toArray()));
        files.close();
    }

}
