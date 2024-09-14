package sys.pro;

import java.util.Objects;

public class Variable extends Expression {
    protected String var;

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
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Variable copy() {
        return new Variable(var);
    }
}
