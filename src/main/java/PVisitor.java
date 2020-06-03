import translator.MarkupParser.PseudocodeBaseVisitor;
import translator.MarkupParser.PseudocodeParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class PVisitor extends PseudocodeBaseVisitor<String> {
    FileWriter outputCode;

    @Override
    public String visitStart(PseudocodeParser.StartContext ctx) {
        System.out.println("in start");
        return super.visitStart(ctx);
    }

    @Override
    public String visitEof(PseudocodeParser.EofContext ctx) {
        System.out.println("visit EOF");
        if(outputCode!=null) {
            System.out.println("close file");
            try {
                outputCode.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitEof(ctx);
    }

    @Override
    public String visitStatement(PseudocodeParser.StatementContext ctx) {
        System.out.println("visit statement");

        if(outputCode==null) {
            System.out.println("open file");
            try {
                outputCode = new FileWriter("outputCode.java", false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitStatement(ctx);
    }

    @Override
    public String visitFor_statement(PseudocodeParser.For_statementContext ctx) {
        System.out.println("visit for stmt");
        // for(a = 0 : 10)
        try {
            outputCode.write(ctx.FOR().getText() + '(');
            visitFor_conditions(ctx.for_conditions());
            outputCode.write(") {\n");

//            for(PseudocodeParser.If_or_loop_bodyContext contentLine : ctx.if_or_loop_body()) {
//                visitIf_or_loop_body(contentLine);
//            }

            visitIf_or_loop_body(ctx.if_or_loop_body());

            outputCode.write("\n}");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; //super.visitFor_statement(ctx);
    }

    @Override
    public String visitFor_conditions(PseudocodeParser.For_conditionsContext ctx) {
        try {
            outputCode.write("int " + ctx.IDENTIFIER() + '=' + ctx.INTEGER_LITERAL(0)+"; "+ctx.IDENTIFIER()+" < "+ctx.INTEGER_LITERAL(1)+"; "+ctx.IDENTIFIER()+"++");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFor_conditions(ctx);
    }

    @Override
    public String visitWhile_statement(PseudocodeParser.While_statementContext ctx) {
        return super.visitWhile_statement(ctx);
    }

    @Override
    public String visitPrint(PseudocodeParser.PrintContext ctx) {
        try {
            outputCode.write("System.out.println" + ctx.LEFT_ROUND_BRACKET());
            visitString(ctx.string());
            outputCode.write(ctx.RIGHT_ROUND_BRACKET().getText() + ';' + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitString(PseudocodeParser.StringContext ctx) {
        try {
            outputCode.write(ctx.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitVariable_declaration(PseudocodeParser.Variable_declarationContext ctx) {
        try {
            if(ctx.ASSIGN() != null) {
                outputCode.write(ctx.type().getText() + " " + ctx.IDENTIFIER() + ctx.ASSIGN() + ctx.literal().getText() + ';' + '\n');
            } else {
                outputCode.write(ctx.type().getText() + " " + ctx.IDENTIFIER() + ';' + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitVariable_declaration(ctx);
    }

    @Override
    public String visitIf_statement(PseudocodeParser.If_statementContext ctx) {
        System.out.println("visit if stmt");
        try {
            outputCode.write(ctx.IF().getText() + '(' + ctx.boolean_expression().getText() + ") {\n");
            if(ctx.trueStatement != null) {
                visitIf_or_loop_body(ctx.if_or_loop_body(0));
            }
            outputCode.write('}');

            if(ctx.ELSE() != null) {
                outputCode.write("else {\n");
                if(ctx.falseStatement != null) {
                    visitIf_or_loop_body(ctx.if_or_loop_body(1));
                }
                outputCode.write("}\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; //super.visitIf_statement(ctx);
    }

    @Override
    public String visitIf_or_loop_body(PseudocodeParser.If_or_loop_bodyContext ctx) {
        return super.visitIf_or_loop_body(ctx);
    }

}
