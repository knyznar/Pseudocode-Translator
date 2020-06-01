import translator.MarkupParser.PseudocodeBaseVisitor;
import translator.MarkupParser.PseudocodeParser;

public class PVisitor extends PseudocodeBaseVisitor<String> {
    @Override
    public String visitStart(PseudocodeParser.StartContext ctx) {
        return super.visitStart(ctx);
//        System.out.println(ctx);
//        return visitChildren(ctx);
    }

    @Override
    public String visitStatement(PseudocodeParser.StatementContext ctx) {
        return super.visitStatement(ctx);
    }

    @Override
    public String visitFor_statement(PseudocodeParser.For_statementContext ctx) {
        return super.visitFor_statement(ctx);
    }

    @Override
    public String visitIf_statement(PseudocodeParser.If_statementContext ctx) {
        return super.visitIf_statement(ctx);
    }

    @Override
    public String visitWhile_statement(PseudocodeParser.While_statementContext ctx) {
        return super.visitWhile_statement(ctx);
    }

    @Override
    public String visitPrint(PseudocodeParser.PrintContext ctx) {
        System.out.println(ctx.getText());
        return super.visitPrint(ctx);
    }

    @Override
    public String visitString(PseudocodeParser.StringContext ctx) {
        System.out.println(ctx.getText());
        return super.visitString(ctx);
    }

    @Override
    public String visitType(PseudocodeParser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public String visitVariable_declaration(PseudocodeParser.Variable_declarationContext ctx) {
        return super.visitVariable_declaration(ctx);
    }

    @Override
    public String visitFor_conditions(PseudocodeParser.For_conditionsContext ctx) {
        return super.visitFor_conditions(ctx);
    }

    @Override
    public String visitBoolean_expression(PseudocodeParser.Boolean_expressionContext ctx) {
        return super.visitBoolean_expression(ctx);
    }

    @Override
    public String visitExpression(PseudocodeParser.ExpressionContext ctx) {
        return super.visitExpression(ctx);
    }

    @Override
    public String visitPrimary_expression(PseudocodeParser.Primary_expressionContext ctx) {
        return super.visitPrimary_expression(ctx);
    }

    @Override
    public String visitMath_operator(PseudocodeParser.Math_operatorContext ctx) {
        return super.visitMath_operator(ctx);
    }


}
