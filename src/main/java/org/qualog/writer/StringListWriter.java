package org.qualog.writer;

import org.quamat.gen.StringWriter;

import java.util.List;

/**
 * Adds output strings to a List<String>.
 */
public class StringListWriter implements StringWriter {
    private final List<String> lines;

    public StringListWriter(List<String> lines) {
        this.lines = lines;
    }

    public void write(String line) {
        lines.add(line);
    }
}
