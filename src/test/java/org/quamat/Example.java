package org.quamat;

import org.incava.ijdk.collect.Hash;
import org.junit.Test;
import org.quamat.fmt.MessageFormatter;
import org.quamat.gen.ObjectGenerator;
import org.quamat.io.StringListWriter;
import org.quamat.gen.Generator;
import org.quamat.gen.GeneratorTestCase;
import org.quamat.gen.StringGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example extends GeneratorTestCase {
    public Generator createGenerator(List<String> lines) {
        StringGenerator sg = new StringGenerator(new MessageFormatter(), new StringListWriter(lines));
        return new Generator(sg);
    }

    @Test
    public void init() {
        List<String> lines = new ArrayList<>();
        Generator gen = createGenerator(lines);
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

    @Test
    public void nestedContainers() {
        List<String> lines = new ArrayList<>();
        Generator gen = createGenerator(lines);

        // from https://en.wikipedia.org/wiki/Portable_computer
        List<Map<String, Object>> computers = new ArrayList<>();
        Map<String, Object> values = new HashMap<>();
        values.put("name", "SCAMP");
        values.put("manufacturer", "IBM");
        values.put("CPU", "PALM");
        computers.add(values);

        values = new HashMap<>();
        values.put("name", "IBM 5100");
        values.put("manufacturer", "IBM");
        values.put("CPU", "PALM");
        computers.add(values);

        values = new HashMap<>();
        values.put("name", "MIT Suitcase");
        values.put("manufacturer", "MIT");
        values.put("CPU", "Motorola 6800");
        computers.add(values);

        values = new HashMap<>();
        values.put("name", "Xerox NoteTaker");
        values.put("manufacturer", "Xerox");
        Map<String, Object> cpu = new HashMap<>();
        cpu.put("manufacturer", "Intel");
        cpu.put("model", 8086);
        values.put("CPU", cpu);
        computers.add(values);

        gen.generate("computers", computers);
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
