package org.glydar.protocol.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZLibOperations {

    public static byte[] compress(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
        byte[] buf = new byte[1024];
        while (!deflater.finished()) {
            int compressed = deflater.deflate(buf);
            output.write(buf, 0, compressed);
        }

        try {
            output.close();
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }

        return output.toByteArray();

    }

    public static byte[] decompress(byte[] data) {
        if (data.length == 0) {
            return new byte[0];
        }

        Inflater inflater = new Inflater();
        inflater.setInput(data);

        byte[] buf = new byte[1024];
        ByteArrayOutputStream output = new ByteArrayOutputStream(data.length);
        try {
            while (!inflater.finished()) {
                int decompressed = inflater.inflate(buf);
                output.write(buf, 0, decompressed);
            }

            inflater.end();
            output.close();

            return output.toByteArray();
        }
        catch (DataFormatException | IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
