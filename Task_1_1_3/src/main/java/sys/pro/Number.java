package sys.pro;

import java.util.HashMap;

/**
 * Class for managing Number.
 */
public class Number extends Expression {
    protected final int val;

    /**
     * Construct new Number.
     *
     * @param val value of number.
     */
    public Number(int val) {
        this.val = val;
    }

    @Override
    public Number derivative(String var) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) {
        return val;
    }

    @Override
    public Number copy() {
        return new Number(val);
    }

    @Override
    public Expression simplify() {
        return new Number(val);
    }

    @Override
    protected int safeEval() {
        return val;
    }

    @Override
    protected boolean hasVariable() {
        return false;
    }


}
