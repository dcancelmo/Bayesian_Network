package bn.core;

import bn.inference.BNEnumeration;
import bn.parser.BIFLexer;
import bn.parser.BIFParser;
import bn.parser.XMLBIFParser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] argv) throws IOException, ParserConfigurationException, SAXException {
        String filename = argv[0];
        BayesianNetwork network;
        if(filename.endsWith(".xml")) {
            XMLBIFParser xbp = new XMLBIFParser();
            network = xbp.readNetworkFromFile(filename);
        } else if(filename.endsWith(".bif")) {
            InputStream input = new FileInputStream(filename);
            BIFLexer bl = new BIFLexer(input);
            BIFParser bp = new BIFParser(bl);
            network = bp.parseNetwork();
        } else {
            System.out.println("Invalid file format");
            return;
        }
//        network.print(System.out);
        BNEnumeration enumber = new BNEnumeration();
        Assignment ass = new Assignment();
        System.out.println("====Assignments====");
//        ass.set(network.getVariableByName("B"), "true");
        for (int i = 2; i < argv.length; i += 2) {
            ass.set(network.getVariableByName(argv[i]), argv[i+1]);
        }
//        System.out.println(ass);
        System.out.println("Query: " + argv[1] + " Exact inference result = " + enumber.ask(network, network.getVariableByName(argv[1]), ass).toString());
        System.out.println("Query: " + argv[1] + " Rejection sampling result = " + enumber.rejectionSampling(network.getVariableByName(argv[1]), ass, network, 10000));
    }
}
