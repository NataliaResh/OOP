package sys.pro;

public class Mul extends Binary {
    public Mul(Expression right, Expression left) {
        super(right, left);
    }

    @Override
    public Add derivative(String var) {
        return new Add(new Mul(right.derivative(var), left.copy()), new Mul(right.copy(), left.derivative(var)));
    }

    @Override
    public String toString() {
        return "(" + right.toString() + "*" + left.toString() + ")";
    }

    @Override
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Mul copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Mul(newRight, newLeft);
    }
}
