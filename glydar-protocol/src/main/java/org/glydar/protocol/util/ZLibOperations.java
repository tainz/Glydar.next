package org.glydar.protocol.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZLibOperations {

    public static ByteBuf decompress(ByteBuf buf) {
        int length = buf.readInt();
        byte[] compressed = new byte[length];
        buf.readBytes(compressed);
        ByteBuf decompressed = Unpooled.copiedBuffer(decompressBytes(compressed));
        return decompressed.order(ByteOrder.LITTLE_ENDIAN);
    }

    public static byte[] decompressBytes(byte[] data) {
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

    public static void compress(ByteBuf buf, BufWritable writable) {
        ByteBuf buf2 = Unpooled.buffer();
        buf2 = buf2.order(ByteOrder.LITTLE_ENDIAN);
        writable.writeTo(buf2);
        byte[] compressed = compressBytes(buf2.array());

        buf.writeInt(compressed.length);
        buf.writeBytes(compressed);
    }

    public static byte[] compressBytes(byte[] data) {
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
}
