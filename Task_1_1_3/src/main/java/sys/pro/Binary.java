package sys.pro;

public abstract class Binary extends Expression {
    protected Expression right;
    protected Expression left;

    public Binary(Expression right, Expression left) {
        this.right = right;
        this.left = left;
    }

    public Expression cope() {
        return null;
    }
}
