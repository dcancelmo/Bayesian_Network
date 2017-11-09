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

    @Override
    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
//        Q(X )← a distribution over X , initially empty
        Distribution result = new Distribution(X);
//        for each value xi of X do
        int i = 0;
        for (Object d : X.getDomain()) {
            Assignment cpy = e.copy();
            cpy.set(X, X.getDomain().get(i));
//            Q(xi)← ENUMERATE-ALL(bn.VARS, exi ) where exi is e extended w
            result.put(X.getDomain().get(i), all(bn.getVariableListTopologicallySorted(), cpy, bn));
            i++;
        }
//        return NORMALIZE(Q(X))
        result.normalize();
        return result;
    }

    public float all(List<RandomVariable> vars, Assignment e, BayesianNetwork bn) {
//        if EMPTY(vars) then return 1.0
        if (vars.isEmpty()) return 1.0f;
//        Y ← FIRST(vars)
        RandomVariable y = vars.remove(0);
        // if Y is assigned a value (call it y) in e then
        if (e.containsValue(y)) {
//        return P(Y=y | values assigned to Y’s parents in e) × ENUMERATE-ALL(REST(vars), e)
            return (float) bn.getProb(y, e) * all(vars, e, bn); //Does this count as pointwise?
        } else {
//            return ∑yi [P(Y=yi | values assigned to Y’s parents in e) × ENUMERATE-ALL(REST(vars), eyi)],
//            where eyi is the evidence e plus the assignment Y=yi
            float yi = 0.0f;
            Assignment assign;
            for(Object value : y.getDomain()) {
                Assignment temp = e.copy();
                temp.put(y, value);
                assign = temp;
                yi += bn.getProb(y, assign) * all(vars, assign, bn); //Does this count as pointwise?
            }
            return yi;
        }
    }

    public Distribution rejectionSampling(RandomVariable X, Assignment e, BayesianNetwork bn, int N) {
        Distribution estDistribution = new Distribution();
        double total = 0.0;
        double consistent = 0.0;
        for (int j = 1; j < N; j++) {  //Sample N times
            Assignment sample = priorSample(bn);
            for (RandomVariable var : e.variableSet())
                if (e.get(var).equals(sample.get(var))) {
                    total += 1;
                    if (sample.get(X).equals("true")) consistent += 1;
                }
        }
        estDistribution.put(X.getDomain().get(0), consistent/total);
        estDistribution.put(X.getDomain().get(1), 1-(consistent/total));
        estDistribution.normalize();
        return estDistribution;
    }

    public Assignment priorSample(BayesianNetwork bn) {
//        x← an event with n elements
        Assignment x = new Assignment();
        x.set(bn.getVariableListTopologicallySorted().get(0), "true");
        Random r = new Random();
//        foreach variable Xi in X1,...,Xn do
        for (RandomVariable xi : bn.getVariableListTopologicallySorted()) {
            Double rVal = r.nextDouble();
//            System.out.println(xi);
//            System.out.println(bn.getNodeForVariable(xi).cpt);
//            Assignment thisTableP = new Assignment();
            x.set(xi, "true");
            String rResult = (rVal > bn.getNodeForVariable(xi).cpt.get(x)) ? "true" : "false";
//            System.out.println("x: "+x);
//            System.out.println("Result: "+rResult);
            x.set(xi, rResult);
//            x.set(xi, bn.getNodeForVariable(xi));
//            x[i] ← a random sample from P(Xi | parents(Xi)
        }
//        return x
        return x;
    }

//    for j = 1 to N do
//      x← PRIOR-SAMPLE(bn)
//      if x is consistent with e then
//          N[x ] ←N[x ]+1 where x is the value of X in x
//    return NORMALIZE(N)

    //Rejection sampling for the rain/lawn example
    //Repeat 100 times:
    //Visit nodes topologically
    //Sample from prior (always the first node visited)
    //Sample from next node visited, given prior's value (aka given node's parents)
    //Sample from next node visited, given prior's value (aka given node's parents)
    //etc...
    //Visit last node, sample with respect to parent's sampled values
    //Tally that
    //Reject if does not match given evidence
    //Return tally over number of samples that meet evidence



//    private Factor pointwiseProduct(List<Factor> factors) {
//
//        Factor product = factors.get(0);
//        for (int i = 1; i < factors.size(); i++) {
//            product = product.pointwiseProduct(factors.get(i));
//        }
//
//        return product;
//    }

//    public double EnumerateAll(List<RandomVariable> vars, Assignment e, BayesianNetwork bn) {
//        if (vars.isEmpty()) return 1.0;
//        RandomVariable v = vars.get(0);
//        vars.remove(0);
//        if (bn.getProb(v, bn.)) {
//
//        }
//    }

//    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
//        Distribution result = new Distribution();
//        Assignment[] truthTable = new Assignment[(int) (e.variableSet().size() + 1)];
//        for (int i = 0; i < truthTable.length; i++) {
//            String binaryString = String.format("%" + symbolList.size() + "s", Integer.toBinaryString(i)).replace(" ", "0");
//            supermodel[i] = new ModelC();
//            for (int p = 0; p < binaryString.length(); p++) {
//                supermodel[i].set(symbolList.get(p), '1' == binaryString.charAt(p));
//            }
//        }
//    }



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
