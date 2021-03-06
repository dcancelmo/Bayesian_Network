import bn.inference.BNInference;
import bn.parser.BIFLexer;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyBNApproxInferencer {

    public static void main(String[] argv) throws IOException, ParserConfigurationException, SAXException {
        String filename = argv[1];
        BayesianNetwork network = MyBNInferencer.parseNetwork(filename);
        if (network == null) return;
//        network.print(System.out);
        BNInference enumber = new BNInference();
        Assignment ass = new Assignment();
        System.out.println("Bayesian Network file used: " + filename);
        System.out.println("====Assignments====");
//        ass.set(network.getVariableByName("B"), "true");
        for (int i = 3; i < argv.length; i += 2) {
            ass.set(network.getVariableByName(argv[i]), argv[i+1]);
        }
        System.out.println(ass);
        System.out.println("====Results====");
        System.out.println("Query: " + argv[2] + " Rejection sampling result = " + enumber.rejectionSampling(network.getVariableByName(argv[2]), ass, network, Integer.parseInt(argv[0])));
        System.out.println("Query: " + argv[2] + " Likelihood Weighting result = " + enumber.likelihoodWeighting(network.getVariableByName(argv[2]), ass, network, Integer.parseInt(argv[0])));
    }


}
