package by.epam.atl.xml.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.dao.DOMXMLParser;
import by.epam.atl.xml.dao.SAXParser;

public class ParserApp {
	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		
		
		String fileIn = "C:/git_repository/java_tasks/XSD/data/library.xml"; //file for parse xml
			
		saxExecute(fileIn);
		
		domExecute(fileIn);
		
		
		

	}
	
	
	/*
	 * Parse file by DOM parser
	 */
	private static void domExecute(String fName){
		DOMXMLParser parser = new DOMXMLParser();
		Library lib = parser.parseXML(fName);
		
		if (lib != null){
			System.out.println("DOM parser worked:");
			printLibrary(lib);
		} else {
			System.err.println("Error occurred while DOM parser worked. See log.");
		}
	}
	
	/*
	 * Parse file by SAX parser
	 */
	private static void saxExecute(String fName){
		SAXParser parser = new SAXParser();
		Library lib = parser.parseXML(fName);
		
		if (lib != null){
			System.out.println("SAX parser worked:");
			printLibrary(lib);
		} else {
			System.err.println("Error occurred while SAX parser worked. See log.");
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
