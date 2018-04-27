import java.io.*;

public class Encoder {

    private int code(InputStream in, String outputFileName, char[] key) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (FileWriter writer = new FileWriter(outputFileName)) {
                int sym = reader.read();
                int count = 0;
                while (sym != -1) {
                    writer.write(sym ^ key[count % key.length]);
                    count++;
                    sym = reader.read();
                }
                return -1;
            }
        }
    }

    public int code(String inputName, String outputName, char[] key) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            return code(inputStream, outputName, key);
        }
    }
}
