package kl0ck.github.io;

import java.io.File;
import java.util.Objects;
import java.awt.Dimension;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class CustomFileNameGenerator {

    public CustomFileNameGenerator() {
    }

    public String createFileName(File file, Dimension size) {
        Objects.requireNonNull(file);
        Objects.requireNonNull(size);

        String extension = FilenameUtils.getExtension(file.getName());

        if (StringUtils.isBlank(extension)) {
            throw new IllegalArgumentException("Extensão inválida!");
        }

        String filenameWithoutExtension = StringUtils.removeEnd(file.getAbsolutePath(), "." + extension);

        String newFileName = String.format("%s-%s-%s.%s",
                filenameWithoutExtension,
                DimensionConverter.toString(size),
                System.currentTimeMillis(),
                extension);

        return newFileName;
    }

}
