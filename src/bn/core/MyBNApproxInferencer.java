package bn.core;

import bn.inference.BNEnumeration;
import bn.parser.BIFLexer;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
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
        BNEnumeration enumber = new BNEnumeration();
        Assignment ass = new Assignment();
        System.out.println("====Assignments====");
//        ass.set(network.getVariableByName("B"), "true");
        for (int i = 3; i < argv.length; i += 2) {
            ass.set(network.getVariableByName(argv[i]), argv[i+1]);
        }
        System.out.println("Query: " + argv[2] + " Rejection sampling result = " + enumber.rejectionSampling(network.getVariableByName(argv[2]), ass, network, Integer.parseInt(argv[0])));
    }


}
