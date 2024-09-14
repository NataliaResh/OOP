package sys.pro;

public class Add extends Binary {
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
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Add copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Add(newRight, newLeft);
    }
}
