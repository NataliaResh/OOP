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
        try {
            return new Number(eval(""));
        } catch (NotEnoughSignificationsExpression ignored) {
            Utils.pass();
        }
        Expression newRight = right.simplify();
        Expression newLeft = left.simplify();
        try {
            int rightResult = newRight.eval("");
            if (rightResult == 0) {
                return new Number(0);
            }
        } catch (NotEnoughSignificationsExpression ignored) {
            Utils.pass();
        }
        try {
            int leftResult = newRight.eval("");
            if (leftResult == 1) {
                return newRight;
            }
        } catch (NotEnoughSignificationsExpression ignored) {
            Utils.pass();
        }
        return new Div(newRight, newLeft);
    }
}
