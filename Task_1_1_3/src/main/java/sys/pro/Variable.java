package sys.pro;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class for managing Variable.
 */
public class Variable extends Expression {
    protected String var;

    /**
     * Construct new Variable.
     *
     * @param var name of variable.
     */
    public Variable(String var) {
        this.var = var;
    }

    @Override
    public Number derivative(String var) {
        if (Objects.equals(this.var, var)) {
            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    protected int evalImpl(HashMap<String, Integer> vars) {
        if (!vars.containsKey(var)) {
            Utils.exit("Not all variables have value!");
        }
        return vars.get(var);
    }

    @Override
    public Variable copy() {
        return new Variable(var);
    }
}
