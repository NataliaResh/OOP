package sys.pro;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Expression {
    public abstract Expression derivative(String var);

    @Override
    public abstract String toString();

    protected abstract int evalImpl(HashMap<String, Integer> vars);

    public int eval(String vars) {
        HashMap<String, Integer> dictVars = getVariables(vars);
        return evalImpl(dictVars);
    }

    public abstract Expression copy();

    private static HashMap<String, Integer> getVariables(String vars) {
        if (vars.isEmpty()) {
            return null;
        }
        HashMap<String, Integer> dict = new HashMap<>();
        String[] arrayVars = vars.split("; ");
        for (String str: arrayVars) {
            String[] var = str.split(" = ");
            if (var.length != 2) {
                Utils.exit("Incorrect values of variables!)");
            }
            dict.put(var[0], Integer.parseInt(var[1]));
        }
        return dict;
    }
}
