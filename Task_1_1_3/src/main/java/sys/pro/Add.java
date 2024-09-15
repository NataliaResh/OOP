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
    protected int evalImpl(HashMap<String, Integer> vars) {
        return right.evalImpl(vars) + left.evalImpl(vars);
    }

    @Override
    public Add copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Add(newRight, newLeft);
    }
}
