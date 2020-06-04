import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.tree.*;
import translator.MarkupParser.PseudocodeLexer;
import translator.MarkupParser.PseudocodeParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;


public class Main {
    public static void main(String[] args) throws IOException {
        Main simpleGrammarTest = new Main();
        File inputFile = new File("input.txt");
        simpleGrammarTest.parse(inputFile);
    }

    public void parse(File file) throws IOException {
        String input = readFile(file);

        PseudocodeLexer lexer = new PseudocodeLexer(new ANTLRInputStream(input));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        PseudocodeParser parser = new PseudocodeParser(commonTokenStream);

        ParseTree tree = parser.start();
        System.out.println(tree.toStringTree());

        PVisitor pVisitor = new PVisitor();
        pVisitor.visit(tree);
    }

    private static String readFile(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
