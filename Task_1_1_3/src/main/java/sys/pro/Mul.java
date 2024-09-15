package sys.pro;

import java.util.HashMap;

/**
 * Class for managing Mul.
 */
public class Mul extends Binary {
    /**
     * Construct new Mul expression.
     *
     * @param right right expression in sum;
     * @param left  left expression in sum.
     */
    public Mul(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Add derivative(String var) {
        return new Add(new Mul(right.derivative(var), left.copy()),
                new Mul(right.copy(), left.derivative(var)));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "*" + left.toString() + ")";
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) {
        return right.evalImpl(vars) * left.evalImpl(vars);
    }

    @Override
    public Mul copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Mul(newRight, newLeft);
    }
}
