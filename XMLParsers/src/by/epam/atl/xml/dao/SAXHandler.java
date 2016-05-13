package by.epam.atl.xml.dao;

import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.bean.Genre;
import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.LibraryTagName;
import by.epam.atl.xml.bean.Person;

public class SAXHandler extends DefaultHandler {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	private Library library;
	private Book book;
	private Person author;
	private StringBuilder readText; 
	
	public SAXHandler(){
		library = new Library();
	}
	
	public Library getLibrary(){
		return library;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		readText.append(ch, start, length);
	
	}

	@Override
	public void endDocument() throws SAXException {
		
		LOG.info("SAX parsing ended.");
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		LibraryTagName tagNames = null;
		
		//get suffix from tag (without name of space)
		int positionComma = qName.indexOf(":");
		
		try {
			if (positionComma > 0){
				tagNames =  LibraryTagName.valueOf(qName.toUpperCase().substring(positionComma+1));
			} else {
				tagNames =  LibraryTagName.valueOf(qName.toUpperCase());
			}
			
		}catch(IllegalArgumentException e){
			LOG.error("ERROR: unknown tag "+qName);
			throw e;
		}
		
		switch (tagNames){
			case BOOK:{
				library.addBook(book);
				book = null;
				break;
			}
			
			case BOOKNAME:{
				book.setName(readText.toString());
				break;
			}
			
			case AUTHOR:{
				book.addAuthor(author);
				author = new Person();
				break;
			}
			
			case FIRSTNAME:{
				author.setFirstName(readText.toString());
				break;
			}
			
			case SECONDNAME: {
				author.setSecondName(readText.toString());
				break;
			}
			
			case PARENTNAME: {
				author.setParentName(readText.toString());
				break;
			}
			
			case GENRE:{
				Genre g = Genre.valueOf(readText.toString().toUpperCase());
				book.addGenre(g);
				break;
			}
			
			case PUBLICATIONDATE:{
				
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				try{
					Date startDate = df.parse(readText.toString());
					book.setDatePublication(startDate);
				}catch(ParseException e){
					LOG.error("ERROR: can't parse string  "+ readText.toString() +" into Date format. " );
				}
				
				break;
			}
			
			case SERIESNAME: {
				book.setSeries(readText.toString());
				break;
			}
			
			default:{
				LOG.error("ERROR: unknown tag "+qName);
			}
		}
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		LOG.error("ERROR: line "+e.getLineNumber()+" : "+e.getMessage());
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		LOG.error("FATAL: line "+e.getLineNumber()+" : "+e.getMessage());
		throw e;
	}

	@Override
	public void startDocument() throws SAXException {
		LOG.info("SAX parsing started");
	}

	@Override
	public void startElement(String uri, 
							String localName, 
							String qName, 
							Attributes attributes) throws SAXException {
		
		readText = new StringBuilder();
		
		if (qName.equals("lib:book")){
			book = new Book();
			book.setId(attributes.getValue("id"));
			author = new Person();
			
			
		}
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		LOG.error("WARNING: line "+e.getLineNumber()+" : "+e.getMessage());
	}
	
		
	
	

}
