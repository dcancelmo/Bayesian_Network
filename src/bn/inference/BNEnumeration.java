package bn.inference;

import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.parser.XMLBIFParser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BNEnumeration implements Inferencer {

//    @Override
//    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
//        Distribution q = new Distribution(X);
//        for (Object xi : X.getDomain()) {
//             q.put(xi, all(bn.getVariableListTopologicallySorted(), e, bn));
//        }
//        q.normalize();
//        return q;
//    }
//
////  function ENUMERATE-ALL(vars, e) returns a probability (a real number in [0,1])
////  inputs: vars, a list of all the variables
////  e, observed values for some set of variables E
//    public double all(List<RandomVariable> vars, Assignment e, BayesianNetwork bn) {
//        // if EMPTY(vars) then return 1.0
//        if (vars.isEmpty()) return 1.0f;
//        // Y ← FIRST(vars)
//        RandomVariable y = vars.remove(0);
//        // if Y is assigned a value (call it y) in e then
//        if (e.containsValue(y)) {
//        // return P(Y=y | values assigned to Y’s parents in e) × ENUMERATE-ALL(REST(vars), e)
//            return bn.getProb(y, e) * all(vars, e, bn);
//        } else {
////            return ∑yi [P(Y=yi | values assigned to Y’s parents in e) × ENUMERATE-ALL(REST(vars), eyi)],
////            where eyi is the evidence e plus the assignment Y=yi
//        }
//        return 0.0;
//    }

    @Override
    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
        Distribution result = new Distribution();
        for (Object d : X.getDomain()) {
            result.put(d, EnumerateAll(bn.getVariableList(), e, bn));
        }
        result.normalize();
        return result;
    }

    public double EnumerateAll(List<RandomVariable> vars, Assignment e, BayesianNetwork bn) {
        if (vars.isEmpty()) return 1.0;
        RandomVariable v = vars.get(0);
        vars.remove(0);
        if (bn.getProb(v, bn.)) {

        }
    }

    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
        Distribution result = new Distribution();
        Assignment[] truthTable = new Assignment[(int) (e.variableSet().size() + 1)];
        for (int i = 0; i < truthTable.length; i++) {
            String binaryString = String.format("%" + symbolList.size() + "s", Integer.toBinaryString(i)).replace(" ", "0");
            supermodel[i] = new ModelC();
            for (int p = 0; p < binaryString.length(); p++) {
                supermodel[i].set(symbolList.get(p), '1' == binaryString.charAt(p));
            }
        }
    }



//    @Override
//    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
//        Distribution q = new Distribution(X);
//        factors ← [for each variable v in bn.VARS, the CPT for v given e
//        BigDecimal product = BigDecimal.valueOf(1);
//        List<BigDecimal> factors = new ArrayList<BigDecimal>();
//        for (RandomVariable var : bn.getVariableList()) {
//            System.out.print("var: "+var);
//            if (e.variableSet().contains(var)) {
//                System.out.println(" " + bn.getProb(var, e));
//            } else {
//                System.out.println(" Probability not found!");
//                System.out.println(e);
//            }
//        }
//        if (!var.equals(X) && !e.variableSet().contains(var)) {
//
//        }
//        q.normalize();
//        return q;
//        return null;
//    }

}
