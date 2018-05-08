package encoder;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.Option;

import java.io.IOException;

import static encoder.Encoder.code;

public class EncoderLauncher {

    @Option(name = "-c", usage = "code File")
    private String cKey;

    @Option(name = "-d", forbids = "-c", usage = "decode File")
    private String dKey;

    @Argument(required = true, usage = "Name of input file")
    private String inputFileName;

    @Option(name = "-o", usage = "Name of output file")
    private String outputFileName;

    public static void main(String[] args) {
        new EncoderLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Xor.jar [-c|-d] inputname.txt -o outputname.txt");
            parser.printUsage(System.err);
            return;
        }

        if (cKey == null && dKey == null) {
            System.err.println("invalid key");
        }

        String key;
        if (cKey != null) {
            key = cKey;
        } else {
            key = dKey;
        }

        boolean keyIsNotValid = false;
        for (int i = 0; i < key.length(); i++) {
            if (!"0123456789ABCDEF".contains(key.substring(i, i + 1))) {
                keyIsNotValid = true;
                break;
            }
        }

        if (keyIsNotValid) {
            System.err.println("invalid key");
        }

        if (outputFileName == null) {
            outputFileName = inputFileName + "1";
        }

        try {
            code(inputFileName, outputFileName, key.getBytes());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}