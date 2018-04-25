import java.io.*;

public class Encoder {

    private final String charsetInput;
    private final String charsetOutput;
    public Encoder(String charsetInput, String charsetOutput) {
        this.charsetInput = charsetInput;
        this.charsetOutput = charsetOutput;
    }

    public int code(InputStream in, OutputStream out, char[] key) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, charsetInput)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, charsetOutput)) {
                int sym = reader.read();
                int count = 0;
                while (sym != -1) {
                    writer.write(sym ^ key[count % key.length]);
                    count++;
                    sym = reader.read();
                }
                return count;
            }
        }
    }

    public int code(String inputName, String outputName, char[] key) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return code(inputStream, outputStream, key);
            }
        }
    }
}
