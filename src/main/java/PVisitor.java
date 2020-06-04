
import java.io.FileWriter;
import java.io.IOException;


public class PVisitor extends PseudocodeBaseVisitor<String> {
    FileWriter outputCode;

    @Override
    public String visitStart(PseudocodeParser.StartContext ctx) {
        return super.visitStart(ctx);
    }

    @Override
    public String visitEof(PseudocodeParser.EofContext ctx) {
        if(outputCode!=null) {
            try {
                outputCode.write("\n}\n}");
                outputCode.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitEof(ctx);
    }

    @Override
    public String visitStatement(PseudocodeParser.StatementContext ctx) {
        if(outputCode==null) {
            try {
                outputCode = new FileWriter("OutputCode.java", false);
                outputCode.write("class OutputCode {\n" +
                        "public static void main(String[] args) {\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.visitStatement(ctx);
    }

    @Override
    public String visitFor_statement(PseudocodeParser.For_statementContext ctx) {
        try {
            outputCode.write(ctx.FOR().getText() + '(');
            visitFor_conditions(ctx.for_conditions());
            outputCode.write(") {\n");

            visitIf_or_loop_body(ctx.if_or_loop_body());

            outputCode.write("}\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try {
            outputCode.write(ctx.WHILE().getText() + '(');
            visitBoolean_expression(ctx.boolean_expression());
            outputCode.write(") {\n");
            visitIf_or_loop_body(ctx.if_or_loop_body());
            outputCode.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitBoolean_expression(PseudocodeParser.Boolean_expressionContext ctx) {
        return super.visitBoolean_expression(ctx);

    }

    @Override
    public String visitComparison(PseudocodeParser.ComparisonContext ctx) {
        try {
            if(ctx.NOT_EQUALS() != null) {
                outputCode.write("!=");
            } else if(ctx.EQUALS() != null) {
                outputCode.write("==");
            } else {
                outputCode.write(ctx.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitExpression(PseudocodeParser.ExpressionContext ctx) {
        visitPrimary_expression(ctx.primary_expression(0));
        visitMath_operator(ctx.math_operator());
        visitPrimary_expression(ctx.primary_expression(1));
        return null;
    }

    @Override
    public String visitPrimary_expression(PseudocodeParser.Primary_expressionContext ctx) {
        try {
            outputCode.write(ctx.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitMath_operator(PseudocodeParser.Math_operatorContext ctx) {
        try {
            outputCode.write(ctx.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            if(ctx.type() != null) {
                if(ctx.type().getText().equals("string"))
                    outputCode.write("String" + " ");
                else if(ctx.type().getText().equals("bool"))
                    outputCode.write("boolean" + " ");
                else
                    outputCode.write(ctx.type().getText() + " ");

                if(ctx.IDENTIFIER() != null) {
                    outputCode.write(ctx.IDENTIFIER().getText() + ';' + '\n');
                    return null;
                }
            } else {
                visitAssignment(ctx.assignment());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitVariable_declaration(ctx);
    }

    @Override
    public String visitAssignment(PseudocodeParser.AssignmentContext ctx) {
        try {
            if(ctx.literal() != null) {
                outputCode.write(ctx.IDENTIFIER().getText() + " " + ctx.ASSIGN() + " " + ctx.literal().getText() + ";\n");
            } else if(ctx.expression() != null) {
                outputCode.write(ctx.IDENTIFIER().getText() + " " + ctx.ASSIGN() + " " + ctx.expression().getText() + ";\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitIf_statement(PseudocodeParser.If_statementContext ctx) {
        try {
            outputCode.write(ctx.IF().getText() + '(');
            visitBoolean_expression(ctx.boolean_expression());
            outputCode.write(") {\n");
            if(ctx.trueStatement != null) {
                visitIf_or_loop_body(ctx.if_or_loop_body(0));
            }
            outputCode.write("}");

            if(ctx.ELSE() != null) {
                outputCode.write(" else {\n");
                if(ctx.falseStatement != null) {
                    visitIf_or_loop_body(ctx.if_or_loop_body(1));
                }
                outputCode.write("}\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitIf_or_loop_body(PseudocodeParser.If_or_loop_bodyContext ctx) {
        return super.visitIf_or_loop_body(ctx);
    }
}
