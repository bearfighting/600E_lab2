
package postfix;

import postfix.analysis.*;
import postfix.node.*;

public class Translation
        extends DepthFirstAdapter {

    @Override
    public void caseTNumber(
            TNumber node) {

        System.out.print(node.getText() + " ");
    }

    @Override
    public void outAPlusExpr(
            APlusExpr node) {

        System.out.print(node.getPlus().getText() + " ");
    }

    @Override
    public void outAMinusExpr(
            AMinusExpr node) {

        System.out.print(node.getMinus().getText() + " ");
    }

    @Override
    public void outAMultFactor(
            AMultFactor node) {

        System.out.print(node.getMult().getText() + " ");
    }

    @Override
    public void outADivFactor(
            ADivFactor node) {

        System.out.print(node.getDiv().getText() + " ");
    }

    @Override
    public void outAModFactor(
            AModFactor node) {

        System.out.print(node.getMod().getText() + " ");
    }
}
