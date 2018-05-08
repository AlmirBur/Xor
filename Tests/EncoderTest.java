import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

import static encoder.Encoder.code;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EncoderTest {

    @Test
    void codeTest() {
        String key;
        try {
            key = "12F";
            code("files/input", "files/output", key.getBytes());
            code("files/output", "files/check", key.getBytes());
            assertFileContent("files/input", "files/check");

            key = "A3";
            code("files/input", "files/output", key.getBytes());
            code("files/output", "files/check", key.getBytes());
            assertFileContent("files/input", "files/check");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void assertFileContent(String name1, String name2) throws IOException {
        RandomAccessFile reader1 = new RandomAccessFile(name1, "r");
        RandomAccessFile reader2 = new RandomAccessFile(name2, "r");
        String actualContent1 = reader1.readLine();
        String actualContent2 = reader2.readLine();
        assertEquals(actualContent1, actualContent2);
        reader1.close();
        reader2.close();
    }
}