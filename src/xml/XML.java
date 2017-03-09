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
	public static final String DEFAULT_FILE_NAME = "/data/defaults.xml";
	public static final File DEFAULT_FILE = new File(System.getProperty("user.dir") + DEFAULT_FILE_NAME);
	public static final Document XML_DOCUMENT = getDocument(DEFAULT_FILE);
	public static final Element ROOT = XML_DOCUMENT.getDocumentElement();

	private static Document getDocument(File xmlFile){
		try{
			DOCUMENT_BUILDER.reset();
			return DOCUMENT_BUILDER.parse(xmlFile);
		}catch (SAXException | IOException e) {
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
