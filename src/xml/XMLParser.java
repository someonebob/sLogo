package xml;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import main.Defaults;

public class XMLParser
{
	public static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	public static final String DEFAULT_FILE = "/data/defaults.xml";
	public static final List<String> DATA_FIELDS = Arrays
			.asList(new String[] { "background", "pen", "image", "numTurtles", "language" });

	private String background;
	private String pen;
	private String image;
	private String numTurtles;
	private String language;

	public XMLParser()
	{
		File defaultFile = new File(System.getProperty("user.dir") + DEFAULT_FILE);
		Element root = getRootElement(defaultFile);
		background = root.getElementsByTagName(DATA_FIELDS.get(0)).item(0).getTextContent();
		pen = root.getElementsByTagName(DATA_FIELDS.get(1)).item(0).getTextContent();
		image = root.getElementsByTagName(DATA_FIELDS.get(2)).item(0).getTextContent();
		numTurtles = root.getElementsByTagName(DATA_FIELDS.get(3)).item(0).getTextContent();
		language = root.getElementsByTagName(DATA_FIELDS.get(4)).item(0).getTextContent();

	}

	public Defaults setDefaults()
	{
		return new Defaults(background, pen, image, Integer.parseInt(numTurtles), language);
	}

	private Element getRootElement(File xmlFile)
	{
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	private static DocumentBuilder getDocumentBuilder()
	{
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}
}
