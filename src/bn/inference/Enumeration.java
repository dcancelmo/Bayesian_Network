package bn.inference;

import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.RandomVariable;
import bn.parser.XMLBIFParser;

public class Enumeration implements Inferencer {

    @Override
    public Distribution ask(BayesianNetwork bn, RandomVariable X, Assignment e) {
        Distribution q = new Distribution(X);
        for (Object xi : X.getDomain()) {
             //q.put(all(), xi);
        }
        q.normalize();
        return q;
    }

}
