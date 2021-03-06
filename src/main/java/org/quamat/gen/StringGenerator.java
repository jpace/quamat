package org.quamat.gen;

import org.quamat.fmt.StringFormatter;
import org.quamat.io.StringWriter;

/**
 * Generates one line, using the formatter and writer.
 */
public class StringGenerator {
    private final StringFormatter formatter;
    private final StringWriter writer;

    public StringGenerator(StringFormatter formatter, StringWriter writer) {
        this.formatter = formatter;
        this.writer = writer;
    }

    public void generate(String key, String value) {
        String str = formatter.format(key, value);
        writer.write(str);
    }

    public void generate(String msg) {
        String str = formatter.format(msg);
        writer.write(str);
    }
}