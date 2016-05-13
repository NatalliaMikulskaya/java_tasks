package by.epam.atl.marshalling.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlType(name = "Library", propOrder = { "books" })
public class Library {
	@XmlAttribute(required = true)
	private List<Book> books;
		
	public Library(){
		books = new ArrayList<Book>();
		
	}
	
	public Library(List<Book> inBooks){
		books = inBooks;
	}
	
	public void setBooks(List<Book> inBooks){
		books = inBooks;
	}
	
	public List<Book> getBooks(){
		return books;
	}
	
	public void addBook(Book newBook){
		books.add(newBook);
	}
	
	public Book getBookByPosition(int position){
		Book retBook = null;
		
		if ((books != null) && (position < books.size())){
			retBook = books.get(position);
		}
		
		return retBook;
	}
	
	@Override
	public int hashCode(){
		final int prime = 47;
		int result = 1;
		
		result = result * prime +books.hashCode();
		
		return result;
	}
	
	@Override
	public String toString(){
		String result = "";
		
		result = getClass().getName() + " @ [books: " +((books == null ? "" : books.toString()))+ "]";
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj){
		
		if (obj == null) {return false;}
		
		if (this == obj ){ return true;}
		
		if (getClass() != obj.getClass()){ return false;}
		
		Library other = (Library) obj;
		
		if (other.books == null ){
			if (books != null){
				return false;
			}
		}else if (books.equals(other.books)){
			return false;
		}
		
		return true;
	}
}
