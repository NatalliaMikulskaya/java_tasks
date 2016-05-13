package by.epam.atl.marshalling.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.marshalling.bean.Library;
import by.epam.atl.marshalling.bean.Book;
import by.epam.atl.marshalling.dao.LibraryMarshaller;


public class MarshallingApp {

	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		String fileIn = "C:/git_repository/java_tasks/XSD/data/library.xml"; //file for parse xml
		
		LibraryMarshaller libMarsh = new LibraryMarshaller();
		
		Library lib = libMarsh.parseXML(fileIn);
		
		if (lib != null){
			System.out.println("Unmurshaller worked:");
			printLibrary(lib);
		} else {
			System.err.println("Error occurred while unmurshaller worked. See log.");
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
