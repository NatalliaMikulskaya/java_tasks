package by.epam.atl.xml.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.dao.DOMXMLParser;
import by.epam.atl.xml.dao.SAXParser;
import by.epam.atl.xml.dao.STAXParser;

public class ParserApp {
	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		
		
		String fileIn = "C:/git_repository/java_tasks/XSD/data/library.xml"; //file for parse xml
			
		saxExecute(fileIn);
		
		staxExecute(fileIn);
		
		domExecute(fileIn);
	

	}
	
	/*
	 * Parse file by DOM parser
	 */
	private static void domExecute(String fName){
		DOMXMLParser parser = new DOMXMLParser();
		
		Library lib = parser.parseXML(fName);
		
		resultAlert("DOM", lib);
		
	}
	
	/*
	 * Parse file by SAX parser
	 */
	private static void saxExecute(String fName){
		SAXParser parser = new SAXParser();
		
		Library lib = parser.parseXML(fName);
		
		resultAlert("SAX", lib);
		
	}
	
	/*
	 * Parse file by STAX parser
	 */
	private static void staxExecute(String fName){
		STAXParser parser = new STAXParser();
		
		Library lib = parser.parseXML(fName);
		
		resultAlert("STAX", lib);
		
	}
	
	
	public static void resultAlert(String parserName, Library lib){
		if (lib != null){
			System.out.println(parserName+" parser worked:");
			printLibrary(lib);
		} else {
			System.err.println("Error occurred while "+parserName+" parser worked. See log.");
		}
	}
	
	/*
	 * Print library
	 */
	private static void  printLibrary(Library lib){
		for (Book book : lib.getBooks()){
			System.out.println(book.toString());
		}
	}

}
