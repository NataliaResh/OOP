package sys.pro;

public class Sub extends Binary {
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
    public int eval(String vars) {
        return 0;
    }

    @Override
    public Sub copy() {
        Expression newRight = right.copy();
        Expression newLeft = left.copy();
        return new Sub(newRight, newLeft);
    }
}
