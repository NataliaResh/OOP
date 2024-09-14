package sys.pro;

public class Div extends Binary {
    public Div(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Div derivative(String var) {
        return new Div(new Sub(new Mul(right.derivative(var), left.copy()),
                new Mul(right.copy(), left.derivative(var))),
                new Mul(left.copy(), left.copy()));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "/" + left.toString() + ")";
    }

    @Override
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Div copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Div(newRight, newLeft);
    }
}
