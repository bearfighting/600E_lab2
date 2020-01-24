package postfix;

import postfix.node.Token;

public class CalculatorException extends RuntimeException {
    private Token location;
    public CalculatorException(Token location, String message) {
        super("at line " + location.getLine() + "pos " + location.getPos() + " " + message);
        this.location = location;
    }
}
