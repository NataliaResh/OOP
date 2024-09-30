package sys.pro;

import java.util.HashMap;

/**
 * Class for managing Div.
 */
public class Div extends Binary {
    /**
     * Construct new Div expression.
     *
     * @param right right expression in sum;
     * @param left left expression in sum.
     */
    public Div(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Div derivative(String var) {
        return new Div(new Sub(new Mul(right.derivative(var), left.copy()),
                new Mul(right.copy(), left.derivative(var))),
                new Mul(left.copy(), left.copy()));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "/" + left.toString() + ")";
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) throws NotEnoughSignificationsExpression {
        int leftValue = left.evalImpl(vars);
        if (leftValue == 0) {
            Utils.exit("Division by zero!");
        }
        return right.evalImpl(vars) / leftValue;
    }

    @Override
    public Div copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Div(newRight, newLeft);
    }

    @Override
    public Expression simplify() {
        if (!hasVariable()) {
            return new Number(safeEval());
        }
        Expression newRight = right.simplify();
        Expression newLeft = left.simplify();
        if (!newRight.hasVariable()) {
            int rightResult = newRight.safeEval();
            if (rightResult == 0) {
                return new Number(0);
            }
        }
        if (!newLeft.hasVariable()) {
            int leftResult = newLeft.safeEval();
            if (leftResult == 1) {
                return newRight;
            }
        }
        return new Div(newRight, newLeft);
    }

    @Override
    protected int safeEval() {
        return right.safeEval() / left.safeEval();
    }
}
