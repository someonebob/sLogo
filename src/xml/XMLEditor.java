package xml;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class XMLEditor extends XML{
		
	public XMLEditor(){
		super();
	}
	
	public void setDefault(String tagName, String newDefault) throws TransformerException{
		if(newDefault != null){
			XML.ROOT.getElementsByTagName(tagName).item(0).setTextContent(newDefault);
			transform();
		}
		
	}
	
	private void transform() throws TransformerException{
		TransformerFactory transformerfactory = TransformerFactory.newInstance();
		Transformer transformer = transformerfactory.newTransformer();
		DOMSource source = new DOMSource(XML.XML_DOCUMENT);
		StreamResult result = new StreamResult(XML.DEFAULT_FILE);
		transformer.transform(source, result);
	}

}
