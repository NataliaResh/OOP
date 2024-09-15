package sys.pro;

import java.util.HashMap;

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
    protected int evalImpl(HashMap<String, Integer> vars) {
        return right.evalImpl(vars) - left.evalImpl(vars);
    }

    @Override
    public Sub copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Sub(newRight, newLeft);
    }
}
