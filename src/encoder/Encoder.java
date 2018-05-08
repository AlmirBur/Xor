package encoder;

import java.io.*;

public class Encoder {
    private static void code(InputStream in, OutputStream out, byte[] key) throws IOException {
        int b = in.read();
        int count = 0;
        while (b != -1) {
            out.write(b ^ key[count % key.length]);
            count++;
            b = in.read();
        }
    }

    public static void code(String inputName, String outputName, byte[] key) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                code(inputStream, outputStream, key);
            }
        }
    }
}
