package sys.pro;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class for managing Sub.
 */
public class Sub extends Binary {
    /**
     * Construct new Sub expression.
     *
     * @param right right expression in sum;
     * @param left left expression in sum.
     */
    public Sub(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Sub derivative(String var) {
        return new Sub(right.derivative(var), left.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "-" + left.toString() + ")";
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) throws NotEnoughSignificationsExpression {
        return right.evalImpl(vars) - left.evalImpl(vars);
    }

    @Override
    public Sub copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Sub(newRight, newLeft);
    }

    public Expression simplify() {
        try {
            return new Number(eval(""));
        } catch (NotEnoughSignificationsExpression ignored) {}
        Expression newRight = right.simplify();
        Expression newLeft = left.simplify();
        try {
            int rightResult = newRight.eval("");
            if (rightResult == 0) {
                return newLeft;
            }
        } catch (NotEnoughSignificationsExpression ignored) {}
        try {
            int leftResult = newRight.eval("");
            if (leftResult == 0) {
                return newRight;
            }
        } catch (NotEnoughSignificationsExpression ignored) {}
        if (Objects.equals(newRight.toString(), newLeft.toString())) {
            return new Number(0);
        }
        return new Sub(newRight, newLeft);
    }
}
