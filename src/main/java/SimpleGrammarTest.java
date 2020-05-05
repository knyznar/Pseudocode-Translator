import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import translator.MarkupParser.PseudocodeLexer;
import translator.MarkupParser.PseudocodeParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class SimpleGrammarTest {
    public static void main(String[] args) {
        SimpleGrammarTest simpleGrammarTest = new SimpleGrammarTest();
        simpleGrammarTest.parse();
    }
    public void parse() {
//        String exampleCode = "1+2";
        ANTLRInputStream inputStream = new ANTLRInputStream("1+2");
        PseudocodeLexer pseudocodeLexer = new PseudocodeLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(pseudocodeLexer);
        PseudocodeParser pseudocodeParser = new PseudocodeParser(commonTokenStream);

//        CommonTree tree = (CommonTree)pseudocodeParser.javaSource().getTree();
//        DOTTreeGenerator gen = new DOTTreeGenerator();
//        StringTemplate st = gen.toDOT(tree);
//        System.out.println(st);

//        ParseTree tree = pseudocodeParser.r();



//        ParseTree parseTree = pseudocodeParser.;


//        PseudocodeParser.FileContext fileContext = markupParser.file();
//        MarkupVisitor visitor = new MarkupVisitor();
//        visitor.visit(fileContext);

    }
}
