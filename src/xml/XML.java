package xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XML {
	public static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	public static final String DEFAULT_FILE = "/data/defaults.xml";
	
	protected File defaultFile;
	protected Element root;
	protected Document xmlDocument;
	
	public XML(){
		defaultFile = new File(System.getProperty("user.dir") + DEFAULT_FILE);
		root = getRootElement(defaultFile);
	}

	
	private Element getRootElement(File xmlFile) {
		try {
			DOCUMENT_BUILDER.reset();
			xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}
}
