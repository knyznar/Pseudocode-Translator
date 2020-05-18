import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import translator.MarkupParser.PseudocodeLexer;
import translator.MarkupParser.PseudocodeParser;
import org.antlr.v4.runtime.tree.*;

public class SimpleGrammarTest {
    public static void main(String[] args) {
        SimpleGrammarTest simpleGrammarTest = new SimpleGrammarTest();
        simpleGrammarTest.parse();
    }
    public void parse() {
        String exampleCode = "1+2";
        PseudocodeLexer lexer = new PseudocodeLexer(new ANTLRInputStream(exampleCode));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        PseudocodeParser parser = new PseudocodeParser(commonTokenStream);
        parser.setBuildParseTree(true);

        ParseTree tree = parser.start();

    }
}
