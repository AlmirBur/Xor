package Encoder;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

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

        if (cKey == null && dKey == null) throw new IllegalArgumentException("invalid key");

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

        if (keyIsNotValid) throw new IllegalArgumentException("invalid key");

        String extra = "";
        if (outputFileName == null) {
            extra = "1";
        }

        try {
            Encoder.code(inputFileName, outputFileName + extra, key.toCharArray());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}