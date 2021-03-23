package org.quamat.gen;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.ijdk.collect.StringArray;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GeneratorTest extends GeneratorTestCase {
    @Test
    @Parameters
    @TestCaseName("{method}(...) #{index} [{params}]")
    public <T> void generate(StringArray expected, String key, Object obj) {
        StringArray result = StringArray.empty();
        StringGenerator sg = createStringGenerator(result);
        new Generator(sg).generate(key, obj);
        assertThat(result, equalTo(expected));
    }

    private List<Object[]> parametersForGenerate() {
        return paramsList(params(StringArray.of("abc: def"), "abc", "def"),
                params(StringArray.of("abc: null"), "abc", null));
    }
}
