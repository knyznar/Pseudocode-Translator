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
        String exampleCode = "if(a) {\n" +
                "int b=2 \n" +
                "int g=5\n" +
                "} else {\n" +
                "int c=3\n" +
                "}\n print(\"aaa\")\n int a=4";
//        String exampleCode = "for(a = 0 : 10) {int a=1\nprint(\"aaa\")}";
        PseudocodeLexer lexer = new PseudocodeLexer(new ANTLRInputStream(exampleCode));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        PseudocodeParser parser = new PseudocodeParser(commonTokenStream);
//        parser.setBuildParseTree(true);

        ParseTree tree = parser.start();
        System.out.println(tree.toStringTree());
        System.out.println("VISITOR:");

        PVisitor pVisitor = new PVisitor();
        pVisitor.visit(tree);
    }
}
