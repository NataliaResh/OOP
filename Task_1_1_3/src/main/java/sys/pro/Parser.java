package sys.pro;

import java.util.Stack;

/**
 * Class for managing Parser.
 */
public class Parser {
    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    /**
     * Checks if string is decimal.
     *
     * @param str string for checking.
     * @return true if all characters in string is decimal.
     */
    private static boolean isDecimal(char[] str) {
        for (char ch : str) {
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Return parsed subexpression.
     *
     * @param str   string for parsing;
     * @param start start index;
     * @param end   end index;
     * @return parsed expression.
     * @throws IncorrectExpressionException if string with expression is incorrect.
     */
    private static Expression subExpression(char[] str, int start, int end)
            throws IncorrectExpressionException {
        return parse(String.valueOf(str).substring(start, end));
    }

    private static int getPriority(char ch) {
        if (ch == PLUS || ch == MINUS) {
            return 0;
        }
        if (ch == MULTIPLICATION || ch == DIVISION) {
            return 1;
        }
        return 2;
    }

    private static char[] getSubstring(char[] chars, int start, int end) {
        char[] ans = new char[end - start + 1];
        if (end - start >= 0) {
            System.arraycopy(chars, start, ans, 0, end - start);
        }
        return ans;
    }

    /**
     * Parses string with expression.
     *
     * @param str string for parsing;
     * @return parsed expression.
     * @throws IncorrectExpressionException if string with expression is incorrect.
     */
    public static Expression parse(String str) throws IncorrectExpressionException {
        if (str.isEmpty()) {
            return null;
        }
        char[] chars = str.toCharArray();
        if (chars[0] == LEFT_BRACKET && chars[chars.length - 1] == RIGHT_BRACKET) {
            chars = getSubstring(chars, 1, chars.length - 1);
        }
        Stack<Integer> stack = new Stack<>();
        int[] priorityIndex = {-1, -1};
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case LEFT_BRACKET:
                    stack.push(i);
                    break;
                case RIGHT_BRACKET:
                    if (stack.empty()) {
                        throw new IncorrectExpressionException("There not left bracket"
                                + "before right one!");
                    }
                    stack.pop();
                    break;
                case PLUS:
                    if (stack.empty()) {
                        return new Add(subExpression(chars, 0, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                case MINUS:
                    if (stack.empty()) {
                        return new Sub(subExpression(chars, 0, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                case MULTIPLICATION:
                    if (stack.empty()) {
                        int priority = getPriority(MULTIPLICATION);
                        if (priorityIndex[priority] == -1) {
                            priorityIndex[priority] = i;
                        }
                    }
                    break;
                case DIVISION:
                    if (stack.empty()) {
                        int priority = getPriority(DIVISION);
                        if (priorityIndex[priority] == -1) {
                            priorityIndex[priority] = i;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (!stack.empty()) {
            throw new IncorrectExpressionException("There not right bracket!");
        }
        int index = priorityIndex[getPriority(MULTIPLICATION)];
        if (index != -1) {
            if (chars[index] == MULTIPLICATION) {
                return new Mul(subExpression(chars, 0, index),
                        subExpression(chars, index + 1, chars.length - 1));
            } else if (chars[index] == DIVISION) {
                return new Div(subExpression(chars, 0, index),
                        subExpression(chars, index + 1, chars.length - 1));
            }
        }
        if (isDecimal(chars)) {
            return new Number(Integer.parseInt(String.valueOf(chars)));
        } else {
            return new Variable(String.valueOf(chars));
        }
    }
}
