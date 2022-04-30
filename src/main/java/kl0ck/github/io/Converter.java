package kl0ck.github.io;

import java.util.Objects;
import java.awt.Dimension;

public class Converter {

    private Converter() {
    }

    public static Dimension toDimension(String size) {
        Objects.requireNonNull(size);

        boolean isTamanhoValido = size.matches("\\d+x\\d+");

        if (isTamanhoValido) {
            String[] dim = size.split("x");
            int w = Integer.parseInt(dim[0]);
            int h = Integer.parseInt(dim[1]);

            return new Dimension(w, h);

        } else {
            throw new IllegalArgumentException(size);
        }
    }

}
