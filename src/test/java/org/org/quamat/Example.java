package org.org.quamat;

import org.junit.Test;
import org.quamat.fmt.MessageFormatter;
import org.quamat.io.StringListWriter;
import org.quamat.gen.Generator;
import org.quamat.gen.GeneratorTestCase;
import org.quamat.gen.StringGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example extends GeneratorTestCase {
    @Test
    public void init() {
        List<String> lines = new ArrayList<>();
        StringGenerator sg = new StringGenerator(new MessageFormatter(), new StringListWriter(lines));
        Generator gen = new Generator(sg);
        gen.generate("x", 17);
        writeLines(lines);

        lines.clear();
        List<String> list = new ArrayList<>();
        list.add("alex");
        list.add("david");
        list.add("eddie");
        list.add("michael");

        gen.generate("members", list);
        writeLines(lines);

        lines.clear();
        Map<String, Object> map = new HashMap<>();
        map.put("π", 3.1415);
        map.put("φ", 1.618);
        map.put("√2", 1.414);

        gen.generate("values", map);
        writeLines(lines);
    }

    private void writeLines(List<String> lines) {
        System.out.println("-----");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("-----\n");
    }
}
