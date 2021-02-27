package org.quamat.fmt;

/**
 * Generates a string for the key/value or message.
 */
public interface StringFormatter {
    String format(String key, String value);

    String format(String msg);
}
