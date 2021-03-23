package org.quamat.gen;

import org.incava.attest.Parameterized;
import org.quamat.fmt.MessageFormatter;
import org.quamat.io.StringListWriter;

import java.util.List;

public class GeneratorTestCase extends Parameterized {
    public StringGenerator createStringGenerator(String keyValueFormat, List<String> lines) {
        return new StringGenerator(new MessageFormatter(keyValueFormat, null), new StringListWriter(lines));
    }

    public StringGenerator createStringGenerator(List<String> lines) {
        return new StringGenerator(new MessageFormatter(), new StringListWriter(lines));
    }
}
