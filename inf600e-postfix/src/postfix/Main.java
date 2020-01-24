
package postfix;

import java.io.*;

import postfix.lexer.*;
import postfix.node.*;
import postfix.parser.*;

public class Main {

    public static void main(
            String[] args) {

        System.out.print("Type an expression: ");

        Parser p = new Parser(new Lexer(
                new PushbackReader(new InputStreamReader(System.in), 1024)));

        Node tree;

        try {
            tree = p.parse();
//            tree.apply(new Translation());
            int res = Calculator.calculate(tree);
//            System.out.println();
            System.out.println("The result is " + res);

        }
        catch (ArithmeticException e) {
            System.err.println("DIVISION BY ZERO ERROR: " + e.getMessage());
        }
        catch (CalculatorException e) {
            System.err.println("NUMBER OVERFLOW ERROR: " + e.getMessage());
            System.exit(1);
        }
        catch (ParserException e) {
            System.err.println("SYNTAX ERROR: " + e.getMessage());
            System.exit(1);
        }
        catch (LexerException e) {
            System.err.println("LEXICAL ERROR: " + e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("INPUT/OUTPUT ERROR: " + e.getMessage());
            System.exit(1);
        }
    }

}
