package ge.sh2.core.command.loader;

import ge.sh2.utils.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class CommandsConfigReader {

    private static final String GROUP_INTERNAL_X_PATH = "/commands/command-groups/internal/group/text()";
    private static final String GROUP_CUSTOM_X_PATH = "/commands/command-groups/custom/group/text()";
    private static final XPath X_PATH = XPathFactory.newInstance().newXPath();

    private final Set<String> internalPackages;
    private final Set<String> customPackages;

    public CommandsConfigReader(String resourceName) throws Exception {
        this(ResourceUtils.getResourceAsStream(resourceName));
    }

    public CommandsConfigReader(InputStream stream) throws Exception {
        Document doc = parse(stream);
        internalPackages = findByXPath(doc, GROUP_INTERNAL_X_PATH);
        customPackages = findByXPath(doc, GROUP_CUSTOM_X_PATH);
    }

    public Set<String> getInternalPackages() {
        return unmodifiableSet(internalPackages);
    }

    public Set<String> getCustomPackages() {
        return unmodifiableSet(customPackages);
    }

    private Set<String> findByXPath(Document doc, String xPathExpr) throws Exception {
        XPathExpression expression = X_PATH.compile(xPathExpr);
        NodeList nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
        Set<String> groups = new HashSet<>();
        for(int i = 0; i < nodes.getLength(); i++) {
            groups.add(nodes.item(i).getNodeValue());
        }
        return groups;
    }

    private static Document parse(InputStream stream) throws Exception {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
    }

}
