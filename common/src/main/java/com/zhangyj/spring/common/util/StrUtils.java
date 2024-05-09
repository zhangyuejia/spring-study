package com.zhangyj.spring.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * @author ZHANG
 */
public class StrUtils {

    public static String decompressString(byte[] compressedData) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);

        byte[] buffer = new byte[1024];
        while (!decompressor.finished()) {
            try {
                int count = decompressor.inflate(buffer);
                outputStream.write(buffer, 0, count);
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        }

        outputStream.close();
        decompressor.end();

        return outputStream.toString();
    }

    public static byte[] convertListToByteArray(List<Integer> integerList) {
        byte[] byteArray = new byte[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            byteArray[i] = integerList.get(i).byteValue();
        }
        return byteArray;
    }

}
