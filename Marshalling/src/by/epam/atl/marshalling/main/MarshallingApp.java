package by.epam.atl.marshalling.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sun.javafx.binding.StringFormatter;

import by.epam.atl.marshalling.bean.Library;
import by.epam.atl.marshalling.bean.Person;
import by.epam.atl.marshalling.bean.Person2;
import by.epam.atl.marshalling.bean.Book;
import by.epam.atl.marshalling.bean.Book2;
import by.epam.atl.marshalling.bean.Genre;
import by.epam.atl.marshalling.dao.LibraryMarshaller;


public class MarshallingApp {

	public static final Logger LOG = LogManager.getRootLogger();
	
	public static void main(String[] args) {
		String fileIn = "D:/git/java_tasks/XSD/data/library.xml";
		//String fileIn = "C:/git_repository/java_tasks/XSD/data/library.xml"; //file for parse xml
		String fileOut = "D:/lib.xml";
		
		
		LibraryMarshaller libMarsh = new LibraryMarshaller();
		
		//unload into file
		Library lib = createLibrary();
		//libMarsh.makeXML(fileOut, lib);
		
		Person2 p = createPerson();
		//libMarsh.makeXMLs(fileOut, p);
		
		Book2 b = createBook();
		libMarsh.makeXMLm(fileOut, b);
		
		//parsing
		lib = libMarsh.parseXML(fileIn);
		
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
	
	private static Library createLibrary(){
		Library lib = new Library();
		
		
		
		Book b = new Book();
		b.setId("12-1-34");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse("2014-01-02");
			b.setDatePublication(date);
		} catch (java.text.ParseException e) {
			
			LOG.error("Exception:"+e+" Can't convert string to Data");
			
		}
		
		b.setName("White book");
		b.setSeries("Others");
		
		Person p = new Person();
		
		p.setFirstName("Oscar");
		p.setSecondName("Word");
		
		b.addAuthor(p);
		
		Genre g = Genre.valueOf("FANTASY");
		b.addGenre(g);
		lib.addBook(b);
		
		//next book
		b = new Book();
		b.setId("22-1-09");
		
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse("2004-02-02");
			b.setDatePublication(date);
		} catch (java.text.ParseException e) {
			
			LOG.error("Exception:"+e+" Can't convert string to Data");
			
		}
		
		b.setName("Black book");
		//.setSeries("Others";
		
		p = new Person();
		
		p.setFirstName("Mila");
		p.setSecondName("Red");
		
		b.addAuthor(p);

		p = new Person();
		
		p.setFirstName("Alex");
		p.setSecondName("Sad");
		
		b.addAuthor(p);
		
		g = Genre.valueOf("FANTASY");
		b.addGenre(g);
		g = Genre.valueOf("DRAMA");
		b.addGenre(g);
		
		b.setName("Black book");
		return lib;
	}
	
	private static Person2 createPerson(){
		
		
		Person2 p = new Person2();
		
		p.setFirstName("Oscar");
		p.setSecondName("Word");
		
		
		return p;
	}

	private static Book2 createBook(){
				
		
		Book2 b = new Book2();
		b.setId("12-1-34");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse("2014-01-02");
			b.setDatePublication(date);
		} catch (java.text.ParseException e) {
			
			LOG.error("Exception:"+e+" Can't convert string to Data");
			
		}
		
		b.setName("White book");
		b.setSeries("Others");
		
		Person2 p = new Person2();
		
		p.setFirstName("Oscar");
		p.setSecondName("Word");
		
		b.addAuthor(p);
		
		p = new Person2();
		
		p.setFirstName("Ali");
		p.setSecondName("Kane");
		p.setParentName("Sahid");
		
		b.addAuthor(p);
		
		Genre g = Genre.valueOf("FANTASY");
		b.addGenre(g);
		g = Genre.valueOf("DRAMA");
		b.addGenre(g);
		
		b.setName("Black book");
		return b;
	}
	
}
