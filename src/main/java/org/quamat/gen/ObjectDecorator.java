package org.quamat.gen;

/**
 * An outputter for decorated objects.
 */
public class ObjectDecorator {
    public String toString(Object obj) {
        return toString(obj, obj.toString());
    }

    public String toString(Object obj, String msg) {
        return String.format("%s (%s) #%h", msg, obj.getClass().getName(), obj.hashCode());
    }
}
