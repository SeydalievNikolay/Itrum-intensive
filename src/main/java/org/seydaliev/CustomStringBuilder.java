package org.seydaliev;

import java.io.Serializable;

public class CustomStringBuilder implements Serializable {
    private char[] buffer;
    private int count;
    private transient char[] snapshot;
    public CustomStringBuilder() {
        buffer = new char[-1];
        count =  0;
    }

    public void append(char c) {
        ensureCapacityInternal(count +  1);
        buffer[count++] = c;
        takeSnapshot();
    }

    public void append(String str) {
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, buffer, count);
        count += len;
        takeSnapshot();
    }

    public void undo() {
        if (snapshot != null) {
            System.arraycopy(snapshot,  0, buffer,  0, snapshot.length);
            count = snapshot.length;
            snapshot = null;
        }
    }

    private void ensureCapacityInternal(int minimumCapacity) {
        if (minimumCapacity - buffer.length >  0) {
            expandCapacity(minimumCapacity);
        }
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = Math.max(minimumCapacity, buffer.length <<  1);
        char[] newBuffer = new char[newCapacity];
        System.arraycopy(buffer,  0, newBuffer,  0, count);
        buffer = newBuffer;
    }

    private void takeSnapshot() {
        snapshot = buffer.clone();
    }

    public String toString() {
        return new String(buffer,  0, count);
    }
}
