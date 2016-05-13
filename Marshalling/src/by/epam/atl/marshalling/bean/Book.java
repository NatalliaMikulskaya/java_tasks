package by.epam.atl.marshalling.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlType (namespace="http://www.example.org/bookSchema")
/*@XmlType( propOrder = {"id",  
						"bookData:bookName", 
						"bookData:author", 
						"bookData:genre", 
						"bookData:publicationDate",  
						"bookData:seriesName" })*/
@XmlRootElement(name = "book")
public class Book {
	@XmlElement(name = "author", type=Person.class, required = true)
	private List<Person> authors;
	
	@XmlElement(name = "bookName", required = true)
	private String name="";
	
	@XmlElement(name = "publicationDate", required = true)
	private Date datePublication;
	
	@XmlElement(name = "genre", type=Genre.class, required = true)
	private EnumSet<Genre> genre;
	
	@XmlElement(name = "seriesName", required = false)
	private String series="";
	
	@XmlAttribute(name = "id", required = true)
	private String id="";
	
	public Book(){
		authors = new ArrayList<Person>();
		genre = EnumSet.noneOf(Genre.class);
	}
	
	public void setAuthors(List<Person> auth){
		authors = auth;
	}
	
	public void addAuthor(Person auth){
		authors.add(auth);
	}
	
	public void setName(String bookName){
		name = bookName;
	}
	
	public void setDatePublication(Date publicDate){
		datePublication = publicDate;
	}

	public void setGenre(EnumSet<Genre> genres){
		genre = genres;
	}
	
	public void addGenre(Genre bookGenre){
		genre.add(bookGenre);
	}
	
	public void setSeries(String bookSeries){
		series = bookSeries;
	}
	
	public void setId(String bookID){
		id = bookID;
	}
	
	public List<Person> getAuthors(){
		return authors;
	}
	
	public Person getAuthorByPosition(int position){
		Person retPerson = new Person();
		
		if ((authors != null ) && position < authors.size() ){
			retPerson = authors.get(position);
		}
		
		return retPerson;
	}
	
	public String getName(){
		return name;
	}
	
	public Date getDatePublication(){
		return datePublication;
	}
	
	public EnumSet<Genre> getGenre (){
		return genre;
	}
	
	public Genre getGenreByPosition(int position){
		Genre retGenre = null;
		
		if ((genre != null ) && position < genre.size() ){
			Genre[] arrayGenre = (Genre[]) genre.toArray();
			
			retGenre = arrayGenre[position];
		}
		
		return retGenre;
	}
	
	public String getSeries(){
		return series;
	}
	
	public String getId(){
		return id;
	}
	
	@Override
	public int hashCode(){
		final int prime = 47;
		int result = 1;
		
		result = result*prime + ((authors == null)? 0 : authors.hashCode());
		result = result*prime + ((name == null) ? 0 : name.hashCode());
		result = result*prime + ((datePublication == null) ? 0 : datePublication.hashCode());
		result = result*prime + ((genre == null) ? 0 : datePublication.hashCode());
		result = result*prime + ((series == null) ? 0 : series.hashCode());
		result = result*prime + ((id == null) ? 0 : id.hashCode());
		
		return result;
	}
	
	@Override
	public String toString(){
		StringBuilder result ;
		
		result = new StringBuilder(getClass().getName() + " @ [ id: " + id);
		
		result.append("\n		 authors: " + ((authors == null) ? "": authors.toString()));
		result.append("\n		 name: " + ((name == null) ? "" : name ));
		result.append("\n		 series: " + ((series == null)? "" : series ));
		result.append("\n 	 	 genre: "+ ((genre == null)? "": genre.toString()));
		result.append("\n 		 publication date: " + ((datePublication == null) ? "" : datePublication.toString())+"]");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		
		if (obj == null) { return false; }
		
		if (this == obj) { return true; }
		
		if (getClass() != obj.getClass()) { return false;}
		
		Book getObj = (Book) obj;
		
		if ( getObj.authors == null) {
			if (authors != null) {
				return false;
			}
		} else if (!authors.equals(getObj.authors)){
					return false;
			}
				 
		if (getObj.name == null){
			if (name != null){
				return false;
			}
		}else if (!name.equals(getObj.name)){
			return false;
		}
		
		if (getObj.id == null){
			if (id != null) {
				return false;
			}
		} else if (!id.equals(getObj.id)){
			return false;
		}
		
		if (getObj.datePublication == null){
			if (datePublication != null) {
				return false;
			}
		}else if (!datePublication.equals(getObj.datePublication)){
			return false;
		}
		
		if (getObj.genre == null){
			if (genre != null){
				return false;
			}
		}else if (!genre.equals(getObj.genre)){
			return false;
		}
		
		if (getObj.series == null){
			if (series != null){
				return false;
			}
		}else if (!series.equals(getObj.series)){
			return false;
		}
		
		return true;
	}
}
