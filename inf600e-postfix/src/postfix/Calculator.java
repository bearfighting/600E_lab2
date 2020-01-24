package postfix;

import postfix.analysis.DepthFirstAdapter;
import postfix.node.*;

public class Calculator extends DepthFirstAdapter {
    private int result;

    @Override
    public void caseANumberTerm(ANumberTerm node) {
        try {
            this.result = Integer.parseInt(node.getNumber().getText());
        } catch (NumberFormatException e) {
            throw new CalculatorException(node.getNumber(), "invalid number " + node.getNumber().getText());
        }
    }

    private Calculator() {}

    public static int calculate(Node tree) {
        Calculator calculator = new Calculator();
        tree.apply(calculator);


        return calculator.result;
    }

    @Override
    public void caseAPlusExpr(APlusExpr node) {
        node.getFactor().apply(this);
        int left = this.result;

        node.getExpr().apply(this);
        int right = this.result;

        this.result = left + right;
        if (isOverFlow(left, right, result)) {
            throw new CalculatorException(node.getPlus(), "overflow number " + "left: " + left + " right: " + right);
        }
    }

    private boolean isOverFlow(int left, int right, int result) {
        if (left > 0 && right > 0) {
            return result < 0;
        } else if (left < 0 && right < 0) {
            return result > 0;
        }

        return false;
    }

    @Override
    public void caseAMinusExpr(AMinusExpr node) {
        node.getFactor().apply(this);
        int right = this.result;
        node.getExpr().apply(this);
        int left = this.result;

        this.result = left - right;
    }

    @Override
    public void caseAMultFactor(AMultFactor node) {
        node.getFactor().apply(this);
        int left = this.result;
        node.getTerm().apply(this);
        int right = this.result;
        this.result = left * right;
    }

    @Override
    public void caseADivFactor(ADivFactor node) throws ArithmeticException {
        node.getFactor().apply(this);
        int left = this.result;
        node.getTerm().apply(this);
        int right = this.result;
        this.result = left / right;
    }

    @Override
    public void caseAModFactor(AModFactor node) {
        node.getFactor().apply(this);
        int left = this.result;
        node.getTerm().apply(this);
        int right = this.result;
        this.result = left % right;
    }
}
