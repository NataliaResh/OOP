package sys.pro;

import java.util.HashMap;

/**
 * Class for managing Expressions.
 */
public abstract class Expression {
    /**
     * Take the derivative.
     *
     * @param var differentiation variable.
     * @return derivative of expression.
     */
    public abstract Expression derivative(String var);

    /**
     * Returns string of expression.
     *
     * @return string of expression.
     */
    @Override
    public abstract String toString();

    /**
     * Implements of {@link Expression#eval} method.
     *
     * @param vars HashMap of variables with theirs values.
     * @return result of expression.
     */
    protected abstract int evalImpl(HashMap<String, Integer> vars);

    /**
     * Returns result of expression.
     *
     * @param vars designation of variables via {@code ;}.
     * @return result of expression.
     */
    public int eval(String vars) {
        HashMap<String, Integer> dictVars = getVariables(vars);
        return evalImpl(dictVars);
    }

    /**
     * Returns deep copy of expression.
     *
     * @return deep copy of expression.
     */
    public abstract Expression copy();

    /**
     * Returns HashMap of variables with theirs values from string
     * with designation of variables via {@code ;}.
     *
     * @param vars string with designation of variables via {@code ;}.
     * @return HashMap of variables with theirs values
     */
    private static HashMap<String, Integer> getVariables(String vars) {
        if (vars.isEmpty()) {
            return null;
        }
        HashMap<String, Integer> dict = new HashMap<>();
        String[] arrayVars = vars.split("; ");
        for (String str : arrayVars) {
            String[] var = str.split(" = ");
            if (var.length != 2) {
                Utils.exit("Incorrect values of variables!)");
            }
            dict.put(var[0], Integer.parseInt(var[1]));
        }
        return dict;
    }
}
