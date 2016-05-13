package by.epam.atl.xml.dao;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.atl.xml.bean.Book;
import by.epam.atl.xml.bean.Genre;
import by.epam.atl.xml.bean.Library;
import by.epam.atl.xml.bean.Person;

public class DOMXMLParser {

	private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public DOMXMLParser(){
		
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
		
			DOMParser parser = new DOMParser();
			try{
				parser.parse(fileName);
				
				Document doc = parser.getDocument();
				Element root = doc.getDocumentElement();
				
				NodeList nodeList = root.getElementsByTagName("lib:book");
				Book book = null;
				for (int i=0; i<nodeList.getLength(); i++){
					book = new Book();
					Element bookElement = (Element) nodeList.item(i);
					
					book.setId(bookElement.getAttribute("id"));
					
					setBookName(bookElement, "bookData:bookName", book);
					
					setBookDatePublication(bookElement, "bookData:publicationDate", book);
					
					setBookSeries(bookElement, "bookData:seriesName", book);
					
					setBookAuthors(bookElement, "bookData:author", book);
					
					setBookGenres(bookElement, "bookData:genre", book);
					
				}
				
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
	
	/*
	 * Return the first element with tag {childName} 
	 */
	private static Element getChild(Element el, String childName){
		
		NodeList nList = el.getElementsByTagName(childName);
		if (nList != null){
			Element child = (Element)nList.item(0);
			
			return child;
		}
		return null;
	}
	
	/*
	 * Return all child elements with tag {childName} 
	 */
	private static NodeList getChilds(Element el, String childName){
		
		NodeList nList = el.getElementsByTagName(childName);
		
		return nList;
	}
	
	/*
	 * Set information about book name
	 * @param el
	 * 		element in with search is doing
	 * @param tagName
	 * 		name for searching tag
	 * @param book
	 * 		object by type Book for filling data
	 * 
	 * @return	nothing
	 */
	private void setBookName(Element el, String tagdName, Book book){
		Element bookInfo = getChild(el,tagdName);
		if (bookInfo != null){
			book.setName(bookInfo.getTextContent().trim());
		}
	}
	
	/*
	 * Set information about book data publication
	 * @param el
	 * 		element in with search is doing
	 * @param tagName
	 * 		name for searching tag
	 * @param book
	 * 		object by type Book for filling data
	 * 
	 * @return	nothing
	 */
	private void setBookDatePublication(Element el, String tagdName, Book book){
		Element bookInfo = getChild(el,tagdName);
		if (bookInfo != null){
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			try{
				Date startDate = df.parse(bookInfo.getTextContent().trim());
				book.setDatePublication(startDate);
			}catch(ParseException e){
				LOG.error("ERROR: can't parse string  "+ bookInfo.getTextContent() +" into Date format. " );
			}
			
		}
	}
	
	/*
	 * Set information about book series
	 * @param el
	 * 		element in with search is doing
	 * @param tagName
	 * 		name for searching tag
	 * @param book
	 * 		object by type Book for filling data
	 * 
	 * @return	nothing
	 */
	private void setBookSeries(Element el, String tagdName, Book book){
		Element bookInfo = getChild(el,tagdName);
		if (bookInfo != null){
			book.setSeries(bookInfo.getTextContent().trim());
		}
	}
	
	/*
	 * Set information about book authors
	 * @param el
	 * 		element in with search is doing
	 * @param tagName
	 * 		name for searching tag
	 * @param book
	 * 		object by type Book for filling data
	 * 
	 * @return	nothing
	 */
	private void setBookAuthors(Element el, String tagdName, Book book){
		NodeList listNodes = getChilds(el,tagdName);
		if (listNodes != null){
			for (int i=0; i<listNodes.getLength(); i++){
				Element authorElement = (Element) listNodes.item(i);
				Person nextAuthor = new Person();
				setAuthorData(authorElement, nextAuthor);
				
				book.addAuthor(nextAuthor);
			}
		}
	}
	
	private void setAuthorData(Element el, Person person){
		
		Element nextElement = getChild(el,"authorData:firstName");
		if (nextElement != null){
			person.setFirstName(nextElement.getTextContent().trim());
		}
		
		nextElement = getChild(el,"authorData:secondName");
		if (nextElement != null){
			person.setSecondName(nextElement.getTextContent().trim());
		}
		
		nextElement = getChild(el,"authorData:parentName");
		if (nextElement != null){
			person.setParentName(nextElement.getTextContent().trim());
		}
		
	}
	
	private void setBookGenres(Element el, String tagdName, Book book){
		
		NodeList listNodes = getChilds(el,tagdName);
		if (listNodes != null){
			for (int i=0; i<listNodes.getLength(); i++){
				Element genreElement = (Element) listNodes.item(i);
				Genre g = Genre.valueOf(genreElement.getTextContent().trim().toUpperCase());
								
				book.addGenre(g);
			}
		}
		
	}
	
}
