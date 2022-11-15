package kl0ck.github.io;

import java.util.Objects;
import java.awt.Dimension;

public class DimensionConverter {

    private DimensionConverter() {
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
            throw new IllegalArgumentException("Formato de tamanho inválido: " + size);
        }
    }

    public static String toString(Dimension size) {
        Objects.requireNonNull(size);

        return size.width + "x" + size.height;
    }

}
