package by.epam.atl.xml.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.dao.SAXParser;

public class ParserApp {
	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		
		
		String fileIn = "C:/git_repository/java_tasks/XSD/data/library.xml"; //file for parse xml
			
		SAXParser parser = new SAXParser();
		Library lib = parser.parseXML(fileIn);
		
		if (lib != null){
			System.out.println("SAX parser worked:");
			printLibrary(lib);
		} else {
			System.err.println("Error occurred. See log.");
		}
		
		
		

	}
	
	private static void  printLibrary(Library lib){
		for (Book book : lib.getBooks()){
			System.out.println(book.toString());
		}
	}

}
