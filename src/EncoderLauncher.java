
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class EncoderLauncher {

    @Option(name = "-c", usage = "code File")
    private String cKey;

    @Option(name = "-d", usage = "decode File")
    private String dKey;

    @Argument(required = true, usage = "Name of input file")
    private String inputFileName;

    @Option(name = "-o", required = true, usage = "Name of output file")
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
            System.err.println("java -jar Xor.jar [-c|-d] -o outputname.txt inputname.txt");
            parser.printUsage(System.err);
            return;
        }

        //{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}; if key is not valid...

        if (cKey == null && dKey == null /* &keyIsNotValid */) System.err.println("invalid key");
        char[] key;
        if (cKey != null) {
            key = cKey.toCharArray();
        } else {
            key = dKey.toCharArray();
        }

        Encoder encoder = new Encoder();
        try {
            encoder.code(inputFileName, outputFileName, key);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}