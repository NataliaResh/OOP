package sys.pro;

import java.util.Arrays;
import java.util.Stack;

public class Parser {
    private final static char LEFT_BRACKET = '(';
    private final static char RIGHT_BRACKET = ')';
    private final static char PLUS = '+';
    private final static char MINUS = '-';
    private final static char MULTIPLICATION = '*';
    private final static char DIVISION = '/';

    private static boolean isDecimal(char[] str) {
        for (char ch : str) {
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    private static Expression subExpression(char[] str, int start, int end) throws IncorrectExpressionException {
        return parse(String.valueOf(str).substring(start, end));
    }

    public static Expression parse(String str) throws IncorrectExpressionException {
        char[] chars = str.toCharArray();
        if (chars.length == 0) {
            return null;
        }
        if (chars[0] != LEFT_BRACKET) {
            if (isDecimal(chars)) {
                return new Number(Integer.parseInt(String.valueOf(chars)));
            } else {
                return new Variable(String.valueOf(chars));
            }
        }
        if (chars[chars.length - 1] != RIGHT_BRACKET) {
            throw new IncorrectExpressionException("There not right bracket at the end!");
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i < chars.length - 1; i++) {
            switch (chars[i]) {
                case LEFT_BRACKET:
                    stack.push(i);
                    break;
                case RIGHT_BRACKET:
                    if (stack.empty()) {
                        throw new IncorrectExpressionException("There not left bracket before right one!");
                    }
                    stack.pop();
                    break;
                case PLUS:
                    if (stack.empty()) {
                        return new Add(subExpression(chars, 1, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                case MINUS:
                    if (stack.empty()) {
                        return new Sub(subExpression(chars, 1, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                case MULTIPLICATION:
                    if (stack.empty()) {
                        return new Mul(subExpression(chars, 1, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                case DIVISION:
                    if (stack.empty()) {
                        return new Div(subExpression(chars, 1, i),
                                subExpression(chars, i + 1, chars.length - 1));
                    }
                    break;
                default:
                    break;
            }
        }
        throw new IncorrectExpressionException("There is no correct operation!");
    }
}
