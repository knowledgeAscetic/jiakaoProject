package com.wang.jk.common.util;

import java.io.*;
import java.nio.ByteBuffer;

public class Objects {
    @SafeVarargs
    public static <T> T notNull(T... sources) {
        if (sources == null) return null;
        for (T source : sources) {
            if (source != null) return source;
        }
        return null;
    }

    public static byte[] getBytes(Object obj) {
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)
        ) {
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getObject(byte[] bytes) {
        try (
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
        ) {
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ByteBuffer getByteBuffer(Object obj) {
        return ByteBuffer.wrap(java.util.Objects.requireNonNull(getBytes(obj)));
    }

    public static <T> T getObject(ByteBuffer byteBuffer) {
        try (
            InputStream is = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream ois = new ObjectInputStream(is)
        ) {
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
