package org.quamat.fmt;

import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.incava.attest.Parameterized;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesRegex;

public class JdkFormatterTest extends Parameterized {
    public void assertStream(String expected, ByteArrayOutputStream stream) {
        String str = stream.toString();
        System.err.println("vvvvv\n" + str + "\n^^^^^");
        assertThat(str, matchesRegex(expected));
    }

    public Object[] setUpTest(String name, String format) throws Exception {
        System.err.println("JdkFormatterTest#setUpTest(name: " + name + ", format: " + format + ")");
        String config = "";
        config += "handlers=java.util.logging.ConsoleHandler\n";
        config += "java.util.logging.ConsoleHandler.formatter=java.util.logging.SimpleFormatter\n";
        config += "java.util.logging.SimpleFormatter.format=" + format + "\n";

        LogManager.getLogManager().readConfiguration(new ByteArrayInputStream(config.getBytes()));

        return setUpTest(name);
    }

    public Object[] setUpTest(String name) {
        Logger logger = Logger.getLogger(JdkFormatterTest.class.getName() + name);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Formatter formatter = new JdkFormatter();
        Handler handler = new StreamHandler(stream, formatter);

        logger.setUseParentHandlers(false);
        logger.addHandler(handler);

        return new Object[]{logger, stream};
    }

    public void closeHandlers(Logger logger) {
        Handler[] handlers = logger.getHandlers();
        for (Handler it : handlers) {
            it.close();
        }
    }

    @Ignore
    @Test
    @Parameters
    @TestCaseName("{method}(...) #{index} [{params}]")
    public void format(String expected, String format) throws Exception {
        Object[] ary = setUpTest("#format" + format, format);
        Logger logger = (Logger) ary[0];
        ByteArrayOutputStream stream = (ByteArrayOutputStream) ary[1];

        logger.log(Level.INFO, "L4 A");

        closeHandlers(logger);

        assertStream(expected, stream);
    }

    private java.util.List<Object[]> parametersForFormat() {
        java.util.List<Object[]> pl = paramsList();
        pl.add(params("\\[\\d{4}-\\d{2}-\\d{2} \\d{1,2}:\\d{2}:\\d{2}\\]\n", "[%1$tF %1$tT]%n"));
        pl.add(params("\\w{3} \\d{2}\n", "%1$tb %1$td%n"));

        // default
        pl.add(params("\\w{3} \\d{2}, \\d{4} \\d{1,2}:\\d{2}:\\d{2} \\w{2} org.quamat.fmt.JdkFormatterTest format\nINFO: L4 A\n",
                "%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n"));

        pl.add(params("org.quamat.fmt.JdkFormatterTest format\n", "%2$s%n"));
        pl.add(params("org.quamat.fmt.JdkFormatterTest#format%3\\$s%n\n", "%3$s%n"));
        pl.add(params("INFO\n", "%4$s%n"));
        pl.add(params("L4 A\n", "%5$s%n"));
        pl.add(params("\n", "%6$s%n"));

        return pl;
    }

    @Test
    public void jdkFormatter() throws Exception {
        JdkFormatter fmt = new JdkFormatter();
        System.err.println("fmt: " + fmt);

        String name = "jdkFormatter";

        String format = "%4$s %5$s%n";

        Object[] ary = setUpTest("#format" + format, format);
        Logger logger = (Logger) ary[0];
        ByteArrayOutputStream stream = (ByteArrayOutputStream) ary[1];

        // logger.log(Level.INFO, "L4 A");
        logger.log(Level.INFO, "L4 B {0}", "object");
        // logger.log(Level.INFO, "L4 C %s", "object");

        closeHandlers(logger);

        String str = stream.toString();
        System.err.println("AAAAA\n" + str + "\nVVVVV");
        // assertThat(str, matchesRegex(expected));
    }
}
