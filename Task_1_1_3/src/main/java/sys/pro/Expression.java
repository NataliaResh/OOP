package sys.pro;

import java.util.Arrays;

public abstract class Expression {
    public static Expression toExpression(String string) {
        String[] strings = string.split("\\(");
        System.out.println(Arrays.toString(strings));
        return null;
    }

    public abstract Expression derivative(String var);

    @Override
    public abstract String toString();

    public abstract int eval(String vars);

    public abstract Expression copy();
}
