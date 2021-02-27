package org.qualog.jdk;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Test;

import java.util.Arrays;
import java.util.Formattable;
import java.util.Formatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormattingTest extends Parameterized {
    static class TestABC implements Formattable {
        private final String str;

        public TestABC(String str) {
            this.str = str;
        }

        @Override
        public void formatTo(Formatter formatter, int flags, int width, int precision) {
            System.out.println("formatter: " + formatter);
            System.out.println("flags: " + flags);
            System.out.println("width: " + width);
            System.out.println("precision: " + precision);
            formatter.format(str);
        }
    }
    @Test @Parameters @TestCaseName
    public void format(String expected, String format, Object ... args) {
        System.out.println("format: " + format);
        System.out.println("args: " + Arrays.asList(args));
        String result = String.format(format, args);
        System.out.println("result: " + result);
        assertThat(result, equalTo(expected));
    }

    private java.util.List<Object[]> parametersForFormat() {
        java.util.List<Object[]> pl = paramsList();

        pl.add(params("abc", "%s", new Object[] { "abc" }));
        pl.add(params("abc", "%s", new Object[] { new TestABC("abc") }));

        return pl;
    }
}
