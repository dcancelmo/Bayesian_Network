package bn.core;

import bn.inference.BNEnumeration;
import bn.parser.XMLBIFParser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] argv) throws IOException, ParserConfigurationException, SAXException {
        XMLBIFParser parser = new XMLBIFParser();
        BayesianNetwork network = parser.readNetworkFromFile(argv[0]);
        network.print(System.out);
        BNEnumeration enumber = new BNEnumeration();
        Assignment ass = new Assignment();
//        ass.set(network.getVariableByName(argv[2]), Boolean.parseBoolean(argv[3]));
//        ass.set(network.getVariableByName(argv[4]), Boolean.parseBoolean(argv[5]));
//        ass.set(network.getVariableByName(argv[2]), argv[3]);
//        ass.set(network.getVariableByName(argv[4]), argv[5]);
        System.out.println("====Assignments====");
        ass.set(network.getVariableByName("B"), "true");
        ass.set(network.getVariableByName("E"), "true");
        ass.set(network.getVariableByName("A"), "true");
        ass.set(network.getVariableByName("M"), "true");
        ass.set(network.getVariableByName("J"), "true");
        System.out.println(ass);
        System.out.println("ENNNNNNNÑD MEEEEEE");
        enumber.ask(network, network.getVariableByName(argv[1]), ass);
    }
}
