package org.quamat.gen;

import org.incava.attest.Parameterized;
import org.qualog.format.MessageFormatter;
import org.qualog.writer.StringListWriter;

import java.util.List;

public class GeneratorTestCase extends Parameterized {
    public StringGenerator createGenerator(String keyValueFormat, List<String> lines) {
        return new StringGenerator(new MessageFormatter(keyValueFormat, null), new StringListWriter(lines));
    }

    public StringGenerator createGenerator(List<String> lines) {
        return new StringGenerator(new MessageFormatter(), new StringListWriter(lines));
    }
}
