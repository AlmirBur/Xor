/**
        import org.kohsuke.args4j.Argument;
        import org.kohsuke.args4j.CmdLineException;
        import org.kohsuke.args4j.CmdLineParser;
        import org.kohsuke.args4j.Option;

        import java.io.IOException;

public class EncoderLauncher {


    @Option(name = "-c", usage = "code File")
    private boolean unpackFlag;

    @Option(name = "-d", usage = "decode File")
    private boolean packFlag;

    @Option(name = "-out", required = true, usage = "Name of input file")
    private String inputFileName;

    @Argument(required = true, usage = "Name of output file")
    private String outputFileName;

    public static void main(String[] args) {
        new RecoderLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Xor.jar -c CodingI -d DecodingI InputName OutputName");
            parser.printUsage(System.err);
            return;
        }

        Encoder encoder = new Encoder(inputEncoding, outputEncoding);
        try {
            int result = recoder.recode(inputFileName, outputFileName);
            System.out.println("Total of " + result + " symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
