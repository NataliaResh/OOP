package sys.pro;

public class Number extends Expression {
    protected int val;

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
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Number copy() {
        return new Number(val);
    }
}
