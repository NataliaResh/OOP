package sys.pro;

/**
 * Class for managing Binary expression.
 */
public abstract class Binary extends Expression {
    protected final Expression right;
    protected final Expression left;

    /**
     * Construct new Binary expression.
     *
     * @param right right expression in sum;
     * @param left left expression in sum.
     */
    public Binary(Expression right, Expression left) {
        this.right = right;
        this.left = left;
    }

    protected boolean hasVariable() {
        return right.hasVariable() || left.hasVariable();
    }
}
