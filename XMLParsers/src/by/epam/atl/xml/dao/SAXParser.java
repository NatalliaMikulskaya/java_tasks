package by.epam.atl.xml.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import by.epam.atl.xml.bean.Library;

public class SAXParser {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public SAXParser(){
		
	}
	
	public Library parseXML(String fileName){
		Library lib = null;
		
		if ((fileName == null) || (fileName.length() == 0) ){
			LOG.error("File name is not specified! ");
			return lib;
		}
		
		File fl = new File(fileName);
		
		//checking if file exists and can be read
		if (fl.exists() && fl.isFile() && fl.canRead()){
			try{
				org.xml.sax.XMLReader reader =  XMLReaderFactory.createXMLReader();
				
				FileInputStream fis = new FileInputStream(fl);
				InputSource is = new InputSource(fis);
				
				SAXHandler handler = new SAXHandler();
				reader.setContentHandler(handler);
				reader.parse(is);
				
				reader.setFeature("http://xml.org/sax/features/validation", true);
				reader.setFeature("http://xml.org/sax/features/namespaces", true);
				reader.setFeature("http://xml.org/sax/features/string-interning", true);
				reader.setFeature("http://apache.org/xml/features/validation/schema", false);
				
				lib = handler.getLibrary();
								
			}
			catch(SAXException e){
				LOG.error("Error occurred while create XML reader. "+e.getMessage());
			}
			catch (IOException e){
				LOG.error("Error occurred while read file "+fileName+" : "+e.getMessage());
			}
			
		} else {
			LOG.error("Error read file! ");
		}
		
		return lib;
	}
}
