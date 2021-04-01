package org.quamat.fmt;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class JdkFormatter extends SimpleFormatter {
    public String format(LogRecord record) {
        System.err.println("rec.instant:          " + record.getInstant());
        System.err.println("rec.sourceClassName:  " + record.getSourceClassName());
        System.err.println("rec.sourceMethodName: " + record.getSourceMethodName());
        System.err.println("rec.loggerName:       " + record.getLoggerName());
        System.err.println("rec.level:            " + record.getLevel());
        System.err.println("rec.message:          " + record.getMessage());
        Object[] params = record.getParameters();
        System.err.println("rec.parameters:       " + (params == null ? "null" : Arrays.asList(params)));
        System.err.println("rec.resourceBundle:   " + record.getResourceBundle());

        ZonedDateTime time = ZonedDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String source;
        if (record.getSourceClassName() == null) {
            source = record.getLoggerName();
        } else {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
                source += " " + record.getSourceMethodName();
            }
        }

        System.err.println("time: " + time);
        System.err.println("source: " + source);

        //$$$ not formatted with parameters
        String message = record.getMessage();

        Throwable t = record.getThrown();
        String throwMsg = t == null ? "" : toMessage(t);

        String logName = record.getLoggerName();
        String levelName = record.getLevel().getLocalizedName();
        String format = "%4$s %5$s%n";

        String str = String.format(format,
                time,
                source,
                logName,
                levelName,
                message,
                throwMsg);

        System.err.println("str: " + str);

        return super.format(record);
    }

    public String toMessage(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.println();
        t.printStackTrace(pw);
        pw.close();
        return sw.toString();
    }
}
