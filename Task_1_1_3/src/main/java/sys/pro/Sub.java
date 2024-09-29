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
                return newLeft;
            }
        }
        if (!newLeft.hasVariable()) {
            int leftResult = newLeft.safeEval();
            if (leftResult == 0) {
                return newRight;
            }
        }
        if (Objects.equals(newRight.toString(), newLeft.toString())) {
            return new Number(0);
        }
        return new Sub(newRight, newLeft);
    }

    @Override
    protected int safeEval() {
        return right.safeEval() - left.safeEval();
    }
}
