package sys.pro;

import java.util.HashMap;

/**
 * Class for managing Add.
 */
public class Add extends Binary {
    /**
     * Construct new Add expression.
     *
     * @param right right expression in sum;
     * @param left left expression in sum.
     */
    public Add(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Add derivative(String var) {
        return new Add(right.derivative(var), left.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "+" + left.toString() + ")";
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) throws NotEnoughSignificationsExpression {
        return right.evalImpl(vars) + left.evalImpl(vars);
    }

    @Override
    public Add copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Add(newRight, newLeft);
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
                return newLeft;
            }
        } catch (NotEnoughSignificationsExpression ignored) {
            Utils.pass();
        }
        try {
            int leftResult = newRight.eval("");
            if (leftResult == 0) {
                return newRight;
            }
        } catch (NotEnoughSignificationsExpression ignored) {
            Utils.pass();
        }
        return new Add(newRight, newLeft);
    }
}
