package by.epam.atl.xml.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.bean.Genre;
import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.LibraryTagName;
import by.epam.atl.xml.bean.Person;

public class STAXParser {
	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public STAXParser(){
		
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
				XMLInputFactory inFactory = XMLInputFactory.newInstance();
				InputStream is = new FileInputStream(fileName);
				XMLStreamReader xmlReader = inFactory.createXMLStreamReader(is);
			
				lib = process(xmlReader);
			}
			catch(FactoryConfigurationError e){
				LOG.error("Error occurred while create XML reader. " + e.getMessage());
				
			}
			catch (FileNotFoundException e){
				LOG.error("File " + fileName + " not found. " + e.getMessage());
			}
			catch (XMLStreamException e){
				LOG.error("Error occurred while read XML file. " + e.getMessage());
			}
		}else{
			LOG.error("Error read file! ");
		}
		return lib;
	}
	
	private Library process(XMLStreamReader reader) throws XMLStreamException{
		Library lib = null;
		Book book = null;
		Person author = null;
		
		LibraryTagName elName = null;
		
		LOG.info("STAX parsing started");
		
		while (reader.hasNext()){
			int type = reader.next();
			
			switch (type){
				case XMLStreamConstants.START_ELEMENT:{
					elName = LibraryTagName.getElementTagName(reader.getLocalName());
					
					switch (elName){
						case BOOK:{
							if (lib == null){
								lib = new Library();
							}
							book = new Book();
							book.setId(reader.getAttributeValue(null, "id"));
							
							break;
						}
						case AUTHOR:{
							author = new Person();
							break;
						}
					}
					break;
				}
				
				case XMLStreamConstants.CHARACTERS:{
					String text = reader.getText().trim();
					
					setBookData(text, elName, book, author);
					
					break;
				}
				
				case XMLStreamConstants.END_ELEMENT:{
					elName = LibraryTagName.getElementTagName(reader.getLocalName());
					switch (elName){
						case BOOK:{
							lib.addBook(book);
							break;
						}
						case AUTHOR:{
							book.addAuthor(author);
							break;
						}
					
				}
				break;
				}
			}
		}
		
		LOG.info("STAX parsing ended");
		
		return lib;
	}
	
	private void setBookData(String text, LibraryTagName elName, Book book, Person person){
		if (text.isEmpty() ){
			return;
		}
		
		switch (elName){
			case BOOKNAME:{
				book.setName(text);
				break;
			}
			
			case FIRSTNAME:{
				person.setFirstName(text);
				break;
			}
			
			case SECONDNAME:{
				person.setSecondName(text);
				break;
			}
			
			case PARENTNAME:{
				person.setParentName(text);
				break;
			}
			
			case GENRE:{
				Genre g = Genre.valueOf(text.toUpperCase());
				book.addGenre(g);
				break;
			}
			
			case PUBLICATIONDATE:{
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				try{
					Date startDate = df.parse(text);
					book.setDatePublication(startDate);
				}catch(ParseException e){
					LOG.error("ERROR: can't parse string  "+ text +" into Date format. " );
				}
				
				break;
			}
			
			case SERIESNAME:{
				
				book.setSeries(text);
				break;
			}
			
		}
	}
}
