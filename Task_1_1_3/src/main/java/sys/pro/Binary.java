package sys.pro;

/**
 * Class for managing Binary expression.
 */
public abstract class Binary extends Expression {
    protected Expression right;
    protected Expression left;

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
}
